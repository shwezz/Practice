package com.qait.automation.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.testng.Reporter;

public class DateUtil {

	public static String getCurrentDateTime() {
		String ranNum = "";
		DateFormat formatter = new SimpleDateFormat("MMM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE));
		try {
			ranNum = dformatter.format(dateParse.parse(date)) + formatter.format(monthParse.parse(month)) + "_"
					+ Integer.toString(cal.get(Calendar.HOUR_OF_DAY)) + ":" + Integer.toString(cal.get(Calendar.MINUTE))
					+ ":" + Integer.toString(cal.get(Calendar.SECOND));
		} catch (Exception e) {
		}
		return ranNum;
	}

	public static String getDate() {
		DateFormat formatter = new SimpleDateFormat("MM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE));
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String calDate = month + "/" + date + "/" + year;
		return calDate;
	}

	public static String getTommorrowsDate() {
		DateFormat formatter = new SimpleDateFormat("MM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE) + 1);
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String calDate = month + "/" + date + "/" + year;
		return calDate;
	}

	public static String getTommorrowsDateFne() {
		DateFormat formatter = new SimpleDateFormat("MMM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE) + 1);
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String calDate = month + " " + date;
		return calDate;
	}

	public static String getDayAfterTommorrowsDate() {
		DateFormat formatter = new SimpleDateFormat("MM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE) + 2);
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String calDate = month + "/" + date + "/" + year;
		return calDate;
	}

	public static String getDayAfterTommorrowsDateFne() {
		DateFormat formatter = new SimpleDateFormat("MMM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE) + 2);
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String calDate = month + " " + date;
		return calDate;
	}

	public static String getDayToDayAfterTommorrowsDate() {
		DateFormat formatter = new SimpleDateFormat("MM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE) + 3);
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String calDate = month + "/" + date + "/" + year;
		return calDate;
	}

	public static String getDayToDayAfterTommorrowsDateFne() {
		DateFormat formatter = new SimpleDateFormat("MMM");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		DateFormat dformatter = new SimpleDateFormat("DD");
		SimpleDateFormat dateParse = new SimpleDateFormat("DD");
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String date = Integer.toString(cal.get(Calendar.DATE) + 3);
		try {
			month = formatter.format(monthParse.parse(month));
			date = dformatter.format(dateParse.parse(date));
		} catch (ParseException e) {
		}
		String calDate = month + " " + date;
		Reporter.log("end date:" + calDate + getCurrentTime(), true);
		return calDate + getCurrentTime();
	}

	public static String getDate(String date) {
		if (date.equalsIgnoreCase("TomorrowDate"))
			return getTommorrowsDate();
		if (date.equalsIgnoreCase("DayAfterTomorrowDate"))
			return getDayAfterTommorrowsDate();
		if (date.equalsIgnoreCase("DayToDayAfterTomorrowDate"))
			return getDayToDayAfterTommorrowsDate();
		return fixedDate();
	}

	public static String getDateForFne(String date) {
		if (date.equalsIgnoreCase("TomorrowDate"))
			return getTommorrowsDateFne();
		if (date.equalsIgnoreCase("DayAfterTomorrowDate"))
			return getDayAfterTommorrowsDateFne();
		if (date.equalsIgnoreCase("DayToDayAfterTomorrowDate"))
			return getDayToDayAfterTommorrowsDateFne();
		return fixedDate();
	}

	public static String getTimeAsPerTimeZone(String time, String timeZOne) {
		time = time.split("Minutes")[1];
		DateFormat formatter = new SimpleDateFormat("hh:mm a");
		TimeZone tz = TimeZone.getTimeZone("EST5EDT");
		Calendar cal = new GregorianCalendar(tz);
		cal.add(Calendar.MINUTE, Integer.parseInt(time));
		formatter.setTimeZone(tz);
		return formatter.format(cal.getTime());
	}

	// public static String getTommorrowsDate(){
	// Calendar cal = Calendar.getInstance();
	// String month = Integer.toString(cal.get(Calendar.MONTH)+1);
	// String date = Integer.toString(cal.get(Calendar.DATE)+1);
	// String year = Integer.toString(cal.get(Calendar.YEAR));
	// if (month.length()==1){
	// month = "0".concat(month);
	// }
	// String calDate = month+"/"+date+"/"+year;
	// return calDate;
	//
	// }

	public static String fixedDate() {
		return "12/31/2013";
	}

	public static String getCurrentTime() {
		Date date = new Date();
		String strDateFormat = "HH:mm a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return (sdf.format(date));
	}

	public static long getTimeInMiliSeconds() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	public static String changeDateFormat1(String Date) {
		DateFormat originalFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
		DateFormat targetFormat = new SimpleDateFormat("mm/dd/yy");
		Date date = null;
		try {
			date = originalFormat.parse(Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formattedDate = targetFormat.format(date);
		return formattedDate;
	}

	public static int changeMonthinDateFormat(String monthName) {

		Date date = null;
		try {
			date = new SimpleDateFormat("MMM").parse(monthName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int s = cal.get(Calendar.MONTH);
		s = s + 1;
		return s;
	}

	public static int changeFullMonthinNumberFormat(String monthName) {

		Date date = null;
		try {
			date = new SimpleDateFormat("MMMM").parse(monthName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int s = cal.get(Calendar.MONTH);
		s = s + 1;
		return s;
	}

}
