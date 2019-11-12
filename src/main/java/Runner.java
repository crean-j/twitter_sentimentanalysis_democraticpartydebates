package main.java;
import java.util.ArrayList;

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


		// DataAnalysis da = new DataAnalysis(tweets);

		// run analysis
		// print output
	}
}
