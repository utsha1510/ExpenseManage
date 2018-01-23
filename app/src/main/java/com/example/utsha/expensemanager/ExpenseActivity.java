package com.example.utsha.expensemanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import sql.databasehelper;
import usermodel.Transaction;

public class ExpenseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textInputEditTextExpense;
    private TextInputEditText textInputEditTextExpenseName;

    private TextInputLayout textInputLayoutExpense;

    private AppCompatButton appCompatButtonfillInfo;

    private databasehelper databaseHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);
        initObjects();
        initViews();
        initListeners();
    }

    private void initObjects() {
        databaseHelper = new databasehelper(this);
    }

    private void initListeners() {
        appCompatButtonfillInfo.setOnClickListener((View.OnClickListener) this);
    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        textInputLayoutExpense = (TextInputLayout) findViewById(R.id.textInputLayoutExpense);
        textInputEditTextExpense = (TextInputEditText) findViewById(R.id.textInputEditTextExpense);
        textInputEditTextExpenseName = (TextInputEditText) findViewById(R.id.textInputEditTextExpenseName);
        appCompatButtonfillInfo = (AppCompatButton) findViewById(R.id.button);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.button)
            postDataToSQLite();

        Intent intent = new Intent(this, UserExpenseActivity.class);
        intent.putExtra("EMAIL", getIntent().getExtras().getString("EMAIL"));
        intent.putExtra("PASSWORD", getIntent().getExtras().getString("PASSWORD"));
        startActivity(intent);
    }

    private void postDataToSQLite() {
        Transaction tranasaction = new Transaction();
        Log.i("APP_TAG", getIntent().getExtras().getString("EMAIL"));

        tranasaction.setEmail(getIntent().getExtras().getString("EMAIL"));
        tranasaction.setExpense(textInputEditTextExpense);
        tranasaction.setExpenseName(textInputEditTextExpenseName);
        databaseHelper.addTransaction(tranasaction);
    }

    private void emptyInputEditText() {
        textInputEditTextExpense.setText(null);
    }






}

