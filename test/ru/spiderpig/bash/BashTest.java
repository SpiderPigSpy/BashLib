package ru.spiderpig.bash;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class BashTest {
    
    private static List<QuoteInterface> testList;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        Bash bash = new Bash();
        testList = bash.getIndexQuotes();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void notEmpty() {
        assertTrue(!testList.isEmpty());
    }
    
    @Test 
    public void haveDates() {
        boolean haveDates = !testList.isEmpty();
        for (QuoteInterface quote : testList) {
            if (quote.getDate().getTime() == 0) {
                haveDates = false;
            }
        }
        assertTrue(haveDates);
    }
    
    @Test 
    public void haveRatings() {
        boolean haveRatings = !testList.isEmpty();
        for (QuoteInterface quote : testList) {
            if (quote.getRating() == 0) {
                haveRatings = false;
            }
        }
        assertTrue(haveRatings);
    }
    
    @Test 
    public void haveTexts() {
        boolean haveTexts = !testList.isEmpty();
        for (QuoteInterface quote : testList) {
            if (quote.getText().trim().isEmpty()) {
                haveTexts = false;
            }
        }
        assertTrue(haveTexts);
    }
    
}
