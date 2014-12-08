package ru.spiderpig.bash;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Alex
 */
class Utils {
    
    protected static List<QuoteInterface> sortByDate(List<QuoteInterface> initial, boolean desc) {
        Collections.sort(initial, (QuoteInterface o1, QuoteInterface o2) -> {
            if (desc) {
                return o1.getDate().compareTo(o2.getDate());
            } else {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return initial;
    }
    
    protected static List<QuoteInterface> sortByRating(List<QuoteInterface> initial, boolean desc) {
        Collections.sort(initial, (QuoteInterface o1, QuoteInterface o2) -> {
            if (desc) {
                return o1.getRating()  - o2.getRating();
            } else {
                return o2.getRating()  - o1.getRating();
            }
        });
        return initial;
    }
    
}
