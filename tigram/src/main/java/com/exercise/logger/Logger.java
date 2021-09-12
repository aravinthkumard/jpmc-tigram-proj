package com.exercise.logger;

import java.time.LocalDateTime;

import com.exercise.contants.Constants;

/**
 * Logger methods for various scenarios
 *
 */
public class Logger {

	public static void log(String string) {
		System.out.println(LocalDateTime.now() + Constants.SINGLE_SPACE + Constants.INFO
				+ Constants.DOUBLE_COLON_WITH_SPACE + string);
	}

	public static void logError(String string) {
		System.out.println(LocalDateTime.now() + Constants.SINGLE_SPACE + Constants.ERROR
				+ Constants.DOUBLE_COLON_WITH_SPACE + string);
	}

}