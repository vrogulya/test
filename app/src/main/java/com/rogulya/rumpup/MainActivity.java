package com.rogulya.rumpup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NamesListFragment.OnListItemSelectedListener {

	private FragmentManager mfragmentManager = getFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NamesListFragment bf = new NamesListFragment();
		FragmentTransaction ft = mfragmentManager.beginTransaction();
		ft.add(R.id.container23, bf);
		ft.commit();



		setContentView(R.layout.act_main);
	}


	@Override
	public void onListItemSelected(int itemIndex) {
		DescriptionFragment fragment2 = (DescriptionFragment) mfragmentManager.findFragmentById(R.id.secondFragment);

		if (fragment2 == null || !fragment2.isVisible()) {
			fragment2 = new DescriptionFragment();

			FragmentTransaction ft = mfragmentManager.beginTransaction();
			Bundle args = new Bundle();
			args.putInt(DescriptionFragment.ITEM_INDEX, itemIndex);

			fragment2.setArguments(args);
			ft.replace(R.id.container23, fragment2);
			ft.addToBackStack(null);
			ft.commit();
		} else {
			fragment2.setDescription(itemIndex);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == MyService.RESULT_CODE) {
			NamesListFragment fragment = (NamesListFragment) mfragmentManager
					.findFragmentById(R.id.container23);
			fragment.addTomNames("UPDATED " + Calendar.getInstance().getTime());
			fragment.getAdapter().notifyDataSetChanged();
		}
	}
}
