import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.stanford.nlp.util.CoreMap;

public class DemDebate {

	public static void main(String[] args) {

		ArrayList<Tweet> tweets = SaveTweets.loadFile();
		System.out.println(tweets.size());
		for (int i = 0; i < tweets.size(); i++) {
			Tweet tweet = tweets.get(i);
		}
		
		ArrayList<Tweet> warren = new ArrayList<>();
		ArrayList<Tweet> biden = new ArrayList<>();
		ArrayList<Tweet> sanders = new ArrayList<>();
		ArrayList<Tweet> pete = new ArrayList<>();
				
		for(Tweet t : tweets) {
			if (t.getCandidate().equals("Warren")) {
				warren.add(t);
			}
			if (t.getCandidate().equals("Sanders")) {
				sanders.add(t);
			}
			if (t.getCandidate().equals("Buttigieg")) {
				pete.add(t);
			}
			if (t.getCandidate().equals("Biden")) {
				biden.add(t);
			}
		}

		/*
		 * Analysis of all tweets
		 */
		
		NLPAnalyser nlp = new NLPAnalyser();
		int current = 0;
		for (Tweet tweet : tweets) {
			List<CoreMap> sentences = nlp.nlpPipeline(tweet.getTextInTweet());
			double sentimentScore = nlp.getSentimentScore(sentences);
			tweet.setSentimentScore(sentimentScore);
			HashMap<String, Double> as = nlp.adjectivesScoring(sentences);
			tweet.setAdjSentiment(as);
			System.out.println(current + "/" + tweets.size() + "; score=" + sentimentScore + "; adj =" + as);
			current += 1;
		}

		TweetsByState tbs = new TweetsByState(tweets);
		System.out.println(tbs.states.get("DC").size());
		int count = 0;
		for (String state : tbs.states.keySet()) {
			System.out.println(state + " " + tbs.states.get(state).size());
			count += tbs.states.get(state).size();
		}

		System.out.println(count);

		DataAnalysis da = new DataAnalysis(tweets);
		System.out.println(da.sentimentState(tbs));
		System.out.println(da.mostUsedWords());

		/*
		 * Analysis specific to Elizabeth Warren
		 */
		
		NLPAnalyser nlpWarren = new NLPAnalyser();
		int currentWarren = 0;
		for (Tweet tweet : warren) {
			List<CoreMap> sentences = nlp.nlpPipeline(tweet.getTextInTweet());
			double sentimentScore = nlp.getSentimentScore(sentences);
			tweet.setSentimentScore(sentimentScore);
			HashMap<String, Double> as = nlp.adjectivesScoring(sentences);
			tweet.setAdjSentiment(as);
			System.out.println(currentWarren + "/" + tweets.size() + "; score=" + sentimentScore + "; adj =" + as);
			currentWarren += 1;
		}
		
		TweetsByState tbsWarren = new TweetsByState(warren);
		System.out.println(tbsWarren.states.get("DC").size());
		int countWarren = 0;
		for (String state : tbsWarren.states.keySet()) {
			System.out.println(state + " " + tbsWarren.states.get(state).size());
			count += tbsWarren.states.get(state).size();
		}

		System.out.println(countWarren);

		DataAnalysis daWarren = new DataAnalysis(warren);
		System.out.println(daWarren.sentimentState(tbsWarren));
		System.out.println(daWarren.mostUsedWords());
	}
}
