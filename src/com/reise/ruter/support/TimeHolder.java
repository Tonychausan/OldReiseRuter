package com.reise.ruter.support;

import java.util.Calendar;

public class TimeHolder {
	private int hour;
	private int minute;
	private int dayOfMonth;
	private int year;
	private int month;
	
	public TimeHolder(){
		this.hour = 0;
		this.minute = 0;
		this.dayOfMonth = 0;
		this.month = 0;
		this.year = 0;
	}
	
	public TimeHolder(Calendar calendar){
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH);
		year = calendar.get(Calendar.YEAR);
		
	}
	
	
	public void setTime(int hour, int minute){
		this.hour = (hour != 0) ? hour : this.hour;
		this.minute = (minute != 0) ? minute : this.minute;
	}

	public void setDate(int dayOfMonth, int month, int year){
		this.dayOfMonth = (dayOfMonth != 0) ? dayOfMonth : this.dayOfMonth;
		this.month = (month != 0) ? month : this.month;
		this.year = (year != 0) ? year : this.year;
	}
	
	public void insertCalendarClass(Calendar calendar){
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH);
		year = calendar.get(Calendar.YEAR);
		
	}
	
	public String timeToString(){
		String hourString = hour < 10 ? "0"+Integer.toString(hour) : Integer.toString(hour); 
		String minuteString = minute < 10 ? "0"+Integer.toString(minute) : Integer.toString(minute);
		return hourString + ":" + minuteString;
	}
	
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}
}
