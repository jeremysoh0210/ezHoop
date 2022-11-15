package com.example.ezhoop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ezhoop.App;
import com.example.ezhoop.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Context context;

    private EditText inputEmail, inputPassword;
    private Button btnLogin, btnLoginAsGuest, btnForgotPassword, btnRegister;
    private ImageButton btnTogglePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        setupViews();
    }

    private void setupViews() {
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> login());

        btnLoginAsGuest = findViewById(R.id.btn_continue_as_guest);
        btnForgotPassword = findViewById(R.id.btn_forgot_password);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);
        });

        btnForgotPassword = findViewById(R.id.btn_forgot_password);
        btnForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(context, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        setupTogglePassword();
    }

    private void setupTogglePassword() {
        btnTogglePassword = findViewById(R.id.input_password_toggle);
        btnTogglePassword.setOnClickListener(v -> {
            int selectionStart = inputPassword.getSelectionStart();

            if (inputPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                btnTogglePassword.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_hide_password));
                inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                btnTogglePassword.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_show_password));
                inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            inputPassword.setSelection(selectionStart);
        });
    }

    private void login() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(context, "Email & password cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth firebaseAuth = ((App) getApplicationContext()).firebaseAuth;
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, task -> {
            if (task.isSuccessful()) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            } else {
                Toast.makeText(context, "Login failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}