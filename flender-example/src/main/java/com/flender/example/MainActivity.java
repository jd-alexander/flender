package com.flender.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.flender.weaving.Flender;
import com.flender.weaving.annotations.InternetRequired;
import com.flender.weaving.listeners.InternetUnavailable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Flender.init(this);
        testMethod();
    }

    @InternetRequired
    public void testMethod()
    {
        Toast.makeText(this,"Method is being called",Toast.LENGTH_SHORT).show();
    }


}
