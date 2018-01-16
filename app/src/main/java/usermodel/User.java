package usermodel;




public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private long income;
    private long budget;
    private long savings;
    private long expense;

    public User(){}

    public User(int id, String name, String email, String password, long income, long budget, long expense){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.income = income;
        this.budget = budget;
        this.savings = income - expense;
        this.expense = expense;
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

    public void setIncome(long income) {
        this.income = income;
    }
    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
    public long getSavings() {
        return savings;
    }

    public void setSavings(long savings) {
        this.savings = savings;
    }
    public long getExpense() {
        return expense;
    }

    public void setExpense(long expense) {
        this.expense = expense;
    }
}
