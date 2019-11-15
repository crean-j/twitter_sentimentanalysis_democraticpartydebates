package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import twitter4j.Status;

/*
 * Test Class
 * Save tweets to a text file as we can only go back 7 in history
 */

public class SaveTweets {
    ArrayList<Tweet> tweet = new ArrayList<>();
    HashMap <Long, Tweet> tweetArchive = new HashMap<>();
    String tweetLine;

    public SaveTweets(ArrayList<Tweet> tweet) {
	this.tweet = tweet;
    }

    public void saveToFile() {
	// read file an HashMap
	Scanner in;
	File myFile = new File ("TweetArchive.txt");
	in = new Scanner(System.in);
	while (in.hasNextLine()) {
	    tweetLine = in.nextLine();
	}
	
	
	
	
	
	
	try {
	    FileWriter fw = new FileWriter("TweetArchive.txt", true);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	for (Tweet tw : tweet) {
	    System.out.println(tw.getId());
	}
	// FileWriter f = new FileWriter("tweetDownload.txt", false);

    }

}