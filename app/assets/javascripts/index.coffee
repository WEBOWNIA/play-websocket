$ ->
# WebSocket
  ws = new WebSocket('ws://'.concat(window.location.hostname).concat(':').concat(window.location.port).concat('/conquer'))

  ws.onopen = () ->
    console.log('Opening a connection...')
    window.identified = false

  ws.onmessage = (message) ->
    data = JSON.parse(message.data)
    console.log(data)
    clickCountElement = document.getElementById('clickCount' + data.counterPanel.conquerNumber)
    $('#'+clickCountElement.getAttribute('id')).html(''+data.counterPanel.clickCount)
    $('#countAllClick').html(''+data.yourAllConquers)
    $('#countAllEven').html(''+data.yourAllEvenConquers)
    return

  $('#button11').click () -> ws.send(JSON.stringify {conquerNumber: 11})
  $('#button12').click () -> ws.send(JSON.stringify {conquerNumber: 12})
  $('#button13').click () -> ws.send(JSON.stringify {conquerNumber: 13})
  $('#button21').click () -> ws.send(JSON.stringify {conquerNumber: 21})
  $('#button22').click () -> ws.send(JSON.stringify {conquerNumber: 22})
  $('#button23').click () -> ws.send(JSON.stringify {conquerNumber: 23})
  $('#button31').click () -> ws.send(JSON.stringify {conquerNumber: 31})
  $('#button32').click () -> ws.send(JSON.stringify {conquerNumber: 32})
  $('#button33').click () -> ws.send(JSON.stringify {conquerNumber: 33})