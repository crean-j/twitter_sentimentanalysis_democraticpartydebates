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
		// sets up the sentiment analyser and add a sentiment score and a hashmap of
		// adjectives
		// and their sentiments scores for each tweet
		NLPAnalyser nlp = new NLPAnalyser();
		int current = 0;
		for (Tweet tweet : result) {
			List<CoreMap> sentences = nlp.nlpPipeline(tweet.getTextInTweet());
			double sentimentScore = nlp.getSentimentScore(sentences);
			tweet.setSentimentScore(sentimentScore);
			HashMap<String, Double> as = nlp.adjectivesScoring(sentences);
			tweet.setAdjSentiment(as);

			// print out result after each tweet is analyzed for progress tracking
			System.out.println(current + "/" + result.size() + "; " + tweet.getCandidate() + "; Score=" + sentimentScore
					+ "; adj =" + as);
			current += 1;
		}
		
		TweetsByState tbs = new TweetsByState(result);
		int count = 0;
		for (String state : tbs.states.keySet()) {
			count += tbs.states.get(state).size();
		}
		
		System.out.println("\n\n===================================================================================");
		System.out.println("A N A L Y S I S   C O M P L E T E");
		System.out.println("===================================================================================\n");

		System.out.println("Total number of tweets in sample: " + result.size() + ".");
		System.out.println("Total number of tweets with matched location: " + count + ".\n");

		DataAnalysis da = new DataAnalysis(result);
		
		System.out.println("\nAverage sentiment score: " + da.sentimentScore() + "\n");
		
		System.out.println("Most used positive words: " + da.topNPos(5));
		System.out.println("Most used negative words: " + da.topNNeg(5));
		System.out.println(da.topPosStates(5, tbs));
		System.out.println(da.topNegStates(5, tbs));

	}
}
