import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
    private String id;
    private String name; // Bank name
    private ArrayList<User> users; // people who have account in the bank
    private ArrayList<Account> accounts; // list of all the accounts that a bank has to see which account does a user belongs to

    public ArrayList<Bank> bankList;


    private int accountID = 1;
    private int userID = 1;

    /**
     * create constructor. Here, user and account lists are empty
     * @param name : name of Bank
     *
     */
    public Bank(String name,String id) {
        this.id = id;
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts=new ArrayList<Account>();

    }

    /**
     * Generating unique ids for the User. eg ( 000001, 000002, etc)
     * @return unique id for user
     */
    public String getNewUserUUID() {
        //initialize
        String uuid = String.format("%06d", userID);
        userID = userID + 1;
        return uuid;
    }
    /**
     * Generating unique ids for the Account. eg ( ACC000001, ACC000002, etc)
     * @return the unique id for account
     */
    public String getNewAccountUUID() {
        String uuid = String.format("%06d", accountID);
        accountID += 1;
        return "ACC" + uuid;
    }


    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /** Adding users and their accounts into the respective ArrayLists
     * @param firstName user's first time
     * @param lastName user's last name
     * @param pin pin entered by user
     * @return the new user ( who is added into the ArrayList of users now), also their account is added to
     * ArrayList of accounts
     */

    public User addUser(String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser); // adds newUser to the users ARRAY LIST
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount); // newAccount is added to User Accounts
        this.accounts.add(newAccount); // Adds newAccount to the arraylist of accounts.
        return newUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Get User object if the userID and pin exists.
     *
     * @param userID userID entered by user
     * @param pin entered by user.
     * @return u if validation is successful
     */
    public User userLogin(String userID, String pin) {
        //search through list of users for validating the pin and userID entered.
        for (User u : this.users) {
            //
            if (u.getUUID().compareTo(userID) == 0 && u.validate(pin)) {
                return u;
            }
        }
        return null;
    }

}

