package com.pucmm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    TextView newData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        newData = (TextView) findViewById(R.id.newData);
        String sendData = getIntent().getStringExtra("dataP");
        newData.setText(sendData);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}