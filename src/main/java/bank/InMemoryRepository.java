package bank;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class InMemoryRepository implements TransactionRepository {
    @Override
    public void store(Transaction transaction) {
        throw new NotImplementedException();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        throw new NotImplementedException();
    }

}
