package com.example.jongwook.gameapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void secondMenuClicked(View view) {

        Intent intent;
        switch(view.getTag().toString()) {
            case "setting":
                intent = new Intent(this, SecondSettingActivity.class);
                startActivity(intent);
                return;
            default:
                return;
        }

    }
}
