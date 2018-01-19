package com.example.utsha.expensemanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sql.databasehelper;
import sql.expenseDatabase;
import usermodel.User;

public class fillInfo extends AppCompatActivity implements View.OnClickListener {
Button button;


    private TextInputEditText textInputEditTextExpense;


    private TextInputLayout textInputLayoutExpense;

    private AppCompatButton appCompatButtonfillInfo;
    private final AppCompatActivity activity = fillInfo.this;

    private expenseDatabase databaseHelper;
    private User user;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);
        initObjects();

    button.setOnClickListener((View.OnClickListener) this);}
    private void initObjects() {

        databaseHelper = new expenseDatabase(activity);
        user = new User();

    }
    private void initListeners() {
        appCompatButtonfillInfo.setOnClickListener((View.OnClickListener) this);


    }
    @SuppressLint("WrongViewCast")
    private void initViews() {

        textInputLayoutExpense = (TextInputLayout) findViewById(R.id.textInputLayoutExpense);
        textInputEditTextExpense = (TextInputEditText) findViewById(R.id.textInputEditTextExpense);
        appCompatButtonfillInfo = (AppCompatButton) findViewById(R.id.button);



    }

    public void onClick(View v) {

       if( v.getId()== R.id.button)
                postDataToSQLite();


        Intent i= new Intent(this,UserExpenseActivity.class);
        startActivity(i);

    }
    private void postDataToSQLite() {


            user.setExpense(textInputEditTextExpense);

           // expenseDatabase.addUser(user);



    }
    private void emptyInputEditText() {

        textInputEditTextExpense.setText(null);

    }



}

