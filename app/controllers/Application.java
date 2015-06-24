package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.CounterPanel;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    private final static Integer ROW_SIZE = 3;
    private final static Integer COL_SIZE = 3;
    private static Map<String, CounterPanel> panel = new HashMap<>();

    public static Result index() {
        if(panel.isEmpty()) {
            for(int row = 1; row <= ROW_SIZE; row++){
                for(int col = 1; col <= COL_SIZE; col++){
                    panel.put(row+""+col, new CounterPanel());
                }
            }
        }
        return ok(views.html.index.render(panel, ROW_SIZE, COL_SIZE));
    }

    public static WebSocket<String> conquer() {
        return new WebSocket<String>() {

            public void onReady(final In<String> in, final Out<String> out) {
                in.onMessage(this::doPrint);
            }

            private void doPrint(String json) {
                JsonNode jsonNode = Json.parse(json);
                Long conquerNumber = jsonNode.findValue("conquerNumber").asLong();
                panel.get(conquerNumber.toString()).clickCount++;
            }
        };
    }
}
