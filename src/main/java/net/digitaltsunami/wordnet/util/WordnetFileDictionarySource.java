/**
 * 
 */
package net.digitaltsunami.wordnet.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.digitaltsunami.word.util.DictionarySource;
import net.digitaltsunami.word.util.WordscopeException;

/**
 * Wordnet dictionary file processor. Extracts terms (may be 1 to n words in
 * length).
 * 
 * @author dhagberg
 * 
 */
public abstract class WordnetFileDictionarySource implements DictionarySource {

    private String sourceLocation;
    private BufferedReader dataReader;

    public WordnetFileDictionarySource(String fileLocation) {
        this.sourceLocation = fileLocation;
    }
    
    public WordnetFileDictionarySource(Reader fileReader) {
        if (! (fileReader instanceof BufferedReader)) {
            dataReader = new BufferedReader(fileReader);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.digitaltsunami.word.util.DictionarySource#open()
     */
    public void open() throws WordscopeException {
        try {
            if (dataReader == null) {
                dataReader = new BufferedReader(new FileReader(sourceLocation));
            }
        } catch (FileNotFoundException fnfe) {
            throw new WordscopeException("Failed to open dictionary source: " + sourceLocation,
                    fnfe);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.digitaltsunami.word.util.DictionarySource#close()
     */
    public void close() throws WordscopeException {
        try {
            if (dataReader != null) {
                dataReader.close();
            }
        } catch (IOException ioe) {
            throw new WordscopeException("Failed to close dictionary source", ioe);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.digitaltsunami.word.util.DictionarySource#getNextTerm()
     */
    public String getNextTerm() throws WordscopeException {
        String term = null;
        String line;
        try {
            // While more lines in the file and until we have found the next
            // term.
            while ((line = dataReader.readLine()) != null) {
                term = processFileLine(line);
                if (term != null) {
                    break;
                }
            }
        } catch (IOException ioe) {
            throw new WordscopeException("Failed to retrieve next dictionary term", ioe);
        }
        return term;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.digitaltsunami.word.util.DictionarySource#getNextTerms(int)
     */
    public Collection<String> getNextTerms(int limit) throws WordscopeException {
        List<String> terms = new ArrayList<String>(limit);
        String line, term;
        int termCount = 0;

        try {
            // While more lines in the file and until we have reached the term
            // limit.
            while ((line = dataReader.readLine()) != null || termCount == limit) {
                term = processFileLine(line);
                if (term != null) {
                    terms.add(term);
                    termCount++;
                }
            }
        } catch (IOException ioe) {
            throw new WordscopeException("Failed to retrieve dictionary terms", ioe);
        }
        return terms;
    }

    /**
     * Process the line specific to the file type and extract the term.
     * 
     * @param line
     * @return Term or null if no term available on this line.
     */
    protected abstract String processFileLine(String line);
}
