package com.flender.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.flender.weaving.Flender;
import com.flender.weaving.annotations.InternetRequired;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Flender.init(this);
        test();
    }

    @InternetRequired("Silent")
    public void test()
    {
        Toast.makeText(this,"Method is being called",Toast.LENGTH_SHORT).show();
    }


}
