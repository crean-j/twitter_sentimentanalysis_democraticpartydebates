import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.stanford.nlp.util.CoreMap;

public class DemDebate {

	public static void main(String[] args) {
		// Create a twitter search for each candidate at the debate date
		// Need to adjust to consume from file
//		TwitterSearch tsWarren = new TwitterSearch("Warren", "20191120");
//		TwitterSearch tsSanders = new TwitterSearch("Sanders", "20191120");
//		TwitterSearch tsBiden = new TwitterSearch("Biden", "20191120");
//		TwitterSearch tsPete = new TwitterSearch("Buttigieg", "20191120");

//		ArrayList<Tweet> result = tsWarren.mainSearch();

//		System.out.println(result);

		ArrayList<Tweet> tweets = SaveTweets.loadFile();
		System.out.println(tweets.size());
		for (int i = 0; i < tweets.size(); i++) {
			Tweet tweet = tweets.get(i);
//			System.out.println(tweet.getCandidate());
//			System.out.println(tweet.getId());
//			System.out.println(tweet.getTweetDate());
//			System.out.println(tweet.getInfluenceScore());
//			System.out.println(tweet.getNumberOfFollowers());
//			System.out.println(tweet.getLocation());
//			System.out.println(tweet.getTextInTweet());
//			System.out.println(tweet.getIsRetweet());
//			System.out.println(tweet.getLocation());

		}

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

		// run analysis
		// print output

		// Create a csv with total tweets per candidate per day or hour (Date, Time?,
		// Candidate, number of tweets)
		// Create a csv with average sentiment per candidate per day or hour (Date,
		// Time?, Candidate, average sentiment)
		// Create a csv with sentiment per state before debate (Candidate, State,
		// Average Sentiment)
		// Create a csv with sentiment per state after debate (Candidate, State, Average
		// Sentiment)
		// Create a csv with most used adjectives per candidate (Candidate, word, number
		// of times seen, sentiment score)
		// return most changed in average sentiment before and after
		// return followers gained for each candidate

	}
}
