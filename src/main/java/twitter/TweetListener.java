package twitter;


import twitter4j.Status;

/**
 * Objects that wish to be notified when a tweet is published must implement this interface
 * and register with {@code TweetBroker}
 */
public interface TweetListener {
    void notify(Status status);
}
