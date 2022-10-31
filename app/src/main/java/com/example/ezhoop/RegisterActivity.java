package com.example.ezhoop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputFullName,inputEmailAddress, inputPassword;
    private DatePicker inputDateOfBirth;
    private RadioGroup inputGender;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFullName = findViewById(R.id.input_full_name);
        inputEmailAddress = findViewById(R.id.input_email_address);
        inputPassword = findViewById(R.id.input_password1);
        inputDateOfBirth = findViewById(R.id.input_date_of_birth);
        inputGender = findViewById(R.id.input_gender);
        btnRegister = findViewById(R.id.btn_register);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    break;
            case R.id.radio_female:
                if (checked)
                    break;
        }
    }
}