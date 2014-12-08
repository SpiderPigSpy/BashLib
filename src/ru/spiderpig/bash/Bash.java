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
    
    public List<QuoteInterface> getIndexQuotes() {
        return Downloader.getFromUrl(INDEX_URL);
    }
    
}
