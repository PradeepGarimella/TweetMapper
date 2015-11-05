package twitter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.ArrayList;
import java.util.List;


/**
 * An singleton object that acts a broker i.e listens to tweets by registering
 * with twitter4j. Upon receiving a tweet, it publishes the tweet to its consumers.
 */
public class TweetBroker implements StatusListener {
    List<TweetListener> listenerList = new ArrayList<TweetListener>();
    private static volatile TweetBroker instance = null;

    //Private constructor since this is going to be a singleton
    private TweetBroker() {

    }

    @Override
    public void onStatus(Status status) {
        notifyListeners(status);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        //do nothing
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        //do nothing
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        //do nothing
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        //do nothing
    }

    @Override
    public void onException(Exception ex) {
        //do nothing
    }

    public void addListener(TweetListener tweetListener) {
        if (tweetListener != null) {
            this.listenerList.add(tweetListener);
        }
    }

    public void removeListener(TweetListener tweetListener) {
        if (tweetListener != null) {
            this.listenerList.remove(tweetListener);
        }
    }

    public void notifyListeners(Status status) {
        for (TweetListener tweetListener : this.listenerList) {
            tweetListener.notify(status);
        }
    }

    public static TweetBroker getInstance() {
        if (instance == null) {
            instance = new TweetBroker();
        }
        return instance;
    }
}
