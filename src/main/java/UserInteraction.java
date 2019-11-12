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
		} while (!validateDate(optionSelected));
	}

	/**
	 * This class validates the data entered by the user
	 *
	 * @param year  year entered by the user
	 * @param month month entered by the user
	 * @param day   day entered by the user
	 * @return true if date is valid, false otherwise
	 */
	private boolean validateDate(String optionSelected) {
		if (!Pattern.matches("\\d{8}", optionSelected)) {
			System.out.println("Incorrect date entered. Please try again");
			return false;
		} else {
			year = Integer.valueOf(optionSelected.substring(0, 4));
			month = Integer.valueOf(optionSelected.substring(4, 6));
			day = Integer.valueOf(optionSelected.substring(6, 8));
		}

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
	 * @return the date in format YYYY-MM-DD
	 */
	public String getDate() {
		return year + "-" + (month <= 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	}

	/**
	 * @return the candidate
	 */
	public String getCandidate() {
		return candidate;
	}
}
