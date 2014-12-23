package com.reise.ruter.data;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable{
	private int id;
	private String name;
	private String district;
	private String placeType;
	private Object[] stops;
	private boolean realTimeStop;
	
	public Place(int id, String name, String district, String placeType){
		setId(id);
		setName(name);
		setDistrict(district);
		setPlaceType(placeType);
		setRealTimeStop(false);
		stops = null;
	}
	
	public Place(Parcel in) {
		id = in.readInt();
		name = in.readString();
		district = in.readString();
		placeType = in.readString();
		stops = in.readArray(Place.class.getClassLoader());
		realTimeStop = in.readByte() != 0;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public Object[] getStops() {
		return stops;
	}

	public void setStops(Place[] stops) {
		this.stops = stops;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(district);
		dest.writeString(placeType);
		dest.writeArray(stops);
		dest.writeByte((byte) (realTimeStop ? 1 : 0)); 
	}
	

	public boolean isRealTimeStop() {
		return realTimeStop;
	}

	public void setRealTimeStop(boolean realTimeStop) {
		this.realTimeStop = realTimeStop;
	}


	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Place createFromParcel(Parcel in) {
			return new Place(in); 
		}  
		public Place[] newArray(int size) {
			return new Place[size]; 
		}
	}; 
}
