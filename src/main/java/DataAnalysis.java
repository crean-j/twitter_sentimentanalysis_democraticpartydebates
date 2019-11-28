
/*
 * This class takes in Tweet objects and analyzes them for sentiment
 * in different ways
 * Constructor (takes in Tweets and makes ArrayList)
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

	public String sentimentScore() {
		String average;
		double total = 0.0;
		for (Tweet t : tweets) {
			total += t.getSentimentScore();
		}
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		average = numberFormat.format(total / tweets.size());
		return average;
	}

	/**
	 * mostUsedWords method Returns the most seen adjectives used in tweets
	 * mentioning the keyword. Returns a HashMap with word + number of times
	 * mentioned
	 * 
	 */
	public HashMap<String, Integer> topPositiveWords() {
		HashMap<String, Integer> positive = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			for (String adj : t.getAdjSentiment().keySet()) {
				if (t.getAdjSentiment().get(adj) >= 3) {
					if (!positive.containsKey(adj)) {
						positive.put(adj, 0);
					}
					positive.put(adj, positive.get(adj) + 1);
				} else
					continue;
			}
		}

		// sort hashmap using Comparator
		LinkedHashMap<String, Integer> topPositive = new LinkedHashMap<>();
		positive.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> topPositive.put(x.getKey(), x.getValue()));

		return topPositive;
	}

	public HashMap<String, Integer> topNegativeWords() {
		HashMap<String, Integer> negative = new HashMap<String, Integer>();
		for (Tweet t : tweets) {
			for (String adj : t.getAdjSentiment().keySet()) {
				if (t.getAdjSentiment().get(adj) < 2) {
					if (!negative.containsKey(adj)) {
						negative.put(adj, 0);
					}
					negative.put(adj, negative.get(adj) + 1);
				}
			}
		}

		// sort hashmap using Comparator
		LinkedHashMap<String, Integer> topNegative = new LinkedHashMap<>();
		negative.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> topNegative.put(x.getKey(), x.getValue()));

		return topNegative;
	}

	public String topNPos(int numResults) {
		String output = "Top " + numResults + " positive adjectives: ";
		List<String> topPosKeys = new ArrayList<String>(topPositiveWords().keySet());
		// iterate through ArrayList and add each result to the output string
		if (topPosKeys.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += topPosKeys.get(i) + "; ";
			}
			// add last one with special formatting
			output += topPosKeys.get(numResults) + ".";

			// return String containing all top n results
			return output;
		} else {
			return "Not enough data available";
		}
	}

	public String topNNeg(int numResults) {
		String output = "Top " + numResults + " negative adjectives: ";
		List<String> topNegKeys = new ArrayList<String>(topNegativeWords().keySet());
		// iterate through ArrayList and add each result to the output string
		if (topNegKeys.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += topNegKeys.get(i) + "; ";
			}
			// add last one with special formatting
			output += topNegKeys.get(numResults) + ".";

			// return String containing all top n results
			return output;
		} else {
			return "Not enough data available";
		}
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

			if (count > 0) {
				double average = totalSent / count;
				sentState.put(state, average);
			}
		}

		LinkedHashMap<String, Double> sentStateSorted = new LinkedHashMap<>();
		sentState.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sentStateSorted.put(x.getKey(), x.getValue()));

		return sentStateSorted;
	}

	public HashMap<String, Double> lowSentState(TweetsByState tbs2) {
		HashMap<String, Double> sentState = new HashMap<String, Double>();

		TweetsByState tbs = new TweetsByState(tweets);
		for (String state : tbs.states.keySet()) {

			double totalSent = 0.0;
			int count = 0;

			for (Tweet t : tbs.states.get(state)) {
				totalSent += t.getSentimentScore();
				count += 1;
			}

			if (count > 0) {
				double average = totalSent / count;
				sentState.put(state, average);
			}
		}

		LinkedHashMap<String, Double> sentStateSorted = new LinkedHashMap<>();
		sentState.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(x -> sentStateSorted.put(x.getKey(), x.getValue()));

		return sentStateSorted;
	}

	public String topPosStates(int numResults, TweetsByState tbs3) {
		String output = "Top " + numResults + " States with the highest Sentiment Score: ";
		List<String> topPosKeys = new ArrayList<String>(sentimentState(tbs3).keySet());
		List<Double> topPosValues = new ArrayList<Double>(sentimentState(tbs3).values());
		DecimalFormat numberFormat = new DecimalFormat("#.00");

		// iterate through ArrayList and add each result to the output string
		if (topPosKeys.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += topPosKeys.get(i) + "= " + numberFormat.format(topPosValues.get(i)) + ", ";
			}
			// add last one with special formatting
			output += topPosKeys.get(numResults) + "= " + numberFormat.format(topPosValues.get(numResults)) + ".";

			// return String containing all top n results
			return output;
		} else {
			return "Not enough data available";
		}
	}

	public String topNegStates(int numResults, TweetsByState tbs4) {
		String output = "Top " + numResults + " States with the lowest Sentiment Score: ";
		List<String> topNegKeys = new ArrayList<String>(lowSentState(tbs4).keySet());
		List<Double> topNegValues = new ArrayList<Double>(lowSentState(tbs4).values());
		DecimalFormat numberFormat = new DecimalFormat("#.00");

		if (topNegKeys.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += topNegKeys.get(i) + "= " + numberFormat.format(topNegValues.get(i)) + ", ";
			}
			// add last one with special formatting
			output += topNegKeys.get(numResults) + "= " + numberFormat.format(topNegValues.get(numResults)) + ".";

			// return String containing all top n results
			return output;
		} else {
			return "Not enough data available";
		}
	}

}
