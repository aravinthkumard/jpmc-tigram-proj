package com.exercise.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exercise.contants.Constants;
import com.exercise.logger.Logger;

/**
 * InputProcessor reads input file from source folder and generate words list for further process
 *
 */
public class InputProcessor {

	private Map<String, ArrayList<String>> tigramMap;
	private List<String> wordsList;

	/**
	 * fileReader reads filePath and splits each word into individual string
	 * 
	 * @param filePath path to input file representing book
	 * @throws FileNotFoundException when path is invalid
	 */

	public void fileReader(String filePath) throws FileNotFoundException {

		try {
			wordsList = new ArrayList<>();
			File file = new File(filePath);
			Scanner fileScanner = new Scanner(file);
			Pattern pattern = Pattern.compile("[^a-zA-Z0-9]\\p{Punct}");
			Matcher matcher = pattern.matcher("");

			while (fileScanner.hasNextLine()) {
				String row = fileScanner.nextLine().trim();
				matcher = matcher.reset(row);
				row = matcher.replaceAll(" ");
				String[] tempWords = row.split("\\s+");
				for (String word : tempWords) {
					if (!word.isEmpty()) {
						wordsList.add(word);
					}
				}
			}
			fileScanner.close();
		} catch (Exception ex) {
			Logger.logError(Constants.FILE_READ_ERROR);
		}

	}

	/**
	 * creates a Map object from the list of strings generated in fileReader method
	 */

	public void tigramCreator() {
		tigramMap = new HashMap<String, ArrayList<String>>();
		int set = wordsList.size() - 3;
		for (int i = 0; i <= set; i++) {
			String key = wordsList.get(i) + " " + wordsList.get(i + 1);
			String s = wordsList.get(i + 2);
			if (tigramMap.containsKey(key)) {
				tigramMap.get(key).add(s);
			} else {
				ArrayList<String> ngramList = new ArrayList<String>();
				ngramList.add(s);
				tigramMap.put(key, ngramList);
			}
		}
	}

	public Map<String, ArrayList<String>> getTigramMap() {
		return tigramMap;
	}

	public void setTigramMap(Map<String, ArrayList<String>> tigramMap) {
		this.tigramMap = tigramMap;
	}

	public List<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(List<String> wordsList) {
		this.wordsList = wordsList;
	}
}