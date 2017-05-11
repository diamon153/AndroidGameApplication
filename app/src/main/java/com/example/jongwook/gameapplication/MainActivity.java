package com.example.jongwook.gameapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    static final String userID = "ryan";
    static String partnerID;
    static String roomID;
    static int order;

    static String username;
    static TextView welcomeText;
    int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the welcome text
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        username = settings.getString("username", "user");
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome " + username);
        state = 0;

        // Get relevant data from firebase
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users/" + userID);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                order = dataSnapshot.child("order").getValue(Integer.class);
                partnerID = dataSnapshot.child("partner").getValue(String.class);
                roomID = dataSnapshot.child("room").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    // Find out which menu button was clicked and move to a different activity
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

    // Secret options menu
    public void statusUpdate(View view) {
        int buttonPressed = Integer.parseInt(view.getTag().toString());
        if (state == buttonPressed) {
            state++;
            if (state == 4) {
                state = 0;
                showPasswordDialog();
            }
        } else {
            state = 0;
        }
    }

    // Password dialog for the secret activity
    public void showPasswordDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                // Add action buttons
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        EditText passwordText;
                        passwordText = (EditText) f.findViewById(R.id.passwordText);
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                        String password = settings.getString("password", "yonsei");
                        if (passwordText.getText().toString().equals(password)) {
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            startActivity(intent);
                        } else {
                            dialog.dismiss();
                        }

                    }
                })
                .setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
