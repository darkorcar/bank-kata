package bank;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;

public class Account {

    private final TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;
    private Clock clock;

    public Account(StatementPrinter statementPrinter, TransactionRepository transactionRepository, Clock clock) {
        this.statementPrinter = statementPrinter;
        this.transactionRepository = transactionRepository;
        this.clock = clock;
    }

    public void deposit(double amount) {
        transactionRepository.submit(
                new Transaction(
                        LocalDate.now(clock),
                        new BigDecimal(amount))
        );
    }

    public void withdraw(double amount) {
        transactionRepository.submit(
                new Transaction(
                        LocalDate.now(clock),
                        new BigDecimal(-amount)
                )
        );
    }

    public void printStatement() {
        statementPrinter.printAll(
                transactionRepository.findAll()
        );
    }
}
