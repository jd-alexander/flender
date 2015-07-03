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
        methodwithToast();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        silentMethod();
    }

    /*
    *
    * Once internet is present, then this method will be executed.
    * If it's not then a toast will be shown saying no internet.
    * */
    @InternetRequired
    public void methodwithToast()
    {
        Toast.makeText(this,"This should only show if there's no internet.",Toast.LENGTH_SHORT).show();
    }

    /*
    Nothing is shown. The method just doesn't execute due
     to no internet being available*/
    @InternetRequired("Silent")
    public void silentMethod()
    {
        Toast.makeText(this,"This should only show if there's no internet.",Toast.LENGTH_SHORT).show();
    }


}
