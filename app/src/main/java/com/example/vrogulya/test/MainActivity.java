package com.example.vrogulya.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    String[] namesArray;
    ArrayAdapter<String> adapter;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namesArray = getResources().getStringArray(R.array.names);
        names = new ArrayList<String>(Arrays.asList(namesArray));

        ListView lvMain = (ListView) findViewById(R.id.listMain);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                names);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(this);

        Button button = (Button) findViewById(R.id.buttonMain);
        button.setOnClickListener(this);
        Button btnDb = (Button) findViewById(R.id.btndb);
        btnDb.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonMain:
                addItems(view);
                break;
            case R.id.btndb:
                Intent i = new Intent(this, DBActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent intent = new Intent(this, TwoActivity.class);
        intent.putExtra("name", names.get(pos));
        intent.putExtra("position", pos);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String name = data.getStringExtra("name");
        int position = data.getIntExtra("position", 0);
        names.set(position, name);
        adapter.notifyDataSetChanged();
    }

    public void addItems(View v) {
        EditText text = (EditText) findViewById(R.id.editText2);
        if (text.getText().toString().trim().equals("")) {
            text.setError("name is required!");
        } else {
            names.add(text.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }
}