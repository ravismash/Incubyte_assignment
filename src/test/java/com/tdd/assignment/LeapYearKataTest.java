package com.tdd.assignment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tdd.assignment.service.LoggingService;

public class LeapYearKataTest {

	private LeapYearKataApp leapYearTester;

	private LoggingService loggingService = Mockito.mock(LoggingService.class);

	@Before
	public void setup() {
		leapYearTester = new LeapYearKataApp(loggingService);
	}

	@Test
	public void testNothingYear() {
		boolean isLeapYear = leapYearTester.isLeapYear(1234);
		assertEquals(false, isLeapYear);
	}

	@Test
	public void testDivideBy400Year() {
		boolean isNotLeapYear = leapYearTester.isLeapYear(1234);
		assertEquals(false, isNotLeapYear);

		boolean isLeapYear = leapYearTester.isLeapYear(400);
		assertEquals("Is leap year :: ", true, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(800);
		assertEquals("is 800 leap year :: ", true, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(0);
		assertEquals("0/400 case :: ", false, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(399);
		assertEquals("0/399 case :: ", false, isLeapYear);
	}

	@Test
	public void testDivisibleBy100And400Year() {
		boolean isLeapYear = leapYearTester.isLeapYear(1800);
		assertEquals(false, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(1900);
		assertEquals(false, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(2100);
		assertEquals(false, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(1700);
		assertEquals(false, isLeapYear);
	}

	@Test
	public void divisibleBy4ButNot100() {
		boolean isLeapYear = leapYearTester.isLeapYear(2008);
		assertEquals(true, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(2012);
		assertEquals(true, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(2016);
		assertEquals(true, isLeapYear);
	}

	@Test
	public void notDivisibleBy4() {
		boolean isLeapYear = leapYearTester.isLeapYear(2017);
		assertEquals(false, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(2018);
		assertEquals(false, isLeapYear);

		isLeapYear = leapYearTester.isLeapYear(2019);
		assertEquals(false, isLeapYear);
	}

	@Test
	public void logTest() {
		/*
		 * everytime when we call isLeapYear we want to verify that
		 * loggingService.log(year) must be called
		 */

		boolean isLeapYear = leapYearTester.isLeapYear(2018);
		assertEquals(false, isLeapYear);
		verify(loggingService).log(2018);
	}

}
