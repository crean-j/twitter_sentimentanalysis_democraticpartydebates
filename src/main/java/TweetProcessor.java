//package main.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * Class takes tweet text and cleans it up for natural language processing
 * It includes methods to:
 * Lowercase the text
 * Remove noise from text
 *
 * @author joannecrean
 */

public class TweetProcessor {

    /**
     * Constructor sets up the Tweet Processor
     */
    public TweetProcessor() { }

    /**
     * Method removes whitespace and makes the text lower case
     * @return String of text
     */
    public String cleanText(String text) {

        // remove whitespace
        String trimText = text.trim();

        // to lowercase
        return trimText.toLowerCase();
    }

    /**
     * Method removes noise from text
     * @return String of text
     */
    /*public String removeNoise(String text) {
    	String finalText = text;
    	//finalText.replace(/(?:https?|ftp):\/\/[\n\S]+/g, "") // remove links
        //.replace(/\#\w\w+\s?/g,"") remove hashtags words
        if (finalText.contains("RT")) {
        	finalText.; // remove hashtags only
        	System.out.println(finalText);
        	System.out.println("true");
        }
        //System.out.println(finalText);
        //.replace(/\@\w\w+\s?/g, ''), newChar)
    	//String[] splitter = finalText.split("\\|\\|\\|");
    	//System.out.println(Arrays.deepToString(splitter));
    	//url identifier regex
    	String urlIdentifier = "((http|ftp|https):\\/\\/)?[\\w\\-_]+"
    			+ "(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
    	//Removes URL's, RT, Twitter usernames and any non alpha numeric character
        String[] removeNoise = {"RT", urlIdentifier, "(?:\\s|\\A)[@]+([A-Za-z0-9-_]+)", "[^a-zA-Z0-9 ]"};
        System.out.println(removeNoise.toString());
        for (String noise : removeNoise) {
        	/*if (splitter.) {
        		finalText.replace(noise, "");
        		System.out.println(finalText);
        	}
        }
        return finalText;
      }


    	
    	
        /*remove html markup
        // method needs more work
        String nohtml = text.replace("(<.*?>)","");

        // remove non-ascii and digits
        // method needs more work

        return nohtml.replace("(\\W|\\d)","");
    }*/
    
    /*public static void main(String[] args) {
    	TweetProcessor tp = new TweetProcessor();
        String test = "RT This made my day; glad @JeremyKappell is standing up against #ROC’s disgusting mayor. "
        		+ "Former TV meteorologist Jeremy Kappell suing Mayor Lovely Warren"
        		+ "https://t.co/rJIV5SN9vB (Via NEWS 8 WROC)";
        String finalText = tp.removeNoise(test);
        System.out.println(finalText); 	
    }*/
}
