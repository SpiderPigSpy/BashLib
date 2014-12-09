package ru.spiderpig.bash;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Bash {
    
    private static final Logger LOG = Logger.getLogger(Bash.class.getName());
    private static final String INDEX_URL = "http://bash.im/";
    
    public Bash() {}
    
    public List<QuoteInterface> getIndexQuotes() throws Exception {
        return Downloader.getFromUrl(INDEX_URL);
    }
    
    public static List<QuoteInterface> sortByDateDesc(List<QuoteInterface> initial) {
        return Utils.sortByDate(initial, true);
    }
    
    public static List<QuoteInterface> sortByDateAsc(List<QuoteInterface> initial) {
        return Utils.sortByDate(initial, false);
    }
    
    public static List<QuoteInterface> sortByRatingDesc(List<QuoteInterface> initial) {
        return Utils.sortByDate(initial, true);
    }
    
    public static List<QuoteInterface> sortByRatingAsc(List<QuoteInterface> initial) {
        return Utils.sortByRating(initial, false);
    }
    
}
