package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.CounterPanel;
import model.ViewModel;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    private final static Integer ROW_SIZE = 3;
    private final static Integer COL_SIZE = 3;
    private static Map<Integer, CounterPanel> panel = new HashMap<>();
    private static long yourAllConquers;
    private static long yourAllEvenConquers;

    public static Result index() {
        if (panel.isEmpty()) {
            for (int row = 1; row <= ROW_SIZE; row++) {
                for (int col = 1; col <= COL_SIZE; col++) {
                    int conquerNumber = Integer.valueOf(row + "" + col);
                    panel.put(conquerNumber, new CounterPanel(conquerNumber));
                }
            }
        }
        return ok(views.html.index.render(panel, ROW_SIZE, COL_SIZE));
    }

    public static WebSocket<JsonNode> conquer() {
        return new WebSocket<JsonNode>() {

            public void onReady(final In<JsonNode> in, final Out<JsonNode> out) {
                in.onMessage((jsonNode) -> doOut(jsonNode, out));
            }

            private void doOut(JsonNode jsonNode, Out<JsonNode> out) {
                int conquerNumber = jsonNode.findValue("conquerNumber").asInt();
                CounterPanel counterPanel = panel.get(conquerNumber);
                counterPanel.clickCount++;

                yourAllConquers++;
                if(yourAllConquers > 0 &&  yourAllConquers % 2 == 0) {
                    yourAllEvenConquers++;
                }

                ViewModel viewModel = new ViewModel();
                viewModel.counterPanel = counterPanel;
                viewModel.yourAllConquers = yourAllConquers;
                viewModel.yourAllEvenConquers = yourAllEvenConquers;

                out.write(Json.toJson(viewModel));
            }
        };
    }

//    public class MapAnime extends UntypedActor {
//
//        static ActorRef actor = Akka.system().actorOf(new Props(MapAnime.class));
//
//        Map<String, WebSocket.Out<JsonNode>> registrered = new HashMap<String, WebSocket.Out<JsonNode>>();
//
//        /**
//         *
//         * @param id
//         * @param in
//         * @param out
//         * @throws Exception
//         */
//        public static void register(final String id,
//                                    final WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out)
//                throws Exception {
//
//            actor.tell(new RegistrationMessage(id, out));
//
//            // For each event received on the socket,
//            in.onMessage(new F.Callback<JsonNode>() {
//                @Override
//                public void invoke(JsonNode event) {
//                    // nothing to do
//                }
//            });
//
//            // When the socket is closed.
//            in.onClose(new F.Callback0() {
//                @Override
//                public void invoke() {
//                    actor.tell(new UnregistrationMessage(id));
//                }
//            });
//        }
//
//        public static void moveTo(float longitude, float latitude) {
//
//            actor.tell(new MoveMessage(longitude, latitude));
//
//        }
//
//        @Override
//        public void onReceive(Object message) throws Exception {
//
//            if (message instanceof RegistrationMessage) {
//
//                // Received a Join message
//                RegistrationMessage registration = (RegistrationMessage) message;
//
//                Logger.info("Registering " + registration.id + "...");
//                registrered.put(registration.id, registration.channel);
//
//            } else if (message instanceof MoveMessage) {
//
//                // Received a Move message
//                MoveMessage move = (MoveMessage) message;
//
//                for (WebSocket.Out<JsonNode> channel : registrered.values()) {
//
//                    ObjectNode event = Json.newObject();
//                    event.put("longitude", move.longitude);
//                    event.put("latitude", move.latitude);
//
//                    channel.write(event);
//                }
//
//            } else if (message instanceof UnregistrationMessage) {
//
//                // Received a Unregistration message
//                UnregistrationMessage quit = (UnregistrationMessage) message;
//
//                Logger.info("Unregistering " + quit.id + "...");
//                registrered.remove(quit.id);
//
//            } else {
//                unhandled(message);
//            }
//
//        }
//
//        public static class RegistrationMessage {
//            public String id;
//            public WebSocket.Out<JsonNode> channel;
//
//            public RegistrationMessage(String id, WebSocket.Out<JsonNode> channel) {
//                super();
//                this.id = id;
//                this.channel = channel;
//            }
//        }
//
//        public static class UnregistrationMessage {
//            public String id;
//
//            public UnregistrationMessage(String id) {
//                super();
//                this.id = id;
//            }
//        }
//
//        public static class MoveMessage {
//
//            public float longitude;
//
//            public float latitude;
//
//            public MoveMessage(float longitude, float latitude) {
//                this.longitude = longitude;
//                this.latitude = latitude;
//            }
//
//        }
//
//    }
}
