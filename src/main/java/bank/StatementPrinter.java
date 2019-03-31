package bank;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class StatementPrinter {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private PrintStream printStream;

    public StatementPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printAll(List<Transaction> transactions) {
        printHeader();
        printStatement(transactions);
    }

    private void printStatement(List<Transaction> transactions) {
        transactions
                .stream()
                .collect(
                        LinkedList<TransactionWithBalance>::new,
                        (acc, transaction) -> {
                            var runningBalance = acc.isEmpty() ?
                                    new BigDecimal(0.0) :
                                    acc.get(acc.size() - 1).balance;
                            acc.add(
                                    new TransactionWithBalance(
                                            transaction,
                                            runningBalance.add(transaction.getAmount())));
                        },
                        LinkedList::addAll)
                .descendingIterator()
                .forEachRemaining(this::printTransactionsWithBalance);
    }

    private void printTransactionsWithBalance(TransactionWithBalance transactionWithBalance) {
        printStream.format("%-10s |%10.2f |%10.2f%n",
                transactionWithBalance.getTransaction().getDate().format(dateFormatter),
                transactionWithBalance.getTransaction().getAmount(),
                transactionWithBalance.getBalance());
    }

    private void printHeader() {
        printStream.format("%-10s |%10s |%10s%n", "DATE", "AMOUNT", "BALANCE");
    }

    class TransactionWithBalance {
        private final Transaction transaction;
        private final BigDecimal balance;

        public TransactionWithBalance(Transaction transaction, BigDecimal balance) {
            this.transaction = transaction;
            this.balance = balance;
        }

        public Transaction getTransaction() {
            return transaction;
        }

        public BigDecimal getBalance() {
            return balance;
        }
    }
}
