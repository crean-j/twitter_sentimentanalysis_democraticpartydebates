/**
 * Class takes tweet text and cleans it up for natural language processing
 * It includes methods to:
 * Remove noise from text: urls, users mentions and hash-tags
 *
 * @author joannecrean
 */

public class TweetProcessor {

    /**
     * Constructor sets up the Tweet Processor
     */
    public TweetProcessor() { }

    /**
     * Method to do additional clean-up of tweets
     * Method is optional if sentiment score returned is being influenced by:
     * casing, whitespace, capitalisation
     * @return String of text
     */
    public String cleanText(String text) {

        // remove whitespace
        String trimText = text.trim();

        //remove punctuation
        trimText = trimText.replaceAll("\\p{Punct}+", "");

        // to lowercase
        return trimText.toLowerCase();

    }

    /**
     * Method removes noise from text
     * @return String of text
     */
    public String removeNoise(String text) {
        String tweet = text; // processed text

        //remove urls
        tweet = tweet.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");

        //remove user names
        tweet = tweet.replaceAll("@[^\\s]+", "");

        //remove # from hash tag
        tweet = tweet.replaceAll("#", "");

        return tweet;
    }
}