package com.rogulya.rumpup;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyService extends Service {

	private final String FILENAME = "file.txt";

	public MyService() {
	}


	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {

		new Thread(new Runnable() {
			public void run() {
				String names = intent.getStringExtra("names");

				try {
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
							openFileOutput(FILENAME, MODE_PRIVATE)));
					bw.write(names);
					bw.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}


				// to sd card
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					return;
				}
				File sdPath = Environment.getExternalStorageDirectory();
				sdPath = new File(sdPath.getAbsolutePath() + "/myFiles");
				sdPath.mkdirs();
				File sdFile = new File(sdPath, FILENAME);
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
					bw.write(names);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				PendingIntent pi = intent.getParcelableExtra(FirstDialogFragment.PARAM_PINTENT);
				try {
					pi.send(42);
				} catch (PendingIntent.CanceledException e) {
					e.printStackTrace();
				}
			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
