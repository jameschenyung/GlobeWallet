package objects;

public class main {
    public static void main(String[] args) {
        User user = new User(1234, "John", "Doe", "johndoe", "password123");
        CheckingAccount checkingAccount = new CheckingAccount("C1001", user);
        SavingsAccount savingsAccount = new SavingsAccount("S2001", user);

        user.addAccount(checkingAccount);
        user.addAccount(savingsAccount);

        checkingAccount.deposit(1000.0);
        savingsAccount.deposit(5000.0);

        ///

        User user2 = new User(1234, "Ja", "Doe", "johndoe", "password123");
        CheckingAccount checkingAccount2 = new CheckingAccount("C1002", user2);
        SavingsAccount savingsAccount2 = new SavingsAccount("S2002", user2);

        user2.addAccount(checkingAccount2);
        user2.addAccount(savingsAccount2);

        checkingAccount2.deposit(1000.0);
        savingsAccount2.deposit(5000.0);

        ///

        checkingAccount.transfer(checkingAccount, checkingAccount2, 500);

        System.out.println(user.getFullName() + "'s Accounts:");
        for (Account account : user.getAccounts()) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Balance: $" + account.getBalance());
        }

        for (Account account : user2.getAccounts()) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Balance: $" + account.getBalance());
        }
    }
}
