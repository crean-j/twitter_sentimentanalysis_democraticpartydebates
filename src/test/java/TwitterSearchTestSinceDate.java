import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * This test ensures that for a given date, the code calculates the correct date
 * seven days before and on e day after it
 *
 */
class TwitterSearchTestSinceDate {

    @Test
    void testSinceDate() {
	TwitterSearch tp = new TwitterSearch("Warren", "20191201");
	assertEquals("2019-12-07", tp.sinceDate("2019-12-14"));
	assertEquals("2019-11-30", tp.sinceDate("2019-12-07"));
	assertEquals("2018-12-25", tp.sinceDate("2019-01-01"));
	assertEquals("2019-02-27", tp.sinceDate("2019-03-06"));
	assertEquals("2020-02-28", tp.sinceDate("2020-03-06"));
    }

    @Test
    void testToDate() {
	TwitterSearch tp = new TwitterSearch("Warren", "20191201");
	assertEquals("2019-12-08", tp.toDate("2019-12-07"));
	assertEquals("2019-12-01", tp.toDate("2019-11-30"));
	assertEquals("2019-01-01", tp.toDate("2018-12-31"));
	assertEquals("2019-02-28", tp.toDate("2019-02-27"));
	assertEquals("2020-02-29", tp.toDate("2020-02-28"));
    }
}
