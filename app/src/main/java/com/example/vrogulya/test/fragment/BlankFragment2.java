package com.example.vrogulya.test.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vrogulya.test.R;
import com.example.vrogulya.test.activity.InfoActivity;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment implements AdapterView.OnItemClickListener{
    String[] namesArray;
    ArrayAdapter<String> adapter;
    ArrayList<String> names;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        namesArray = getActivity().getResources().getStringArray(R.array.names);
        names = new ArrayList<String>(Arrays.asList(namesArray));

        ListView lvMain = (ListView) view.findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, names);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(this);
        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent intent = new Intent(getActivity().getApplicationContext(), InfoActivity.class);
        intent.putExtra("name", names.get(pos));
        intent.putExtra("position", pos);
        startActivityForResult(intent, 1);
    }
}
