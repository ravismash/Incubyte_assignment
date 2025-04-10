package com.tdd.assignment;

import com.tdd.assignment.service.LoggingService;

public class LeapYearKataApp {

	private LoggingService logService;
	
	public LeapYearKataApp(LoggingService logService) {
		this.logService = logService;
	}
	
	public boolean isLeapYear(int year) {

		if (year == 0) {
			return false;
		}

		boolean isLeapYear = isDivisibleBy(year, 400);

		if (!isLeapYear) {
			isLeapYear = isDivisibleBy(year, 4) && !isDivisibleBy(year, 100);
		}
		logService.log(year);
		return isLeapYear;
	}

	private boolean isDivisibleBy(int year, int divisor) {

		if (year % divisor == 0) {
			return true;
		} else {
			return false;
		}

	}

}
