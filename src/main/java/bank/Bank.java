package bank;

import java.math.BigDecimal;

public class Bank {

    private StatementPrinter statementPrinter;
    private TransactionRepository transactionRepository;

    public Bank(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(BigDecimal amount, String date) {
        transactionRepository.store(new Transaction(amount, date));
    }

    public void withdraw(BigDecimal amount, String date) {
        transactionRepository.store(new Transaction(amount.negate(), date));
    }

    public void printStatement() {
        statementPrinter.print(
                transactionRepository.getAllTransactions()
        );
    }
}
