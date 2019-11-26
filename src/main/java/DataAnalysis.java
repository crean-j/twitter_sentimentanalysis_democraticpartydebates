
/*
 * This class takes in Tweet objects and analyzes them for sentiment
 * in different ways
 * Constructor (takes in Tweets and makes ArrayList)
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

	public double sentimentScore() {

		double total = 0.0;
		for (Tweet t : tweets) {
			total += t.getSentimentScore();
		}
		return total / tweets.size();
	}

	/**
	 * mostUsedWords method Returns the most seen adjectives used in tweets
	 * mentioning the keyword. Returns a HashMap with word + number of times
	 * mentioned
	 * 
	 */
	public HashMap<String, Integer> mostUsedWords() {
		HashMap<String, Integer> adjectivesCount = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			for (String adj : t.getAdjSentiment().keySet()) {
				if (!adjectivesCount.containsKey(adj)) {
					adjectivesCount.put(adj, 0);
				}
				adjectivesCount.put(adj, adjectivesCount.get(adj) +1);
				
			}
		}

		// sort hashmap using Comparator
		LinkedHashMap<String, Integer> adjectivesSorted = new LinkedHashMap<>();
		adjectivesCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> adjectivesSorted.put(x.getKey(), x.getValue()));

		return adjectivesSorted;
	}

	/**
	 * numberOfTweets method returns a HashMap with Key State and value number of
	 * tweets originating from that state Uses the TweetsByState class
	 */
	public HashMap<String, Integer> numberOfTweetsState() {
		HashMap<String, Integer> stateTweets = new HashMap<String, Integer>();
		TweetsByState tbs = new TweetsByState(tweets);
		for (String state : tbs.states.keySet()) {
			stateTweets.put(state, tbs.states.values().size());
			;
		}
		return stateTweets;
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
		return mostRetweeted.getUser() + ": " + mostRetweeted.getTextInTweet() + "retweeted: "
				+ mostRetweeted.getRetweetedCount() + " times."; // to be formatted
	}

	/**
	 * sentiment by state. Create a Hashmap with the average sentiment by state
	 * could return most postive or negative states.
	 * 
	 * Issues to solve: returns same average score for each state
	 */
	public HashMap<String, Double> sentimentState(TweetsByState tbs2) {
		HashMap<String, Double> sentState = new HashMap<String, Double>();

		TweetsByState tbs = new TweetsByState(tweets);
		for (String state : tbs.states.keySet()) {
			
				double totalSent = 0.0;
				int count = 0;
				
				for (Tweet t : tbs.states.get(state)) {
					totalSent += t.getSentimentScore();
					count += 1;
				}
				
				if(count > 0) {
				double average = totalSent / count;
				sentState.put(state, average);
				}
		}
		
		LinkedHashMap<String, Double> sentStateSorted = new LinkedHashMap<>();
		sentState.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sentStateSorted.put(x.getKey(), x.getValue()));

		return sentStateSorted;
	}

	/**
	 * Method gets the average sentiment one week before a certain date
	 */
	public int averageSevenDBefore(String date) {
		int average = 0;

		// loops through array list of tweets
		// gets average sentiment for tweets one week before date

		return average;
	}

	/**
	 * Method gets the average sentiment one week after a certain date
	 */
	public int averageSevenDAfter(String date) {
		int average = 0;

		// loops through array list of tweets
		// gets average sentiment for tweets one week after date

		return average;
	}
}