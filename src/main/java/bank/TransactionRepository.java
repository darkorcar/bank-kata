package bank;

import java.util.List;

public interface TransactionRepository {

    void submit(Transaction transaction);

    List<Transaction> findAll();
}
