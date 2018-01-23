package com.example.utsha.expensemanager;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sql.databasehelper;
import usermodel.Transaction;
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

        TransAdapter adapter = new TransAdapter(this, user.getTransactions());
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.transList);
        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        Intent k = new Intent(this, ExpenseActivity.class);
        k.putExtra("EMAIL", getIntent().getExtras().getString("EMAIL"));
        k.putExtra("PASSWORD", getIntent().getExtras().getString("PASSWORD"));
        startActivity(k);
    }

    class TransAdapter extends ArrayAdapter<Transaction> {

        public TransAdapter(Context context, ArrayList<Transaction> transactions) {
          //  super();
             super(context, 0, transactions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Transaction transaction = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
            }

            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
            // Populate the data into the template view using the data object
            tvName.setText(transaction.getExpenseName());
            Log.i("APP_TAG",transaction.getExpenseName() + position);
            tvAmount.setText(String.valueOf(transaction.getExpense()));
            // Return the completed view to render on screen
            return convertView;
        }
    }

}
