import java.util.Date;
import java.util.HashMap;

import twitter4j.GeoLocation;
import twitter4j.User;

/**
 * Defines an object Tweet with variables that will be used for data analysis
 *
 * @author juangoleniowski
 *
 */
public class Tweet {
    private long id;
    private String user;
    private int numberOfFollowers;
    private String location;
    private String textInTweet;
    private Date tweetDate;
    private String candidate;
    private double influenceScore;
    private int retweetedCount;
    private double sentimentScore;
    private boolean isRetweet;
    private GeoLocation geoLocation;
    private HashMap<String, Double> adjSentiment;

    // Constructor
    public Tweet(long id, String user, int numberOfFollowers, String location, String textInTweet, Date tweetDate,
                 String candidate, double influenceScore, int retweetedCount, boolean isRetweet, GeoLocation geoLocation) {
    this.id = id;
        this.user = user;
        this.numberOfFollowers = numberOfFollowers;
        this.location = location;
        this.textInTweet = textInTweet;
        this.tweetDate = tweetDate;
        this.candidate = candidate;
        this.influenceScore = influenceScore;
        this.retweetedCount = retweetedCount;
        this.isRetweet = isRetweet;
        this.geoLocation = geoLocation;
    }

    /**
     * @return the geoLocation
     */
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * @param geoLocation the geoLocation to set
     */
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * @return the isRetweet
     */
    public boolean isRetweet() {
        return isRetweet;
    }

    /**
     * @param isRetweet the isRetweet to set
     */
    public void setRetweet(boolean isRetweet) {
        this.isRetweet = isRetweet;
    }

    /**
     * @return the tweet ID
     */
    public long getId() {
        return id;
    }

    /**
     * @param user the user to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the numberOfFollowers
     */
    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    /**
     * @param numberOfFollowers the numberOfFollowers to set
     */
    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the textInTweet
     */
    public String getTextInTweet() {
        return textInTweet;
    }

    /**
     * @param textInTweet the textInTweet to set
     */
    public void setTextInTweet(String textInTweet) {
        this.textInTweet = textInTweet;
    }

    /**
     * @return the tweetDate
     */
    public Date getTweetDate() {
        return tweetDate;
    }

    /**
     * @param tweetDate the tweetDate to set
     */
    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }

    /**
     * @return the candidate
     */
    public String getCandidate() {
        return candidate;
    }

    /**
     * @param candidate the candidate to set
     */
    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    /**
     * @return the influenceScore
     */
    public double getInfluenceScore() {
        return influenceScore;
    }

    /**
     * @param influenceScore the influenceScore to set
     */
    public void setInfluenceScore(double influenceScore) {
        this.influenceScore = influenceScore;
    }

    /**
     * @return the retweetedCount
     */
    public int getRetweetedCount() {
        return retweetedCount;
    }

    /**
     * @param retweetedCount the retweetedCount to set
     */
    public void setRetweetedCount(int retweetedCount) {
        this.retweetedCount = retweetedCount;
    }

    /**
     * @return the sentimentScore
     */
    public double getSentimentScore() {
        return sentimentScore;
    }
    
    /**
     * @return the isRetweet
     */
    public boolean getIsRetweet() {
        return isRetweet;
    }

    /**
     * @param sentimentScore the sentimentScore to set
     */
    public void setSentimentScore(double sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public HashMap<String, Double> getAdjSentiment() {
        return adjSentiment;
    }

    public void setAdjSentiment(HashMap<String, Double> adjSentiment) {
        this.adjSentiment = adjSentiment;
    }

}
