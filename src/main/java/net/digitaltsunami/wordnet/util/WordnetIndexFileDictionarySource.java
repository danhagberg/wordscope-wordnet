package net.digitaltsunami.wordnet.util;

import java.io.Reader;


public class WordnetIndexFileDictionarySource extends WordnetFileDictionarySource {

	public WordnetIndexFileDictionarySource(String fileLocation) {
		super(fileLocation);
	}
	public WordnetIndexFileDictionarySource(Reader fileReader) {
		super(fileReader);
	}

	protected String processFileLine(String line) {
		// Skip comment lines
		if (line.charAt(0) == ' ') {
			return null;
		}
		// Term is first value up to the space.
		return line.substring(0, line.indexOf(' '));
	}

}
