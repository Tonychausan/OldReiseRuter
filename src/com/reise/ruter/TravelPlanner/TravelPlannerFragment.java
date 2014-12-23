package com.reise.ruter.TravelPlanner;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.reise.ruter.R;
import com.reise.ruter.data.Place;
import com.reise.ruter.support.AnimationSupport;
import com.reise.ruter.support.TimeHolder;
import com.reise.ruter.support.Variables;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

/*
 * TravelPlannerFragment, fragment to search for a trip
 */

public class TravelPlannerFragment extends Fragment implements TimePickerFragment.OnSetTimeListener{
	public static final int TIME_PICKER_FRAGMENT = 0;
	
	// Request codes
	public static final int PICK_START_REQUEST = 1;
	public static final int PICK_DEST_REQUEST = 2;
	
	Calendar timeNow = Calendar.getInstance();
	TimeHolder timeHolder = new TimeHolder(Calendar.getInstance()); 
	
	RadioGroup groupArrOrDep;
	Spinner spinnerDate;
	Button buttonSetTime;
	Button buttonExpandAdvance;
	View viewAdvanceSettings;
	EditText editStart;
	EditText editDestination;
	Button buttonFinish;
	
	Place startPlace;
	Place destPlace;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View view = inflater.inflate(R.layout.fragment_travel_planner, container, false);
        
        // Setup chooser for start and destination
        editStart = (EditText) view.findViewById(R.id.edit_choose_start);
        setPlaceTouchListener(editStart);
        setPlaceClickListener(editStart, PICK_DEST_REQUEST);
        
       	editDestination = (EditText) view.findViewById(R.id.edit_choose_dest);
       	setPlaceTouchListener(editDestination);
       	setPlaceClickListener(editDestination, PICK_START_REQUEST);
       	
       	// Arrive and departure radiogroup
       	groupArrOrDep = (RadioGroup) view.findViewById(R.id.radiogroup_arrive_departure);
       	
       	// Setup dates
       	String[] dateList = makeDateList();
       	spinnerDate = (Spinner) view.findViewById(R.id.spinner_date);
       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dateList);
       	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       	spinnerDate.setAdapter(adapter);
       	
       	// Setup time
       	buttonSetTime = (Button) view.findViewById(R.id.button_time);
       	buttonSetTime.setText(timeHolder.timeToString());
       	buttonSetTime.setGravity(Gravity.CENTER);
       	buttonSetTime.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Open a dialog time-picker
				DialogFragment timePickerFragment = new TimePickerFragment();
				timePickerFragment.setTargetFragment(TravelPlannerFragment.this, TIME_PICKER_FRAGMENT); 
				
				Bundle args = new Bundle();
		        args.putInt(TimePickerFragment.HOUR_TAG, timeHolder.getHour());
		        args.putInt(TimePickerFragment.MINUTE_TAG, timeHolder.getMinute());
		        
		        timePickerFragment.setArguments(args);
				
			    timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
			}
       	});
       	
       	//TODO implement finish button
       	buttonFinish = (Button) view.findViewById(R.id.button_search_trip);
       	buttonFinish.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
       	});
       	
       	//advance settings button
       	viewAdvanceSettings = (View) view.findViewById(R.id.layout_advance_settings);
       	viewAdvanceSettings.setVisibility(View.GONE);
       	
       	buttonExpandAdvance = (Button) view.findViewById(R.id.button_expand_settings);
       	buttonExpandAdvance.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//expand advance menu if not expanded, or close it
				if(viewAdvanceSettings.isShown()){
					viewAdvanceSettings.clearAnimation();
					viewAdvanceSettings.setVisibility(View.GONE);					
				}
				else{
					viewAdvanceSettings.setVisibility(View.VISIBLE);
					AnimationSupport.settingDownSlider(getActivity(), viewAdvanceSettings);
				}
				
			}
       	});
       	
       	// Setup transport check-buttons
       	String[] transportLabels = getResources().getStringArray(R.array.transport_labels);
       	LinearLayout transportFilter = (LinearLayout) viewAdvanceSettings.findViewById(R.id.layout_transport_filter);
       	for(int i=0; i < transportLabels.length; i++){
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setChecked(true);
            checkBox.setText(transportLabels[i]);
            transportFilter.addView(checkBox);
       	}
       	
       	return view;
    }
    
    // Hover effect for choose destination/start
    private void setPlaceTouchListener(final EditText editText){
		editText.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motion) {
				//TODO change color when hovering
				switch(motion.getActionMasked()){
				case MotionEvent.ACTION_DOWN:
					editText.setHintTextColor(Color.BLUE);
					break;
				case MotionEvent.ACTION_UP:
					editText.setHintTextColor(Color.GRAY);
					
					break;
				case MotionEvent.ACTION_CANCEL:
					editText.setHintTextColor(Color.GRAY);
					
					break;
				}
				return false;
			}
		});
    }
    
    private void setPlaceClickListener(final EditText editText, final int key){
		editText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), TravelPlannerPlaceChooserActivity.class);
				startActivityForResult(intent, key);
			}
		});
    }
    
    
    // Make a list of dates
    private String[] makeDateList(){
    	Calendar dummyTime = Calendar.getInstance();
    	dummyTime.add(Calendar.DAY_OF_MONTH, 2);
    	
    	String[] week_day = getResources().getStringArray(R.array.week_days);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	int nWeekdays = Variables.NUMBUER_OF_WEEKDAYS;
    	String[] dates = new String[Variables.NUMBUER_OF_WEEKDAYS];
    	
    	for(int i = 0; i < nWeekdays; i++){
    		if(i == 0)
    			dates[i] = getResources().getString(R.string.Today);
    		else if(i == 1)
    			dates[i] = getResources().getString(R.string.Tomorrow);
    		else{
	    		dates[i] = week_day[dummyTime.get(Calendar.DAY_OF_WEEK)%7 ] + " - " + sdf.format(dummyTime.getTime());
	    		dummyTime.add(Calendar.DAY_OF_MONTH, 1);
	    	}
    	}
    	return dates;
    }

	@Override
	public void onSetTime(TimeHolder time) {
		timeHolder.setTime(time.getHour(), time.getMinute());
		buttonSetTime.setText(timeHolder.timeToString());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle b;
		Place place;
		if (requestCode == PICK_START_REQUEST && resultCode == Activity.RESULT_OK) {
			b = data.getExtras();
			place = b.getParcelable(TravelPlannerPlaceChooserActivity.KEY_STRING);
			destPlace = place;
			editDestination.setText(place.getName());
        }
		else if(requestCode == PICK_DEST_REQUEST && resultCode == Activity.RESULT_OK){
			b = data.getExtras();
			place = b.getParcelable(TravelPlannerPlaceChooserActivity.KEY_STRING);
			startPlace = place;
			editStart.setText(place.getName());
		}
		
	}
	
	
}
