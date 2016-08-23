package com.example.vrogulya.test.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vrogulya.test.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    EditText et;
    Button btnEdir;
    int position;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra("position", 0);
        String name = intent.getStringExtra("name");
        et = (EditText) view.findViewById(R.id.editText);
        et.setText(name);

        btnEdir = (Button) view.findViewById(R.id.editButton);
        btnEdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", et.getText().toString());
                intent.putExtra("position", position);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
