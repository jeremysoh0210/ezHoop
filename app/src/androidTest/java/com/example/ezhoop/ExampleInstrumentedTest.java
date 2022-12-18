package com.example.ezhoop;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;



import com.example.ezhoop.activities.LoginActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private EditText usernameField, passwordField;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.ezhoop", appContext.getPackageName());
    }

//    public void testUserNameAndPasswordFields()
//    {
//        usernameField = findViewByID(R.id.input_email);
//        passwordField = findViewByID(R.id.input_password);
//
//        assertNotNull(usernameField);
//        assertNotNull(passwordField);
//
//        usernameField.requestFocus();
//        passwordField.requestFocus();
//
//
//    }
}