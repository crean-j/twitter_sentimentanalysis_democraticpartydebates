import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TwitterSearchTest {

    @Test
    void testMainSearch() {
	TwitterSearch ts = new TwitterSearch("Warren", 2019, 10, 10);
	assertEquals(100, ts.MainSearch().size());
    }
}
