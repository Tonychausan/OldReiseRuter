package com.reise.ruter.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RealTimeTableObjects {
	private String destinationName;
	private String departurePlatformName;
	private String lineRef;
	private int destinationRef;
	private Date expectedDepartureTime;
	private Date aimedDepartureTime;
	private String lineColor;
	private String publishedLineName;
	

	public String getDeparturePlatformName() {
		return departurePlatformName;
	}

	public void setDeparturePlatformName(String departurePlatformName) {
		this.departurePlatformName = departurePlatformName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getLineRef() {
		return lineRef;
	}

	public void setLineRef(String lineRef) {
		this.lineRef = lineRef;
	}

	public int getDestinationRef() {
		return destinationRef;
	}

	public void setDestinationRef(int destinationRef) {
		this.destinationRef = destinationRef;
	}

	public Date getExpectedDepartureTime() {
		return expectedDepartureTime;
	}

	public void setExpectedDepartureTime(String expectedDepartureTime) {
		this.expectedDepartureTime = stringToDate(expectedDepartureTime);
		
	}
	
	public String getLineColor() {
		return lineColor;
	}

	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	
	public Date stringToDate(String dateInString){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse(dateInString.substring(0, 21));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getPublishedLineName() {
		return publishedLineName;
	}

	public void setPublishedLineName(String publishedLineName) {
		this.publishedLineName = publishedLineName;
	}

	public Date getAimedDepartureTime() {
		return aimedDepartureTime;
	}

	public void setAimedDepartureTime(String aimedDepartureTime) {
		this.aimedDepartureTime = stringToDate(aimedDepartureTime);
	}

	
}
