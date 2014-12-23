package com.reise.ruter.TravelPlanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reise.ruter.PlaceChooserFragment;
import com.reise.ruter.R;
import com.reise.ruter.data.Place;

/*
 * This class is the fragment part of the activity of the same name
 */

public class TravelPlannerPlaceChooserFragment extends PlaceChooserFragment{
	OnPlaceSelectListener callback;
	
	public interface OnPlaceSelectListener{
		public void onSelectPlace(Place place);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        setView(inflater.inflate(R.layout.fragment_choose_place, container, false));
        
        this.setup();
        
        return view;
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			callback = (OnPlaceSelectListener) activity;
		}catch(ClassCastException e) {
			throw new ClassCastException("must implement OnSetTimeListener");
		}
	}

	@Override
	protected void selectPlace(int position) {
		Place selectedPlace = adapter.getItem(position);
		callback.onSelectPlace(selectedPlace);
	}
}
