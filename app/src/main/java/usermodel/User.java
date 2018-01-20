package usermodel;


import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private long income;
    private long budget;
    private long savings;
    private long expense;
    private List<Transaction> transactions;

    public User(){}

    public User(int id, String name, String email, String password, long income, long budget, long expense, List<Transaction> transactions){
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
        return savings;
    }

    public void setSavings(TextInputEditText savings) {
        String incom = savings.getText().toString();
        int inco = new Integer(incom).intValue();
        this.savings = inco;
    }
    public long getExpense() {
        return expense;
    }

    public void setExpense(TextInputEditText expense)
    {String incom = expense.getText().toString();
        int inco = new Integer(incom).intValue();
        this.expense = inco;
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions(){
        return this.transactions;
    }
}
