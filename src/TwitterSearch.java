
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
    private String candidate;
    private int year, month, day;  
    
    /**
     * Constructor
     */
    public TwitterSearch(String candidate, int year, int month, int day) {
	this.candidate = candidate;
	this.year = year;	
	this.month = month;
	this.day = day;
    }

    /**
     * This class executes the search for the candidates selected and populates an
     * ArrayList with the result
     * 
     * @return
     */
    public ArrayList<Tweet> MainSearch() {

	// Creates Twitter instance
	ConfigurationBuilder cf = new ConfigurationBuilder().setTweetModeExtended(true);
	Twitter twitter = TwitterFactory.getSingleton();
	TwitterFactory tf = new TwitterFactory(cf.build());
	ArrayList<Tweet> queryResult = new ArrayList<>();

	// Runs the query for candidate
	// Pending to add dates to the query
	Query query = new Query(candidate);
	QueryResult result;
	try {
	    result = twitter.search(query.count(100));
	    for (Status status : result.getTweets()) {
		String text = status.getRetweetedStatus() != null ? status.getRetweetedStatus().getText()
			: status.getText();
		//Added if condition to check the candidate name is in the main text
		if (text.contains(candidate)) {
		Tweet tw = new Tweet(status.getUser(), status.getUser().getFriendsCount(),
			status.getUser().getLocation(), text, status.getCreatedAt(), candidate, 0,
			status.getRetweetCount());
		queryResult.add(tw);
	    }
	    }

	} catch (TwitterException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NullPointerException f) {
	    f.printStackTrace();
	}
	return queryResult;

    }

}
