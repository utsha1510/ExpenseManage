package sql;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import usermodel.Transaction;
import usermodel.User;

public class databasehelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    public static final String TABLE_USER = "user";
    private static final String TABLE_USER_EXPENSE = "userExpense";

    private static final String COLUMN_EXPENSE_NAME = "expense_name";
    private static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";

    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_INCOME = "user_income";
    private static final String COLUMN_SAVINGS = "user_savings";
    private static final String COLUMN_BUDGET = "user_budget";
    private static final String COLUMN_EXPENSE = "user_expense";
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_INCOME + " LONG, "
            + COLUMN_SAVINGS + " LONG, " + COLUMN_BUDGET + " LONG, " + COLUMN_EXPENSE + " LONG " +
            ")";

    private String CREATE_USER_EXPENSE_TABLE = "CREATE TABLE " + TABLE_USER_EXPENSE + "("
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_EXPENSE_NAME + " TEXT,"
            + COLUMN_EXPENSE_AMOUNT + " LONG " +
            ")";

    private Context context;

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_USER_EXPENSE_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER_EXPENSE;

    public databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public databasehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_EXPENSE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_USER_EXPENSE_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_INCOME, user.getIncome());
        values.put(COLUMN_BUDGET, user.getBudget());
        //values.put(COLUMN_EXPENSE, user.getExpense());
        //values.put(COLUMN_SAVINGS, user.getSavings());

        long newRowId = db.insert(TABLE_USER, null, values);
        if (newRowId == -1) {
            Toast.makeText(this.context, "Error with registering", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.context, "registered successfully ", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public boolean checkUser(String email) {

        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public User getUser(String email, String password) {

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_INCOME,
                COLUMN_BUDGET
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        cursor.moveToNext();

        String transColumns[] = {COLUMN_USER_EMAIL, COLUMN_EXPENSE_NAME, COLUMN_EXPENSE_AMOUNT};

        String transSelection = COLUMN_USER_EMAIL + " = ?";
        String transSelectionArgs[] = {email};

        Cursor transCursor = db.query(TABLE_USER_EXPENSE,
                                transColumns,
                                transSelection,
                                transSelectionArgs,
                                null,null,null);

        Long totalExpense = 0l;

        List<Transaction> transactionList = new ArrayList<Transaction>();
        while(transCursor.moveToNext()){
            Transaction transaction = new Transaction(transCursor.getString(0),transCursor.getString(1),transCursor.getInt(2));
            transactionList.add(transaction);
            totalExpense += transCursor.getLong(2);
        }

        for (Transaction trans: transactionList){
            Log.i("APP_TAG",trans.getEmail() + " " + trans.getExpense() + trans.getExpenseName());
        }

        User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getLong(4), cursor.getLong(5), totalExpense, transactionList);

        cursor.close();
        db.close();

        return user;
    }

    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, transaction.getEmail());
        values.put(COLUMN_EXPENSE_NAME, transaction.getExpenseName());
        values.put(COLUMN_EXPENSE_AMOUNT, transaction.getExpense());

        long newRowId = db.insert(TABLE_USER_EXPENSE, null, values);
        if (newRowId == -1) {
            Toast.makeText(this.context, "Error with adding expense", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.context, "Expense added successfully ", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

}


