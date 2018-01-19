package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import usermodel.Transaction;
import usermodel.User;

import static sql.databasehelper.TABLE_USER;

/**
 * Created by utsha on 18-01-2018.
 */

public class expenseDatabase extends SQLiteOpenHelper {
    private static final  int  DATABASE_VERSION = 1;
    private static final String COLUMN_USER_ID = "user_id";
    private static final String DATABASE_NAME = "UserExpense.db";
    private static final String TABLE_USER_EXPENSE = "userExpense";
    private static final String COLUMN_EXPENSE_NAME = "expense_name";
    private static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";

    private String CREATE_USER_EXPENSE_TABLE = "CREATE TABLE " + TABLE_USER_EXPENSE + "("
            + COLUMN_USER_ID + " INTEGER ,FOREIGN KEY(COLUMN_USER_ID) REFERENCES " + TABLE_USER +"(USER_ID)," +  COLUMN_EXPENSE_NAME + " TEXT,"
            + COLUMN_EXPENSE_AMOUNT + " LONG " +
            ")";
    private Context context;
    private String DROP_USER_EXPENSE_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER_EXPENSE;
    public expenseDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public expenseDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }
    public void addUser(Transaction user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getUser_id());
        values.put(COLUMN_EXPENSE_NAME, user.getExpenseName());
        values.put(COLUMN_EXPENSE_AMOUNT, user.getExpense());
        //values.put(COLUMN_SAVINGS, user.getSavings());

        long newRowId=db.insert(TABLE_USER_EXPENSE, null, values);
        if (newRowId == -1) {
            Toast.makeText(this.context, "Error with adding expense", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.context, "Expense added successfully " , Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_EXPENSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_EXPENSE_TABLE);
        onCreate(db);
    }
}
