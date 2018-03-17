package net.digitaltsunami.wordnet.util;

import java.io.Reader;


public class WordnetDataFileDictionarySource extends WordnetFileDictionarySource {

	public WordnetDataFileDictionarySource(String fileLocation) {
		super(fileLocation);
	}
	
	public WordnetDataFileDictionarySource(Reader fileReader) {
		super(fileReader);
	}

	protected String processFileLine(String line) {
		if (line.charAt(0) == ' ') {
			return null;
		}
		// Format up to word is %d %d %c %d WORD
		int delPos = 0;
		for (int i = 0; i < 4; i++) {
			delPos = line.indexOf(' ', delPos) + 1;
		}
		return line.substring(delPos, line.indexOf(' ', delPos));
	}

}
