package usermodel;

import android.support.design.widget.TextInputEditText;

import java.util.List;

/**
 * Created by utsha on 19-01-2018.
 */

public class Transaction {
   private int user_id;
    private String expenseName;
    private long expense;
    public Transaction(){}

    public Transaction(int use_id, String expenseName,  long expense){
        this.user_id = user_id;
        this.expenseName=expenseName;
        this.expense = expense;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }
    public long getExpense() {
        return expense;
    }

    public void setExpense(TextInputEditText expense)
    {String incom = expense.getText().toString();
        int inco = new Integer(incom).intValue();
        this.expense = inco;
    }
    List <Transaction> l;
}
