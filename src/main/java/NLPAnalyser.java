import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Class uses the Stanford CoreNLP API to analyse tweet text
 * It includes methods to:
 * Create an nlp pipeline to prep a tweet for analysis
 * Get the sentiment score for a tweet
 * Get the adjectives in a tweet
 * Get the sentiment analysis of a tweet
 * <p>
 * The sentiment score for a tweet can be:
 * very negative = 0
 * negative = 1
 * neutral = 2
 * positive = 3
 * very positive = 4
 * <p>
 * Link to Stanford CoreNLP documentation: https://stanfordnlp.github.io/CoreNLP/index.html
 *
 * @author joannecrean
 */
public class NLPAnalyser {

    private Properties props; //new properties file from Stanford CoreNLP library
    private StanfordCoreNLP pipeline; // sets up a new pipeline
    private TweetProcessor tp = new TweetProcessor(); // to clean up Tweets
    // list of over-used words that are removed from the adjectives list for all tweets
    private String[] commonWords = {"best", "good", "bad", "better", "great", "real",
            "worst", "trump", "lovely", "wrong", "right", "worse", "least", "many",
            "nice", "such", "hilary", "kerry", "fine"};

    /**
     * Constructor creates a new Sentiment Analyser object
     * Sets the properties needed for NLP analysis in the pipeline
     */
    public NLPAnalyser() {
        props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    /**
     * Method runs NLP pipeline on a tweet
     * <p>
     * The following steps take place in the pipeline:
     * Tokenisation - breaks down into words
     * Sentence split - combines the words into sentences
     * Pos - parts of speech assigned to each word in a sentence,e.g. noun
     * Parse - provides full syntactic analysis, minimally a constituency (phrase-structure tree) parse of sentences
     * Further information: https://stanfordnlp.github.io/CoreNLP/api.html
     *
     * @return List<CoreMap> mapping the sentences to their annotations
     */
    public List<CoreMap> nlpPipeline(String tweetText) {
        String processedText = tp.removeNoise(tweetText);

        // Create a new annotation object from the tweet
        // This is needed to prep the tweet for the sentiment analysis
        Annotation document = new Annotation(processedText);

        // Run the text through the pipeline to annotate it
        pipeline.annotate(document);

        // Create a CoreMap containing the annotations for each sentence
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        return sentences;
    }

    /**
     * Method gets a sentiment score for a tweet.
     * <p>
     * Once the sentiment has been calculated for each line then the average sentiment is found to give
     * the overall tweet sentiment
     *
     * @return double containing the sentiment for the tweet
     */
    public int getSentimentScore(List<CoreMap> sentences) {
        int totalSentiment = 0; // running total of sentiment for a tweet sentiment
        int tweetSentiment = 0; // total tweet sentiment

        // if the text passed into the tweet is an empty string, a neutral value is assigned to the tweet
        if (sentences.size() == 0) {
            tweetSentiment = 2;
        } else {
            // Loop through each sentence and get the sentiment score
            for (CoreMap sentence : sentences) {

                // Create a Tree object containing the sentence broken into a tree structure for sentiment analysis
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

                // Assign the sentiment score for the sentence to an integer
                int sentimentSentence = RNNCoreAnnotations.getPredictedClass(tree);

                // Add the sentiment score for this sentence to the total sentiment for the tweet
                totalSentiment = totalSentiment + sentimentSentence;

                // Get the total sentiment for the tweet by getting the average sentiment for all
                // sentences in the tweet
                // round this to the nearest sentiment score value
                tweetSentiment = totalSentiment / sentences.size();
            }
        }
        return tweetSentiment;
    }

    /**
     * Method to get the adjectives in each tweet
     * Loops through the words and if it's an adjective then adds it to array list
     *
     * @return ArrayList<String> containing adjectives in the tweet
     */
    public ArrayList<String> adjectives(List<CoreMap> sentences) {
        String adjective = "JJ"; //adjectives are marked with 'JJ' tag when going through the the NLP pipeline
        String adjectiveComp = "JJR"; // comparative adjectives are marked by the 'JJR' tag
        String adjectiveSup = "JJS"; // superlative adjectives are marked with the 'JJS' tag
        ArrayList<String> adjectives = new ArrayList<String>(); //array list for the adjectives
        boolean flag = false;

        for (CoreMap sentence : sentences) {
            // Loop through sentences
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // Gets the is the text of the word
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                // if the word is an adjective then add it to the the ArrayList of adjectives
                if (pos.contains(adjective) || pos.contains(adjectiveComp) || pos.contains(adjectiveSup)) {
                    // check if the word is in the list of common words
                    for (String common : commonWords) {
                        // if the word is in the common list, set the flag to true
                        if (word.equals(common)) {
                            flag = true;
                            //break the loop once the flag is true
                            break;
                        } else {
                            flag = false;
                        }
                    }
                    // if the word was not a common word
                    if (!flag) {
                        // add the adjectives to the array list in lowercase
                        adjectives.add(word.toLowerCase());
                    }
                }
            }
        }
        return adjectives;
    }

    /**
     * Method to get a list of positive and negative adjectives
     * Adjective is stored in an array list along with its sentiment score
     *
     * @return adjectivesScore as a HashMap<String, Integer>
     */

    public HashMap<String, Double> adjectivesScoring(List<CoreMap> sentences) {
        HashMap<String, Double> adjectivesScore = new HashMap<String, Double>(); //Hashmap to store the adjectives and their values
        ArrayList<String> adjectives = adjectives(sentences); //ArrayList of adjectives, calls the adjectives method
        // loop through the adjectives
        for (String adjective : adjectives) {
            //run the NLPpipeline on the adjective
            List<CoreMap> tweetAdjectives = nlpPipeline(adjective);
            // get the sentiment score for the adjective
            double adjSentiment = getSentimentScore(tweetAdjectives);
            //add the adjective score and its sentiment score to a hashmap
            adjectivesScore.put(adjective.toLowerCase(), adjSentiment);
        }
        return adjectivesScore;
    }

    /**
     * Getter to get the properties applied
     *
     * @return properties
     */
    public Properties getProps() {
        return props;
    }

    /**
     * Getter to get array of common words
     *
     * @return array of common words
     */
    public String[] getCommonWords() {
        return commonWords;
    }
}

