
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

    public static void main(String[] args) throws TwitterException {

	// Creates Twitter instance

	Twitter twitter = TwitterFactory.getSingleton();

	// Runs the query for "Warren"
	Query query = new Query("Warren");
	QueryResult result = twitter.search(query);
	for (Status status : result.getTweets()) {
	    System.out.println("@" + status.getUser().getScreenName() + "," + status.getUser().getFollowersCount() + ","
		    + "," + status.getId() + "," + status.getText() + " END");

	}

    }

}
