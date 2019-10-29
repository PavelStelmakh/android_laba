package com.example.lenovo.laba;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddPill extends Fragment
{
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.add_pill, container,
                false);

        Button submit = rootView.findViewById(R.id.button_submit);

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

            long res = MainActivity.db.insert(DatabaseHelper.TABLE, null, cv);
            }
        });

        return rootView;
    }
}
