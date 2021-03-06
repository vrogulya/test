package com.rogulya.rumpup;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DescriptionFragment extends Fragment {
	public static final String ITEM_INDEX = "item_index";
	private TextView mTextView;
	private int mItemIndex;

	public DescriptionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frg_blank_2, container, false);
		mTextView = (TextView) view.findViewById(R.id.text1);

		Bundle args = getArguments();
		mItemIndex = args != null ? args.getInt(ITEM_INDEX, -1) : -1;
		if (mItemIndex != -1) {
			setDescription(mItemIndex);
		}

//        Button btnBD = (Button) view.findViewById(R.id.btnBD);
//        btnBD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), DBActivity.class);
//                startActivity(intent);
//            }
//        });

		return view;
	}

	public void setDescription(int itemIndex) {
		mTextView.setText(new NamesListFragment().getmNames().get(itemIndex));
	}
}
