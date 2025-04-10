package com.tdd.assignment;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {

	private static final String BASIC_DELIMITER = ",|\n";
	private static final String ANY_LENGTH_DELIMITER_IDENTIFIER = "//[";
	private static final String CUSTOM_DELIMITER_IDENTIFIER = "//";
	private static final String CUSTOM_MULTIPLE_DELIMITER_IDENTIFIER = "]\\[";
	private static final String ESCAPE_CHAR = "\\";
	private static final int DELIMITER_INDEX = 2;
	private static final int BRACKET_DELIMITER_INDEX = 3;

	private int count = 0;

	public Integer add(String numbers) {
		count++;
		if (numbers == null || numbers.isEmpty()) {
			return 0;
		}

		// Get Delimiter
		String[] delimiterAndNumberArray = numbers.split("\n", 2);

		if (hasInbuiltDelimiter(numbers)) {
			delimiterAndNumberArray = numbers.split("\n", 2);
			String delimiter = getDelimiter(delimiterAndNumberArray[0]);
			numbers = delimiterAndNumberArray[1];
			return calculateSum(numbers, delimiter);
		} else {
			return calculateSum(numbers, BASIC_DELIMITER);
		}

	}

	private String getDelimiter(String delimiterPart) {

		// delimiter string starts with //[
		if (delimiterPart.startsWith(ANY_LENGTH_DELIMITER_IDENTIFIER)) {
			String delimiter = delimiterPart.substring(BRACKET_DELIMITER_INDEX, delimiterPart.length() - 1);
			return Stream.of(delimiter.split(CUSTOM_MULTIPLE_DELIMITER_IDENTIFIER)).collect(Collectors.joining("|"));
		} else {
			// delimiter string starts with // only
			return delimiterPart.substring(DELIMITER_INDEX);
		}
	}

	private Integer calculateSum(String numbers, String delimiter) {
		// split operation
		String[] numberStringArray = splitNumberString(numbers, delimiter);

		// check for negatives
		IntStream posIntStream = checkForNegatives(numberStringArray);

		// ignore number > 1000
		IntStream finaIntStream = limitMaxNumber(posIntStream);

		// perform summation
		return finaIntStream.sum();
	}

	private IntStream limitMaxNumber(IntStream posIntStream) {
		return posIntStream.filter(num -> num < 1000);
	}

	private boolean hasInbuiltDelimiter(String numbers) {
		return numbers.startsWith(CUSTOM_DELIMITER_IDENTIFIER);
	}

	private IntStream getIntegers(String[] numberStringArray) {
		return Arrays.stream(numberStringArray).mapToInt(Integer::parseInt);
	}

	private String[] splitNumberString(String numbers, String delimiter) {
		return numbers.split(ESCAPE_CHAR + delimiter);
	}

	public int getCalledCount() {
		return count;
	}

	private IntStream checkForNegatives(String[] numberStringArray) {
		String negativeNumbers = getIntegers(numberStringArray).filter(n -> n < 0)
				.mapToObj(integer -> Integer.toString(integer)).collect(Collectors.joining(","));
		if (!negativeNumbers.isEmpty()) {
			throw new UnsupportedOperationException("negatives not allowed :: " + negativeNumbers);
		} else {
			return getIntegers(numberStringArray);
		}
	}

}
