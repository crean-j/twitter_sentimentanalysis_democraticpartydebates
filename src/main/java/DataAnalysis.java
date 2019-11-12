
/*
 * This class takes in Tweet objects and analyzes them for sentiment
 * in different ways
 * Constructor (takes in Tweets and makes ArrayList)
 */

import java.util.ArrayList;
import java.util.HashMap;

public class DataAnalysis {

	ArrayList<Tweet> tweets;

	/**
	 * Class constructor. Initializes the ArrayList of Tweet objects
	 */
	public DataAnalysis(ArrayList<Tweet> tweets) {
		this.tweets = tweets;
	}

	/**
	 * numTweets counts the number of tweets containing the keyword. Since all
	 * Tweets in the ArrayList contain the keyword it returns the size of the
	 * ArrayList
	 */
	public int numTweets() {
		return tweets.size();
	}

	/**
	 * sentimentScore. loops through all tweets and returns the average sentiment
	 * score for the keyword
	 */
	public String sentimentScore() {
		double total = 0.0;
		for (Tweet t : tweets) {
			total += t.getSentimentScore();
		}
		return "The sentiment score is: " + total / tweets.size() + "/4";
	}

	/**
	 * mostUsedWords method Returns the most seen adjectives used in tweets
	 * mentioning the keyword Ideally return with the key and value (for example: 1.
	 * Brilliant, mentioned 234 times.
	 */
	public String mostUsedWords(int numberOfResults) {
		String topWords = null;
		HashMap<String, Integer> mostUsedAdjectives = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			// for each tweet count adjectives used and add to HashMap
		}
		return topWords;
	}

	/**
	 * topLocations method returns a String with the number of tweets per location for 
	 * a variable number of results
	 */
	public String topLocations(int numberOfResults) {
		String topStates = null;
		HashMap<String, Integer> location = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			// count how many tweets per location
			// may need to do some data modifications to just take in states
		}
		return topStates; // ideally return with the key and value (for example: 1. CA, 45000 tweets.
	}

	/**
	 * mostRetweeted returns a String containing the most retweeted tweet
	 */
	public String mostRetweeted(int numberOfResults) {
		Tweet mostRetweeted = null;
		for (Tweet t : tweets) {
			if (t.getRetweetedCount() > mostRetweeted.getRetweetedCount()) {
				mostRetweeted = t;
			}
		}
		return mostRetweeted.getUser() + ": "+ mostRetweeted.getTextInTweet() + "retweeted: " + mostRetweeted.getRetweetedCount() + " times."; // to be formatted
	}

	/**
	 * sentiment by state. Create a Hashmap with the average sentiment by state
	 * could return most postive or negative states.
	 */
	public String sentimentState() {
		String sentiment = null;
		// hashmap with key state and tweets
		// average sentiment by state?
		// need to figure out how to tackle this one
		return sentiment;
	}

	/**
	 * Method gets the average sentiment one week before a certain date
	 */
	public int averageSevenDBefore(String date){
		int average = 0;

		//loops through array list of tweets
		//gets average sentiment for tweets one week before date

		return average;
	}

	/**
	 * Method gets the average sentiment one week after a certain date
	 */
	public int averageSevenDAfter(String date){
		int average = 0;

		//loops through array list of tweets
		//gets average sentiment for tweets one week after date

		return average;
	}
}
