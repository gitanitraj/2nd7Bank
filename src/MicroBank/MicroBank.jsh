// this file is java for jshell

// read the input data from the file
// each line looks like: 1/10/2026, deposit, 100.00

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner; 

class MicroBank {
    // input file is "input.data"
    double balance = 0.0;

    class Transaction {
        String date;
        String type;
        double amount;

        public Transaction(String date, String type, double amount) {
            this.date = date;
            this.type = type;
            this.amount = amount;
        }

        public double getAmount() {
            return this.amount;
        }
    }

    public static void main(String[] args) {
        MicroBank mb = new MicroBank();
        ArrayList<Transaction> transactions = mb.readData("input.data");
        for (Transaction t : transactions) {
            if (t.type.equals("withdrawal")) {
                mb.balance -= t.amount;
            } else if (t.type.equals("deposit")) {
                mb.balance += t.amount;
            }
        }
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";

        while (!userChoice.equals("quit")) {
            System.out.println("\nCurrent Balance: $" + String.format("%.2f", mb.balance));
            System.out.println("Type 'deposit', 'withdrawal', or 'quit'");
            userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("deposit") || userChoice.equals("withdrawal")) {
                System.out.println("Enter amount:");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (userChoice.equals("deposit")) {
                mb.balance += amount;
                System.out.println("Deposited $" + amount);
            } else {
                mb.balance -= amount;
                System.out.println("Withdrew $" + amount);
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
            scanner.nextLine();
        }
    } else if (!userChoice.equals("quit")) {
        System.out.println("Invalid option. Select again.");
    }
}

System.out.println(String.format("Final Balance: $%.2f", mb.balance));
scanner.close();
}

MicroBank.main(null);