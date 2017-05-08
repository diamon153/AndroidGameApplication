package com.example.jongwook.gameapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    TextView settingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        settingText = (TextView) findViewById(R.id.settingText);
        settingText.setText("Current username: " + MainActivity.username);
    }

    public void changeUsername(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        String newUsername = usernameEditText.getText().toString();

        MainActivity.username = newUsername;
        MainActivity.welcomeText.setText("Welcome " + newUsername);
        settingText.setText("Current username: " + newUsername);
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        settings.edit().putString("username", newUsername).commit();

    }
}
