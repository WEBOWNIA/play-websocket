$ ->
  # WebSocket
  conquerWs = new WebSocket("ws://localhost:9000/conquer/2")
  conquerWs.onmessage = (message) -> console.log(message.data)
  $("#q1").click () -> conquerWs.send("{conquerNumber: 1}")