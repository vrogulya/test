package com.rogulya.rumpup;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public static final int RESULT_CODE = 42;
    private static final String FILENAME = "file.txt";
    MyBinder binder = new MyBinder();
    public MyService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String names = intent.getStringExtra(ChackDialogFragment.NAMES);

                writeFileToMemory(names);
                writeFileToSDCard(names);

                PendingIntent pi = intent.getParcelableExtra(ChackDialogFragment.PARAM_PINTENT);
                try {
                    pi.send(RESULT_CODE);
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("aaa", "created service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("aaa", "destroyed service");
    }

    public void writeFileToMemory(String names) {
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
    }

    private void writeFileToSDCard(String names){
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
    }
}
