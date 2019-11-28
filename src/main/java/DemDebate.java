import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.stanford.nlp.util.CoreMap;

public class DemDebate {

	public static void main(String[] args) {
		// Start analysis welcome message
		System.out.println("===================================================================================");
		System.out.println("Analysis started");
		ArrayList<Tweet> tweets = SaveTweets.loadFile();
		System.out.println("The selected file contains: " + tweets.size() + " tweets.");
		System.out.println("===================================================================================");

		// Create an ArrayList of tweets for each candidate
		ArrayList<Tweet> warren = new ArrayList<>();
		ArrayList<Tweet> biden = new ArrayList<>();
		ArrayList<Tweet> sanders = new ArrayList<>();
		ArrayList<Tweet> pete = new ArrayList<>();

		/*
		 * Analysis of all tweets
		 */

		System.out.println("===================================================================================");
		System.out.println("Analyzing all tweets");
		System.out.println("===================================================================================");

		// Initialize sentiment analyzer
		NLPAnalyser nlp = new NLPAnalyser();
		int current = 0;
		// iterate over tweets and analyze
		for (Tweet tweet : tweets) {
			List<CoreMap> sentences = nlp.nlpPipeline(tweet.getTextInTweet());
			double sentimentScore = nlp.getSentimentScore(sentences);
			tweet.setSentimentScore(sentimentScore);
			HashMap<String, Double> as = nlp.adjectivesScoring(sentences);
			tweet.setAdjSentiment(as);

			if (tweet.getCandidate().equals("Warren")) {
				warren.add(tweet);
			}
			if (tweet.getCandidate().equals("Sanders")) {
				sanders.add(tweet);
			}
			if (tweet.getCandidate().equals("Buttigieg")) {
				pete.add(tweet);
			}
			if (tweet.getCandidate().equals("Biden")) {
				biden.add(tweet);
			}

			// print out result after each tweet is analyzed for progress tracking
			System.out.println(current + "/" + tweets.size() + "; " + tweet.getCandidate() + "; Score=" + sentimentScore
					+ "; adj =" + as);
			current += 1;

		}

		// separate tweets into states
		TweetsByState tbs = new TweetsByState(tweets);
		System.out.println("===================================================================================");
		System.out.println("Analyzing tweets by state");
		System.out.println("===================================================================================");
		int count = 0;
		for (String state : tbs.states.keySet()) {
			System.out.println(state + ": " + tbs.states.get(state).size() + " tweets.");
			count += tbs.states.get(state).size();
		}

		System.out.println("===================================================================================");
		System.out.println("Total number of tweets with matched location: " + count + ".");

		/*
		 * Analysis specific to Elizabeth Warren
		 */

		System.out.println("===================================================================================");
		System.out.println("Analyzing tweets about Elizabeth Warren");
		System.out.println("===================================================================================");
		System.out.println("Initiating sentiment analysis.");
		System.out.println("===================================================================================");

		TweetsByState tbsWarren = new TweetsByState(warren);
		System.out.println(tbsWarren.states.get("DC").size());
		int countWarren = 0;
		for (String state : tbsWarren.states.keySet()) {
			System.out.println(state + " " + tbsWarren.states.get(state).size());
			count += tbsWarren.states.get(state).size();
		}

		System.out.println(countWarren);

		DataAnalysis daWarren = new DataAnalysis(warren);
		System.out.println(daWarren.sentimentState(tbsWarren));
		System.out.println(daWarren.topPositiveWords());
		System.out.println(daWarren.topNegativeWords());
		System.out.println(daWarren.topNPos(10));
		System.out.println(daWarren.topNNeg(3));
		System.out.println(daWarren.topPosStates(5, tbsWarren));
		System.out.println(daWarren.topNegStates(5, tbsWarren));

		/*
		 * Analysis specific to Bernie Sanders
		 */
		System.out.println("===================================================================================");
		System.out.println("Analyzing tweets about Bernie Sanders");
		System.out.println("===================================================================================");
		System.out.println("Initiating sentiment analysis.");
		System.out.println("===================================================================================");

		TweetsByState tbsSanders = new TweetsByState(sanders);
		System.out.println(tbsSanders.states.get("DC").size());
		int countSanders = 0;
		for (String state : tbsSanders.states.keySet()) {
			System.out.println(state + " " + tbsSanders.states.get(state).size());
			count += tbsSanders.states.get(state).size();
		}

		System.out.println(countSanders);

		DataAnalysis daSanders = new DataAnalysis(sanders);
		System.out.println(daSanders.sentimentState(tbsSanders));
		System.out.println(daSanders.topPositiveWords());
		System.out.println(daSanders.topNegativeWords());

		/*
		 * Analysis specific to Joe Biden
		 */

		System.out.println("===================================================================================");
		System.out.println("Analyzing tweets about Joe Biden");
		System.out.println("===================================================================================");
		System.out.println("Initiating sentiment analysis.");
		System.out.println("===================================================================================");

		TweetsByState tbsBiden = new TweetsByState(biden);
		System.out.println(tbsBiden.states.get("DC").size());
		int countBiden = 0;
		for (String state : tbsBiden.states.keySet()) {
			System.out.println(state + " " + tbsBiden.states.get(state).size());
			count += tbsBiden.states.get(state).size();
		}

		System.out.println(countBiden);

		DataAnalysis daBiden = new DataAnalysis(biden);
		System.out.println(daBiden.sentimentState(tbsBiden));
		System.out.println(daBiden.topPositiveWords());
		System.out.println(daBiden.topNegativeWords());

		/*
		 * Analysis specific to Pete Buttigieg
		 */
		System.out.println("===================================================================================");
		System.out.println("Analyzing tweets about Pete Buttigieg");
		System.out.println("===================================================================================");
		System.out.println("Initiating sentiment analysis.");
		System.out.println("===================================================================================");

		TweetsByState tbsPete = new TweetsByState(pete);
		System.out.println(tbsPete.states.get("DC").size());
		int countPete = 0;
		for (String state : tbsPete.states.keySet()) {
			System.out.println(state + " " + tbsPete.states.get(state).size());
			count += tbsPete.states.get(state).size();
		}

		System.out.println(countPete);

		DataAnalysis daPete = new DataAnalysis(pete);
		System.out.println(daPete.sentimentState(tbsPete));
		System.out.println(daPete.topPositiveWords());
		System.out.println(daPete.topNegativeWords());

		/*
		 * Write CSV with results
		 */
		try (PrintWriter writer = new PrintWriter(new File("DataByState.csv"))) {
			StringBuilder sb = new StringBuilder();
			// Create Headers
			sb.append("State");
			sb.append(',');
			sb.append("TotalTweets");
			sb.append(',');
			sb.append("WarrenTweets");
			sb.append(',');
			sb.append("WarrenSent");
			sb.append(',');
			sb.append("SandersTweets");
			sb.append(',');
			sb.append("SandersSent");
			sb.append(',');
			sb.append("BidenTweets");
			sb.append(',');
			sb.append("BidenSent");
			sb.append(',');
			sb.append("ButtigiegTweets");
			sb.append(',');
			sb.append("ButtigiegSent");
			sb.append('\n');

			for (String state : tbs.states.keySet()) {
				sb.append(state);
				sb.append(',');
				sb.append(tbs.states.get(state).size());
				sb.append(',');
				sb.append(tbsWarren.states.get(state).size());
				sb.append(',');
				sb.append(daWarren.sentimentState(tbsWarren).get(state));
				sb.append(',');
				sb.append(tbsSanders.states.get(state).size());
				sb.append(',');
				sb.append(daSanders.sentimentState(tbsSanders).get(state));
				sb.append(',');
				sb.append(tbsBiden.states.get(state).size());
				sb.append(',');
				sb.append(daBiden.sentimentState(tbsBiden).get(state));
				sb.append(',');
				sb.append(tbsPete.states.get(state).size());
				sb.append(',');
				sb.append(daPete.sentimentState(tbsPete).get(state));
				sb.append('\n');
			}

			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try (PrintWriter writer = new PrintWriter(new File("DataByCandidate.csv"))) {
			StringBuilder sb = new StringBuilder();
			// Create Headers
			sb.append("Candidate");
			sb.append(',');
			sb.append("TotalTweets");
			sb.append(',');
			sb.append("AverageSentiment");
			sb.append(',');
			sb.append("TopPositiveWords");
			sb.append(',');
			sb.append("TopNegativeWords");
			sb.append('\n');

			// Warren
			sb.append("Elizabeth Warren");
			sb.append(',');
			sb.append(warren.size());
			sb.append(',');
			sb.append(daWarren.sentimentScore());
			sb.append(',');
			sb.append(daWarren.topNPos(5));
			sb.append(',');
			sb.append(daWarren.topNNeg(5));
			sb.append('\n');

			// Sanders
			sb.append("Bernie Sanders");
			sb.append(',');
			sb.append(sanders.size());
			sb.append(',');
			sb.append(daSanders.sentimentScore());
			sb.append(',');
			sb.append(daSanders.topNPos(5));
			sb.append(',');
			sb.append(daSanders.topNNeg(5));
			sb.append('\n');

			// Biden
			sb.append("Joe Biden");
			sb.append(',');
			sb.append(biden.size());
			sb.append(',');
			sb.append(daBiden.sentimentScore());
			sb.append(',');
			sb.append(daBiden.topNPos(5));
			sb.append(',');
			sb.append(daBiden.topNNeg(5));
			sb.append('\n');

			// Buttigieg
			sb.append("Pete Buttigieg");
			sb.append(',');
			sb.append(pete.size());
			sb.append(',');
			sb.append(daPete.sentimentScore());
			sb.append(',');
			sb.append(daPete.topNPos(5));
			sb.append(',');
			sb.append(daPete.topNNeg(5));
			sb.append('\n');

			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * Print out final report:
		 */
		System.out.println("\n\n===================================================================================");
		System.out.println("A N A L Y S I S   C O M P L E T E");
		System.out.println("===================================================================================\n\n");

		System.out.println("\nTotal number of tweets in sample: " + tweets.size() + ".");
		System.out.println("Total number of tweets with matched location: " + count + ".\n");

		// Warren
		System.out.println("\nCandidate: ELIZABETH WARREN\n");
		System.out.println("Total number of tweets: " + warren.size());
		System.out.println("Average sentiment score: " + daWarren.sentimentScore());
		System.out.println("Most used positive words: " + daWarren.topNPos(5));
		System.out.println("Most used negative words: " + daWarren.topNNeg(5));
		System.out.println(daWarren.topPosStates(5, tbsWarren));
		System.out.println(daWarren.topNegStates(5, tbsWarren));

		// Bernie
		System.out.println("\nCandidate: BERNIE SANDERS\n");
		System.out.println("Total number of tweets: " + sanders.size());
		System.out.println("Average sentiment score: " + daSanders.sentimentScore());
		System.out.println("Most used positive words: " + daSanders.topNPos(5));
		System.out.println("Most used negative words: " + daSanders.topNNeg(5));
		System.out.println(daSanders.topPosStates(5, tbsSanders));
		System.out.println(daSanders.topNegStates(5, tbsSanders));

		// Bernie
		System.out.println("\nCandidate: PETE BUTTIGIEG\n");
		System.out.println("Total number of tweets: " + pete.size());
		System.out.println("Average sentiment score: " + daPete.sentimentScore());
		System.out.println("Most used positive words: " + daPete.topNPos(5));
		System.out.println("Most used negative words: " + daPete.topNNeg(5));
		System.out.println(daPete.topPosStates(5, tbsPete));
		System.out.println(daPete.topNegStates(5, tbsPete));

		// Biden
		System.out.println("\nCandidate: JOE BIDEN\n");
		System.out.println("Total number of tweets: " + biden.size());
		System.out.println("Average sentiment score: " + daBiden.sentimentScore());
		System.out.println("Most used positive words: " + daBiden.topNPos(5));
		System.out.println("Most used negative words: " + daBiden.topNNeg(5));
		System.out.println(daBiden.topPosStates(5, tbsBiden));
		System.out.println(daBiden.topNegStates(5, tbsBiden));

		System.out.println("\n\n======================================================================================");
		System.out.println("A CSV file with data by state and candidate called 'DataByState.csv' has been saved.");
		System.out.println("======================================================================================");
		System.out.println("A CSV file with data by candidate called 'DataByCandidate.csv' has been saved.");
		System.out.println("======================================================================================\n\n");
	}
}
