package twitter;


import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is responsible for listening to twitter tweet feed.
 * Connection to twitter is made when first websocket connection opens, subsequent websocket connections will
 * start listening to twitter only if the thread is terminated.
 */
public class TwitterStreamManager implements Runnable {
    private static TwitterStreamManager instance = new TwitterStreamManager();
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private TwitterStreamManager() {

    }

    @Override
    public void run() {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener statusListener = TweetBroker.getInstance();
        twitterStream.addListener(statusListener);
        FilterQuery x = new FilterQuery();
        double[][] loc1 = {{-118.55, 27.67}, {-68.76, 46.94}};
//        double[][] loc = new double[2][2];
//        loc[0][0] = -118.55;
//        loc[0][1] = 27.67;
//        loc[1][0] = -68.76;
//        loc[1][1] = 46.94;
        x.locations(loc1);
        twitterStream.filter(x);
    }

    private boolean isAlive() {
        return instance.atomicBoolean.get();
    }

    public static boolean isTwitterStreamAlive() {
        return instance.isAlive();
    }

    public static void startTwitterStream() {
        if (!instance.isAlive()) {
            instance.atomicBoolean.getAndSet(true);
            new Thread(instance).start();
        }
    }

}
