package com.idunnolol.mvcdemo;

import com.idunnolol.mvcdemo.MainActivity.ModelListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment extends Fragment implements ModelListener {

	private TextView mTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, container, false);

		mTextView = (TextView) view.findViewById(R.id.text_view);

		// Initial setup of data
		updateTextView();

		// The button calls a method in the controller to change state
		Button button = (Button) view.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((MainActivity) getActivity()).increaseCount();
			}
		});

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		((MainActivity) activity).addListener(this);
	}

	@Override
	public void onDetach() {
		super.onDetach();

		((MainActivity) getActivity()).removeListener(this);
	}

	private void updateTextView() {
		// View gets data from the InstanceFragment
		int count = ((MainActivity) getActivity()).getInstance().mCount;
		mTextView.setText("The button has been pressed " + count + " time(s)");
	}

	@Override
	public void onModelChanged() {
		updateTextView();
	}
}
