package com.idunnolol.mvcdemo;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

	private String TAG_INSTANCE_FRAGMENT = "TAG_INSTANCE_FRAGMENT";

	private InstanceFragment mInstance;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Either create a new InstanceFragment, or retrieve the retained copy
		FragmentManager fm = getFragmentManager();
		mInstance = (InstanceFragment) fm.findFragmentByTag(TAG_INSTANCE_FRAGMENT);
		if (mInstance == null) {
			mInstance = InstanceFragment.newInstance();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(mInstance, TAG_INSTANCE_FRAGMENT);
			ft.commit();
		}

		setContentView(R.layout.main);
	}

	public InstanceFragment getInstance() {
		return mInstance;
	}

	// When the count is increased, we notify listeners that the underlying data has changed
	public void increaseCount() {
		mInstance.mCount++;
		notifyListeners();
	}

	// This is a simple event interface to notify Fragments when the underlying model has changed

	public interface ModelListener {
		public void onModelChanged();
	};

	private Set<ModelListener> mListeners = new HashSet<ModelListener>();

	public void addListener(ModelListener listener) {
		mListeners.add(listener);
	}

	public void removeListener(ModelListener listener) {
		mListeners.remove(listener);
	}

	private void notifyListeners() {
		for (ModelListener listener : mListeners) {
			listener.onModelChanged();
		}
	}
}