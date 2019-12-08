import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.stanford.nlp.util.CoreMap;

/**
 * This is the main class for the sentiment analysis when running in real-time
 * It will take input from the user around which candidate to look at,
 * and the date of interest.
 * It will run all the necessary analysis and print out
 * console responses as well as create a file with the report
 *
 * @author juangoleniowski, Federica Pelzel, joannecrean
 */

public class Runner {

    /**
     * Main method to start the program running in real-time
     */
    public static void main(String[] args) {

        // get keyword from user
        UserInteraction ui = new UserInteraction();

        // search Twitter for tweets mentioning the keyword
        TwitterSearch ts = new TwitterSearch(ui.getCandidate(), ui.getDate());

        // create an ArrayList of Tweet objects from the results
        ArrayList<Tweet> result = ts.mainSearch();

        // calculate the influence score for each tweet and add it to the Tweet object
        InfluenceScore is = new InfluenceScore(result);

        // create a file containing the raw Tweets that were returned
        SaveTweets st = new SaveTweets(result);
        st.saveToFile();

        // set up the sentiment analyser and add a sentiment score and a HashMap of
        // adjectives and their sentiments scores for each Tweet in the ArrayList
        System.out.println();
        System.out.println("\nStarting sentiment analysis....\n");
        NLPAnalyser nlp = new NLPAnalyser();
        int current = 0; //count the Tweets as they are being processed to print
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

        // start the data analysis
        DataAnalysis da = new DataAnalysis(result);

        // get the Tweets by state for analysis
        TweetsByState tbs = new TweetsByState(result);
        int count = 0;
        for (String state : tbs.states.keySet()) {
            count += tbs.states.get(state).size();
        }

        // analysis the Tweets if there are Tweets to analyze

        // check if there area enough Tweets to create an output file
        if (result.size() > 0) {

            // print out a file containing the analysis results
            try (PrintWriter writer = new PrintWriter(new File("report_live.txt"))) {
                StringBuilder sb = new StringBuilder();
                sb.append(
                        "\n==========================================================================================================\n");
                sb.append("A N A L Y S I S   R E P O R T + \n\n");
                sb.append(
                        "\n==========================================================================================================\n");
                sb.append(
                        "Tweet sentiment is measured in a 0 to 4 scale. 0 being extremely negative and 4 extremely positive");
                sb.append(
                        "\n==========================================================================================================\n");

                sb.append("\nTotal number of tweets in sample: " + result.size() + ".");
                sb.append(System.getProperty("line.separator"));
                sb.append("\nTotal number of tweets with matched location: " + count + ".\n");
                sb.append(System.getProperty("line.separator"));

                sb.append("\nAverage sentiment score: " + da.sentimentScore());
                sb.append("\nPercentage of tweets with positive sentiment (2 or higher): " + da.posPercent(result));
                sb.append("\nPercentage of tweets with negative sentiment (under 2): " + da.negPercent(result));
                sb.append("\nMedian sentiment score: " + da.calculateMedian(result));
                sb.append("\nMode sentiment score: " + da.calculateMode(result) + "\n");
                sb.append("\nMost used positive words: " + da.topNPos(5));
                sb.append("\nMost used negative words: " + da.topNNeg(5));
                sb.append("\n" + da.topPosStates(5, tbs));
                sb.append("\n" + da.topNegStates(5, tbs));
                sb.append("\n\nMost influential Tweets: " + da.topNInf(5));

                writer.write(sb.toString());

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // check if there are no results and print out in console that no results were found
        if (result.size() == 0) {
            System.out.println("Your search returned no results");
        } else {
            // print out the analysis results in the console
            System.out.println("\n\n===================================================================================");
            System.out.println("A N A L Y S I S   C O M P L E T E");
            System.out.println("===================================================================================\n");
            System.out.println("A file containing the report called 'report_live.txt' has been saved");
            System.out.println("===================================================================================\n");
            System.out.println(
                    "Tweet sentiment is measured in a 0 to 4 scale. 0 being extremely negative and 4 extremely positive\n\n");
            System.out.println("Total number of tweets in sample: " + result.size() + ".");
            System.out.println("Total number of tweets with matched location: " + count + ".\n");

            System.out.println("Average sentiment score: " + da.sentimentScore());
            System.out.println("Percentage of tweets with positive sentiment (2 or higher): " + da.posPercent(result));
            System.out.println("Percentage of tweets with negative sentiment (under 2): " + da.negPercent(result));
            System.out.println("Median sentiment score: " + da.calculateMedian(result));
            System.out.println("Mode sentiment score: " + da.calculateMode(result));
            System.out.println("Most used positive words: " + da.topNPos(5));
            System.out.println("Most used negative words: " + da.topNNeg(5));
            System.out.println(da.topPosStates(5, tbs));
            System.out.println(da.topNegStates(5, tbs));
            System.out.println("\nMost influential Tweets: " + da.topNInf(3));
        }
    }
}
