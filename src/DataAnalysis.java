/*
 * This class takes in Tweet objects and analyzes them for sentiment
 * in different ways
 * Constructor (takes in Tweets and makes ArrayList)
 */

import java.util.ArrayList;
import java.util.HashMap;

public class DataAnalysis {

	ArrayList<Tweet> tweets;

	public DataAnalysis(ArrayList<Tweet> tweets) {
		this.tweets = tweets;
		}
	
	//return number of tweets that include the keyowrd/keywords
	public int numTweets() {
		return tweets.size();
	}
	
	//take in sentiment score per tweets an calculate the average
	//results should be a number between 0 and 4, with 2 being neutral.
	public String sentimentScore() {
		double total;
		for (Tweet t : tweets) {
			total += t.getSentimentScore; //the sentimentScore variable still needs to be added to the Tweet class
		}	
		return "The sentiment score is: " + total/tweets.size() +"/4";
	}
	
	//Return the most seen adjectives used in tweets mentioning the keyword
	public String mostUsedWords(int numberOfResults){
		String topWords = null;
		HashMap<String, Integer> mostUsedAdjectives = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			//for each tweet count adjectives used and add to HashMap
		}
	return topWords; //ideally return with the key and value (for example: 1. Brilliant, mentioned 234 times.
	}
	
	public String topLocations(int numberOfResults){
		String topStates = null;
		HashMap<String, Integer> location = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			//count how many tweets per location
			//may need to do some data modifications to just take in states
		}
	return topStates; //ideally return with the key and value (for example: 1. CA, 45000 tweets.
	}
	
	//Find and return most retweeted tweet mentioning the keyword
	public String mostRetweeted() {
		Tweet mostRetweeted = null;
		for (Tweet t : tweets) {
			if (t.getRetweetedCount() > mostRetweeted.getRetweetedCount()) {
				mostRetweeted = t;
			}
		}
		return mostRetweeted + mostRetweeted.getRetweetedCount(); //to be formatted
	}
	
	//Find and return sentiment score by State
	public String sentimentState() {
		String sentiment = null;
		//hashmap with key state and tweets
		//average sentiment by state?
		//need to figure out how to tackle this one
		return sentiment;
	}

	
}


