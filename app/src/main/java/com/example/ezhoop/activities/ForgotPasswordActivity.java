package com.example.ezhoop.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//import com.example.handyopinion.R;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ezhoop.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Context context;
    EditText etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        viewInitializations();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void viewInitializations() {
        etEmail = findViewById(R.id.et_email);

        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    // Checking if the input in form is valid
//    boolean validateInput() {
//
//        if (etEmail.getText().toString().equals("")) {
//            etEmail.setError("Please Enter Email");
//            return false;
//        }
//
//        // checking the proper email format
//        if (!isEmailValid(etEmail.getText().toString())) {
//            etEmail.setError("Please Enter Valid Email");
//            return false;
//        }
//
//
//        return true;
//    }
//
//    boolean isEmailValid(String email) {
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//    // Hook Click Event
//
//    public void performCodeVerify (View v) {
//        if (validateInput()) {
//
//            // Input is valid, here send data to your server
//
//            String email = etEmail.getText().toString();
//
//            Intent intent = new Intent(this, EmailVerify.class);
//            startActivity(intent);
//            Toast.makeText(this,"Email send to Register Email Address",Toast.LENGTH_SHORT).show();
//            // Here you can call you API
//            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
//
//        }
//    }

}