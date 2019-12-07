/**
 * @author: Federica Pelzel
 * 
 * This class creates an ArrayList per state in a HashMap with the state name as Key
 * It also creates natural language conditions for location, and stores tweets from each location
 * in the corresponding ArrayList
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

public class TweetsByState {

	ArrayList<Tweet> tweets;
	// Create HashMap with key State code and Value ArrayList of tweets for that state
	HashMap<String, ArrayList<Tweet>> states = new HashMap<String, ArrayList<Tweet>>();

	/**
	 * TweetsByState takes in an ArrayList of Tweet objects, looks at their location field,
	 * analyzes location through a matching algorithm, and stores tweets in ArrayLists by state.
	 * 
	 * @param tweets
	 */
	
	public TweetsByState(ArrayList<Tweet> tweets) {
		this.tweets = tweets;
		// Populate states hashmap and create arraylists of tweets for each
		states.put("AL", new ArrayList<Tweet>());
		states.put("AK", new ArrayList<Tweet>());
		states.put("AS", new ArrayList<Tweet>());
		states.put("AZ", new ArrayList<Tweet>());
		states.put("AR", new ArrayList<Tweet>());
		states.put("CA", new ArrayList<Tweet>());
		states.put("CO", new ArrayList<Tweet>());
		states.put("CT", new ArrayList<Tweet>());
		states.put("DE", new ArrayList<Tweet>());
		states.put("DC", new ArrayList<Tweet>());
		states.put("FL", new ArrayList<Tweet>());
		states.put("GA", new ArrayList<Tweet>());
		states.put("GU", new ArrayList<Tweet>());
		states.put("HI", new ArrayList<Tweet>());
		states.put("IA", new ArrayList<Tweet>());
		states.put("ID", new ArrayList<Tweet>());
		states.put("IL", new ArrayList<Tweet>());
		states.put("IN", new ArrayList<Tweet>());
		states.put("KS", new ArrayList<Tweet>());
		states.put("KY", new ArrayList<Tweet>());
		states.put("LA", new ArrayList<Tweet>());
		states.put("ME", new ArrayList<Tweet>());
		states.put("MD", new ArrayList<Tweet>());
		states.put("MA", new ArrayList<Tweet>());
		states.put("MI", new ArrayList<Tweet>());
		states.put("MN", new ArrayList<Tweet>());
		states.put("MS", new ArrayList<Tweet>());
		states.put("MO", new ArrayList<Tweet>());
		states.put("MT", new ArrayList<Tweet>());
		states.put("NE", new ArrayList<Tweet>());
		states.put("NV", new ArrayList<Tweet>());
		states.put("NH", new ArrayList<Tweet>());
		states.put("NJ", new ArrayList<Tweet>());
		states.put("NM", new ArrayList<Tweet>());
		states.put("NY", new ArrayList<Tweet>());
		states.put("NC", new ArrayList<Tweet>());
		states.put("ND", new ArrayList<Tweet>());
		states.put("OH", new ArrayList<Tweet>());
		states.put("OK", new ArrayList<Tweet>());
		states.put("OR", new ArrayList<Tweet>());
		states.put("PA", new ArrayList<Tweet>());
		states.put("PR", new ArrayList<Tweet>());
		states.put("RI", new ArrayList<Tweet>());
		states.put("SC", new ArrayList<Tweet>());
		states.put("SD", new ArrayList<Tweet>());
		states.put("TN", new ArrayList<Tweet>());
		states.put("TX", new ArrayList<Tweet>());
		states.put("UT", new ArrayList<Tweet>());
		states.put("VT", new ArrayList<Tweet>());
		states.put("VI", new ArrayList<Tweet>());
		states.put("VA", new ArrayList<Tweet>());
		states.put("WA", new ArrayList<Tweet>());
		states.put("WV", new ArrayList<Tweet>());
		states.put("WI", new ArrayList<Tweet>());
		states.put("WY", new ArrayList<Tweet>());

		// iterate through tweets and add to the corresponding state ArrayList
		for (Tweet t : tweets) {
			if (t.getLocation().contains("Alaska") || t.getLocation().contains("AK")
					|| t.getLocation().contains("alaska")) {
				states.get("AK").add(t);
			}
			if (t.getLocation().contains("Alabama") || t.getLocation().contains("AL")
					|| t.getLocation().contains("alabama")) {
				states.get("AL").add(t);
			}

			if (t.getLocation().contains("Arkansas") || t.getLocation().contains("AR")
					|| t.getLocation().contains("arkansas")) {
				states.get("AR").add(t);
			}

			if (t.getLocation().contains("American Samoa") || t.getLocation().contains("AS")
					|| t.getLocation().contains("Samoa")) {
				states.get("AS").add(t);
			}

			if (t.getLocation().contains("Arizona") || t.getLocation().contains("AZ")
					|| t.getLocation().contains("arizona")) {
				states.get("AZ").add(t);
			}

			if (t.getLocation().contains("California") || t.getLocation().contains("CA")
					|| t.getLocation().contains("california")) {
				states.get("CA").add(t);
			}

			if (t.getLocation().contains("Colorado") || t.getLocation().contains("CO")
					|| t.getLocation().contains("colorado")) {
				states.get("CO").add(t);
			}

			if (t.getLocation().contains("Connecticut") || t.getLocation().contains("CT")
					|| t.getLocation().contains("connecticut")) {
				states.get("CT").add(t);
			}

			if (t.getLocation().contains("District of Columbia") || t.getLocation().contains("DC")
					|| t.getLocation().contains("Washington DC")) {
				states.get("DC").add(t);
			}

			if (t.getLocation().contains("Delaware") || t.getLocation().contains("DE")
					|| t.getLocation().contains("delaware")) {
				states.get("DE").add(t);
			}

			if (t.getLocation().contains("Florida") || t.getLocation().contains("FL")
					|| t.getLocation().contains("florida")) {
				states.get("FL").add(t);
			}

			if (t.getLocation().contains("Georgia") || t.getLocation().contains("GA")
					|| t.getLocation().contains("georgia")) {
				states.get("GA").add(t);
			}

			if (t.getLocation().contains("Guam") || t.getLocation().contains("GU")
					|| t.getLocation().contains("guam")) {
				states.get("GU").add(t);
			}

			if (t.getLocation().contains("Hawaii") || t.getLocation().contains("HI")
					|| t.getLocation().contains("hawaii")) {
				states.get("HI").add(t);
			}
			if (t.getLocation().contains("Idaho") || t.getLocation().contains("ID")
					|| t.getLocation().contains("idaho")) {
				states.get("ID").add(t);
			}

			if (t.getLocation().contains("Iowa") || t.getLocation().contains("IA")
					|| t.getLocation().contains("iowa")) {
				states.get("IA").add(t);
			}

			if (t.getLocation().contains("Indiana") || t.getLocation().contains("IN")
					|| t.getLocation().contains("indiana")) {
				states.get("IN").add(t);
			}

			if (t.getLocation().contains("Illinois") || t.getLocation().contains("IL")
					|| t.getLocation().contains("illinois")) {
				states.get("IL").add(t);
			}

			if (t.getLocation().contains("Kansas") || t.getLocation().contains("KS")
					|| t.getLocation().contains("kansas")) {
				states.get("KS").add(t);
			}

			if (t.getLocation().contains("Kentucky") || t.getLocation().contains("KY")
					|| t.getLocation().contains("kentucky")) {
				states.get("KY").add(t);
			}

			if (t.getLocation().contains("Louisiana") || t.getLocation().contains("LA")
					|| t.getLocation().contains("louisiana")) {
				states.get("LA").add(t);
			}

			if (t.getLocation().contains("Massachusetts") || t.getLocation().contains("MA")
					|| t.getLocation().contains("massachusetts")) {
				states.get("MA").add(t);
			}

			if (t.getLocation().contains("Maryland") || t.getLocation().contains("MD")
					|| t.getLocation().contains("maryland")) {
				states.get("MD").add(t);
			}

			if (t.getLocation().contains("Maine") || t.getLocation().contains("ME")
					|| t.getLocation().contains("maine")) {
				states.get("ME").add(t);
			}

			if (t.getLocation().contains("Michigan") || t.getLocation().contains("MI")
					|| t.getLocation().contains("michigan")) {
				states.get("MI").add(t);
			}

			if (t.getLocation().contains("Minnesota") || t.getLocation().contains("MN")
					|| t.getLocation().contains("minnesota")) {
				states.get("MN").add(t);
			}

			if (t.getLocation().contains("Missouri") || t.getLocation().contains("MO")
					|| t.getLocation().contains("missouri")) {
				states.get("MO").add(t);
			}

			if (t.getLocation().contains("Mississippi") || t.getLocation().contains("MS")
					|| t.getLocation().contains("mississippi")) {
				states.get("MS").add(t);
			}

			if (t.getLocation().contains("Montana") || t.getLocation().contains("MT")
					|| t.getLocation().contains("montana")) {
				states.get("MT").add(t);
			}

			if (t.getLocation().contains("North Carolina") || t.getLocation().contains("NC")
					|| t.getLocation().contains("north carolina")) {
				states.get("NC").add(t);
			}

			if (t.getLocation().contains("North Dakota") || t.getLocation().contains("ND")
					|| t.getLocation().contains("north dakota")) {
				states.get("ND").add(t);
			}

			if (t.getLocation().contains("Nebraska") || t.getLocation().contains("NE")
					|| t.getLocation().contains("nebraska")) {
				states.get("NE").add(t);
			}

			if (t.getLocation().contains("New Hampshire") || t.getLocation().contains("NH")
					|| t.getLocation().contains("new hampshire")) {
				states.get("NH").add(t);
			}

			if (t.getLocation().contains("New Jersey") || t.getLocation().contains("NJ")
					|| t.getLocation().contains("Jersey")) {
				states.get("NJ").add(t);
			}

			if (t.getLocation().contains("New Mexico") || t.getLocation().contains("NM")
					|| t.getLocation().contains("new mexico")) {
				states.get("NM").add(t);
			}

			if (t.getLocation().contains("Nevada") || t.getLocation().contains("NV")
					|| t.getLocation().contains("nevada")) {
				states.get("NV").add(t);
			}

			if (t.getLocation().contains("New York") || t.getLocation().contains("NY")
					|| t.getLocation().contains("new york")) {
				states.get("NY").add(t);
			}

			if (t.getLocation().contains("Ohio") || t.getLocation().contains("OH")
					|| t.getLocation().contains("ohio")) {
				states.get("OH").add(t);
			}

			if (t.getLocation().contains("Oklahoma") || t.getLocation().contains("OK")
					|| t.getLocation().contains("oklahoma")) {
				states.get("OK").add(t);
			}

			if (t.getLocation().contains("Oregon") || t.getLocation().contains("OR")
					|| t.getLocation().contains("oregon")) {
				states.get("OR").add(t);
			}

			if (t.getLocation().contains("Pennsylvania") || t.getLocation().contains("PA")
					|| t.getLocation().contains("pennsylvania")) {
				states.get("PA").add(t);
			}

			if (t.getLocation().contains("Puerto Rico") || t.getLocation().contains("PR")
					|| t.getLocation().contains("puerto rico")) {
				states.get("PR").add(t);
			}

			if (t.getLocation().contains("Rhode Island") || t.getLocation().contains("RI")
					|| t.getLocation().contains("rhode island")) {
				states.get("RI").add(t);
			}

			if (t.getLocation().contains("South Carolina") || t.getLocation().contains("SC")
					|| t.getLocation().contains("south carolina")) {
				states.get("SC").add(t);
			}

			if (t.getLocation().contains("South Dakota") || t.getLocation().contains("SD")
					|| t.getLocation().contains("south dakota")) {
				states.get("SD").add(t);
			}

			if (t.getLocation().contains("Tennessee") || t.getLocation().contains("TN")
					|| t.getLocation().contains("tennessee")) {
				states.get("TN").add(t);
			}

			if (t.getLocation().contains("Texas") || t.getLocation().contains("TX")
					|| t.getLocation().contains("texas")) {
				states.get("TX").add(t);
			}

			if (t.getLocation().contains("Utah") || t.getLocation().contains("UT")
					|| t.getLocation().contains("utah")) {
				states.get("UT").add(t);
			}

			if (t.getLocation().contains("Virginia") || t.getLocation().contains("VA")
					|| t.getLocation().contains("virginia")) {
				states.get("VA").add(t);
			}

			if (t.getLocation().contains("Virgin Islands") || t.getLocation().contains("VI")
					|| t.getLocation().contains("virgin islands")) {
				states.get("VI").add(t);
			}

			if (t.getLocation().contains("Vermont") || t.getLocation().contains("VT")
					|| t.getLocation().contains("vermont")) {
				states.get("VT").add(t);
			}

			if (t.getLocation().contains("Washington State") || t.getLocation().contains("WA")
					|| t.getLocation().contains("Washington state")) {
				states.get("WA").add(t);
			}

			if (t.getLocation().contains("Wisconsin") || t.getLocation().contains("WI")
					|| t.getLocation().contains("wisconsin")) {
				states.get("WI").add(t);
			}

			if (t.getLocation().contains("West Virginia") || t.getLocation().contains("WV")
					|| t.getLocation().contains("west virginia")) {
				states.get("WV").add(t);
			}

			if (t.getLocation().contains("Wyoming") || t.getLocation().contains("WY")
					|| t.getLocation().contains("wyoming")) {
				states.get("WY").add(t);
			}

			else
				continue;
		}
	}

}
