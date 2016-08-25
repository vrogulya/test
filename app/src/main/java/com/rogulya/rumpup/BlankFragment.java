package com.rogulya.rumpup;


import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends ListFragment implements AdapterView.OnItemClickListener {
	List<String> names = new ArrayList<>(Arrays.asList("Apple", "Avocado", "Banana",
			"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
			"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple"));
	ArrayAdapter<String> adapter;

	public BlankFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_fragment, container, false);
//
//		adapter = new ArrayAdapter<>(view.getContext(),
//				android.R.layout.simple_list_item_1, names);
//
//		lvMain.setOnItemClickListener(this);
//
//		Button btnDialog = (Button) view.findViewById(R.id.btnDialog);
//		btnDialog.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//				FragmentManager manager = getFragmentManager();
//				FirstDialogFragment fdf = new FirstDialogFragment();
//				fdf.show(manager, "dialog");
//			}
//		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, names);
		setListAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view1, int pos, long l) {
		OnListItemSelectedListener listener = (OnListItemSelectedListener) getActivity();
		listener.onListItemSelected(pos);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Toast.makeText(getActivity(), "Вы выбрали позицию: " + position, Toast.LENGTH_SHORT).show();
		OnListItemSelectedListener listener = (OnListItemSelectedListener) getActivity();
		listener.onListItemSelected(position);
	}

	public interface OnListItemSelectedListener {
		void onListItemSelected(int itemIndex);
	}

}
