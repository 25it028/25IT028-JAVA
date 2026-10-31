import model.Account;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.SavingsAccount;
import service.AccountStore;
import repository.Repository;
import service.TransactionProcessor;

import java.util.ArrayList;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank: Generic Repository (BANK_P13) ===");

        AccountStore store = new AccountStore();

        Account krisha = new SavingsAccount("Krisha", 5000, 1000);
        Account aarav = new CurrentAccount("Aarav", 3000, 2000);
        Account diya = new FixedDepositAccount("Diya", 10000);
        Account riya = new SavingsAccount("Riya", 2500, 500);

        store.add(krisha);
        store.add(aarav);
        store.add(diya);
        store.add(riya);

        System.out.println("\nAccounts sorted by balance (low to high):");
        ArrayList<Account> sortedAccounts = store.sortedByBalance();
        for (Account account : sortedAccounts) {
            System.out.println(account + " | type=" + account.getClass().getSimpleName());
        }


        System.out.println("\n=== Generic Repository Demo ===");
        Repository<Account> accountRepository = new Repository<>();
        accountRepository.saveAll(krisha, aarav, diya, riya);

        System.out.println("Fetch by ID (" + aarav.getAccountNumber() + "):");
        System.out.println(accountRepository.findById(aarav.getAccountNumber()));
        System.out.println("All accounts in repository:");
        for (Account account : accountRepository.findAll()) {
            System.out.println(account + " | type=" + account.getClass().getSimpleName());
        }
        System.out.println("Average of sample numbers [10, 20, 30]: "
                + Repository.average(new Integer[] {10, 20, 30}));

        System.out.println("\nSearch by account number:");
        Account found = store.get(aarav.getAccountNumber());s
        System.out.println(found == null ? "Account not found" : found);

        store.recordTransaction("DEPOSIT: " + krisha.getAccountNumber() + ": 500");
        store.recordTransaction("WITHDRAW: " + aarav.getAccountNumber() + ": 200");
        System.out.println("\nTransaction history:");
        for (String transaction : store.getTransactionHistory()) {
            System.out.println(transaction);
        }
        TransactionProcessor processor = new TransactionProcessor();
        for (Account account : store.getAll()) {
            processor.addAccount(account);
        }
        System.out.println("\nAccounts in thread-safe shared map: "
                + processor.getSharedAccounts().size());
        System.out.println("Account lookup from shared map: "
                + processor.getAccount(krisha.getAccountNumber()));
        processor.stop();
        System.out.println("\nComparable check (account numbers):");
        System.out.println(krisha.getAccountNumber() + " compared with "
                + aarav.getAccountNumber() + " = " + krisha.compareTo(aarav));
    }
}
