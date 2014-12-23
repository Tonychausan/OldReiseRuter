package com.reise.ruter.data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.reise.ruter.support.Variables;

public class RuterApiReader {
	public static Place getStop(String id){
		JSONParser jParser = new JSONParser();
		String url = "http://reisapi.ruter.no/Place/GetStop/" + id + "?&json=true";
		JSONObject json = jParser.getJSONFromUrl(url);
		try {
			Place place = new Place(json.getInt("ID"),
					json.getString("Name"),
					json.getString("District"),
					json.getString("PlaceType"));
			return place;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	public static JSONArray getPlaces(String id){
		JSONParser jParser = new JSONParser();
		id = getValidIdGetPlaces(id);
		if(id == null){
			return null;
		}
		String url = "http://reisapi.ruter.no/Place/GetPlaces/" + id + "?json=true";
		JSONArray jArray = jParser.getJSONArrayFromUrl(url);
		
		return jArray;
	}
	
	public static JSONArray getTravels(String id){
		//TODO
		return null;
	}
	
	public static JSONArray getDepartures(Place place){
		JSONParser jParser = new JSONParser();
		String id = Integer.toString(place.getId());
		String url = "http://reisapi.ruter.no/StopVisit/GetDepartures/" + id + "?json=true";
		JSONArray jArray = jParser.getJSONArrayFromUrl(url);
		return jArray;
	}
	
	public static JSONArray getClosestStops(String coordinates){
		JSONParser jParser = new JSONParser();
		String url = "http://reisapi.ruter.no/Place/GetClosestStops?coordinates=" + coordinates + "&json=true";
		JSONArray jArray = jParser.getJSONArrayFromUrl(url);
		return jArray;
	}
	
	
	
	
	private static String getValidIdGetPlaces(String id){
		if(id.trim().length() == 0){
			return null;
		}
		String returnString = "";
		int index = 0;
		char c = id.charAt(index);
		
		//remove space at start
		while(c == ' '){
			index++;
			c = id.charAt(index);
		}
		if(index == id.length()-1){
			return null;
		}
		
		// count space at end
		int endSpace = 0;
		c = id.charAt(id.length()-1);
		while (c == ' '){
			endSpace++;
			c = id.charAt(id.length()- 1 -endSpace);
		}
		
		for(int i = index; i < id.length()-endSpace; i++){
			c = id.charAt(i);
			if(isLetter(c)){
				returnString += c;
			}
			else if(c == ' '){
				returnString += Variables.SPACE_IN_URL;
			}
			else{
				return null;
			}
		}
		return returnString;
	}
	
	private static boolean isLetter(char c){
		return ((c >= 'a' && c <= 'z') || 
				(c >= 'A' && c <= 'Z') || 
				c == 'æ' || c == 'ø' || c == 'å' ||
				c == 'Æ' || c == 'Ø' || c == 'Å');
	}
}
