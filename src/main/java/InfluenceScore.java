package main.java;
import java.util.ArrayList;
import twitter4j.User;

/**
 * This class will calculate an influence score for each tweet
 */
public class InfluenceScore {
    private int maxFollowers = 0;
    private int minFollowers = 0;
    private int maxRetweets = 0;
    private int minRetweets = 0;

    //Constructor
    public InfluenceScore(ArrayList<Tweet> result) {
        calculationOfTheInfluenceScore(result);
    }

<<<<<<< HEAD
    /**
     * This class calculates and updates an ArrayList with the score
     * @param result ArrayList with the Tweets that match the search term
     */
=======
    //
>>>>>>> c6ac6e8a084204e8da54c6e3be0add15228ee701
    private void calculationOfTheInfluenceScore (ArrayList<Tweet> result) {
        for (int i = 0 ; i<result.size() ; i++) {
            maxFollowers = maxFollowers < result.get(i).getNumberOfFollowers() ? result.get(i).getNumberOfFollowers() : maxFollowers;
            minFollowers = minFollowers > result.get(i).getNumberOfFollowers() ? result.get(i).getNumberOfFollowers() : minFollowers;
            maxRetweets = maxRetweets < result.get(i).getRetweetedCount() ? result.get(i).getRetweetedCount() : maxRetweets;
            minRetweets = minRetweets > result.get(i).getRetweetedCount() ? result.get(i).getRetweetedCount() : minRetweets;
        }
        for (int i = 0 ; i<result.size() ; i++) {
            int followersScore = (result.get(i).getNumberOfFollowers()-minFollowers)/(maxFollowers - minFollowers);
            int retweetsScore = (result.get(i).getRetweetedCount()-minRetweets)/(maxRetweets - minRetweets);
            result.get(i).setInfluenceScore((followersScore+retweetsScore)/2.0);
        }
    }
}

