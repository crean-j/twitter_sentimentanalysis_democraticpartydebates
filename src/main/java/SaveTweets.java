import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/*
 * 
 * This class saves tweets to a text file as we can only go back 7 days in history
 */

public class SaveTweets {
    ArrayList<Tweet> queryResult = new ArrayList<>(); // Stores the result of latest search
    ArrayList<String> tweetsAlreadyStored = new ArrayList<>(); // Stores the tweets that are saved in the txt file
    // Date stamp and file name to save the tweets
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
    static Date dateForFile = new Date();
 //   static private Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // Creates a new timestamp
    private static String filename = "Tweet_Analysis_" + dateFormat.format(dateForFile) + ".txt";

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
     * 
     */
    public void saveToFile() {
	// read file with save tweets into an ArrayList and stores the tweet ID. This section of the code is used when creating the 
	try {
	    Scanner in;
	    File myFile = new File(filename);
	    in = new Scanner(myFile);
	    while (in.hasNextLine()) {
		String[] lineInFile = in.nextLine().split("@@@");
		tweetsAlreadyStored.add(lineInFile[0]);
	    }
	    in.close();
	} catch (FileNotFoundException e1) {
	    try {
		FileWriter fw;
		fw = new FileWriter(filename, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.flush();
		pw.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    System.out.println("A file called " + filename + " will be created.");
	}

	// Loops the result of the last search and adds to the txt file the tweets that
	// are not already there
	for (Tweet tw : queryResult) {
	    if (!tweetsAlreadyStored.contains(String.valueOf(tw.getId()))) {
		try {
		    FileWriter fw = new FileWriter(filename, true);
		    PrintWriter pw = new PrintWriter(fw);
		    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		    pw.println(tw.getId() + "@@@" + timestamp + "@@@" + tw.getCandidate() + "@@@" + tw.getUser() + "@@@"
			    + tw.getLocation() + "@@@" + tw.getNumberOfFollowers() + "@@@"
			    + tw.getTextInTweet().replaceAll("\\n*", "") + "@@@" + tw.getLocation() + "@@@"
			    + tw.getRetweetedCount() + "@@@" + tw.getInfluenceScore() + "@@@" + tw.getTweetDate()
			    + "@@@" + tw.getSentimentScore() + "@@@" + tw.isRetweet() + "@@@" + tw.getGeoLocation());
		    pw.flush();
		    pw.close();
		} catch (IOException e) {
		    System.out.println("Error writing to the file.");
		    e.printStackTrace();
		}

	    }
	}
    }

    /*
     * author: Federica Pelzel the loadfile method reads in a csv with tweets and
     * adds them to an ArrayList of Tweets
     */

    static public ArrayList<Tweet> loadFile(String fileToRead) {

	Scanner in;
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	// read tweets file and add to array list
	try {
	    in = new Scanner(new FileReader(fileToRead));

	    // read column headers
	    while (in.hasNextLine()) {
		Tweet parsed = tweetsParse(in.nextLine());
		if (parsed != null) {
		    tweets.add(parsed);
		}
	    }

	    in.close();

	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return tweets;

    }

    /*
     * author: Federica Pelzel the tweetsParse method parses each row in the file an
     * creates Tweet Objects from them
     */
    static public Tweet tweetsParse(String row) {

	String[] rowArr = row.split("@@@");
	try {
	    long id = rowArr[0].length() == 0 ? -1 : Long.parseLong(rowArr[0]);
	    String user = rowArr[3];
	    int numberOfFollowers = rowArr[5].length() == 0 ? -1 : Integer.parseInt(rowArr[5]);
	    String location = rowArr[4];
	    String textInTweet = rowArr[6];
	    SimpleDateFormat simpleDate = new SimpleDateFormat("EEE MMM d HH:mm:ss z YYYY");
	    Date tweetDate = null;
	    tweetDate = simpleDate.parse(rowArr[10]);
	    String candidate = rowArr[2];
	    double influenceScore = rowArr[9].length() == 0 ? -1 : Double.parseDouble(rowArr[8]);
	    int retweetedCount = rowArr[8].length() == 0 ? -1 : Integer.parseInt(rowArr[8]);
	    double sentimentScore = rowArr[11].length() == 0 ? -1 : Double.parseDouble(rowArr[11]);
	    boolean isRetweet = rowArr[12].equals("true");

	    Tweet t = new Tweet(id, user, numberOfFollowers, location, textInTweet, tweetDate, candidate,
		    influenceScore, retweetedCount, isRetweet, null);

	    return t;

	} catch (Exception e) {
	    return null;
	}
    }
}
