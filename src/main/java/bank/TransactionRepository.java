package bank;

import java.util.List;

public interface TransactionRepository {

    void store(Transaction transaction);

    List<Transaction> getAllTransactions();
}
