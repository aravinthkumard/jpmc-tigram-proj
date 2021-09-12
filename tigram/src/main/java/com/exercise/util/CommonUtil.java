package com.exercise.util;

import java.time.LocalDateTime;

import com.exercise.contants.Constants;

public class CommonUtil {

	private CommonUtil() {
		// restrict instantiation
	}

	public static String getOutputFileName() {
		String now = LocalDateTime.now().toString();
		String fileName = Constants.OUTPUT_FILE + "Output_" + now + ".txt";
		return fileName;
	}

}
