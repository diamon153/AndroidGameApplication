package com.example.jongwook.gameapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_setting);
    }

    public void changePassword(View view) {
        EditText oldPasswordEditText = (EditText) findViewById(R.id.oldPasswordEditText);
        EditText newPasswordEditText = (EditText) findViewById(R.id.newPasswordEditText);
        EditText newPasswordRepeatEditText = (EditText) findViewById(R.id.newPasswordRepeatEditText);
        String newPassword = newPasswordEditText.getText().toString();

        // Check if the repeats are the same
        if (newPassword.equals(newPasswordRepeatEditText.getText().toString())) {

            // Check if old password is correct
            SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
            if(settings.getString("password", "yonsei").equals(oldPasswordEditText.getText().toString())) {
                settings.edit().putString("password", newPassword).commit();
                Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show();
                oldPasswordEditText.setText("");
                newPasswordEditText.setText("");
                newPasswordRepeatEditText.setText("");
            }  else {
                oldPasswordEditText.setText("");
                Toast.makeText(this, "Current password is incorrect", Toast.LENGTH_SHORT).show();
            }
        } else {
            newPasswordEditText.setText("");
            newPasswordRepeatEditText.setText("");
            Toast.makeText(this, "Repeated password does not match", Toast.LENGTH_SHORT).show();
        }
    }
}
