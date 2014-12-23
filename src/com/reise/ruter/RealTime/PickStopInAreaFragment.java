package com.reise.ruter.RealTime;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.reise.ruter.R;
import com.reise.ruter.data.Place;
import com.reise.ruter.list.PlaceListAdapter;

/*
 * When an area is selected on real-time a dialog with stops in the area will appear
 */

public class PickStopInAreaFragment extends DialogFragment {
	public static final String PLACE = "place";
	
	private ArrayList<Place> places = new ArrayList<Place>();
	protected PlaceListAdapter adapter;
	
	OnPlaceSelectListener callback;
	
	public interface OnPlaceSelectListener{
		public void onPlaceSelect(Place place);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		try{
			callback = (OnPlaceSelectListener) this.getTargetFragment();
		}catch(ClassCastException e) {
			throw new ClassCastException("must implement OnSetTimeListener");
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		Place area = this.getArguments().getParcelable(PLACE);
		builder.setTitle(this.getResources().getString(R.string.header_stopInArea));
		
		ListView placeList = new ListView(getActivity());
		
		for(int i = 0; i < area.getStops().length; i++)
			places.add((Place) area.getStops()[i]);
		
		adapter = new PlaceListAdapter(this.getActivity(), places);
		placeList.setAdapter(adapter);
		placeList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectPlace(position);
			} 
		 });
		builder.setView(placeList);
		
		return builder.create();	
	}
	
	private void selectPlace(int position){
		Place place = adapter.getItem(position);
		callback.onPlaceSelect(place);
		this.dismiss();
	}
	
}