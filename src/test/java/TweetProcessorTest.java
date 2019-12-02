/**
 * Class tests the methods in the TweetProcessor class
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TweetProcessorTest {

    // Test makes sure that whitespace is being removed and text is converted to lower case
    @Test
    void testCleanText() {
        TweetProcessor tp = new TweetProcessor();
        String test = "AAtEsT! ";
        assertEquals("aatest!", tp.cleanText(test));
    }

    // Test makes sure that hashtags, user mentions and urls are being removed
    @Test
    void removeNoise() {
        TweetProcessor tp = new TweetProcessor();
        String test = "RT This made my day; glad @JeremyKappell is standing up against #ROC’s disgusting mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren" +
                "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        assertEquals("RT This made my day; glad  is standing up against ROC’s disgusting mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren (Via NEWS 8 WROC)"
                , tp.removeNoise(test));
    }

    // Test checks that hashtags, user mentions and urls are being removed if there is more than one in the text
    @Test
    void removeNoiseDouble() {
        TweetProcessor tp = new TweetProcessor();
        String test = "RT This made my day; glad @JeremyKappell @JeremyKappell is standing up against " +
                "#ROC’s #ROC’s disgusting mayor." +
                "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren" +
                "https://t.co/rJIV5SN9vB https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        assertEquals("RT This made my day; glad   is standing up against ROC’s ROC’s disgusting mayor." +
                        "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren  (Via NEWS 8 WROC)"
                , tp.removeNoise(test));
    }
}