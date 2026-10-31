import exception.InsufficientFundsException;
import model.Account;
import model.SavingsAccount;
import org.junit.jupiter.api.Test;
import repository.Repository;
import service.BankService;
import util.TransactionLog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {
    private BankService newBank(Path log) {
        return new BankService(new Repository<>(), new java.util.concurrent.ConcurrentHashMap<>(), log);
    }

    @Test
    void depositIncreasesBalance() throws Exception {
        Path log = Files.createTempDirectory("minibank-test").resolve("transactions.log");
        BankService bank = newBank(log);
        Account account = bank.openAccount("CURRENT", "Test User", 1000);
        bank.deposit(account.getAccountNumber(), 250);
        assertEquals(1250, account.getBalance());
    }

    @Test
    void withdrawalOverAvailableLimitThrowsException() throws Exception {
        Path log = Files.createTempDirectory("minibank-test").resolve("transactions.log");
        BankService bank = newBank(log);
        Account account = bank.openAccount("SAVINGS", "Test User", 1000);
        assertThrows(InsufficientFundsException.class,
                () -> bank.withdraw(account.getAccountNumber(), 600));
    }

    @Test
    void concurrentDepositsKeepCorrectBalance() throws Exception {
        Path log = Files.createTempDirectory("minibank-test").resolve("transactions.log");
        BankService bank = newBank(log);
        Account account = bank.openAccount("CURRENT", "Concurrent User", 0);
        int count = 100;
        long amount = 10;
        CountDownLatch latch = new CountDownLatch(count);
        AtomicReference<Throwable> failure = new AtomicReference<>();
        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(() -> {
                try { bank.deposit(account.getAccountNumber(), amount); }
                catch (Throwable ex) { failure.compareAndSet(null, ex); }
                finally { latch.countDown(); }
            });
            threads[i].start();
        }
        latch.await();
        assertNull(failure.get());
        assertEquals(count * amount, account.getBalance());
    }
}
