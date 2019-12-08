import edu.stanford.nlp.util.CoreMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the methods in the DataAnalysis class
 *
 * @author joannecrean
 */

class DataAnalysisTest {

    /**
     * Test checks the number of tweets in an array list is being correctly calculated
     */
    @Test
    void numTweets() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        tw.add(t);
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals(tw.size(), da.numTweets());
    }

    /**
     * Test checks the sentiment score string is correctly in the range 0 - 4
     */
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

    /**
     * Test checks the median number for tweets is being correctly calculated
     */
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

    /**
     * Test checks the mode number for tweets is being correctly calculated
     */
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

    /**
     * Test checks the array list of most influential tweets is being correctly sorted by score
     */
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

    /**
     * Test checks the array list of most influential tweets is being correctly sorted by score
     * Verifies that it's catching the case where the order is not correct
     */
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

    /**
     * Test checks that if there isn't enough influential tweets
     * then the user is correctly told
     */
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

    /**
     * Test checks that if there is enough influential tweets, is shown the output string containing tweet
     */
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
        assertEquals('U', da.topNInf(3).charAt(1));
    }

    /**
     * Test checks that the array list of positive words is correctly sorted by sentiment score
     */
    @Test
    void topPositiveWords() {
        boolean flag = true;
        HashMap<String, Double> th = new HashMap<String, Double>();
        th.put("nice", 2.0);
        HashMap<String, Double> tu = new HashMap<String, Double>();
        tu.put("calm", 2.0);
        HashMap<String, Double> tv = new HashMap<String, Double>();
        tv.put("angry", 1.0);
        HashMap<String, Double> ta = new HashMap<String, Double>();
        ta.put("beautiful", 3.0);
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setAdjSentiment(th);
        tw.add(t);
        Tweet u = new Tweet();
        u.setAdjSentiment(tu);
        tw.add(u);
        Tweet v = new Tweet();
        v.setAdjSentiment(tv);
        tw.add(v);
        Tweet w = new Tweet();
        w.setAdjSentiment(ta);
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
    }

    /**
     * Test checks that the array list of negative words is correctly sorted by sentiment score
     */
    @Test
    void topNegativeWords() {
        boolean flag = true;
        HashMap<String, Double> th = new HashMap<String, Double>();
        th.put("nice", 2.0);
        HashMap<String, Double> tu = new HashMap<String, Double>();
        tu.put("calm", 2.0);
        HashMap<String, Double> tv = new HashMap<String, Double>();
        tv.put("angry", 1.0);
        HashMap<String, Double> ta = new HashMap<String, Double>();
        ta.put("beautiful", 3.0);
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setAdjSentiment(th);
        tw.add(t);
        Tweet u = new Tweet();
        u.setAdjSentiment(tu);
        tw.add(u);
        Tweet v = new Tweet();
        v.setAdjSentiment(tv);
        tw.add(v);
        Tweet w = new Tweet();
        w.setAdjSentiment(ta);
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
    }

    /**
     * Test checks that if there isn't enough postive words to analyse, then the user is correctly told
     */
    @Test
    void topNPos() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setTextInTweet("A long time ago in Ireland there lived a very wise old man named Finnegas.");
        tw.add(t);
        Tweet u = new Tweet();
        u.setTextInTweet("He knew everything about nature, birds, animals, plants, clouds and the stars.");
        tw.add(u);
        Tweet v = new Tweet();
        v.setTextInTweet("Whoever caught and ate this fish would know all there was to know in the world.");
        tw.add(v);
        Tweet w = new Tweet();
        w.setTextInTweet("Lots of people, including Finnegas tried to catch this fish but they had no luck.");
        tw.add(w);
        NLPAnalyser np = new NLPAnalyser();
        for (Tweet tweet : tw) {
            List<CoreMap> sentences = np.nlpPipeline(tweet.getTextInTweet());
            HashMap<String, Double> adj = np.adjectivesScoring(sentences);
            tweet.setAdjSentiment(adj);
        }
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals("Not enough data available", da.topNPos(4));
    }

    /**
     * Test checks that if there isn't enough negative words to analyse, then the user is correctly told
     */
    @Test
    void topNNeg() {
        ArrayList<Tweet> tw = new ArrayList<Tweet>();
        Tweet t = new Tweet();
        t.setTextInTweet("A long time ago in Ireland there lived a very wise old man named Finnegas.");
        tw.add(t);
        Tweet u = new Tweet();
        u.setTextInTweet("He knew everything about nature, birds, animals, plants, clouds and the stars.");
        tw.add(u);
        Tweet v = new Tweet();
        v.setTextInTweet("Whoever caught and ate this fish would know all there was to know in the world.");
        tw.add(v);
        Tweet w = new Tweet();
        w.setTextInTweet("Lots of people, including Finnegas tried to catch this fish but they had no luck.");
        tw.add(w);
        NLPAnalyser np = new NLPAnalyser();
        for (Tweet tweet : tw) {
            List<CoreMap> sentences = np.nlpPipeline(tweet.getTextInTweet());
            HashMap<String, Double> adj = np.adjectivesScoring(sentences);
            tweet.setAdjSentiment(adj);
        }
        DataAnalysis da = new DataAnalysis(tw);
        assertEquals("Not enough data available", da.topNNeg(4));
    }
}