package com.rogulya.rumpup;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends ListFragment  {
	private static List<String> mNames = new ArrayList<>(Arrays.asList("Apple", "Avocado", "Banana",
			"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
			"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple"));

	private ArrayAdapter<String> mAdapter;

	public BlankFragment() {
	}

	public List<String> getmNames() {
		return mNames;
	}

	public void addTomNames(String name) {
		this.mNames.add(name);
	}

	public ArrayAdapter<String> getAdapter() {
		return mAdapter;
	}

	public void setAdapter(ArrayAdapter<String> mAdapter) {
		this.mAdapter = mAdapter;
	}

	public void addToAdapter(String name) {
		this.mAdapter.add(name);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frg_list, container, false);
		Button btnDialog = (Button) view.findViewById(R.id.btnDialog);
		btnDialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				FragmentManager manager = getFragmentManager();
				FirstDialogFragment fdf = new FirstDialogFragment();
				fdf.show(manager, "dialog");
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, mNames);
		setListAdapter(mAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Toast.makeText(getActivity(), "Selected: " + position, Toast.LENGTH_SHORT).show();
		OnListItemSelectedListener listener = (OnListItemSelectedListener) getActivity();
		listener.onListItemSelected(position);
	}


	public interface OnListItemSelectedListener {
		void onListItemSelected(int itemIndex);
	}

}
