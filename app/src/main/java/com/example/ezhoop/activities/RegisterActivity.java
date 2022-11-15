package com.example.ezhoop.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ezhoop.App;
import com.example.ezhoop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private Context context;

    private EditText inputFullName, inputEmail, inputPassword;
    private DatePicker inputDateOfBirth;
    private RadioGroup inputGender;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;

        setupViews();
    }

    private void setupViews() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password1);

        inputFullName = findViewById(R.id.input_full_name);
        inputDateOfBirth = findViewById(R.id.input_date_of_birth);
        inputGender = findViewById(R.id.input_gender);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(v -> register());
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)
                    break;
            case R.id.radio_female:
                if (checked)
                    break;
        }
    }

    private void register() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        FirebaseAuth firebaseAuth = ((App) getApplicationContext()).firebaseAuth;
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                // TODO: Add user data to Firestore w/ UID
            } else {
                Toast.makeText(context, "Registration failed.", Toast.LENGTH_SHORT).show();
            }
        });
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
}