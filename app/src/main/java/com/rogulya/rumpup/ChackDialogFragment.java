package com.rogulya.rumpup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by vrogulya on 25.08.2016.
 */
public class ChackDialogFragment extends DialogFragment {

	public static final String PARAM_PINTENT = "pintent";
	public static final String NAMES = "names";
	MyService myService;
	ServiceConnection sConn;
	Intent intent;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final String[] catNamesArray = {"chuck norris", "chuck", "norris"};
		final boolean[] checkedItemsArray = {false, true, false};
		intent = new Intent(getActivity(), MyService.class);
		sConn = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
				myService = ((MyService.MyBinder) iBinder).getService();
				Log.d("aaa", "MainActivity onServiceConnected");
			}

			@Override
			public void onServiceDisconnected(ComponentName componentName) {
				Log.d("aaa", "MainActivity onServiceDisconnected");
			}
		};
		getActivity().bindService(intent, sConn, getActivity().BIND_AUTO_CREATE);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Please select nicknames")
				.setMultiChoiceItems(catNamesArray, checkedItemsArray,
						new DialogInterface.OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
												int which, boolean isChecked) {
								checkedItemsArray[which] = isChecked;
							}
						})
				.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
												int id) {
								StringBuilder state = new StringBuilder();
								for (int i = 0; i < catNamesArray.length; i++) {
									state.append(catNamesArray[i]);
									if (checkedItemsArray[i])
										state.append(" selected\n");
									else
										state.append(" not selected\n");
								}
								myService.writeFileToMemory(state.toString());

								FragmentManager mfragmentManager = getFragmentManager();
								NamesListFragment listFragment = (NamesListFragment) mfragmentManager.findFragmentById(R.id.container23);

								listFragment.addTomNames("asdasd");
								listFragment.getAdapter().notifyDataSetChanged();
								myService.writeFileToSDCard(state.toString());
//								PendingIntent pi = getActivity().createPendingResult(123, new Intent(), 0);
//								Intent intent = new Intent(getActivity(), MyService.class)
//										.putExtra(NAMES, state.toString())
//										.putExtra(PARAM_PINTENT, pi);
//								getActivity().startService(intent);

							}
						})

				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
												int id) {
								dialog.cancel();
							}
						});

		return builder.create();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unbindService(sConn);
	}
}
