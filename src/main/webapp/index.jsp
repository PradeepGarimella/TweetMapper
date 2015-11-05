<!DOCTYPE html>
<html>
<head>
  <title>Simple Map</title>
  <meta name="viewport" content="initial-scale=1.0">
  <meta charset="utf-8">
  <style>
  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
  }
  #map {
    height: 100%;
  }
  .tweetBox {
    width: 500px;
    height: auto;
  }
  .tweet {
    font-family: verdana, Helvetica, sans-serif;
  }
  </style>
</head>
<body>
  <div id="map"></div>
  <script>

  var map;
  function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
      center: {lat: 40.43, lng: -101.13},
      zoom: 4
    });
  }

  function addMarker(data) {
    var myLatLng = {lat: data.lat, lng: data.lng};
    var contentString =   '<div class="tweetBox"><div class="banner"><img src="'+ data.profileImageUrl + '" />'
    + '<div><a href="https://twitter.com/' + data.screenName + '">' + data.screenName + '</a></div>'
    + '</div><br />'
    + '<div class="tweet">'
    + data.message
    + '</div></div>';
    // var contentString = '<div class="tweetBox"><img src="' +data.profileImageUrl+'" alt=""><div> <a href="'+ data.profileBannerUrl + data.screenName + '""</div>'
    // + '<div class = "tweet">' + data.message + '</div></div>';

    var infowindow = new google.maps.InfoWindow({
      content: contentString
    });

    var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Hello World!'
    });

    marker.addListener('click', function() {
      infowindow.open(map, marker);
      marker.setIcon('https://www.google.com/mapfiles/marker_green.png');
    });
  }
  // var ws = new WebSocket("ws://localhost:7070/events");
  var ws = new WebSocket("ws://localhost:8080/myfirstjettywebapp-1.0-SNAPSHOT/events");
  ws.onopen = function() {
    console.log("Opened!");
  };

  ws.onmessage = function (evt) {
    try {
      addMarker(JSON.parse(evt.data));
    } catch (e) {
      console.log("re");
    }
  };

  ws.onclose = function() {
    ws.close();
  };

  ws.onerror = function(err) {
    ws.close();
    console.log("Error: " + err);
  };

  </script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4CvTDfpDVLPdbhbs1Lz_moR3G7k9fe54&callback=initMap"
  async defer></script>
</body>
</html>
