package main.java;

public class DemDebate {

	public static void main(String[] args) {
		//Create a twitter search for each candidate at the debate date
		//Need to adjust to consume from file
		TwitterSearch tsWarren = new TwitterSearch("Warren", "20191120");
		TwitterSearch tsSanders = new TwitterSearch("Sanders", "20191120");
		TwitterSearch tsBiden = new TwitterSearch("Biden", "20191120");
		TwitterSearch tsPete = new TwitterSearch("Buttigieg", "20191120");
		
		
		
		//Create a csv with total tweets per candidate per day or hour (Date, Time?, Candidate, number of tweets)
		//Create a csv with average sentiment per candidate per day or hour (Date, Time?, Candidate, average sentiment)
		//Create a csv with sentiment per state before debate (Candidate, State, Average Sentiment)
		//Create a csv with sentiment per state after debate (Candidate, State, Average Sentiment)
		//Create a csv with most used adjectives per candidate (Candidate, word, number of times seen, sentiment score)
		//return most changed in average sentiment before and after
		//return followers gained for each candidate
		
		
		
	}
}
