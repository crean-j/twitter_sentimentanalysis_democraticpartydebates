import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class tests the methods in the TweetProcessor class
 *
 *  @author joannecrean
 */

class TweetProcessorTest {

    /**
     * Test makes sure that hashtags, user mentions, urls, non-ascii characters are being removed
     * and emoji's are being replaced with their name
     */
    @Test
    void removeNoise() {
        TweetProcessor tp = new TweetProcessor();
        String test = "RT \uD83C\uDF46 \"This\" made my day; glad @JeremyKappell is standing-up against #ROC’s disgusting_mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren :smile:" +
                "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        assertEquals("RT  eggplant  This made my day; glad  is standing up against ROCs disgusting mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren  smile  (Via NEWS 8 WROC)"
                , tp.removeNoise(test));
    }

    /**
     * Test checks that hashtags, user mentions and urls are being removed if there is more than one in the text
     */
    @Test
    void removeNoiseDouble() {
        TweetProcessor tp = new TweetProcessor();
        String test = "RT This made my day; glad @JeremyKappell @JeremyKappell is standing up against " +
                "#ROC’s #ROC’s disgusting mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren" +
                "https://t.co/rJIV5SN9vB https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        assertEquals("RT This made my day; glad   is standing up against ROCs ROCs disgusting mayor." +
                        "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren  (Via NEWS 8 WROC)"
                , tp.removeNoise(test));
    }

    /**
     * Test checks that emojis are being replaced with text
     */
    @Test
    void emojiParser() {
        TweetProcessor tp = new TweetProcessor();
        String test = "RT \uD83C\uDF46 \"This\" made my day; glad @JeremyKappell is standing-up against #ROC’s disgusting_mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren :smile:" +
                "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        assertEquals("RT  eggplant  This made my day; glad  is standing up against ROCs disgusting mayor." +
                        "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren  smile  (Via NEWS 8 WROC)"
                , tp.removeNoise(test));
    }
}