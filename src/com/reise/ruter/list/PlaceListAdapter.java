package com.reise.ruter.list;

import java.util.ArrayList;
import java.util.List;

import com.reise.ruter.R;
import com.reise.ruter.data.Place;
import com.reise.ruter.support.Variables.PlaceType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PlaceListAdapter extends ArrayAdapter<Place> {
	private Context context;
	private ArrayList<Place> places;
	private LayoutInflater inflater;
	
	public PlaceListAdapter(Context context,ArrayList<Place> places) {
		super(context,0, places);
		this.context = context;
		this.places = places;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
 
		Place place = places.get(position);
		if (place != null) {
			view = inflater.inflate(R.layout.list_search_default_item, null);
			TextView name = (TextView)view.findViewById(R.id.stop_name);
			name.setText(place.getName());
				
			TextView district = (TextView)view.findViewById(R.id.district_label);
			district.setText(place.getDistrict());
		}
		return view;
	}
	
	
	
}
