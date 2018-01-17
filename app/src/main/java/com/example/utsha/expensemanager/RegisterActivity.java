package com.example.utsha.expensemanager;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import sql.databasehelper;
import usermodel.User;


    public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

        private final AppCompatActivity activity = RegisterActivity.this;

        private NestedScrollView nestedScrollView;

        private TextInputLayout textInputLayoutName;
        private TextInputLayout textInputLayoutEmail;
        private TextInputLayout textInputLayoutPassword;
        private TextInputLayout textInputLayoutConfirmPassword;
        private TextInputLayout textInputLayoutIncome;
        private TextInputLayout textInputLayoutBudget;

        private TextInputEditText textInputEditTextName;
        private TextInputEditText textInputEditTextEmail;
        private TextInputEditText textInputEditTextPassword;
        private TextInputEditText textInputEditTextConfirmPassword;
        private TextInputEditText textInputEditTextIncome;
        private TextInputEditText textInputEditTextBudget;

        private AppCompatButton appCompatButtonRegister;
        private AppCompatTextView appCompatTextViewLoginLink;

        private inputValidation inputValidation;
        private databasehelper databaseHelper;
        private User user;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            getSupportActionBar().hide();

            initViews();
            initListeners();
            initObjects();
        }


        private void initViews() {
            nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

            textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
            textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
            textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
            textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
            textInputLayoutIncome= (TextInputLayout) findViewById(R.id.textInputLayoutIncome);
            textInputLayoutBudget= (TextInputLayout) findViewById(R.id.textInputLayoutBudget);
            textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
            textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
            textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
            textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
            textInputEditTextIncome = (TextInputEditText) findViewById(R.id.textInputEditTextIncome);
            textInputEditTextBudget = (TextInputEditText) findViewById(R.id.textInputEditTextBudget);
            appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

            appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        }

        /**
         * This method is to initialize listeners
         */
        private void initListeners() {
            appCompatButtonRegister.setOnClickListener(this);
            appCompatTextViewLoginLink.setOnClickListener(this);

        }

        /**
         * This method is to initialize objects to be used
         */
        private void initObjects() {
            inputValidation = new inputValidation(activity);
            databaseHelper = new databasehelper(activity);
            user = new User();

        }


        /**
         * This implemented method is to listen the click on view
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.appCompatButtonRegister:
                    postDataToSQLite();
                    break;

                case R.id.appCompatTextViewLoginLink:
                    finish();
                    break;
            }
        }

        /**
         * This method is to validate the input text fields and post data to SQLite
         */
        private void postDataToSQLite() {
            if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                return;
            }
            /*if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                return;
            }*/
            if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
                return;
            }
            if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                    textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
                return;
            }

            if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

                user.setName(textInputEditTextName.getText().toString().trim());
                user.setEmail(textInputEditTextEmail.getText().toString().trim());
                user.setPassword(textInputEditTextPassword.getText().toString().trim());
                user.setIncome(textInputEditTextIncome);
                user.setBudget(textInputEditTextBudget);
                databaseHelper.addUser(user);

                // Snack Bar to show success message that record saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                emptyInputEditText();


            } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            }


        }

        /**
         * This method is to empty all input edit text
         */
        private void emptyInputEditText() {
            textInputEditTextName.setText(null);
            textInputEditTextEmail.setText(null);
            textInputEditTextPassword.setText(null);
            textInputEditTextConfirmPassword.setText(null);

        }
    }

