package com.reise.ruter.support;

public class Variables {
	public static int SEARCH_TRESHOLD = 2;
	public static int SEARCH_LIST_UPDATE = 15;
	public static String SPACE_IN_URL = "%20";
	public static String PLATFORM = "Platform ";
	public static String REFRESH_TOAST = "Refreshing";
	public static String NEARBY_SEARCH = "Nearby";
	
	public static int NUMBUER_OF_WEEKDAYS = 7; 

	public static class PlaceType{
		public static String AREA = "Area";
		public static String STREET = "Street";
		public static String POI = "POI";
		public static String STOP = "Stop";
	}
	
	public static class PlaceField{
		public static String ID = "ID";
		public static String NAME = "Name";
		public static String DISTRICT = "District";
		public static String PLACE_TYPE = "PlaceType";
		public static String STOPS = "Stops";
		public static String REAL_TIME_STOP = "RealTimeStop";
		
	}
	
	public static class DeparturesField{
		public static String MONITORED_VEHICLE_JOURNEY = "MonitoredVehicleJourney";
		public static String MONITORED_CALL = "MonitoredCall";
		public static String DESTINATION_NAME = "DestinationName";
		public static String DESTINATION_REF = "DestinationRef";
		public static String DEPARTURE_PLATFORM_NAME = "DeparturePlatformName";
		public static String LINE_REF = "LineRef";
		public static String EXPECTED_DEPARTURE_TIME = "ExpectedDepartureTime";
		public static String AIMED_DEPARTURE_TIME = "AimedDepartureTime";
		public static String LINE_COLOUR = "LineColour";
		public static String EXTENSIONS = "Extensions";
		public static String PUBLISHED_LINE_NAME = "PublishedLineName";
		
	}
	
}
