
/*
 * @author: Federica Pelzel 
 * 
 * DemDebate instantiates other classes and creates a runner around the 
 * democratic primary debate which happened on nov 20th 2019.
 * It returns analysis results and creates 2 CSV files with the results.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import edu.stanford.nlp.util.CoreMap;

public class DemDebate {

	public static void main(String[] args) {
		// Start analysis welcome message
	    	Scanner in = new Scanner(System.in);
	    	System.out.println("Please enter the file name to analyze: ");
	    	String fileToRead = in.nextLine();
		System.out.println("===================================================================================");
		System.out.println("Analysis started");
		ArrayList<Tweet> tweets = SaveTweets.loadFile(fileToRead);
		System.out.println("The selected file contains: " + tweets.size() + " tweets.");
		System.out.println("===================================================================================");

		// Create an ArrayList of tweets for each candidate
		ArrayList<Tweet> warren = new ArrayList<>();
		ArrayList<Tweet> biden = new ArrayList<>();
		ArrayList<Tweet> sanders = new ArrayList<>();
		ArrayList<Tweet> pete = new ArrayList<>();

		// Analyze all tweets and sort into different candidate ArrayLists
		System.out.println("===================================================================================");
		System.out.println("Analyzing all tweets");
		System.out.println("===================================================================================");

		// Initialize sentiment analyzer
		NLPAnalyser nlp = new NLPAnalyser();
		int current = 0;
		// iterate over tweets, analyze, and sort
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
			System.out.println(current + "/" + tweets.size() + "; " + tweet.getCandidate() + "; Influence Score: "
					+ tweet.getInfluenceScore() + "; Sentiment Score=" + sentimentScore + "; adj =" + as);
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

		//instantiate general DataAnalysis Class
		DataAnalysis da = new DataAnalysis(tweets);
		
		/*
		 * Analysis specific to Elizabeth Warren
		 */

		TweetsByState tbsWarren = new TweetsByState(warren);

		for (String state : tbsWarren.states.keySet()) {
			System.out.print("Tweets by State: " + state + "=" + tbsWarren.states.get(state).size() + ", ");
		}
		DataAnalysis daWarren = new DataAnalysis(warren);

		/**
		 * Analysis specific to Bernie Sanders
		 */

		TweetsByState tbsSanders = new TweetsByState(sanders);

		for (String state : tbsSanders.states.keySet()) {
			System.out.print("Tweets by State: " + state + "=" + tbsSanders.states.get(state).size() + ", ");
		}
		DataAnalysis daSanders = new DataAnalysis(sanders);

		/**
		 * Analysis specific to Joe Biden
		 */

		TweetsByState tbsBiden = new TweetsByState(biden);
		for (String state : tbsBiden.states.keySet()) {
			System.out.print("Tweets by State: " + state + "=" + tbsBiden.states.get(state).size() + ", ");
		}
		DataAnalysis daBiden = new DataAnalysis(biden);

		/**
		 * Analysis specific to Pete Buttigieg
		 */

		TweetsByState tbsPete = new TweetsByState(pete);
		for (String state : tbsPete.states.keySet()) {
			System.out.print("Tweets by State: " + state + "=" + tbsPete.states.get(state).size() + ", ");
		}
		DataAnalysis daPete = new DataAnalysis(pete);


		// Write CSV with results by State
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

		// Create CSV that includes results per candidate
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

		try (PrintWriter writer = new PrintWriter(new File("report.txt"))) {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"\n==========================================================================================================\n");
			sb.append("A N A L Y S I S   R E P O R T\n\n");
			sb.append("5th Democratic Primary Debate\n");
			sb.append("November 20th, 2019.\n");
			sb.append(
					"\n==========================================================================================================\n");
			sb.append(
					"Tweet sentiment is measured in a 0 to 4 scale. 0 being extremely negative and 4 extremely positive");
			sb.append(
					"\n==========================================================================================================\n");

			sb.append("\nTotal number of tweets in sample: " + tweets.size() + ".");
			sb.append(System.getProperty("line.separator"));
			sb.append("\nTotal number of tweets with matched location: " + count + ".\n");
			sb.append(System.getProperty("line.separator"));
			sb.append("\nPercentage of tweets with positive sentiment (2 or higher): " + da.posPercent(tweets));
			sb.append("\nPercentage of tweets with negative sentiment (under 2): " + da.negPercent(tweets));
			sb.append(System.getProperty("line.separator"));
			sb.append("\nMost influential Tweets: " + da.topNInf(5));
			sb.append(System.getProperty("line.separator"));

			// Biden
			sb.append("\n\nCandidate: JOE BIDEN\n");
			sb.append("\nTotal number of tweets: " + biden.size());
			sb.append("\nAverage sentiment score: " + daBiden.sentimentScore());
			sb.append("\nPercentage of tweets with positive sentiment (2 or higher): " + daBiden.posPercent(biden));
			sb.append("\nPercentage of tweets with negative sentiment (under 2): " + daBiden.negPercent(biden));
			sb.append("\nMedian sentiment score: " + daBiden.calculateMedian(biden));
			sb.append("\nMode sentiment score: " + daBiden.calculateMode(biden) + "\n");
			sb.append("\nMost used positive words: " + daBiden.topNPos(5));
			sb.append("\nMost used negative words: " + daBiden.topNNeg(5));
			sb.append("\n" + daBiden.topPosStates(5, tbsBiden));
			sb.append("\n" + daBiden.topNegStates(5, tbsBiden));
			sb.append("\n\nMost influential Tweets: " + daBiden.topNInf(3));

			// Warren
			sb.append("\n\nCandidate: ELIZABETH WARREN\n");
			sb.append("\nTotal number of tweets: " + warren.size());
			sb.append("\nAverage sentiment score: " + daWarren.sentimentScore());
			sb.append("\nPercentage of tweets with positive sentiment (2 or higher): " + daWarren.posPercent(warren));
			sb.append("\nPercentage of tweets with negative sentiment (under 2): " + daWarren.negPercent(warren));
			sb.append("\nMedian sentiment score: " + daWarren.calculateMedian(warren));
			sb.append("\nMode sentiment score: " + daWarren.calculateMode(warren) + "\n");
			sb.append("\nMost used positive words: " + daWarren.topNPos(5));
			sb.append("\nMost used negative words: " + daWarren.topNNeg(5));
			sb.append("\n" + daWarren.topPosStates(5, tbsWarren));
			sb.append("\n" + daWarren.topNegStates(5, tbsWarren));
			sb.append("\n\nMost influential Tweets: " + daWarren.topNInf(3));

			// Bernie
			sb.append("\n\nCandidate: BERNIE SANDERS\n");
			sb.append("\nTotal number of tweets: " + sanders.size());
			sb.append("\nAverage sentiment score: " + daSanders.sentimentScore());
			sb.append("\nPercentage of tweets with positive sentiment (2 or higher): " + daSanders.posPercent(sanders));
			sb.append("\nPercentage of tweets with negative sentiment (under 2): " + daSanders.negPercent(sanders));
			sb.append("\nMedian sentiment score: " + daSanders.calculateMedian(sanders));
			sb.append("\nMode sentiment score: " + daSanders.calculateMode(sanders) + "\n");
			sb.append("\nMost used positive words: " + daSanders.topNPos(5));
			sb.append("\nMost used negative words: " + daSanders.topNNeg(5));
			sb.append("\n" + daSanders.topPosStates(5, tbsSanders));
			sb.append("\n" + daSanders.topNegStates(5, tbsSanders));
			sb.append("\n\nMost influential Tweets: " + daSanders.topNInf(3));

			// Pete
			sb.append("\n\nCandidate: PETE BUTTIGIEG\n");
			sb.append("\nTotal number of tweets: " + pete.size());
			sb.append("\nAverage sentiment score: " + daPete.sentimentScore());
			sb.append("\nPercentage of tweets with positive sentiment (2 or higher): " + daPete.posPercent(pete));
			sb.append("\nPercentage of tweets with negative sentiment (under 2): " + daPete.negPercent(pete));
			sb.append("\nMedian sentiment score: " + daPete.calculateMedian(pete));
			sb.append("\nMode sentiment score: " + daPete.calculateMode(pete) + "\n");
			sb.append("\nMost used positive words: " + daPete.topNPos(5));
			sb.append("\nMost used negative words: " + daPete.topNNeg(5));
			sb.append("\n" + daPete.topPosStates(5, tbsPete));
			sb.append("\n" + daPete.topNegStates(5, tbsPete));
			sb.append("\n\nMost influential Tweets: " + daPete.topNInf(3));

			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		System.out
				.println("\n\n\n\n===================================================================================");
		System.out.println("A N A L Y S I S   C O M P L E T E");
		System.out.println("===================================================================================\n");
		System.out.println(
				"Tweet sentiment is measured in a 0 to 4 scale. 0 being extremely negative and 4 extremely positive\n");

		System.out.println("\nTotal number of tweets in sample: " + tweets.size() + ".");
		System.out.println("Total number of tweets with matched location: " + count + ".");
		System.out.println("Percentage of tweets with positive sentiment (2 or higher): " + da.posPercent(tweets));
		System.out.println("Percentage of tweets with negative sentiment (under 2): " + da.negPercent(tweets));
		System.out.println("\nMost influential Tweets: " + da.topNInf(5));

		// Biden
		System.out.println("\nCandidate: JOE BIDEN\n");
		System.out.println("Total number of tweets: " + biden.size());
		System.out.println("Average sentiment score: " + daBiden.sentimentScore());
		System.out.println("Percentage of tweets with positive sentiment (2 or higher): " + daBiden.posPercent(biden));
		System.out.println("Percentage of tweets with negative sentiment (under 2): " + daBiden.negPercent(biden));
		System.out.println("Median sentiment score: " + daBiden.calculateMedian(biden));
		System.out.println("Mode sentiment score: " + daBiden.calculateMode(biden));
		System.out.println("Most used positive words: " + daBiden.topNPos(5));
		System.out.println("Most used negative words: " + daBiden.topNNeg(5));
		System.out.println(daBiden.topPosStates(5, tbsBiden));
		System.out.println(daBiden.topNegStates(5, tbsBiden));
		System.out.println("\nMost influential Tweets: " + daBiden.topNInf(3));

		// Warren
		System.out.println("\nCandidate: ELIZABETH WARREN\n");
		System.out.println("Total number of tweets: " + warren.size());
		System.out.println("Average sentiment score: " + daWarren.sentimentScore());
		System.out
				.println("Percentage of tweets with positive sentiment (2 or higher): " + daWarren.posPercent(warren));
		System.out.println("Percentage of tweets with negative sentiment (under 2): " + daWarren.negPercent(warren));
		System.out.println("Median sentiment score: " + daWarren.calculateMedian(warren));
		System.out.println("Mode sentiment score: " + daWarren.calculateMode(warren));
		System.out.println("Most used positive words: " + daWarren.topNPos(5));
		System.out.println("Most used negative words: " + daWarren.topNNeg(5));
		System.out.println(daWarren.topPosStates(5, tbsWarren));
		System.out.println(daWarren.topNegStates(5, tbsWarren));
		System.out.println("\nMost influential Tweets: " + daWarren.topNInf(3));

		// Bernie
		System.out.println("\nCandidate: BERNIE SANDERS\n");
		System.out.println("Total number of tweets: " + sanders.size());
		System.out.println("Average sentiment score: " + daSanders.sentimentScore());
		System.out.println(
				"Percentage of tweets with positive sentiment (2 or higher): " + daSanders.posPercent(sanders));
		System.out.println("Percentage of tweets with negative sentiment (under 2): " + daSanders.negPercent(sanders));
		System.out.println("Median sentiment score: " + daSanders.calculateMedian(sanders));
		System.out.println("Mode sentiment score: " + daSanders.calculateMode(sanders));
		System.out.println("Most used positive words: " + daSanders.topNPos(5));
		System.out.println("Most used negative words: " + daSanders.topNNeg(5));
		System.out.println(daSanders.topPosStates(5, tbsSanders));
		System.out.println(daSanders.topNegStates(5, tbsSanders));
		System.out.println("\nMost influential Tweets: " + daSanders.topNInf(3));

		// Pete
		System.out.println("\nCandidate: PETE BUTTIGIEG\n");
		System.out.println("Total number of tweets: " + pete.size());
		System.out.println("Average sentiment score: " + daPete.sentimentScore());
		System.out.println("Percentage of tweets with positive sentiment (2 or higher): " + daPete.posPercent(pete));
		System.out.println("Percentage of tweets with negative sentiment (under 2): " + daPete.negPercent(pete));
		System.out.println("Median sentiment score: " + daPete.calculateMedian(pete));
		System.out.println("Mode sentiment score: " + daPete.calculateMode(pete));
		System.out.println("Most used positive words: " + daPete.topNPos(5));
		System.out.println("Most used negative words: " + daPete.topNNeg(5));
		System.out.println(daPete.topPosStates(5, tbsPete));
		System.out.println(daPete.topNegStates(5, tbsPete));
		System.out.println("\nMost influential Tweets: " + daPete.topNInf(3));

		System.out
				.println("\n\n======================================================================================");
		System.out.println("A CSV file with data by state and candidate called 'DataByState.csv' has been saved.");
		System.out.println("======================================================================================");
		System.out.println("A CSV file with data by candidate called 'DataByCandidate.csv' has been saved.");
		System.out.println("======================================================================================");
		System.out.println("A TXT file with the console printout called 'report.txt' has been saved.");
		System.out.println("======================================================================================\n");
	}

}
