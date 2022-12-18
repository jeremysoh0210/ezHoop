package com.example.ezhoop;

import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //
    @RunWith(RobolectricTestRunner.class)
    public class LoginActivityTest {

        private LoginActivity activity;
        private EditText inputEmail;
        private EditText inputPassword;
        private Button btnLogin;

        @Before
        public void setUp() {
            activity = Robolectric.buildActivity(LoginActivity.class).create().get();
            inputEmail = activity.findViewById(R.id.input_email);
            inputPassword = activity.findViewById(R.id.input_password);
            btnLogin = activity.findViewById(R.id.btn_login);
        }

        @Test
        public void testLoginButton() {
            // Test empty email and password
            inputEmail.setText("");
            inputPassword.setText("");
            btnLogin.performClick();
            assertEquals("Email & password cannot be empty.", ShadowToast.getTextOfLatestToast());

            // Test invalid email
            inputEmail.setText("invalid");
            inputPassword.setText("password");
            btnLogin.performClick();
            // Assert that login failed toast is shown
            assertEquals("Login failed.", ShadowToast.getTextOfLatestToast());

            // Test valid email and password
            inputEmail.setText("valid@email.com");
            inputPassword.setText("password");
            btnLogin.performClick();
            // Assert that the main activity is opened
            assertNotNull(activity.getNextStartedActivity());
            assertEquals(MainActivity.class.getCanonicalName(), activity.getNextStartedActivity().getComponent().getClassName());
        }

        @Test
        public void testTogglePasswordButton_togglesPasswordField() {
            // Set the password field to a known value
            inputPassword.setText("password123");

            // Click the toggle password button
            btnTogglePassword.performClick();

            // Check the value of the password field after it has been transformed
            assertEquals("password123", inputPassword.getText().toString());

            // Click the toggle password button again
            btnTogglePassword.performClick();

            // Check the value of the password field after it has been transformed again
            assertEquals("password123", inputPassword.getText().toString());
        }

        @Test
        public void testLoginButton_triggersLoginMethod() {
            // Set up a mock click listener to verify that it was called
            View.OnClickListener mockListener = Mockito.mock(View.OnClickListener.class);
            btnLogin.setOnClickListener(mockListener);

            // Click the login button
            btnLogin.performClick();

            // Verify that the mock listener was called
            Mockito.verify(mockListener).onClick(btnLogin);
        }
        //
}