package com.exercise.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.exercise.contants.Constants;
import com.exercise.logger.Logger;

/**
 * OutputProcessor generates output file also writes output text in that file
 *
 */
public class OutputProcessor {

	/**
	 * selects random string from String List
	 * 
	 * @param strings
	 * @return random string from String List
	 */

	public String randomString(List<String> strings) {
		Random random = new Random();
		int rInt = random.nextInt(strings.size());
		return strings.get(rInt);
	}

	/**
	 * outputWriter method generates output text using keys and references from
	 * tigramMap
	 * 
	 * @param tigramMap provides keys and references for outputWriter
	 * @return returns a list of strings to be written to file
	 */

	public String outputWriter(Map<String, ArrayList<String>> tigramMap) {
		String randKey = randomString(new ArrayList<String>(tigramMap.keySet()));
		StringBuilder storyBuilder = new StringBuilder();
		int counter = 0;
		while (tigramMap.containsKey(randKey)) {
			String s = randomString(tigramMap.get(randKey));
			storyBuilder.append(s).append(" ");
			randKey = randKey.split(" ")[1] + " " + s;
			counter++;
			if (counter % Constants.LINE_LIMIT == 0) {
				storyBuilder.append(System.getProperty("line.separator"));
			}
		}
		return storyBuilder.toString().trim();
	}

	/**
	 * outputGenerator writes output file in the given target location
	 * 
	 * @param fileName path to output file
	 * @param value    content being written to file
	 * @throws IOException when path is invalid
	 */

	public void outputGenerator(String fileName, String value) throws IOException {

		try {
			archiveOldFiles();
			String s = value;
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(s);
			writer.close();
			Logger.log(Constants.OUTPUT_STATUS);
			Logger.log(Constants.TIGRAM_END);
		} catch (Exception ex) {
			Logger.logError(Constants.FILE_WRITE_ERROR);
		}
	}

	/**
	 * archiveOldFiles method moves previously generated output files to archive
	 * folder
	 * 
	 */
	private void archiveOldFiles() {

		File archiveFolder = new File(Constants.ARCHIVE_FILE);
		File outputFolder = new File(Constants.OUTPUT_FILE);

		if (outputFolder.exists() && outputFolder.isDirectory()) {
			File[] listOfFiles = outputFolder.listFiles();

			if (listOfFiles != null) {
				for (File file : listOfFiles) {
					file.renameTo(new File(archiveFolder + "\\" + file.getName()));
				}
			}
			Logger.log(listOfFiles.length + Constants.SINGLE_SPACE + Constants.ARCHIVE_STATUS);
		}
	}
}