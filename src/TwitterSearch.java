/**
 * 
 */
/**
 * @author juangoleniowski
 *
 */


import java.util.*;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSearch {

    public static void main(String[] args) throws TwitterException {

	ConfigurationBuilder cf = new ConfigurationBuilder();

	cf.setDebugEnabled(true).setOAuthConsumerKey("4Exfj9KQmr7Pz7N3SmCNk1dAm")
		.setOAuthConsumerSecret("6Afvk6erTYLgbg89ME4awpSPr2KGX6Iydpo3AMhKGxa0GZqg3T")
		.setOAuthAccessToken("608176121-EWBAl62ps6D0w7bgcoQsW54yTaEQziXUeBJ7qMbs")
		.setOAuthAccessTokenSecret("VYiXELoFmdn6Bkl3f9vxRtiWf9ceLZQg53khMRbS6P7uX");

	TwitterFactory tf = new TwitterFactory(cf.build());

	twitter4j.Twitter twitter = tf.getInstance();

	
	    Query query = new Query("Warren");
	    QueryResult result = twitter.search(query);
	    for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getUser().getFollowersCount() +  ":" + status.getText());
	        
	    }
	
    }

}
