package com.flender.example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.flender.weaving.Flender;
import com.flender.weaving.annotations.InternetRequired;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Flender.init(this);
        test();
    }
    @InternetRequired
    public void test()
    {
    }


}
