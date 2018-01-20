package usermodel;

import android.support.design.widget.TextInputEditText;

import java.util.List;

/**
 * Created by utsha on 19-01-2018.
 */

public class Transaction {
   private String email;
    private String expenseName;
    private long expense;
    public Transaction(){}

    public Transaction(String email, String expenseName,  long expense){
        this.email = email;
        this.expenseName=expenseName;
        this.expense = expense;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(TextInputEditText expenseName) {
        this.expenseName = expenseName.getText().toString();
    }

    public long getExpense() {
        return expense;
    }

    public void setExpense(TextInputEditText expense)
    {
        String incom = expense.getText().toString();
        int inco = new Integer(incom).intValue();
        this.expense = inco;
    }
}
