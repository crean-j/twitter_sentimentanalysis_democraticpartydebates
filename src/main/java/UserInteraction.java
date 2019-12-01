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
	//System.out.println("\t1. Elizabeth Warren\n\t2. Bernie Sanders\n\t3. Joe Biden\n\t4. Michael Bloomberg\n\t5. Pete Buttigieg");
	candidate = scanner.nextLine();
	//String optionSelected = scanner.nextLine();
	//	while (!optionSelected.equals("1") && !optionSelected.equals("2") && !optionSelected.equals("3")
	//		&& !optionSelected.equals("4") && !optionSelected.equals("5")) {
	//	    System.out.println("\nIncorrect selection. Please select only a number between 1 and 5");
	//	    optionSelected = scanner.nextLine();
	//	}
	//	switch (optionSelected) {
	//	case "1":
	//	    candidate = "Warren";
	//	    break;
	//	case "2":
	//	    candidate = "Sanders";
	//	    break;
	//	case "3":
	//	    candidate = "Biden";
	//	    break;
	//	case "4":
	//	    candidate = "Bloomberg";
	//	    break;
	//	case "5":
	//	    candidate = "Buttigieg";
	//	    break;
	//	}
	System.out.println("\t\t\t *****\tYou have selected " + candidate + "\t*****\n");
	System.out.println("\t We are goint to search for tweets starting from today's date and go back as far as possible, up to seven days");
    }
	//	System.out.println("\t We are going to analyze tweets seven days before and after the date selected.");
	//	System.out.println("\t Please note that Tweeter limits the search to seven days from today's date.");
	//	System.out.println("\t Enter the date in format YYYYMMDD.");
	//        System.out.println("\t For example, for May 6th, 2019, enter 20190506).");
	//	do {
	//	    optionSelected = scanner.nextLine();
	//	} while (!validateDate(optionSelected));
	////    }
	//
	//    /**
	//     * This class validates the data entered by the user
	//     *
	//     * @param year  year entered by the user
	//     * @param month month entered by the user
	//     * @param day   day entered by the user
	//     * @return true if date is valid, false otherwise
	//     */
	//    private boolean validateDate(String optionSelected) {
	//	if (!Pattern.matches("\\d{8}", optionSelected)) {
	//	    System.out.println("\n\tIncorrect date entered. Please try again");
	//	    return false;
	//	} else {
	//	    year = Integer.valueOf(optionSelected.substring(0, 4));
	//	    month = Integer.valueOf(optionSelected.substring(4, 6));
	//	    day = Integer.valueOf(optionSelected.substring(6, 8));
	//	}
	//
	//	if (year != 2019 && year != 2020) {
	//	    System.out.println("Incorrect year entered. Please enter the date again: ");
	//	    return false;
	//	}
	//	if (month < 1 || month > 12) {
	//	    System.out.println("Incorrect month entered. Please enter the date again: ");
	//	    return false;
	//	}
	//	if (day < 1 || day > 31) {
	//	    System.out.println("Incorrect day entered. Please enter the date again: ");
	//	    return false;
	//	}
	//	if ((month == 4 || month == 6 || month == 9 || month == 1 || month == 8 || month == 10) && day > 30) {
	//	    System.out.println("Incorrect day entered. Please enter the date again: ");
	//	    return false;
	//	}
	//	if (month == 2 && day > 28) {
	//	    System.out.println("Incorrect day entered. Please enter the date again: ");
	//	    return false;
	//	}
	//	return true;
	//    }
	//
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
