import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

/**
 * Class takes tweet text and cleans it up for natural language processing
 * It includes methods to:
 * Remove noise from text: urls, users mentions, hash-tags, hyphens, quotation marks, non-ascii characters
 * Replace emojis with their text equivalent using the Emoji Java library: https://github.com/vdurmont/emoji-java
 *
 * @author joannecrean
 */

public class TweetProcessor {

    /**
     * Constructor sets up the Tweet Processor
     */
    public TweetProcessor() {
    }

    /**
     * Method removes replaces emojis with their text equivalent
     * Removes punctuation around that text
     *
     * @return String with text instead of emojis
     */
    public String emojiParser(String text) {
        String emojiText = EmojiParser.parseToAliases(text);

        //remove colon from around emoji text
        emojiText = emojiText.replaceAll(":", " ");

        //remove underscore from emoji text
        emojiText = emojiText.replaceAll("_", " ");
        System.out.println("true: " + emojiText);

        return emojiText;
    }

    /**
     * Method removes noise from text:
     * urls, hashtags, user mentions, hyphens, quotation marks, non-ascii characters
     * emojis are replaced with text
     *
     * @return String of processed text
     */
    public String removeNoise(String text) {
        String tweet = text; // processed text

        //remove urls
        tweet = tweet.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");

        //remove user names
        tweet = tweet.replaceAll("@[^\\s]+", "");

        //remove # from hash tag
        tweet = tweet.replaceAll("#", "");

        // replace emojis with their text
        if (EmojiManager.containsEmoji(tweet)) {
            tweet = emojiParser(tweet);
        }
        //remove quotation marks
        tweet = tweet.replaceAll("\"", "");

        //remove hyphen
        tweet = tweet.replaceAll("-", " ");

        // replace all non-ascii characters with empty string to remove any remaining emojis that the
        //sentiment analyser can't recognise
        tweet = tweet.replaceAll("[^\\x00-\\x7F]", "");

        return tweet;
    }
}