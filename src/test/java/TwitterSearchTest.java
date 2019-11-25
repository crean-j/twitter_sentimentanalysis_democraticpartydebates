package test.java;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.java.Tweet;
import main.java.TwitterSearch;

/**
 * Tests that the method TwitterSearh clas returns an ArrayList with 100
 * elements
 *
 * @author juangoleniowski
 *
 */
class TwitterSearchTest {

	/**
	 * Test that every tweet in the ArrayList contains the name of the candidate
	 */
	@Test
	void testMainSearch() {
		TwitterSearch ts = new TwitterSearch("Warren", "20191010");
		ArrayList<Tweet> result = ts.mainSearch();
		for (int i = 0; i < result.size(); i++) {
			boolean exists;
			if (result.get(i).getTextInTweet().contains("Warren")) {
				exists = true;
			} else {
				exists = false;
			}

			assertEquals(true, exists);
		}

	}

}
