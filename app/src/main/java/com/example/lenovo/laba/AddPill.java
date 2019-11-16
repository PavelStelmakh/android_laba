package com.example.lenovo.laba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.lenovo.laba.MainActivity.db;

public class AddPill extends Fragment
{
    View rootView;
    int _id = -1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.add_pill, container,
                false);

        Button submit = rootView.findViewById(R.id.button_submit);
        try {
            _id = getArguments().getInt("medicine_id", -1);

            if (_id >= 0) {
                Cursor userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                        DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(_id)});
                userCursor.moveToFirst();

                EditText inputName = rootView.findViewById(R.id.input_name);
                EditText inputProducer = rootView.findViewById(R.id.input_producer);
                EditText inputDescription = rootView.findViewById(R.id.input_description);
                EditText inputPrice = rootView.findViewById(R.id.input_price);

                inputName.setText(userCursor.getString(1));
                inputProducer.setText(userCursor.getString(3));
                inputPrice.setText(String.format("%2.2f", userCursor.getDouble(4)));
                inputDescription.setText(userCursor.getString(2));

                submit.setText("update");
            }
        } catch (Exception ex) {
            Log.e("AddPill", ex.getMessage());
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText inputName = rootView.findViewById(R.id.input_name);
            EditText inputProducer = rootView.findViewById(R.id.input_producer);
            EditText inputDescription = rootView.findViewById(R.id.input_description);
            EditText inputPrice = rootView.findViewById(R.id.input_price);

            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COLUMN_NAME, inputName.getText().toString());
            cv.put(DatabaseHelper.COLUMN_PRODUCER, inputProducer.getText().toString());
            cv.put(DatabaseHelper.COLUMN_DESCRIPTION, inputDescription.getText().toString());
            cv.put(DatabaseHelper.COLUMN_PRICE, Double.parseDouble(inputPrice.getText().toString()));

            if (_id >= 0) {
                db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(_id), null);
            }
            else {
                db.insert(DatabaseHelper.TABLE, null, cv);
            }

            View v = MainActivity.instance.getCurrentFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            Toast toast;
            if (_id >= 0) {
                toast = Toast.makeText(rootView.getContext(), "Pill updated", Toast.LENGTH_SHORT);
            }
            else {
                toast = Toast.makeText(rootView.getContext(), "Pill added", Toast.LENGTH_SHORT);
            }
            toast.show();
            }
        });

        return rootView;
    }
}
