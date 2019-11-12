import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

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
	TwitterSearch ts = new TwitterSearch("Warren", 2019, 10, 10);
	ArrayList<Tweet> result = ts.MainSearch();
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
