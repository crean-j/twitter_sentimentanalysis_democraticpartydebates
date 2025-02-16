import java.util.*;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This class handles the queries to Twitter
 *
 * @author juangoleniowski
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
        ArrayList<Tweet> queryResult = new ArrayList<>(); // Stores tweet objects as elements in an array
        long lastTweetMaxId = -1; // tracks the latest tweet retrieved
        final int MAXSEARCHREQUESTS = 10; // Number of requests to be sent (API allows up to 180 requests every 15
        // minutes)

        // Runs the query for the candidate and the date range
        Query query = new Query(candidate); // Search term
        query.setSince(sinceDate(date)); // Lower limit date for the search
        query.setUntil(toDate(date)); // Upper limit date for the search
        query.count(100);// Number of tweets to be retrieved in each request (max 100)
        query.lang("en");// Limits search to tweets in English
        QueryResult result;

        // Run the search as many times as set in MAXSEARCHREQUESTS
        for (int numberOfQueries = 0; numberOfQueries < MAXSEARCHREQUESTS; numberOfQueries++) {

            // Sets the last tweet ID retrieved, so we retrieve a different batch of tweets
            // in every search
            if (lastTweetMaxId != -1) {
                query.setMaxId(lastTweetMaxId - 1);
            }

            // Executes the search
            try {
                result = twitter.search(query);

                // Checks if there are still any tweets to retrieve or if we don't have any
                // requests left
                if (result.getTweets().size() == 0 || result.getRateLimitStatus().getRemaining() == 0) {
                    System.out.println("No more tweets left to retrieve.");
                    return queryResult;
                }

                // If tweet has extended text, retrieves text over 140 characters
                for (Status status : result.getTweets()) {
                    String text = status.getRetweetedStatus() != null ? status.getRetweetedStatus().getText()
                            : status.getText();
                    // Added condition to check that the candidate name is in the main text and to
                    // excludes retweets
                    if (text.contains(candidate) && !status.isRetweet()) {
                        // Updates the highest ID in the tweets retrieved
                        if (lastTweetMaxId == -1 || status.getId() < lastTweetMaxId) {
                            lastTweetMaxId = status.getId();
                        }
                        // Populates the tweet object with the search result
                        Tweet tw = new Tweet(status.getId(), (String) status.getUser().getName(),
                                status.getUser().getFollowersCount(), status.getUser().getLocation(), text,
                                status.getCreatedAt(), candidate, 0, status.getRetweetCount(), status.isRetweet(),
                                status.getGeoLocation());
                        queryResult.add(tw);
                    }
                }

            } catch (TwitterException e) {
                if (e.exceededRateLimitation()) {
                    // Catches if we have exceeded the 180 searches limit and shows how long we have
                    // to wait to run a new query
                    System.out.println("Search rate exceeded. Please wait "
                            + e.getRateLimitStatus().getSecondsUntilReset() + " seconds and try again.");
                    System.exit(0);
                }
            } catch (NullPointerException f) {
                System.out.println("The search did not retrieve any results. Please try again.");
            }
        }
        //System.out.println(queryResult.size()); shows the query size, commenting out unless needed for debugging
        return queryResult;

    }

    /**
     * Calculates the date seven days prior to the date entered
     *
     * @param date string
     * @return a string in the format YYYY-MM-DD
     */

    public String sinceDate(String date) { // Set as public for JUnit test
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));

        day = day - 7; // Seven days prior to the date entered
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
     * Calculates the date one day after the date entered
     *
     * @param date string
     * @return a string in the format YYYY-MM-DD
     */
    public String toDate(String date) { // Set as public for JUnit test
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));

        day = day + 1;// One day after entered date

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
