package ru.spiderpig.bash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Alex
 */
class Downloader {
    
    private static final Logger LOG = Logger.getLogger(Downloader.class.getName());
    
    private final static String CLASS_UP = "up";
    private final static String CLASS_RATING = "rating";
    private final static String CLASS_TEXT = "text";
    private final static String CLASS_DATE = "date";
    
    public static List<QuoteInterface> getFromUrl(String url) {
        Document doc = getDoc(url);
        return getFromDoc(doc);
    }
    
    private static Document getDoc(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).timeout(10000).userAgent("Mozilla/17.0").get();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
        return doc;
    }
    
    private static List<QuoteInterface> getFromDoc(Document doc) {
        List<QuoteInterface> result = new ArrayList<>();
        if (doc==null) return result;
        Elements quotes = doc.getElementsByClass("quote");
        
        if (quotes.isEmpty()){
            return result;
        }
        
        for (Element quote : quotes) {
            QuoteInterface q = getFromElement(quote);
            if (q != null) {
                result.add(q);
            }
        }
        return result;
    }
    
    private static QuoteInterface getFromElement (Element quote) {
        RawQuoteData rqd = getRawFromElement(quote);
        if (rqd == null) {
            return null;
        }
        return convert(rqd);
    }
    
    private static RawQuoteData getRawFromElement (Element quote) {
        Elements dates = quote.getElementsByClass(CLASS_DATE);
        if (dates.size()!=1) {
            return null;
        }
        String rawDate = dates.text();
        
        Elements ratings = quote.getElementsByClass(CLASS_RATING);
        if (ratings.size()!=1) {
            return null;
        }
        String rawRating = ratings.text();
        
        Elements ups = quote.getElementsByClass(CLASS_UP);
        if (ups.size()!=1) {
            return null;
        }
        String quoteNum = ups.get(0).attr("href");
        String rawName = quoteNum.split("/")[2];
        
        Elements text = quote.getElementsByClass(CLASS_TEXT);
        if (text.size() != 1){
            return null;
        }
        String quoteText = text.get(0).text();
        
        return new RawQuoteData(rawDate, rawRating, quoteNum, rawName, quoteText);
    }
    
    private static QuoteImpl convert(RawQuoteData raw) {
        try {
            int rating = Integer.valueOf(raw.rawRating);
            
            String[] splitSpace = raw.rawDate.split(" ");
            String dateStr = splitSpace[0];
            String timeStr = splitSpace[1];
            String[] splitDef = dateStr.split("-");
            String yearStr = splitDef[0];
            String monthStr = splitDef[1];
            String dayStr = splitDef[2];
            String[] splitDoub = timeStr.split(":");
            String hourStr = splitDoub[0];
            String minuteStr = splitDoub[1];
            
            int year = Integer.valueOf(yearStr);
            int month = Integer.valueOf(monthStr);
            int day = Integer.valueOf(dayStr);
            
            GregorianCalendar date = new GregorianCalendar(
                    year,
                    month - 1,
                    day,
                    Integer.valueOf(hourStr),
                    Integer.valueOf(minuteStr)
            );
            return new QuoteImpl(raw.quoteText, rating, date.getTime());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
            return null;
        }
        
    }
    
}
