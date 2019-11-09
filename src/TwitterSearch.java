
/**
 * Final Project for MCIT 591
 * @author juangoleniowski
 *
 */

import java.util.*;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This class handles the queries to Twitter
 * 
 * @author juangoleniowski
 *
 */
public class TwitterSearch {

    public static void main(String[] args) {

	// Creates Twitter instance
	ConfigurationBuilder cf = new ConfigurationBuilder().setTweetModeExtended(true);
	Twitter twitter = TwitterFactory.getSingleton();
	TwitterFactory tf = new TwitterFactory(cf.build());
	String candidate = "Warren";
	HashMap <Integer, Tweet> queryResult = new HashMap<>();
	
	// Runs the query for "Warren"
	Query query = new Query(candidate);
	QueryResult result;
	try {
	    result = twitter.search(query.count(100));
	    for (Status status : result.getTweets()) {
		String text = status.getRetweetedStatus() != null ? status.getRetweetedStatus().getText()
			: status.getText();
		Tweet tw = new Tweet(status.getUser(), status.getUser().getFriendsCount(),
			status.getUser().getLocation(), text, status.getCreatedAt(), candidate, 0,
			status.getRetweetCount());
		queryResult.put(status.hashCode(), tw);
		
	    }

	} catch (TwitterException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NullPointerException f) {
	    f.printStackTrace();
	}
	for (int key : queryResult.keySet()) {
	    System.out.println(queryResult.get(key).getTextInTweet());
	}

    }

}
