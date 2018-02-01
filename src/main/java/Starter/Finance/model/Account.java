package Starter.Finance.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Simple model of a bank account
 */
public class Account {
	private String accountID;
	private double balance;
	private String holder;


    private static int nextAccountID;
	private static double overdraftLimit = 500;


    /**
     * Primary constructor
     * @param name the account holder's name
     * @param balance the starting balance
     */
	public Account(@NotNull String name, double balance) {
		setHolder(name);
		setBalance(balance);
		setAccountID(String.format("SA-%04d", getNextAvailableAccountID()));
	}

    /**
     * Secondary constructor
     * @param name the account holder's name
     * @see #Account(String, double)
     */
	public Account(@NotNull String name) {
		this(name, 0);
	}


    /**
     * Gets the Account ID of the present Account object
     * @return the Account ID
     */
	@Contract(pure = true)
    @NotNull
    private String getAccountID() {
		return accountID;
	}

    /**
     * Sets the accountID of the present Account object
     * @param accountID the new accountID
     */
	private void setAccountID(@NotNull String accountID) {
		this.accountID = accountID;
	}

    /**
     * Gets the balance of the present Account object
     * @return balance
     */
	public double getBalance() {
		return balance;
	}

    /**
     * Sets the amount of the present Account object
     * @param amount the new amount
     */
    private void setBalance(double amount) {
		this.balance = amount;
	}

    /**
     * Gets the name of the account holder for the present Account object
     * @return account holder's name
     */
	@NotNull
	public String getHolder() {
		return holder;
	}

    /**
     * Sets the name of the account holder for the present Account object
     * @param holder The name of the account holder
     */
    private void setHolder(@NotNull String holder) {
		this.holder = holder;
	}

    /**
     * Increments the static ID used to generate new account IDs and returns it
     * @return the incremented ID
     */
    @Contract(pure = true)
    private static int getNextAvailableAccountID() {
        return ++nextAccountID;
    }

    /**
     * Gets the total funds available for the present Account object
     * @return balance + overdraft limit
     */
    public double getTotalFundsAvailable() {
		return getBalance() + getOverdraftLimit();
	}

    /**
     * Gets the overdraft limit for the present Account object
     * @return the overdraft limit
     */
    @Contract(pure = true)
    private static double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Sets the overdraft limit for all accounts
     * @param amount the amount to set the limit to
     * @throws RuntimeException if amount is negative
     */
    public static void setOverdraftLimit(double amount) throws RuntimeException {
        if(amount >= 0) {
            Account.overdraftLimit = amount;
        } else {
            throw new RuntimeException("Bad argument passed to method #setOverdraftLimit(double) in class Account");
        }
    }


    /**
     * Gets details about the present Account object
     * @return a formatted String containing the details
     */
    @NotNull
    public String getDetails() {
        return String.format("\n%-8s\t%-12s\t%.2f", getAccountID(), getHolder(), getBalance());
    }

    /**
     * Deposits an amount into the present Account object
     * @param amount the amount to deposit
     */
	public void deposit(double amount) {
		setBalance(balance + amount);
	}

    /**
     * Withdraws an amount from the present Account object
     * @param amount the amount to withdraw
     * @return true if successful, false if unsuccessful
     */
	public boolean withdraw(double amount) {
		boolean result = false;
		if (amount <= getTotalFundsAvailable()) {
			setBalance(balance - amount);
			result = true;
		}
		return result;
	}

    /**
     * Transfers an amount from one account to another
     * @param from the sender
     * @param to the recipient
     * @param amount the amount to transfer
     * @return true if successful, false if unsuccessful
     */
	public static boolean transfer(@NotNull Account from, @NotNull Account to, double amount) {
		boolean result = false;

		if (from.withdraw(amount)) {
			to.deposit(amount);
			result = true;
		}
		return result;
	}

    /**
     * Prints a user-friendly message indicating whether or not the transfer succeeded
     * @param result the result of the transfer
     */
	public static void printResultOfTransfer(boolean result){
        System.out.printf("\nTransfer Successful:\t %s", result ? "YES" : "NO");
    }
}
