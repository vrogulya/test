package com.rogulya.rumpup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnListItemSelectedListener {

    private boolean mIsDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        BlankFragment2 fragment2 = (BlankFragment2) fragmentManager
                .findFragmentById(R.id.secondFragment);
        mIsDynamic = fragment2 == null || !fragment2.isInLayout();

        if (mIsDynamic) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            BlankFragment fragment1 = new BlankFragment();
            ft.add(R.id.container, fragment1);
            ft.commit();
        }
    }


    @Override
    public void onListItemSelected(int itemIndex) {
        FragmentManager fragmentManager = getFragmentManager();
        BlankFragment2 fragment2;
        if (mIsDynamic) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragment2 = new BlankFragment2();
            Bundle args = new Bundle();
            args.putInt(BlankFragment2.ITEM_INDEX, itemIndex);
            fragment2.setArguments(args);

            ft.replace(R.id.container, fragment2);
            ft.addToBackStack(null);
//            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            ft.commit();
        } else {
            fragment2 = (BlankFragment2) fragmentManager.findFragmentById(R.id.secondFragment);
            fragment2.setDescription(itemIndex);

        }
    }

}
