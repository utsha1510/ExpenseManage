package usermodel;


import android.app.ActionBar;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
//import  android.*;

import com.example.utsha.expensemanager.R;

import java.util.ArrayList;
import java.util.List;

public class User extends ListActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private int id;
    private String name;
    private String email;
    private String password;
    private long income;
    private long budget;
    private long savings;
    private long expense;
    private List<Transaction> transactions;

    public User() {
    }

    public User(int id, String name, String email, String password, long income, long budget, long expense, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.income = income;
        this.budget = budget;
        this.savings = income - expense;
        this.expense = expense;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(TextInputEditText income) {
        String incom = income.getText().toString();
        int inco = new Integer(incom).intValue();
        this.income = inco;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(TextInputEditText budget) {
        String incom = budget.getText().toString();
        int inco = new Integer(incom).intValue();
        this.budget = inco;
    }

    public long getSavings() {
        return income - expense;
    }

    public void setSavings(TextInputEditText savings) {
        String incom = savings.getText().toString();
        int inco = new Integer(incom).intValue();
        this.savings = inco;
    }

    public long getExpense() {
        return expense;
    }

    public void setExpense(TextInputEditText expense) {
        String incom = expense.getText().toString();
        int inco = new Integer(incom).intValue();
        this.expense = inco;
    }

    SimpleAdapter mAdapter;

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }






















    /* ArrayAdapter<String> itemsAdapter =
              new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Transaction);
      ListView listView = (ListView) findViewById(R.id.list);
      listView.setAdapter(itemsAdapter);*/
    //Button b= (Button) findViewById(R.id.)
   /* public class MyListActivity extends ListActivity {
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            mCursor = this.getContentResolver().query(People.CONTENT_URI, null, null, null, null);
            startManagingCursor(mCursor);
List l= getTransactions();
            ListAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.two_line_list_item, mCursor,                                              // Pass in the cursor to bind to.
                    new String[] {User.COLUMN_EXPENSE_NAME,User.COLUMN_EXPENSE_AMOUNT},           // Array of cursor columns to bind to.
                    new int[] {android.R.id.text1, android.R.id.text2});
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 //   android.R.layout.simple_list_item_1, l);
            setListAdapter(adapter);
        }

        @Override
        protected void onListItemClick(ListView list, View v, int position, long id) {
            String item = (String) getListAdapter().getItem(position);
            list = getListView();
        }
        //  mAdapter = new SimpleAdapter(this,getTransactions(),android.R.layout.simple_list_item_1, new String[], android.R.id.list);
    }*/
}