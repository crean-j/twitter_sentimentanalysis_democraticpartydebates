//package main.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * Class takes tweet text and cleans it up for natural language processing
 * It includes methods to:
 * Lowercase the text
 * Remove noise from text
 *
 * @author joannecrean
 */

public class TweetProcessor {

    /**
     * Constructor sets up the Tweet Processor
     */
    public TweetProcessor() { }

    /**
     * Method removes whitespace and makes the text lower case
     * @return String of text
     */
    public String cleanText(String text) {

        // remove whitespace
        String trimText = text.trim();

        // to lowercase
        return trimText.toLowerCase();
    }

    /**
     * Method removes noise from text
     * @return String of text
     */
    public String removeNoise(String text) {
        String finalText = text;
        String tweet = text;

        //convert tweet to lower case
        tweet = tweet.toLowerCase();

        //remove urls
        tweet = tweet.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");

        //remove user names
        tweet = tweet.replaceAll("@[^\\s]+", "");

        //remove # from hash tag
        tweet = tweet.replaceAll("#", "");

        //remove punctuation
        tweet = tweet.replaceAll("\\p{Punct}+", "");

        //System.out.println(tweet);
        return tweet;
    }


    /*public static void main(String[] args) {
    	TweetProcessor tp = new TweetProcessor();
        String test = "RT This made my day; glad @JeremyKappell is standing up against #ROC’s disgusting mayor. "
        		+ "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren"
        		+ "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        String finalText = tp.removeNoise(test);
        System.out.println(finalText);
    }*/
}