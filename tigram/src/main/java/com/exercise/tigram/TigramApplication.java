package com.exercise.tigram;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exercise.contants.Constants;
import com.exercise.logger.Logger;
import com.exercise.processor.InputProcessor;
import com.exercise.processor.OutputProcessor;
import com.exercise.util.CommonUtil;

/**
 * TigramApplication class passes input file from source location and stores the
 * final output file in the target location
 *
 */
@SpringBootApplication
public class TigramApplication {

	public static void main(String[] args) throws IOException {

		Logger.log(Constants.TIGRAM_START);

		InputProcessor inputProcessor = new InputProcessor();
		OutputProcessor outputProcessor = new OutputProcessor();

		inputProcessor.fileReader(Constants.INPUT_FILE);
		inputProcessor.tigramCreator();
		String fileName = CommonUtil.getOutputFileName();
		outputProcessor.outputGenerator(fileName, outputProcessor.outputWriter(inputProcessor.getTigramMap()));
	}
}
