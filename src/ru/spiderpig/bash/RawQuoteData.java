package ru.spiderpig.bash;

/**
 *
 * @author Alex
 */
class RawQuoteData {
    final String rawDate;
    final String rawRating;
    final String quoteNum;
    final String rawName;
    final String quoteText;
    
    protected RawQuoteData(String rawDate, String rawRating, String quoteNum,
            String rawName, String quoteText) {
        this.rawDate = rawDate;
        this.rawRating = rawRating;
        this.quoteNum = quoteNum;
        this.rawName = rawName;
        this.quoteText = quoteText;
    }
}
