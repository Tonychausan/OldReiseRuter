package com.reise.ruter.TravelPlanner;

import com.reise.ruter.R;
import com.reise.ruter.TravelPlanner.TravelPlannerPlaceChooserFragment.OnPlaceSelectListener;
import com.reise.ruter.data.Place;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.FrameLayout;

/*
 * Placer chooser dialog for start and destination in travel planner
 */

public class TravelPlannerPlaceChooserActivity extends FragmentActivity implements OnPlaceSelectListener{
	public static final String KEY_STRING = "Choose_Place";
	FrameLayout container;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choose_place);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.frame_search_container, new TravelPlannerPlaceChooserFragment()).commit();
		}
	}
	
	@Override
	public void onSelectPlace(Place place) {
		Intent intent=new Intent();  
        intent.putExtra(KEY_STRING, place);
          
        setResult(Activity.RESULT_OK, intent);  
		finish();
	}
	
}
