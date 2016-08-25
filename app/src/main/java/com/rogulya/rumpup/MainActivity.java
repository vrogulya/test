package com.rogulya.rumpup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnListItemSelectedListener {

    private boolean mIsDynamic;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

//        FragmentManager fragmentManager = getFragmentManager();
//        BlankFragment2 fragment2 = (BlankFragment2) fragmentManager
//                .findFragmentById(R.id.secondFragment);
//        mIsDynamic = fragment2 == null || !fragment2.isInLayout();

//        if (mIsDynamic) {
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            BlankFragment fragment1 = new BlankFragment();
//            ft.add(R.id.container, fragment1);
//            ft.commit();
//        }
    }


    @Override
    public void onListItemSelected(int itemIndex) {
        fragmentManager = getFragmentManager();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("____________" + resultCode);

        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();

        BlankFragment fragment = (BlankFragment) fragmentManager
                .findFragmentById(R.id.listFragment);
        fragment.adapter.add("UPDATED");


        System.out.println(fragment + "...............................");
    }

}
