//package main.java;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.stanford.nlp.util.CoreMap;

/*
 * This is the main class for the sentiment analysis.
 * It will take input from the user around which candidate to look at,
 * and the date of interest.
 * It will run all the necessary analysis and print out
 * console responses as well as create a file with the report
 */
public class Runner {
	public static void main(String[] args) {

		UserInteraction ui = new UserInteraction();
		TwitterSearch ts = new TwitterSearch(ui.getCandidate(), ui.getDate());
		ArrayList<Tweet> result = ts.mainSearch();
		InfluenceScore is = new InfluenceScore(result);
		SaveTweets st = new SaveTweets(result);
		st.saveToFile();
		// sets up the sentiment analyser and add a sentiment score and a hashmap of adjectives
		// and their sentiments scores for each tweet
		NLPAnalyser nlp = new NLPAnalyser();
		for (Tweet tweet : result) {
			List<CoreMap> sentences = nlp.nlpPipeline(tweet.getTextInTweet());
	    	double sentimentScore = nlp.getSentimentScore(sentences);
	    	tweet.setSentimentScore(sentimentScore);
	    	HashMap<String, Double> as = nlp.adjectivesScoring(sentences);
	    	tweet.setAdjSentiment(as);
		}


		// DataAnalysis da = new DataAnalysis(tweets);

		// run analysis
		// print output
	}
}
