package com.exercise.tigram;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercise.processor.InputProcessor;
import com.exercise.processor.OutputProcessor;

/**
 * TigramApplicationTests
 *
 */
@SpringBootTest
class TigramApplicationTests {

	private InputProcessor inputProcessorTest;
	private OutputProcessor outputProcessorTest;

	@BeforeEach
	public void setup() {
		inputProcessorTest = new InputProcessor();
		outputProcessorTest = new OutputProcessor();
	}

	@Test
	void givenAValidInputFile_whenInputProcessorIsCalled_thenWordsListShouldReturnStringsFromMockFile()
			throws FileNotFoundException {
		inputProcessorTest.fileReader("src/test/resources/source/MockInputFile.txt");
		var result = inputProcessorTest.getWordsList();
		assertEquals(Arrays.asList("This", "is", "a", "mock", "input", "file"), result);
	}

	@Test
	public void givenAInValidInputFile_whenInputProcessorIsCalled_thenAssertionSucceeds() {

		try {
			inputProcessorTest.fileReader("src/test/resources/FackInputFile.txt");
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

	@Test
	void givenAValidWordsList_whenOutputProcessorIsCalled_thenShouldReturnTigramMap() {
		inputProcessorTest.setWordsList(Arrays.asList("One", "Two", "Three", "Four", "One", "Two", "Five"));
		inputProcessorTest.tigramCreator();
		var result = inputProcessorTest.getTigramMap();
		assertEquals(Arrays.asList("Three", "Five"), result.get("One Two"));
		assertEquals(Arrays.asList("One"), result.get("Three Four"));
	}

	@Test
	public void givenAInValidOutputPath_whenOutputGeneratorIsCalled_thenAssertionSucceeds() {
		try {
			outputProcessorTest.outputGenerator("src/test/resources/faketarget/FackInputFile.txt", "");
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

}
