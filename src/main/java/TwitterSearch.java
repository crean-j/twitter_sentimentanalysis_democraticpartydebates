package main.java;

import java.util.*;

import twitter4j.Paging;
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
    private String date;

    /**
     * Constructor
     */
    public TwitterSearch(String candidate, String date) {
	this.candidate = candidate;
	this.date = date;
    }

    /**
     * This class executes the search for the candidates selected and populates an
     * ArrayList with the result
     *
     * @return Arraylist with tweets
     */
    public ArrayList<Tweet> mainSearch() {

	// Creates Twitter instance
	ConfigurationBuilder cf = new ConfigurationBuilder().setTweetModeExtended(true);
	Twitter twitter = TwitterFactory.getSingleton();
	TwitterFactory tf = new TwitterFactory(cf.build());
	ArrayList<Tweet> queryResult = new ArrayList<>(); // Stores tweet objects as elements
	long lastTweetMaxId = -1; // tracks the latest tweet retrieved
	int MAXSEARCHREQUESTS = 20;

	// Runs the query for the candidate and the date range
	Query query = new Query(candidate);
	query.setSince(sinceDate(date));
	query.setUntil(toDate(date));
	query.count(100);
	QueryResult result;

	for (int numberOfQueries = 0; numberOfQueries < MAXSEARCHREQUESTS; numberOfQueries++) {
	    // Sets the last tweet ID retrieved
	    if (lastTweetMaxId != -1) {
		query.setMaxId(lastTweetMaxId - 1);
	    }

	    try {
		result = twitter.search(query);
		System.out.println(result.getRateLimitStatus());
		// Checks if there are still tweets to retrieve
		if (result.getTweets().size() == 0) {
		    return queryResult;
		}
		for (Status status : result.getTweets()) {
		    String text = status.getRetweetedStatus() != null ? status.getRetweetedStatus().getText()
			    : status.getText();
		    // Added if condition to check the candidate name is in the main text
		    if (text.contains(candidate)) {
			// Updates the highest ID in the tweets retrieved
			if (lastTweetMaxId == -1 || status.getId() < lastTweetMaxId) {
			    lastTweetMaxId = status.getId();
			}
			Tweet tw = new Tweet(status.getId(), status.getUser(), status.getUser().getFollowersCount(),
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
	}
	return queryResult;

    }

    /**
     * Calculates the date seven days prior to the date entered
     *
     * @param year  year entered by the user
     * @param month month entered by the user
     * @param day   day entered by the user
     * @return a string in the format YYYY-MM-DD
     */

    public String sinceDate(String date) { // Set as public for JUnit test
	int year = Integer.valueOf(date.substring(0, 4));
	int month = Integer.valueOf(date.substring(5, 7));
	int day = Integer.valueOf(date.substring(8, 10));

	day = day - 7;
	if (day <= 0) {
	    month--;
	}
	if (month <= 0) {
	    year--;
	    month = 12;
	}
	if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		&& day <= 0) {
	    day = day + 31;
	}
	if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 0) {
	    day = day + 30;
	}

	if (month == 2 && day <= 0 && year == 2019) {
	    day = day + 28;
	}
	if (month == 2 && day <= 0 && year == 2020) {
	    day = day + 29;
	}

	return year + "-" + (month <= 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
    }

    /**
     * Calculates the date seven days after the date entered
     *
     * @param year  year entered by the user
     * @param month month entered by the user
     * @param day   day entered by the user
     * @return a string in the format YYYY-MM-DD
     */
    public String toDate(String date) { // Set as public for JUnit test
	int year = Integer.valueOf(date.substring(0, 4));
	int month = Integer.valueOf(date.substring(5, 7));
	int day = Integer.valueOf(date.substring(8, 10));

	day = day + 7;

	if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		&& day > 31) {
	    day = day - 31;
	    month++;
	}
	if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
	    day = day - 30;
	    month++;
	}

	if (month == 2 && day >= 29 && year == 2019) {
	    day = day - 28;
	    month++;
	}
	if (month == 2 && day >= 30 && year == 2020) {
	    day = day - 29;
	    month++;
	}

	if (month >= 13) {
	    year = year + 1;
	    month = 1;
	}

	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
    }
}
