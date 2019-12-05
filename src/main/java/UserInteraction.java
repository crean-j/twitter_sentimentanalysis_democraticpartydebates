import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.*;

/**
 * This class handles the interaction with the user and validation of the data
 * entered
 *
 * @author juangoleniowski
 *
 */
public class UserInteraction {
    private String candidate; // Candidate to be searched
    private int year, month, day; // Date of the event
    private String date, eventLabel; // Event name

    // Constructor
    public UserInteraction() {
	selectSearchTerm();
    }

    /**
     * Ask user to select a candidate and a date. Pending to include code to label
     * the event
     */
    public void selectSearchTerm() {
	Scanner scanner = new Scanner(System.in);
	System.out.println("\tEnter a word to search and do a sentiment analysis: ");
	candidate = scanner.nextLine();
	System.out.println("\t\t\t *****\tYou have selected " + candidate + "\t*****\n");
	System.out.println("\t We are going to search for tweets starting from today's date and go back as far as possible, up to seven days");
	scanner.close();
    }
	    /**
	     * @return the date in format YYYY-MM-DD
	     */
	    public String getDate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return ("" + timestamp).substring(0,11);
	    }

	/**
	 * @return the candidate
	 */
	public String getCandidate() {
	    return candidate;
	}
    }
