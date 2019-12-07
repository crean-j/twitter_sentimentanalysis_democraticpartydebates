import java.util.ArrayList;

/**
 * This class will calculate an influence score for each tweet
 *
 * @author juangoleniowski
 */
public class InfluenceScore {
    private int maxFollowers = 0;
    private int minFollowers = 0;
    private int maxRetweets = 0;
    private int minRetweets = 0;

    /**
     * constructor
     *
     * @param result array list of tweets
     */
    public InfluenceScore(ArrayList<Tweet> result) {
        calculationOfTheInfluenceScore(result);
    }

    /**
     * This class calculates and updates an ArrayList with the score
     *
     * @param result ArrayList with the Tweets that match the search term
     */

    private void calculationOfTheInfluenceScore(ArrayList<Tweet> result) {
        for (Tweet t : result) {
            maxFollowers = maxFollowers < t.getNumberOfFollowers() ? t.getNumberOfFollowers() : maxFollowers;
            minFollowers = minFollowers > t.getNumberOfFollowers() ? t.getNumberOfFollowers() : minFollowers;
            maxRetweets = maxRetweets < t.getRetweetedCount() ? t.getRetweetedCount() : maxRetweets;
            minRetweets = minRetweets > t.getRetweetedCount() ? t.getRetweetedCount() : minRetweets;
        }
        for (Tweet t : result) {
            double followersScore = (double) (t.getNumberOfFollowers() - minFollowers) / (maxFollowers - minFollowers);
            double retweetsScore = (double) (t.getRetweetedCount() - minRetweets) / (maxRetweets - minRetweets);
            t.setInfluenceScore((followersScore + retweetsScore) / 2.0);
        }

    }
}

