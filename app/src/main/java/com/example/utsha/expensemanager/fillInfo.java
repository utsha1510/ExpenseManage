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
import usermodel.User;

public class fillInfo extends AppCompatActivity {
Button button;

    private TextInputEditText textInputEditTextIncome;
    private TextInputEditText textInputEditTextSavings;
    private TextInputEditText textInputEditTextBudget;
    private TextInputEditText textInputEditTextExpense;

    private TextInputLayout textInputLayoutSavings;
    private TextInputLayout textInputLayoutExpense;
    private TextInputLayout textInputLayoutIncome;
    private TextInputLayout textInputLayoutBudget;
    private AppCompatButton appCompatButtonfillInfo;
    private final AppCompatActivity activity = fillInfo.this;

    private databasehelper databaseHelper;
    private User user;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);
        initObjects();

    button.setOnClickListener((View.OnClickListener) this);}
    private void initObjects() {

        databaseHelper = new databasehelper(activity);
        user = new User();

    }
    private void initListeners() {
        appCompatButtonfillInfo.setOnClickListener((View.OnClickListener) this);


    }
    @SuppressLint("WrongViewCast")
    private void initViews() {
        textInputLayoutIncome = (TextInputLayout) findViewById(R.id.textInputLayoutIncome);
        textInputLayoutSavings = (TextInputLayout) findViewById(R.id.textInputLayoutSavings);
        textInputLayoutBudget = (TextInputLayout) findViewById(R.id.textInputLayoutBudget);
        textInputLayoutExpense = (TextInputLayout) findViewById(R.id.textInputLayoutExpense);
        textInputEditTextIncome = (TextInputEditText) findViewById(R.id.textInputEditTextIncome);
        textInputEditTextSavings = (TextInputEditText) findViewById(R.id.textInputEditTextSavings);
        textInputEditTextBudget = (TextInputEditText) findViewById(R.id.textInputEditTextBudget);
        textInputEditTextExpense = (TextInputEditText) findViewById(R.id.textInputEditTextExpense);
        appCompatButtonfillInfo = (AppCompatButton) findViewById(R.id.appCompatButtonfillInfo);



    }

    public void onClick(View v) {

       if( v.getId()== R.id.appCompatButtonfillInfo)
                postDataToSQLite();


        Intent i= new Intent(this,UserExpenseActivity.class);
        startActivity(i);

    }
    private void postDataToSQLite() {


            user.setExpense(textInputEditTextExpense);
            user.setBudget(textInputEditTextBudget);
        user.setIncome(textInputEditTextIncome);
        user.setSavings(textInputEditTextSavings);
            databaseHelper.addUser(user);



    }
    private void emptyInputEditText() {
        textInputEditTextIncome.setText(null);
        textInputEditTextBudget.setText(null);
        textInputEditTextSavings.setText(null);
        textInputEditTextExpense.setText(null);

    }


   /* public void  onClick(View view)
    {
        String d=edit1.getText().toString();
        String m=edit2.getText().toString();
        String p=edit3.getText().toString();
        ContentValues values = new ContentValues();
        values.put("INCOME",d );
        values.put("SAVINGS",m);
        values.put("BUDGET",p);

        user.insertOrThrow("user", null,values);
        Intent k = new Intent(this, UserExpenseActivity.class);
        startActivity(k);

    }*/
}

