package com.idunnolol.mvcdemo;

import android.app.Fragment;

public class InstanceFragment extends Fragment {

	public static InstanceFragment newInstance() {
		InstanceFragment instance = new InstanceFragment();
		instance.setRetainInstance(true);
		return instance;
	}

	public int mCount = 0;
}
