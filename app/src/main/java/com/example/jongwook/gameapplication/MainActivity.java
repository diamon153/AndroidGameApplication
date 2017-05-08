package com.example.jongwook.gameapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PasswordDialogFragment.NoticeDialogListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    static String username;
    static TextView welcomeText;
    int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        username = settings.getString("username", "user");
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome " + username);
        state = 0;
    }

    public void menuClicked(View view) {

        Intent intent;
        switch(view.getTag().toString()) {
            case "rock":
                intent = new Intent(this, RockActivity.class);
                startActivity(intent);
                return;
            case "setting":
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void statusUpdate(View view) {
        int buttonPressed = Integer.parseInt(view.getTag().toString());
        if (state == buttonPressed) {
            state++;
            if (state == 4) {
                //Intent intent = new Intent(this, SecondActivity.class);
                //startActivity(intent);
                state = 0;
                showPasswordDialog();
            }
        } else {
            state = 0;
        }
    }


    public void showPasswordDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new PasswordDialogFragment();
        dialog.show(getSupportFragmentManager(), "PasswordDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive buttton
//        EditText passwordText = (EditText) dialog.findViewById(R.id.passwordText);
//
//        if (passwordText.getText().toString() == "yonsei") {
//            Intent intent = new Intent(this, SecondActivity.class);
//            startActivity(intent);
//        }


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
    }
}
