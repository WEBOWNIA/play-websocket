package controllers;

import model.CounterPanel;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    private Map<Integer, CounterPanel> counterPanelMap = new HashMap<>();

    public static Result index() {
        return ok(views.html.index.render("Hello Play Framework"));
    }


    public static WebSocket<String> conquer(Long number) {
        System.out.println(number);
        return new WebSocket<String>() {
            public void onReady(final In<String> in, final Out<String> out) {
                in.onMessage(out::write);
            }
        };
    }
}
