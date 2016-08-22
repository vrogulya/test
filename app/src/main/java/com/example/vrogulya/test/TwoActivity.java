package com.example.vrogulya.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TwoActivity extends AppCompatActivity {
    EditText et;
    Button btnEdir;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        String name = intent.getStringExtra("name");
        et = (EditText) findViewById(R.id.editText);
        et.setText(name);

        btnEdir = (Button) findViewById(R.id.editButton);
        btnEdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", et.getText().toString());
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
