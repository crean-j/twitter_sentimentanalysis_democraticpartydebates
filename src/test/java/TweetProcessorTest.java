package test.java;
import static org.junit.jupiter.api.Assertions.*;

import main.java.TweetProcessor;

class TweetProcessorTest {

    @org.junit.jupiter.api.Test
    void cleanText() {
        TweetProcessor tp = new TweetProcessor();
        String test = "AAtEsT! ";
        assertEquals("aatest!", tp.cleanText(test));
    }
}