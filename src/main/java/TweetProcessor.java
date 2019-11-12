package main.java;
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
        // remove html markup
        // method needs more work
        String nohtml = text.replace("(<.*?>)","");

        // remove non-ascii and digits
        // method needs more work

        return nohtml.replace("(\\W|\\d)","");
    }
}
