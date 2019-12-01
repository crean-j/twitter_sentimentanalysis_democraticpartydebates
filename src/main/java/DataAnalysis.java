
/**
 * @author: Federica Pelzel
 * 
 * This class takes in Tweet objects and analyzes them according to different criteria
 * Constructor (takes in Tweets and makes ArrayList)
 * Includes: Total tweets, Avg sentiment score, top positive and negative words,
 * most retweeted, tweets by state, sentiment by state, top positive and negative states
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

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
	 * This method computes the median of the values in the input array.
	 * 
	 * @param arr - an array of ints
	 * @return median - the median of the input array
	 */
	public double calculateMedian(ArrayList<Tweet> tweets) {
		List<Double> sent = new ArrayList<Double>();
		for (Tweet t : tweets) {
			sent.add(t.getSentimentScore());
		}
		// Sort our array
		Collections.sort(sent, Collections.reverseOrder());

		double median = 0;

		// If our array's length is even, then we need to find the average of the two
		// centered values
		if (sent.size() % 2 == 0) {
			int indexA = (tweets.size() - 1) / 2;
			int indexB = tweets.size() / 2;

			median = ((double) (sent.get(indexA) + sent.get(indexB))) / 2;
		}
		// Else if our array's length is odd, then we simply find the value at the
		// center index
		else {
			int index = (sent.size() - 1) / 2;
			median = sent.get(index);
		}

		return median;
	}

	/**
	 * This method computes the mode of the values in the input array.
	 * 
	 * @param arr - an array of ints
	 * @return mode - the mode of the input array
	 */
	public double calculateMode(ArrayList<Tweet> tweets) {

		int modeCount = 0; // The count of the mode value
		double mode = 0; // The value of the mode

		int currCount = 0;

		// Iterate through all values in our array and consider it as a possible mode
		for (Tweet t : tweets) {
			// Get Sentiment Score
			double sent = t.getSentimentScore();
			// Reset the number of times we have seen the current value
			currCount = 0;

			// Iterate through the array counting the number of times we see the current
			// candidate mode
			for (Tweet t2 : tweets) {
				// If they match, increment the current count
				if (sent == t2.getSentimentScore()) {
					currCount++;
				}
			}

			// We only save this candidate mode, if its count is greater than the current
			// mode
			// we have stored in the "mode" variable
			if (currCount > modeCount) {
				modeCount = currCount;
				mode = sent;
			}
		}

		return mode;
	}
	/**
	 * % Positive and negative tweets
	 */
	
	public String posPercent(ArrayList<Tweet> tweets){
		ArrayList<Tweet> totalPos = new ArrayList<>();
		
		for (Tweet t : tweets) {
			if(t.getSentimentScore()>1.9) {
				totalPos.add(t);
			}
		}
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		double percent = (totalPos.size() * 100) / tweets.size();
		return numberFormat.format(percent) + "%";
	}
	
	public String negPercent(ArrayList<Tweet> tweets){
		ArrayList<Tweet> totalNeg = new ArrayList<>();
		
		for (Tweet t : tweets) {
			if(t.getSentimentScore()<2.0) {
				totalNeg.add(t);
			}
		}
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		double percent = (totalNeg.size() * 100) / tweets.size();
		return numberFormat.format(percent) + "%";
	}
	
	
	/**
	 * highest influence score. Create sorted arraylist of tweets by descending
	 * influence score
	 */

	public List<Tweet> topInf(ArrayList<Tweet> tweets) {
		HashMap<Tweet, Double> infScore = new HashMap<Tweet, Double>();
		for (Tweet t : tweets) {
			infScore.put(t, t.getInfluenceScore());
		}

		LinkedHashMap<Tweet, Double> infScoreSorted = new LinkedHashMap<>();
		infScore.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> infScoreSorted.put(x.getKey(), x.getValue()));

		List<Tweet> sortedInfScore = new ArrayList<Tweet>(infScoreSorted.keySet());

		return sortedInfScore;

	}

	public String topNInf(int numResults) {
		ArrayList<Tweet> topInf = new ArrayList<Tweet>(topInf(tweets));
		String output = new String();
		// iterate through ArrayList and add each result to the output string
		if (topInf.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += "\nUsername: " + topInf.get(i).getUser() + "; User Followers: "
						+ topInf.get(i).getNumberOfFollowers() + "; Tweet ID" + topInf.get(i).getId() +"; Times Retweeted: " + topInf.get(i).getRetweetedCount()
						+ "; Tweet Influence Score: " + topInf.get(i).getInfluenceScore() + ".\nTweet: "
						+ topInf.get(i).getTextInTweet() + ".\n";
			}
			// add last one with special formatting
			output += topInf.get(numResults);

			// return String containing all top n results
			return output;
		} else {
			return "Not enough data available";
		}
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

	// returns a sorted hashmap of negative word count
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

	// from the hashmap created by TopPositiveWords return n top results
	public String topNPos(int numResults) {
		String output = new String();
		List<String> topPosKeys = new ArrayList<String>(topPositiveWords().keySet());
		// iterate through ArrayList and add each result to the output string
		if (topPosKeys.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += topPosKeys.get(i) + "; ";
			}
			// add last one with special formatting
			output += topPosKeys.get(numResults);

			// return String containing all top n results
			return output;
		} else {
			return "Not enough data available";
		}
	}

	// from the hashmap created by TopNegativeWords return n top results
	public String topNNeg(int numResults) {
		String output = new String();
		List<String> topNegKeys = new ArrayList<String>(topNegativeWords().keySet());
		// iterate through ArrayList and add each result to the output string
		if (topNegKeys.size() > numResults) {
			for (int i = 0; i < numResults; i++) {
				output += topNegKeys.get(i) + "; ";
			}
			// add last one with special formatting
			output += topNegKeys.get(numResults);

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
	 */
	public HashMap<String, Double> sentimentState(TweetsByState tbs2) {
		HashMap<String, Double> sentState = new HashMap<String, Double>();

		// instantiate TweetsByState class
		TweetsByState tbs = new TweetsByState(tweets);
		for (String state : tbs.states.keySet()) {
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			double totalSent = 0.0;
			int count = 0;

			for (Tweet t : tbs.states.get(state)) {
				totalSent += t.getSentimentScore();
				count += 1;
			}

			if (count > 0) {
				String average = numberFormat.format(totalSent / count);
				double d = Double.parseDouble(average);
				sentState.put(state, d);
			}
		}

		// Sort in descending order using Comparator
		LinkedHashMap<String, Double> sentStateSorted = new LinkedHashMap<>();
		sentState.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sentStateSorted.put(x.getKey(), x.getValue()));

		return sentStateSorted;
	}

	// Return a HashMap with sentiment by state from low to high
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

	// Return a Top n states with positive sentiment
	public String topPosStates(int numResults, TweetsByState tbs3) {
		String output = "Top " + numResults + " States with the highest Sentiment Score: ";

		// Create ArrayLists from sorted HashMap in order to be able to call by index
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

	// Return top n states with negative sentiment
	public String topNegStates(int numResults, TweetsByState tbs4) {
		String output = "Top " + numResults + " States with the lowest Sentiment Score: ";

		// Create ArrayLists from sorted HashMap in order to be able to call by index
		List<String> topNegKeys = new ArrayList<String>(lowSentState(tbs4).keySet());
		List<Double> topNegValues = new ArrayList<Double>(lowSentState(tbs4).values());
		DecimalFormat numberFormat = new DecimalFormat("#.00");

		// iterate through ArrayList and add each result to the output string
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
