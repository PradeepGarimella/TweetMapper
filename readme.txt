A web application that listens to geo-tagged tweets coming from continental united states and plots them on a google map.

1) When a web socket connection is made, the websocket servlet registers with a tweet publisher.
2). Pushes tweets to the client, which in turn plots it on a map
