import org.codehaus.jackson.annotate.JsonProperty;
import twitter4j.Status;


public class Tweet {
    @JsonProperty
    String author;
    @JsonProperty
    String message;
    @JsonProperty
    String screenName;
    @JsonProperty
    Double lat;
    @JsonProperty
    Double lng;
    @JsonProperty
    String profileImageUrl;
    @JsonProperty
    Long createdAt;
    public Tweet(Status status) {
        this.author = status.getUser().getName();
        this.message = status.getText();
        this.screenName = status.getUser().getScreenName();
        this.lat = status.getGeoLocation().getLatitude();
        this.lng = status.getGeoLocation().getLongitude();
        this.profileImageUrl = status.getUser().getBiggerProfileImageURL();
        this.createdAt = status.getCreatedAt().getTime();
    }
}
