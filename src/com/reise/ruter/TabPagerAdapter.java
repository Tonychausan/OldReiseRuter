package com.reise.ruter;

import com.reise.ruter.RealTime.RealTimeFragment;
import com.reise.ruter.TravelPlanner.TravelPlannerFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabPagerAdapter extends FragmentPagerAdapter {
	private Context ctx; 
	
    public TabPagerAdapter(FragmentManager fm) {
		super(fm);
	}
    
    @Override  
    public int getCount() {  
         return 3;  
    }  
    
	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		switch(position){
		case 0:
			fragment = new TravelPlannerFragment();
			break;
		case 1:
			fragment = new RealTimeFragment();
			break;
		default:
			fragment = new MapSearchFragment();
			break;
		}
		return fragment;
	} 
}  