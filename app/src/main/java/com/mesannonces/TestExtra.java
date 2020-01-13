package com.mesannonces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TestExtra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_extra);

        TextView testText = (TextView) findViewById(R.id.testText);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("testText") != null) {
            testText.setText(bundle.getString("testText"));
        }
    }
}
