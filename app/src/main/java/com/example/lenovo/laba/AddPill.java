package com.example.lenovo.laba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            }
        });

        return rootView;
    }
}
