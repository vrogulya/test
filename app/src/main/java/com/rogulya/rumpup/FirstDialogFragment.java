package com.rogulya.rumpup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by vrogulya on 25.08.2016.
 */
public class FirstDialogFragment extends DialogFragment {

	public static final String PARAM_PINTENT = "pintent";
	public static final String NAMES = "names";


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final String[] catNamesArray = {"chuck norris", "chuck", "norris"};
		final boolean[] checkedItemsArray = {false, true, false};

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
								Toast.makeText(getActivity(),
										state.toString(), Toast.LENGTH_LONG)
										.show();

//

								PendingIntent pi;
								Intent intent;
								pi = getActivity().createPendingResult(123, new Intent(), 0);
								intent = new Intent(new Intent(getActivity(), MyService.class)
										.putExtra(NAMES, state.toString())
										.putExtra(PARAM_PINTENT, pi));
								getActivity().startService(intent);

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

}
