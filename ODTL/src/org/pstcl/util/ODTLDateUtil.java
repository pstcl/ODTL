package org.pstcl.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ODTLDateUtil {

	public static Date getDayMinusMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.add(Calendar.DATE, -30);



		return new Date(cal.getTime().getTime());
	}


	public static Date getToday()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.AM_PM, Calendar.AM);

		return new Date(cal.getTime().getTime());
	}





	public static Integer getCurrentMonth()
	{
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	public static Integer getCurrentYear()
	{
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static Integer getMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	public static Integer getYear(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getPreviousMonth(Integer month, Integer year) {
		if(month==0)
		{
			month=11;
		}
		if(null==year)
		{
			year=2018;
		}
		return month;
	}

	public static int getYearForPreviousMonth(Integer month, Integer year) {
		if(month==0)
		{
			year--;
		}
		return year;
	}

	public static Date getFirstDayOfMonth(Integer month, Integer year) {

		if(null==month)
		{
			month=1;
		}
		if(null==year)
		{
			year=2018;
		}
		YearMonth yearMonthObject = YearMonth.of(year, month);
		LocalDate dateToConvert= yearMonthObject.atDay(1);
		return java.sql.Date.valueOf(dateToConvert);

	}

	public static Date getLastDayOfMonth(Integer month, Integer year) {

		if(null==month)
		{
			month=12;
		}
		if(null==year)
		{
			year=2018;
		}
		YearMonth yearMonthObject = YearMonth.of(year, month);
		LocalDate dateToConvert= yearMonthObject.atEndOfMonth();
		yearMonthObject.atDay(1);
		return java.sql.Date.valueOf(dateToConvert);

	}

	public static Date convertMonthYearToDate(Integer month, Integer year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,15);
		cal.set(Calendar.MONTH,month);
		cal.set(Calendar.YEAR,year);
		return cal.getTime();

	}


}
