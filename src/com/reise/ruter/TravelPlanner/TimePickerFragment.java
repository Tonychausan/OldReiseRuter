package com.reise.ruter.TravelPlanner;

import java.util.Calendar;

import com.reise.ruter.R;
import com.reise.ruter.support.TimeHolder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	final static String HOUR_TAG = "hour";
	final static String MINUTE_TAG = "minute";
	final static String isToday = "Today";

	final TimeHolder t = new TimeHolder(Calendar.getInstance());
	
	OnSetTimeListener callback;
	Boolean setNewTime = false; 
	
	
	public interface OnSetTimeListener{
		public void onSetTime(TimeHolder time);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		int hour = getArguments().getInt(TimePickerFragment.HOUR_TAG);
		int minute = getArguments().getInt(TimePickerFragment.MINUTE_TAG);
		
		// Create a new instance of TimePickerDialog and return it
		TimePickerDialog timePicker = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
		timePicker.setTitle(getResources().getString(R.string.time_picker_label));
		
		String setButton = getResources().getString(R.string.time_picker_setButton);
		String cancelButton = getResources().getString(R.string.time_picker_cancelButton);
		
		timePicker.setButton(DialogInterface.BUTTON_POSITIVE, setButton, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which)
		    {
		    	setNewTime = true;
		    }
		});
		timePicker.setButton(DialogInterface.BUTTON_NEGATIVE, cancelButton, timePicker);
		return timePicker;
	}	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			callback = (OnSetTimeListener) this.getTargetFragment();
		}catch(ClassCastException e) {
			throw new ClassCastException("must implement OnSetTimeListener");
		}
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
		if(setNewTime){
			TimeHolder timeSet = new TimeHolder();
			timeSet.setTime(hourOfDay, minute);
			callback.onSetTime(timeSet);
		}
	}
}
