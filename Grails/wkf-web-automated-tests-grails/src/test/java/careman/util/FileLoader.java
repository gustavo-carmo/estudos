package careman.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileLoader {

    static final Logger LOG = LoggerFactory.getLogger(FileLoader.class);

    public String load(final String fileName) throws IOException {

        // TODO Get from param
		StringBuilder redourceFromTestFolder = new StringBuilder(System.getProperty("user.dir"));
		redourceFromTestFolder.append("/src/test/resources");
		redourceFromTestFolder.append(fileName);

		File f = new File(redourceFromTestFolder.toString());

		if (!f.exists()) {
			LOG.error(f.getAbsolutePath() + " not found");
			throw new FileNotFoundException();
		}

		try {

			List<String> readLines = FileUtils.readLines(f);
			StringBuilder s = new StringBuilder();
			String concatChar = "";
			for (String line : readLines) {
				if (line.length() > 0) {
					s.append(concatChar);
					// s.append(line.replace("\"", "\""));
					s.append(line);
					concatChar = "\n";
				}
			}
			return s.toString();

		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw e;
		}
    }
    
    public static String readFile(String file) {
    	try {
    		return new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/scriptsGroovy/" + file)));		
    	} catch (IOException e) {
    		System.out.println(e);
    		return null;
    	}
	}
}
