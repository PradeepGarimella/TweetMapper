import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import twitter.TweetBroker;
import twitter.TwitterStreamManager;
import twitter.TweetListener;
import twitter4j.Status;


import java.io.IOException;

/**
 * This is our websocket client that registers itself with EventSocket
 * TweetListener, and notifies the websocket client for every new tweet
 */
public class EventSocket extends WebSocketAdapter implements TweetListener {
    private static final ObjectMapper objectmapper = new ObjectMapper();

    @Override
    public void onWebSocketConnect(Session sess) {
        super.onWebSocketConnect(sess);
        if (!TwitterStreamManager.isTwitterStreamAlive()) {
            TwitterStreamManager.startTwitterStream();
        }
        TweetBroker.getInstance().addListener(this);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        TweetBroker.getInstance().removeListener(this);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    //Should we be decrementing the CONNECTIONS_COUNT here
    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
    }

    public void notify(Status status) {
        if (status.getGeoLocation() != null) {
            try {
                Tweet t = new Tweet(status);
                super.getSession().getRemote().sendString(objectmapper.writeValueAsString(t));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
