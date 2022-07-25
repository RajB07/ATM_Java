import java.util.Scanner;

public class ATMProject {
    public static void main(String[] args) {
       Scanner sc  = new Scanner(System.in);

       Bank theBank  = new Bank("HDFC Bank","HDFC");
       Bank sbi = new Bank("State Bank of INDIA", "sbi");
       Bank axis = new Bank("Axis Bank","axis");

    //   User auser = new User();

      //  System.out.println(user)

//        Account checkingAccount = new Account("Checking",auser,theBank);
//        auser.addAccount(checkingAccount);
//        theBank.addAccount(checkingAccount);

        User curUser;
        while(true){
            //stay in login prompt until successful login

             curUser = ATMProject.mainMenuPrompt(theBank,sc);

            //stay in main menu until user quits
            ATMProject.printUserMenu(curUser,sc);
        }

    }

    /**
     * ATM's login Menu
     * @param
     * @param sc
     * @return authenticated user obj
     */

    private static int selectBank(Bank bank, Scanner sc){
        //sc.nextLine();
        System.out.println("Please enter your bank choice");
        System.out.println("********");
        System.out.println("1)HDFC");
        System.out.println("2)sbi");
        System.out.println("3)Axis");

        int ch = sc.nextInt();
        return ch;
        }


    private static void registerUser(Bank bank, Scanner sc){
        sc.nextLine();
        String fn;
        String ln;
        String p;
        System.out.println("Enter first name");
        fn = sc.nextLine();
        System.out.println("Enter Last name");

        ln = sc.nextLine();
        System.out.println("Enter pin");
        p= sc.nextLine();
        User auser = bank.addUser(fn, ln, p);
        Account checkingAccount = new Account("Checking",auser,bank);
        auser.addAccount(checkingAccount);
        bank.addAccount(checkingAccount);
        System.out.println("Registration success");
    }

    private static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userID;
        String pin;
        User authUser = null;
        int existence;
        ;
        // prompt user for (userID,pin) combo until correct one is reached
        do {

            System.out.println("WELCOME TO THE ATM!");
            int ch= selectBank(theBank,sc);

            switch (ch){
                case 1:
                    theBank.setName("HDFC");
                    break;
                case 2:
                    theBank.setName("SBI");
                    break;
                case 3:
                    theBank.setName("Axis");
                    break;

            }

            System.out.println("\nWelcome to " + theBank.getName() +" Bank "+ "\n");

            System.out.println("Have you been here before? if not type 1");
            existence = sc.nextInt();


            switch (existence) {
                case 1:
                    System.out.println("New user? Please make an account");
                    registerUser(theBank,sc);


                case 2:
                    System.out.println("Already a member? Please Login");
                    System.out.println("Enter User ID: ");
                    userID = sc.nextLine();
                    System.out.println("Enter pin: ");
                    pin = sc.nextLine();

                    //try to get user object corresponding to userID and pin combo

                    authUser = theBank.userLogin(userID, pin);
                    if ((authUser) == null) {
                        System.out.println("Incorrect details .Try Again Please.");
            }
        }

        }while (authUser==null); //continue looping until successful login

            return authUser;
    }

    /**
     * Menu for selecting various options for performing certain operations
     * @param curUser currently logged in user
     * @param sc input choice of operation user wants to perform.
     */
    private static void printUserMenu(User curUser, Scanner sc) {
        //Print a summary of user account once login is done
        curUser.printAccountsSummary();

        int choice;
        do{
            System.out.println("Welcome "+curUser.getFirstName()+" what would u like to do?" );
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdrawal");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.print("Enter choicee ");
            choice=sc.nextInt();

            if(choice<1 || choice>5){
                System.out.println("Invalid choice chosen, Please choose from 1-5");
            }

        }while (choice<1 || choice>5);

        switch (choice){
            case 1:
                ATMProject.showAccountTransaction(curUser,sc);
                break;

            case 2:
                ATMProject.withdrawalFunds(curUser,sc);
                break;

            case 3:
                ATMProject.depositFunds(curUser,sc);
                break;

            case 4:
                ATMProject.transferFunds(curUser,sc);
                break;

            case 5:
                sc.nextLine();
                break;

        }
        //redisplay Menu unless user wants to quit

        if(choice!=5){
            ATMProject.printUserMenu(curUser,sc);
        }
    }


    /**
     * Process of depositing funds into a particular account
     * @param curUser currently logged in user
     * @param sc input acc to which deposit is to be done and amount to be deposited
     */
    private static void depositFunds(User curUser, Scanner sc) {

        int toAcc;
        double amount;
        double amountBal = 0;
        String memo;
        do{
            System.out.println("Enter the number (1-" +curUser.numAccount() + ") of account to transfer from" );
            toAcc = sc.nextInt()-1;

            if(toAcc<0 || toAcc >= Integer.parseInt(curUser.numAccount())){

                System.out.println("INVALID ACC CHOICE");
            }
        //    amountBal = curUser.getAccBalance(toAcc);

        }while(toAcc<0 || toAcc >= Integer.parseInt(curUser.numAccount()));

        // Validate deposit

        do{
            System.out.println("Enter amount u want to deposit ");
            amount= sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than 0");
            }

        }while (amount < 0);

        //Actual withdraw

        sc.nextLine();
        System.out.println("Enter Memo ");
        memo = sc.nextLine();

        curUser.addAcctTransaction(toAcc,amount,memo);

    }

    /**
     * Process of withdrawal of funds from a particular account
     * @param curUser current logged in user
     * @param sc input acc from which you want to withdraw and amount to be withdrawn
     */
    private static void withdrawalFunds(User curUser, Scanner sc) {

        int fromAcc;
        double amount;
        double amountBal = 0;
        String memo;
        // details of acc from which u want to withdraw
        do{
            System.out.println("Enter the number (1-" +curUser.numAccount() + ") of account to withdraw from" );
            fromAcc = sc.nextInt()-1;

            if(fromAcc<0 || fromAcc >= Integer.parseInt(curUser.numAccount())){

                System.out.println("INVALID ACC CHOICE");
            }
            amountBal = curUser.getAccBalance(fromAcc);

        }while(fromAcc<0 || fromAcc >= Integer.parseInt(curUser.numAccount()));

        // Validate withdrawal

        do{
            System.out.println("Enter amount u want to withdraw (max "+ amountBal+ ") :");
            amount= sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than 0");
            }
            else if (amount > amountBal){
                System.out.println("Amount to be withdrawn is more than available balance");
            }
        }while (amount < 0 || amount > amountBal);

      //Actual withdraw

        sc.nextLine();
        System.out.println("Enter Memo ");
        memo = sc.nextLine();

        curUser.addAcctTransaction(fromAcc,-1*amount,memo);

    }

    /**
     * Process transferring funds from one acc to another
     * @param curUser current user who's logged in
     * @param sc input the account from which from which the funds are transferred and account to which funds are
     *           sent
     */
    private static void transferFunds(User curUser, Scanner sc) {

            int fromAcc;
            int toAcc;
            double amount;
            double amountBal;
            //Get details of the account from which the funds are transferred
            do{
                System.out.println("Enter the number (1-" +curUser.numAccount() + ") of account to transfer from" );
                fromAcc = sc.nextInt()-1;

                if(fromAcc<0 || fromAcc >= Integer.parseInt(curUser.numAccount())){

                    System.out.println("INVALID ACC CHOICE");
                }
                amountBal = curUser.getAccBalance(fromAcc);

            }while(fromAcc<0 || fromAcc >= Integer.parseInt(curUser.numAccount()));

           //get details of account in which u want to put fund
        do{
            System.out.println("Enter the number (1-" +curUser.numAccount() + ") of account to transfer to" );
            toAcc = sc.nextInt()-1;

            if(toAcc<0 || toAcc >= Integer.parseInt(curUser.numAccount())){

                System.out.println("INVALID ACC CHOICE");
            }
//            amountBal = curUser.getAccBalance(toAcc);

        }while(toAcc<0 || toAcc >= Integer.parseInt(curUser.numAccount()));


        // get amount that has to be transferred. Check if enough balance is there to transfer

        do{
            System.out.println("Enter amount you want to transfer (max "+amountBal+ ") :");
            amount= sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than 0");
            }
            else if (amount > amountBal){
                System.out.println("Amount to be transferred is more than available balance");
            }
        }while (amount < 0 || amount >amountBal);

        //finally do transfer

        curUser.addAcctTransaction(toAcc,amount,String.format("Transfer to "+curUser.getAcctUUID(toAcc)));

        curUser.addAcctTransaction(fromAcc,-1*amount,String.format("Transfer from "+curUser.getAcctUUID(fromAcc)));
    }

    /**
     * Showing transaction history of a particular acc
     * @param curUser current user who's logged in
     * @param sc input the account name( Savings or checked) so 1- savings acc 2- checked acc
     */
    private static void showAccountTransaction(User curUser, Scanner sc) {
     int theAcct;
     // fetch the account whose transaction history we have to look at

        do{
            System.out.println("Enter the number (1- "+curUser.numAccount()+" ) of account");
            theAcct=sc.nextInt()-1;

            if(theAcct<0 || theAcct > Integer.parseInt(curUser.numAccount())) {

                System.out.println("Invalid acc entered");
            }

        }while (theAcct<0 || theAcct > Integer.parseInt(curUser.numAccount()));

        //print transaction history

        curUser.printAccTransactionHistory(theAcct);

    }

}

