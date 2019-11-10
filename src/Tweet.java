import java.util.Date;

import twitter4j.User;

/**
 * Defines an object Tweet with variables that will be used for data analysis
 * 
 * @author juangoleniowski
 *
 */
public class Tweet {
    private User user;
    private int numberOfFollowers;
    private String location;
    private String textInTweet;
    private Date tweetDate;
    private String candidate;
    private int influenceScore;
    private int retweetedCount;
    private int sentimentScore;

    // Constructor
    public Tweet(User user2, int numberOfFollowers, String location, String textInTweet, Date tweetDate,
	    String candidate, int influenceScore, int retweetedCount) {
	this.user = user2;
	this.numberOfFollowers = numberOfFollowers;
	this.setLocation(location);
	this.textInTweet = textInTweet;
	this.tweetDate = tweetDate;
	this.candidate = candidate;
	this.influenceScore = influenceScore;
	this.setRetweetedCount(retweetedCount);
    }

    /**
     * Get a score that measure how strong an influencer the tweet is
     * 
     * @return influence score
     */
    public int getInfluenceScore() {
	return influenceScore;
    }

    /**
     * sets an influence score for the tweet
     * 
     * @param influenceScore
     */

    public void setInfluenceScore(int influenceScore) {
	this.influenceScore = influenceScore;
    }

    /**
     * gets User
     * 
     * @return user
     */
    public User getUser() {
	return user;
    }

    /**
     * sets user
     * 
     * @param user
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * gets Number of Followers
     * 
     * @return Number of Followers
     */
    public int getNumberOfFollowers() {
	return numberOfFollowers;
    }

    /**
     * Sets Number of Followers
     * 
     * @param numberOfFollowers
     */
    public void setNumberOfFollowers(int numberOfFollowers) {
	this.numberOfFollowers = numberOfFollowers;
    }

    /**
     * Gets the text in a Tweet
     * 
     * @return text
     */
    public String getTextInTweet() {
	return textInTweet;
    }

    /**
     * Sets Text in Tweet
     * 
     * @param textInTweet
     */
    public void setTextInTweet(String textInTweet) {
	this.textInTweet = textInTweet;
    }

    /**
     * gets Tweet Date
     * 
     * @return date
     */
    public Date getTweetDate() {
	return tweetDate;
    }

    /**
     * Sets Tweet date
     * 
     * @param tweetDate
     */
    public void setTweetDate(Date tweetDate) {
	this.tweetDate = tweetDate;
    }

    /**
     * Gets Candidate name
     * 
     * @return candiate name
     */
    public String getCandidate() {
	return candidate;
    }

    /**
     * Sets candidate name
     * 
     * @param candidate
     */
    public void setCandidate(String candidate) {
	this.candidate = candidate;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public int getRetweetedCount() {
	return retweetedCount;
    }

    public void setRetweetedCount(int retweetedCount) {
	this.retweetedCount = retweetedCount;
    }

    public int getSentimentScore() {
	return sentimentScore;
    }

    public void setSentimentScore(int sentimentScore) {
	this.sentimentScore = sentimentScore;
    }

}
