$ ->
  # WebSocket
  ws = new WebSocket("ws://localhost:9000/conquer")
  ws.onmessage = (message) -> console.log(JSON.parse(message.data))
  $("#button11").click () -> ws.send(JSON.stringify {conquerNumber: 11})
  $("#button12").click () -> ws.send(JSON.stringify {conquerNumber: 12})
  $("#button13").click () -> ws.send(JSON.stringify {conquerNumber: 13})
  $("#button21").click () -> ws.send(JSON.stringify {conquerNumber: 21})
  $("#button22").click () -> ws.send(JSON.stringify {conquerNumber: 22})
  $("#button23").click () -> ws.send(JSON.stringify {conquerNumber: 23})
  $("#button31").click () -> ws.send(JSON.stringify {conquerNumber: 31})
  $("#button32").click () -> ws.send(JSON.stringify {conquerNumber: 32})
  $("#button33").click () -> ws.send(JSON.stringify {conquerNumber: 33})