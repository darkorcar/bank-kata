package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private List<Transaction> transactions;

    public InMemoryTransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void submit(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return List.copyOf(transactions);
    }

}
