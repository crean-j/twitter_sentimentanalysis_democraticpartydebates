import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataAnalysisTest {

    @Test
    void numTweets() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        tw.add(t);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals(tw.size(), da.numTweets());
    }

    @Test
    void sentimentScore() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(1.00);
        tw.add(t);
        DataAnalysis da = new DataAnalysis(tw);
        String sentiment = da.sentimentScore();
        assertTrue(sentiment.charAt(0) == '0' || sentiment.charAt(0) == '1' || sentiment.charAt(0) == '2'
        || sentiment.charAt(0) == '3' || sentiment.charAt(0) == '4' || sentiment.charAt(0) == '.');
    }

    @Test
    void calculateMedian() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(1.00);
        tw.add(t);
        Tweet u = new Tweet();
        u.setSentimentScore(1.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setSentimentScore(1.00);
        tw.add(v);
        Tweet w = new Tweet();
        w.setSentimentScore(4.00);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals(1, da.calculateMedian(tw));
    }

    @Test
    void calculateMode() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(1.00);
        tw.add(t);
        Tweet u = new Tweet();
        u.setSentimentScore(1.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setSentimentScore(1.00);
        tw.add(v);
        Tweet w = new Tweet();
        w.setSentimentScore(4.00);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals(1, da.calculateMode(tw));
    }

    @Test
    void topInf() {
        boolean flag = true;
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setInfluenceScore(4.72);
        tw.add(t);
        Tweet u = new Tweet();
        u.setInfluenceScore(40.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setInfluenceScore(4.77);
        tw.add(v);
        Tweet w = new Tweet();
        w.setInfluenceScore(400.30);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        List<Tweet> sortedInfScore = da.topInf(tw);
        for (int i = 0; i < sortedInfScore.size() - 1; i++) {
            if (sortedInfScore.get(i).getInfluenceScore() < sortedInfScore.get(i + 1).getInfluenceScore())
                flag = false;
                break;
        }
        assertTrue(flag);
    }

    @Test
    void topInfFalse() {
        boolean flag = true;
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setInfluenceScore(4.72);
        tw.add(t);
        Tweet u = new Tweet();
        u.setInfluenceScore(40.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setInfluenceScore(4.77);
        tw.add(v);
        Tweet w = new Tweet();
        w.setInfluenceScore(400.30);
        tw.add(w);
        Tweet z = new Tweet();
        z.setInfluenceScore(0.00);
        DataAnalysis da = new DataAnalysis(tw);
        List<Tweet> sortedInfScore = da.topInf(tw);
        sortedInfScore.add(0, z);
        for (int i = 0; i < sortedInfScore.size() - 1; i++) {
            if (sortedInfScore.get(i).getInfluenceScore() < sortedInfScore.get(i + 1).getInfluenceScore())
                flag = false;
            break;
        }
        assertFalse(flag);
    }

    @Test
    void topNInf() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setInfluenceScore(4.72);
        tw.add(t);
        Tweet u = new Tweet();
        u.setInfluenceScore(40.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setInfluenceScore(4.77);
        tw.add(v);
        Tweet w = new Tweet();
        w.setInfluenceScore(400.30);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals("Not enough data available", da.topNInf(4));
    }

    @Test
    void topNInfGreaterThan() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setInfluenceScore(4.72);
        tw.add(t);
        Tweet u = new Tweet();
        u.setInfluenceScore(40.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setInfluenceScore(4.77);
        tw.add(v);
        Tweet w = new Tweet();
        w.setInfluenceScore(400.30);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals('U', da.topNInf(3).charAt(2));
    }

   // @Test
    /*void topPositiveWords() {
        boolean flag = true;
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(4.00);
        tw.add(t);
        Tweet u = new Tweet();
        u.setSentimentScore(3.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setSentimentScore(2.70);
        tw.add(v);
        Tweet w = new Tweet();
        w.setSentimentScore(3.70);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        HashMap<String, Integer> positive = da.topPositiveWords();
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (String key : positive.keySet()) {
            values.add(positive.get(key));
        }
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) < values.get(i + 1))
                flag = false;
            break;
        }
        assertTrue(flag);
    }*/

    //@Test
    /*void topNegativeWords() {
        boolean flag = true;
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(4.00);
        tw.add(t);
        Tweet u = new Tweet();
        u.setSentimentScore(3.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setSentimentScore(2.70);
        tw.add(v);
        Tweet w = new Tweet();
        w.setSentimentScore(3.70);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        HashMap<String, Integer> positive = da.topPositiveWords();
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (String key : positive.keySet()) {
            values.add(positive.get(key));
        }
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) > values.get(i + 1))
                flag = false;
            break;
        }
        assertTrue(flag);
    }*/

    //@Test
    /*void topNPos() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(4.00);
        tw.add(t);
        Tweet u = new Tweet();
        u.setSentimentScore(3.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setSentimentScore(2.70);
        tw.add(v);
        Tweet w = new Tweet();
        w.setSentimentScore(3.70);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals("Not enough data available", da.topNPos(4));
    }*/

    //@Test
    /*void topNNeg() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setSentimentScore(4.00);
        tw.add(t);
        Tweet u = new Tweet();
        u.setSentimentScore(3.00);
        tw.add(u);
        Tweet v = new Tweet();
        v.setSentimentScore(2.70);
        tw.add(v);
        Tweet w = new Tweet();
        w.setSentimentScore(3.70);
        tw.add(w);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals("Not enough data available", da.topNPos(4));
    }*/

    @Test
    void numberOfTweetsState() {
    }

    @Test
    void mostRetweeted() {
    }

    @Test
    void sentimentState() {
    }

    @Test
    void lowSentState() {
    }

    @Test
    void topPosStates() {
    }

    @Test
    void topNegStates() {
    }
}