import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.Timestamp;

/*
 * Test Class
 * Save tweets to a text file as we can only go back 7 days in history
 */

public class SaveTweets {
    ArrayList<Tweet> queryResult = new ArrayList<>(); // Stores the result of latest search
    ArrayList<String> tweetsAlreadyStored = new ArrayList<>(); // Stores the tweet that are saved in the txt file
    Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // Creates a new timestamp

    /**
     * Constructor
     * 
     * @param queryResult the result of the search
     */
    public SaveTweets(ArrayList<Tweet> queryResult) {
	this.queryResult = queryResult;
    }

    /**
     * Loads the tweets from the TweetArchive file, then compares the tweet id from
     * the last search and if it can't find it in the TweetArchive file, it adds the
     * whole tweet to the file.
     */
    public void saveToFile() {
	// read file with save tweets into an ArrayList and stores the tweet ID
	try {
	    Scanner in;
	    File myFile = new File("TweetArchive.txt");
	    in = new Scanner(myFile);
	    while (in.hasNextLine()) {
		String[] lineInFile = in.nextLine().split("@@@");
		tweetsAlreadyStored.add(lineInFile[0]);
	    }
	} catch (FileNotFoundException e1) {
	    // TODO Auto-generated catch block
	    try {
		FileWriter fw;
		fw = new FileWriter("TweetArchive.txt", true);
		PrintWriter pw = new PrintWriter(fw);
		pw.flush();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    e1.printStackTrace();

	}

	// Loops the result of the last search and adds to the txt file the tweets that
	// are not already there
	for (Tweet tw : queryResult) {
	    if (!tweetsAlreadyStored.contains(String.valueOf(tw.getId()))) {
		try {
		    FileWriter fw = new FileWriter("TweetArchive.txt", true);
		    PrintWriter pw = new PrintWriter(fw);
		    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		    pw.println(tw.getId() + "@@@" + timestamp + "@@@" + tw.getCandidate() + "@@@"
			    + tw.getUser().getName() + "@@@" + tw.getUser().getLocation() + "@@@"
			    + tw.getNumberOfFollowers() + "@@@" + tw.getTextInTweet().replaceAll("\\n*", "") + "@@@"
			    + tw.getRetweetedCount() + "@@@" + tw.getInfluenceScore() + "@@@" + tw.getSentimentScore());
		    pw.flush();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    System.out.println("Error writing to the file.");
		    e.printStackTrace();
		}

	    }
	}

	// parse csv and add each row to an array list
	public Tweet tweetsParse(String row) {

		String[] rowArr = row.split("@@@");

		String userName = null;
		String userLocation = null;
		//User user = new User();

		// User user = rowArr[0].length() == 0 ? -1 : Integer.parseInt(rowArr[0]);
		int numberOfFollowers = rowArr[5].length() == 0 ? -1 : Integer.parseInt(rowArr[5]);
		String location = rowArr[4];
		String textInTweet = rowArr[6];
		String tweetDate = rowArr[1];
		String candidate = rowArr[2];
		double influenceScore = rowArr[8].length() == 0 ? -1 : Double.parseDouble(rowArr[8]);
		int retweetedCount = rowArr[7].length() == 0 ? -1 : Integer.parseInt(rowArr[7]);
		double sentimentScore = rowArr[9].length() == 0 ? -1 : Double.parseDouble(rowArr[9]);

		Tweet t = new Tweet();

		return t;

	}
}