package com.example.utsha.expensemanager;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sql.databasehelper;
import usermodel.User;


public class UserExpenseActivity extends AppCompatActivity implements View.OnClickListener {

    private databasehelper databaseHelper;
    private Button button;

    private AppCompatTextView nameTextView;
    private AppCompatTextView incomeTextView;
    private AppCompatTextView savingTextView;
    private AppCompatTextView budgetTextView;
    private AppCompatTextView expenseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_expense);

        this.nameTextView = (AppCompatTextView) findViewById(R.id.textViewName);
        this.incomeTextView = (AppCompatTextView) findViewById(R.id.textViewIncome);
        this.savingTextView = (AppCompatTextView) findViewById(R.id.textViewSaving);

        this.expenseTextView = (AppCompatTextView) findViewById(R.id.textViewExpense);
        this.budgetTextView = (AppCompatTextView) findViewById(R.id.textViewBudget);

        databaseHelper = new databasehelper(this);

        User user = databaseHelper.getUser(getIntent().getExtras().getString("EMAIL"), getIntent().getExtras().getString("PASSWORD"));

        nameTextView.setText(user.getName());
        expenseTextView.setText("Expense: " + Long.toString(user.getExpense()));
        incomeTextView.setText("Income: " + Long.toString(user.getIncome()));
        budgetTextView.setText("Budget: " + Long.toString(user.getBudget()));
        savingTextView.setText("Savings: " + Long.toString(user.getSavings()));


        button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent k = new Intent(this, ExpenseActivity.class);
        k.putExtra("EMAIL", getIntent().getExtras().getString("EMAIL"));
        k.putExtra("PASSWORD", getIntent().getExtras().getString("PASSWORD"));
        startActivity(k);
    }
    public class UsersAdapter extends ArrayAdapter<User> {
        public UsersAdapter(UsersAdapter context, ArrayList<User> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list1, parent, false);
            }
            // Lookup view for data population
            ArrayList<User> arrayOfUsers = new ArrayList<User>();
            // Create the adapter to convert the array to views
            UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
            // Return the completed view to render on screen
            return convertView;
        }
    }

   
}
