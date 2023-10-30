public class main {
    public static void main(String[] args) {
        User user = new User("12345", "John", "Doe", "johndoe", "password123");
        CheckingAccount checkingAccount = new CheckingAccount("C1001", user);
        SavingsAccount savingsAccount = new SavingsAccount("S2001", user);

        user.addAccount(checkingAccount);
        user.addAccount(savingsAccount);

        checkingAccount.deposit(1000.0);
        savingsAccount.deposit(5000.0);

        System.out.println(user.getFullName() + "'s Accounts:");
        for (Account account : user.getAccounts()) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Balance: $" + account.getBalance());
        }
    }
}
