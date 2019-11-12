import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class uses the Stanford CoreNLP API to analyse tweet text
 * It includes methods to:
 * Get the adjectives in a tweet
 * Get the sentiment analysis of a tweet
 * Get the individual words in a tweet
 *
 * The sentiment score for a tweet can be:
 * very negative = 0
 * negative = 1
 * neutral = 2
 * positive = 3
 * very positive = 4
 *
 * Link to Stanford CoreNLP documentation: https://stanfordnlp.github.io/CoreNLP/index.html
 *
 * @author joannecrean
 */
public class NLPAnalyser {

    private Properties props = new Properties(); //new properties file from Stanford CoreNLP library
    private StanfordCoreNLP pipeline = new StanfordCoreNLP(props); // sets up a new pipeline
    private Tweet tweet; // tweet to analyse

    /**
     * Constructor creates a new Sentiment Analyser object
     * Sets the properties needed for NLP analysis in the pipeline
     */
    public NLPAnalyser() {
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
    }

    /**
     * Method runs NLP pipeline on a tweet
     *
     * The following steps take place in the pipeline:
     * Tokenisation - breaks down into words
     * Sentence split - combines the words into sentences
     * Pos - parts of speech assigned to each word in a sentence,e.g. noun
     * Parse - provides full syntactic analysis, minimally a constituency (phrase-structure tree) parse of sentences
     * Further information: https://stanfordnlp.github.io/CoreNLP/api.html
     *
     * @return List<CoreMap> mapping the sentences to their annotations
     */
    public List<CoreMap> nlpPipeline(Tweet tweet) {
        // Create a new annotation object from the tweet
        // This is needed to prep the tweet for the sentiment analysis
        Annotation document = new Annotation(tweet.getTextInTweet());

        // Run the text through the pipeline to annotate it
        pipeline.annotate(document);

        // Create a CoreMap containing the annotations for each sentence
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        return sentences;
    }

    /**
     * Method gets a sentiment score for a tweet.
     *
     * Once the sentiment has been calculated for each line then the average sentiment is found to give
     * the overall tweet sentiment
     *
     * @return int containing the sentiment for the tweet
     */
    public int getSentimentScore(List<CoreMap> sentences) {

        int tweetSentiment = -1; // sentiment for the tweet overall
        int totalSentiment = -1; // sum of the sentiment for each sentence in the tweet

        // Loop through each sentence and get the sentiment score
        for (CoreMap sentence : sentences) {

            // Create a Tree object containing the sentiment score for each sentence
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

            // Assign the sentiment score the sentence to an integer
            int sentimentSentence = RNNCoreAnnotations.getPredictedClass(tree);

            System.out.println("Sentiment Score:" + sentimentSentence); // to delete

            // Add the sentiment score for this sentence to the total sentiment for the tweet
            totalSentiment = totalSentiment + sentimentSentence;
        }

        // Get the total sentiment for the tweet by getting the average sentiment for all
        // sentences in the tweet
        tweetSentiment = totalSentiment / sentences.size();
        return tweetSentiment;
    }

    /**
     * Method to get the adjectives in each tweet
     * Loops through the words and if it's an adjective then adds it to array list
     *
     * @return ArrayList<String> containing adjectives in the tweet
     */
    public ArrayList<String> adjectives(List<CoreMap> sentences) {
        String adjective = "JJ";
        ArrayList<String> adjectives = new ArrayList<String>();

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                // if the word is an adjective then add it to the the ArrayList of adjectives
                if (pos.contains(adjective)) {
                    adjectives.add(word);
                }
            }
        }
        // might add code to also add sentiment score for the adjectives
        return adjectives;
    }

    /**
     * Method to get the individual words in a tweet
     * Breaks the tweet down into its individual words
     *
     * @return ArrayList<String> containing individual words in the tweet
     */
    public ArrayList<String> getWords (List <CoreMap> sentences) {
        ArrayList<String> tokens = new ArrayList<String>();
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);

                tokens.add(word);
            }
        }
        // may need to add code to remove stop words
        return tokens;
    }
}
