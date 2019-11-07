/**
 * Defines an object Tweet with variables that will be used for data analysis
 * 
 * @author juangoleniowski
 *
 */
public class Tweet {
    private String user;
    private int numberOfFollowers;
    private String textInTweet;
    private String tweetDate;
    private String candidate;
    private int influenceScore;

    // Constructor
    public Tweet(String user, int numberOfFollowers, String textInTweet, String tweetDate, String candidate,
	    int influencerScore) {
	this.user = user;
	this.numberOfFollowers = numberOfFollowers;
	this.textInTweet = textInTweet;
	this.tweetDate = tweetDate;
	this.candidate = candidate;
	this.influenceScore = influenceScore;
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
    public String getUser() {
	return user;
    }

    /**
     * sets user
     * 
     * @param user
     */
    public void setUser(String user) {
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
    public String getTweetDate() {
	return tweetDate;
    }

    /**
     * Sets Tweet date
     * 
     * @param tweetDate
     */
    public void setTweetDate(String tweetDate) {
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

}
