package com.rogulya.rumpup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DBActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TABLE_NAMES = "Names";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    Button btnAdd;
    Button btnRead;
    Button btnClear;
    EditText etName;
    EditText etEmail;
    DBHelper dbHelper;
    final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.btnAdd:
                if (etName.getText().toString().trim().equals("")) {
                    etName.setError("name is required!");
                } else {
                    cv.put(COLUMN_NAME, etName.getText().toString());
                    long rowID = db.insert(TABLE_NAMES, null, cv);
                    Log.d(LOG_TAG, " new row " + rowID);
                }
                break;
            case R.id.btnRead:
                Cursor c = db.query(TABLE_NAMES, null, null, null, null, null, null);

                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex(COLUMN_ID);
                    int nameColIndex = c.getColumnIndex(COLUMN_NAME);

                    do {
                        Log.d(LOG_TAG,
                                COLUMN_ID + " = " + c.getInt(idColIndex) +
                                        "," + COLUMN_NAME + "= " + c.getString(nameColIndex));
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();
                break;
            case R.id.btnClear:
                int clearCount = db.delete(TABLE_NAMES, null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;
        }
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAMES + "("
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + COLUMN_NAME + " text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
