import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TwitterSearchTestSinceDate {

    @Test
    void testSinceDate() {
	TwitterSearch ts = new TwitterSearch("Warren", 2019, 01, 01);
	assertEquals("2019-12-07", ts.sinceDate(2019, 12, 14));
	assertEquals("2019-11-30", ts.sinceDate(2019, 12, 07));
	assertEquals("2018-12-25", ts.sinceDate(2019, 01, 01));
	assertEquals("2019-02-27", ts.sinceDate(2019, 03, 06));
	assertEquals("2020-02-28", ts.sinceDate(2020, 03, 06));
    }
    
    @Test
    void testToDate() {
	TwitterSearch ts = new TwitterSearch("Warren", 2019, 01, 01);
	assertEquals("2019-12-14", ts.toDate(2019, 12, 07));
	assertEquals("2019-12-07", ts.toDate(2019, 11, 30));
	assertEquals("2019-01-01", ts.toDate(2018, 12, 25));
	assertEquals("2019-03-06", ts.toDate(2019, 02, 27));
	assertEquals("2020-03-06", ts.toDate(2020, 02, 28));
    }
}
