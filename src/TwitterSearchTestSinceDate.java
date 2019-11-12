import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TwitterSearchTestSinceDate {

    @Test
    void testSinceDate() {
	TwitterSearch ts = new TwitterSearch("Warren", "20190101");
	assertEquals("2019-12-07", ts.sinceDate("20191214"));
	assertEquals("2019-11-30", ts.sinceDate("20191207"));
	assertEquals("2018-12-25", ts.sinceDate("20190101"));
	assertEquals("2019-02-27", ts.sinceDate("20190306"));
	assertEquals("2020-02-28", ts.sinceDate("20200306"));
    }
    
    @Test
    void testToDate() {
	TwitterSearch ts = new TwitterSearch("Warren", "20190101");
	assertEquals("2019-12-14", ts.toDate("20191207"));
	assertEquals("2019-12-07", ts.toDate("20191130"));
	assertEquals("2019-01-01", ts.toDate("20181225"));
	assertEquals("2019-03-06", ts.toDate("20190227"));
	assertEquals("2020-03-06", ts.toDate("20200228"));
    }
}
