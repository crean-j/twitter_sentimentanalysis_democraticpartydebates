
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
    int year, month, day;

    /**
     * Ask user to select a candidate and a date.
     */
    public void selectSearchTerm() {
	String candidate = null;
	Scanner scanner = new Scanner(System.in);
	System.out.println("Select one of the candidatess to search: ");
	System.out.println(
		" 1. Elizabeth Warren \n 2. Bernie Sanders\n 3. Joe Biden\n 4. Michael Bloomberg\n 5. Pete Buttigieg ");
	String optionSelected = scanner.nextLine();
	while (!optionSelected.equals("1") && !optionSelected.equals("2") && !optionSelected.equals("3")
		&& !optionSelected.equals("4") && !optionSelected.equals("5")) {
	    System.out.println("Incorrect selection. Please select only a number between 1 and 5");
	    optionSelected = scanner.nextLine();
	}
	switch (optionSelected) {
	case "1":
	    candidate = "Warren";
	    break;
	case "2":
	    candidate = "Sanders";
	    break;
	case "3":
	    candidate = "Biden";
	    break;
	case "4":
	    candidate = "Bloomberg";
	    break;
	case "5":
	    candidate = "Buttigieg";
	    break;
	}
	System.out.println("You have selected " + candidate + "\n");
	System.out.println("We are going to analyze tweets seven days before and after the date selected. \n"
		+ "For year, options are 2019 or 2020 only.\nEnter the date in format YYYYMMDD. "
		+ "For example, for September 6th, 2019, enter 20190906): ");
	do {
	    optionSelected = scanner.nextLine();
	    parseDate(optionSelected);
	    if (optionSelected.length() != 8) {
		System.out.println("Incorrect date entered. Please try again");
	    }
	} while (!validateDate(year, month, day) && optionSelected.length() != 8);
    }

    /**
     * This class parses the date entered into year, month and day
     * 
     * @param optionSelected date in format YYYYMMDD
     */
    private void parseDate(String optionSelected) {
	year = Integer.valueOf(optionSelected.substring(0, 4));
	month = Integer.valueOf(optionSelected.substring(4, 6));
	day = Integer.valueOf(optionSelected.substring(6, 8));
    }

    /**
     * This class validates the data entered by the user
     * @param year year entered by the user
     * @param month month entered by the user
     * @param day day entered by the user
     * @return true if date is valid, false otherwise
     */
    private boolean validateDate(int year, int month, int day) {
	if (year != 2019 && year != 2020) {
	    System.out.println("Incorrect year entered. Please enter the date again: ");
	    return false;
	}
	if (month < 1 || month > 12) {
	    System.out.println("Incorrect month entered. Please enter the date again: ");
	    return false;
	}
	if (day < 1 || day > 31) {
	    System.out.println("Incorrect day entered. Please enter the date again: ");
	    return false;
	}
	if ((month == 4 || month == 6 || month == 9 || month == 1 || month == 8 || month == 10) && day > 30) {
	    System.out.println("Incorrect day entered. Please enter the date again: ");
	    return false;
	}
	if (month == 2 && day > 28) {
	    System.out.println("Incorrect day entered. Please enter the date again: ");
	    return false;
	}
	return true;
    }

    /**
     * This class executes the search for the candidates selected and populates a
     * HashMap with the result
     * 
     * @return
     */
    public HashMap<Integer, Tweet> MainSearch(String candidate, int year, int month, int day) {
	// Creates Twitter instance
	ConfigurationBuilder cf = new ConfigurationBuilder().setTweetModeExtended(true);
	Twitter twitter = TwitterFactory.getSingleton();
	TwitterFactory tf = new TwitterFactory(cf.build());
	HashMap<Integer, Tweet> queryResult = new HashMap<>();

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
	    System.out.println(queryResult.get(key).getUser());
	}
	return queryResult;

    }

}
