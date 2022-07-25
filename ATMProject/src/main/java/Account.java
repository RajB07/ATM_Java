import java.util.ArrayList;

public class Account {
    private String name; // type of account (SAVINGS JOINT etc)
   // private double balance; // amount of money in the account currently
    private String uuid; // unique id of account
    private User holder; // details of the user who holds/ owns this account
    private ArrayList<Transaction> transactions;  // list of transactions made by the User in  Account


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Create a new Account for User
     *
     @param name Account name/category jaisa
     @param holder the user who hold this account
     @param theBank the Bank object that the user is a customer
     */

    public Account(String name, User holder, Bank theBank) {
        this.name = name;
        this.holder = holder;

        //Get a universal Unique ID for account
        this.uuid = theBank.getNewAccountUUID();

        //initialize all the transactions
       this.transactions = new ArrayList<Transaction>();

        // add the holder to the user class and to the bank also!

        //Here, we are directly sending the object to the User and Bank classes and NOT COPY OF OBJECT.
    //    holder.addAccount(this);
//        theBank.addAccount(this);


    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", holder=" + holder +
                ", transactions=" + transactions +
                '}';
    }

    public String getUuid(){

        return this.uuid;
    }

    /**
     * gives the ACCOUNT summary
     * @return Account id, balance left and name of account
     */



    public String getSummaryLine() {
        //get acc balance
        double balance = this.getBalance();

        //format the summary line , depending on whether balance is negative

        if(balance<0) {
            return String.format(this.uuid + "(" + balance + ")" + this.name);
        }
        else{
                return String.format(this.uuid+" "+balance+" "+this.name);

            }


        }

    /**
     * get the balance by adding the amount present in the transactions
     * @return  the value of balance
     */

    public double getBalance() {
        double balance=0;
        for(Transaction t : this.transactions){
            balance+=t.getAmount();
        }
        return balance;

    }

    /**
     *  Sends formatted transaction summary for a single transaction
     *
     */

    public void printTransactionHistory() {
        System.out.println("\n Transaction history for account "+ this.uuid);
        for(int t= this.transactions.size()-1;t>=0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());

        }
        System.out.println();
    }

    /**
     * Add a new transaction to this particular acct
     * @param amount amount entered by user
     * @param memo memo of transaction
     */
    public void addTransaction(double amount, String memo) {
        // this transaction will be added to particular account

        Transaction ts = new Transaction(amount,memo,this);
        this.transactions.add(ts);
    }
}

