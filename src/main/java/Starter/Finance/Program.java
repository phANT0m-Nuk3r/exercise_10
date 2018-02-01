package Starter.Finance;

import Starter.Finance.model.Account;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * Main class
 */
public class Program {

    /**
     * Application Entry Point
     */
	public static void main(String[] args) {
	    Account.setOverdraftLimit(700);

        Account ac1, ac2;
        ac1 = new Account("Fred", 100);
        ac2 = new Account("Susy");
        ac1.deposit(100);
        Account.printResultOfTransfer(ac2.withdraw(300));
        Account.printResultOfTransfer(Account.transfer(ac1, ac2, 50));
        System.out.print(ac1.getDetails());
        System.out.print(ac2.getDetails());

        // Challenge 1
        System.out.println("\n\nChallenge 1");
        Account[] accounts = { ac1, ac2 };
        processAccounts(accounts);
        System.out.printf("%s\t%.2f\n", ac1.getHolder(), ac1.getBalance());
        System.out.printf("%s\t%.2f\n", ac2.getHolder(), ac2.getBalance());

        // Challenge 2
        System.out.print("\nChallenge 2");
        String[] names = { "Ann", "Anne", "Annie", "Anneka", "Annabel" };
        Account[] studentAccounts = new Account[names.length];

        for (int i = 0; i < studentAccounts.length; i++) {
            studentAccounts[i] = new Account(names[i],
                    (int) (Math.random() * 100) + 1);
        }
        for (Account a : studentAccounts) {
            System.out.printf("%s\tAvailable Balance: %.2f", a.getDetails(), a.getTotalFundsAvailable());
        }
        for (int i = 0; i < studentAccounts.length; i++) {
            int transferAmt = studentAccounts[i].getHolder().length();
            if (i < studentAccounts.length - 1) {

                Account.printResultOfTransfer(Account.transfer(studentAccounts[i], studentAccounts[i + 1],
                        transferAmt));
            }
            else {
                Account.printResultOfTransfer(Account.transfer(studentAccounts[i], studentAccounts[0],
                        transferAmt));
            }
        }
        for (Account a : studentAccounts) {
            System.out.printf("%s\tAvailable Balance: %.2f", a.getDetails(), a.getTotalFundsAvailable());
        }

        System.out.println("\n\nPress Enter to continue . . .");
        new Scanner(System.in).nextLine();
	}

	/**
     * Single-use method
     */
	private static void processAccounts(@NotNull Account[] accounts) {
		for (Account a : accounts) {
			a.deposit(10);
		}
	}
}
