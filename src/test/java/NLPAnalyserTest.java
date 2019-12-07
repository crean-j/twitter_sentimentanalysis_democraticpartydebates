import edu.stanford.nlp.util.CoreMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class tests that the methods in the NLP Analyser class work as expected
 *
 * @author joannecrean
 */

class NLPAnalyserTest {

    /**
     * Test checks that the the List is not empty once the input text has been run through the pipeline
     */
    @Test
    void nlpPipeline() {
        NLPAnalyser np = new NLPAnalyser();
        List<CoreMap> sentences = np.nlpPipeline("RT This made my day; glad @JeremyKappell is standing up against #ROC’s disgusting mayor. "
                + "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren"
                + "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)");
        assertFalse(sentences.isEmpty());
    }

    /**
     * Test checks that the sentiment score is a value between 0 and 4
     */
    @Test
    void getSentimentScore() {
        NLPAnalyser np = new NLPAnalyser();
        List<CoreMap> sentences = np.nlpPipeline("RT This made my day; glad @JeremyKappell is standing up against #ROC’s disgusting mayor. "
                + "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren"
                + "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)");
        assertTrue(np.getSentimentScore(sentences) >= 0 && np.getSentimentScore(sentences) <=4);
    }


    /**
     * Test checks that the adjectives list is excluding common words
     */
    @Test
    void adjectivesCommonList() {
        //boolean flag = false;
        NLPAnalyser np = new NLPAnalyser();
        List<CoreMap> sentences = np.nlpPipeline("RT This made my day good; glad @JeremyKappell is standing up against bad #ROC’s disgusting mayor. "
                + "Former TV meteorologist Jeremy Kappell suing real Mayor Lovely Warren"
                + "https://t.co/rJIV5SN9vB worst(Via NEWS 8 WROC)");
        ArrayList<String> adj = np.adjectives(sentences);
        for (String common : np.getCommonWords()) {
            assertFalse(adj.contains(common));
        }
    }

    /**
     * Test checks that adjective sentiment scores are in the range 0 - 4
     */
    @Test
    void adjectivesScoring() {
        NLPAnalyser np = new NLPAnalyser();
        List<CoreMap> sentences = np.nlpPipeline("RT This made my day; glad @JeremyKappell is standing up against #ROC’s disgusting mayor. "
                + "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren"
                + "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)");
        HashMap<String, Double> as = np.adjectivesScoring(sentences);
        for (String key : as.keySet()) {
            Double value = as.get(key);
            assertTrue(value >= 0 && value <= 4);
        }
    }
}