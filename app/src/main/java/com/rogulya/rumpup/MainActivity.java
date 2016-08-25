package com.rogulya.rumpup;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnListItemSelectedListener {


    private FragmentManager mfragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }


    @Override
    public void onListItemSelected(int itemIndex) {
        BlankFragment2 fragment2 = (BlankFragment2) mfragmentManager.findFragmentById(R.id.secondFragment);
        fragment2.setDescription(itemIndex);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyService.RESULT_CODE) {
            BlankFragment fragment = (BlankFragment) mfragmentManager
                    .findFragmentById(R.id.listFragment);
            fragment.addTomNames("UPDATED " + Calendar.getInstance().getTime());
            fragment.getAdapter().notifyDataSetChanged();
        }
    }

}
