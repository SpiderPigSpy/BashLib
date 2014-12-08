package ru.spiderpig.bash;

import java.util.Date;

/**
 *
 * @author Alex
 */
public class QuoteImpl implements QuoteInterface {
    
    private final String quote;
    private final int rating;
    private final Date date;
    
    public QuoteImpl(String quote, int rating, Date date) {
        this.quote = quote;
        this.rating = rating;
        this.date = date;
    }

    @Override
    public String getText() {
        return quote;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public Date getDate() {
        return date;
    }
    
}
