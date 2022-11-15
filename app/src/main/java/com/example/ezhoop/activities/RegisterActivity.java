package com.example.ezhoop.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ezhoop.App;
import com.example.ezhoop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Context context;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private EditText inputFullName, inputEmail, inputPassword, inputCountry;
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
        inputPassword = findViewById(R.id.input_password);

        inputFullName = findViewById(R.id.input_full_name);
        inputGender = findViewById(R.id.input_gender);

        inputDateOfBirth = findViewById(R.id.input_date_of_birth);
        inputDateOfBirth.setMaxDate(new Date().getTime());

        inputCountry = findViewById(R.id.input_country);

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
        String fullName = inputFullName.getText().toString();
        String country = inputCountry.getText().toString();

        if (email.equals("") || password.equals("") || fullName.equals("")) {
            Toast.makeText(context, "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth firebaseAuth = ((App) getApplicationContext()).firebaseAuth;
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                String uid = user.getUid();

                Calendar calendar = Calendar.getInstance();
                calendar.set(inputDateOfBirth.getYear(), inputDateOfBirth.getMonth(), inputDateOfBirth.getDayOfMonth());
                String dateOfBirth = sdf.format(calendar.getTime());

                RadioButton radioButton = inputGender.findViewById(inputGender.getCheckedRadioButtonId());
                String gender = radioButton.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Create a new user with a first and last name
                Map<String, Object> data = new HashMap<>();
                data.put("fullName", fullName);
                data.put("dateOfBirth", dateOfBirth);
                data.put("gender", gender);
                data.put("country", country);

                db.collection("users").document(uid).set(data)
                        .addOnSuccessListener(documentReference -> {
                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                            prefs.edit()
                                    .putString("fullName", fullName)
                                    .putString("dateOfBirth", dateOfBirth)
                                    .putString("gender", gender)
                                    .putString("country", country)
                                    .apply();

                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        })
                        .addOnFailureListener(e -> Log.e("Error", "Error adding user document", e));
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