package com.reise.ruter.RealTime;

import com.reise.ruter.RealTime.PickStopInAreaFragment.OnPlaceSelectListener;
import com.reise.ruter.RealTime.Tables.GetRealTimeActivity;
import com.reise.ruter.PlaceChooserFragment;
import com.reise.ruter.R;
import com.reise.ruter.data.Place;
import com.reise.ruter.support.Variables.PlaceType;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class RealTimeFragment extends PlaceChooserFragment implements OnPlaceSelectListener{
	public static final String KEY_STRING = "RealTimeFragment";
	public static final int PICK_AREA_STOP_FRAGMENT = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_choose_place, container, false));
        this.setup();
        this.showStreets = false; //Don't show street on search
        this.showPOI = false;
        this.isRealTime = true;
        
        return view;
    }

	@Override
	protected void selectPlace(int position){
		Place place = adapter.getItem(position);
		
		//if place is an AREA open dialog to choose a stop in area
		if(place.getPlaceType().equals(PlaceType.AREA)){
			DialogFragment pickAreaStopFragment = new PickStopInAreaFragment();
			pickAreaStopFragment.setTargetFragment(RealTimeFragment.this, PICK_AREA_STOP_FRAGMENT);
			
			// Hide soft keyboard
			InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(this.editSearchBar.getWindowToken(), 0);
			
				
			//Give user a list of stops at chosen area
			Bundle args = new Bundle();
			args.putParcelable(PickStopInAreaFragment.PLACE, place);
			pickAreaStopFragment.setArguments(args);
	        
			pickAreaStopFragment.show(getActivity().getSupportFragmentManager(), "pickStopFragment");
		}
		else{
			startRealTimeTabels(place);
		}
		
	}
	
	public void startRealTimeTabels(Place place){
		Intent i = new Intent(getActivity(), GetRealTimeActivity.class);
		i.putExtra(KEY_STRING, place);
		startActivity(i);
	}

	@Override
	public void onPlaceSelect(Place place) {
		startRealTimeTabels(place);
	}
	
	
}
