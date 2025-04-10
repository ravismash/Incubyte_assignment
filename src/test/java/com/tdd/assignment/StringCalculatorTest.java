package com.tdd.assignment;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	private StringCalculator calculator;

	private static int addMethodCounter = 0;

	@Before
	public void setup() {
		calculator = new StringCalculator();
	}

	@Test
	public void emptyStringTest() {
		int result = calculator.add("");
		assertEquals(result, 0);
	}

	@Test
	public void singleDigitString() {
		int result = calculator.add("2");
		assertEquals(result, 2);
	}

	@Test
	public void twoDigitString() {
		int result = calculator.add("1,2");
		assertEquals(result, 3);
		int result2 = calculator.add("12,2");
		assertEquals("Adding 12,2 should give 14", result2, 14);
	}

	@Test
	public void multipleDigits() {
		int result = calculator.add("1,2,3,4,5");
		assertEquals(result, 15);
		int result2 = calculator.add("12,22,32,42,25");
		assertEquals(result2, 133);
	}

	@Test
	public void multiplePredefinedDelimiter() {
		int result = calculator.add("1\n2,3");
		assertEquals(result, 6);
	}

	@Test
	public void inbuiltDelimiterTest() {
		int result = calculator.add("//'\n1'2'3");
		assertEquals(result, 6);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void negativeNumberTest() {
		int result = calculator.add("//'\n-1'-2'-3");
		assertEquals(result, 6);
	}

	@Test
	public void lessThanMaxNumberTest() {
		int result = calculator.add("//'\n999'1'1");
		assertEquals(result, 1001);
	}

	@Test
	public void moreThanMaxNumberTest() {
		int result = calculator.add("//'\n1000'1'1");
		assertEquals(result, 2);

		int result2 = calculator.add("//'\n1000'1000'1000");
		assertEquals(result2, 0);
	}

	@Test
	public void anyLengthDelimiterTest() {
		int result = calculator.add("//;;;\n1;;;2;;;3");
		assertEquals("Any Length Delimiter Test", result, 6);
	}

	@Test
	public void multipleCharSingleDelimiterBracketTest() {
		int result = calculator.add("//[;;;]\n1;;;2;;;3");
		assertEquals("Any Length Delimiter Bracke Test", result, 6);
	}

	@Test
	public void singleCharMultipleDelimiterTest() {
		int result = calculator.add("//[.][;]\n1.2;3");
		assertEquals("Multiple Delimiter Test", result, 6);
	}

	@Test
	public void multiCharMultipleDelimiterTest() {
		int result = calculator.add("//[...][;;;]\n1...2;;;3");
		assertEquals("Multiple Delimiter Test", result, 6);

		int result2 = calculator.add("//[%%][;;]\n1%%2;;3");
		assertEquals("Multiple Delimiter Test", result2, 6);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void multiCharMultipleDelimiterNegativeTest() {
		int result = calculator.add("//[...][;;;]\n1...2;;;-3");
		assertEquals("Multiple Delimiter Test", result, 6);
	}

	@After
	public void countAddMethodTest() {
		addMethodCounter += calculator.getCalledCount();
	}

	@AfterClass
	public static void addInvocationCounter() {
		System.out.println("The add method was invoked " + addMethodCounter + " time(s).");
	}

}
