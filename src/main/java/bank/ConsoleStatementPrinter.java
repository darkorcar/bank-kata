package bank;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.PrintStream;
import java.util.List;

public class ConsoleStatementPrinter implements StatementPrinter {

    private PrintStream printStream;

    public ConsoleStatementPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(List<Transaction> transactions) {
        throw new NotImplementedException();
    }
}
