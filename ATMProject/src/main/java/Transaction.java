import java.util.Date;

public class Transaction {

    private double amount; // kitne ka kharida xD
    private Date timestamp; // Date and time when transaction was done.
    private String memo; // Memo/ Receipt containing transaction details.
    private Account inAccount; // account which made the transaction

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Account getInAccount() {
        return inAccount;
    }

    public void setInAccount(Account inAccount) {
        this.inAccount = inAccount;
    }

    /**
     * Create a new transaction
     * @param amount the amount transacted
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = " ";
    }

    /**
     * Create a new transaction
     * @param amount the amount transacted
     * @param memo the memo for the transaction
     * @param inAccount the account the transaction belongs to
     */

    public Transaction(double amount, String memo, Account inAccount) {
       this(amount, inAccount);
       this.memo = memo;
    }

    /**
     * Gives the TRANSACTION  summary
     * Transaction history for account ACC000001
     * Fri Jul 22 09:47:05 IST 2022 $ 1000.0Memo: sal
     * eg. Rajeev's account summary
     * 1 ) ACC000001 1000.0 Savings
     *
     * 2 ) ACC000002 0.0 Checking
     * @return the timestamp of the transaction , the amount and memo
     */
    public String  getSummaryLine() {
        if(this.amount>=0){
            return String.format(this.timestamp.toString()+" $ "+this.amount+" Memo: "+this.memo);
        }
        else {
            return String.format(this.timestamp.toString()+"( $ "+ -this.amount+")"+" Memo: "+this.memo);
        }
      //  return null;
    }
}
