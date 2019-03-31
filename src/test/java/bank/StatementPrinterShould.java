package bank;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatementPrinterShould {

    @Test
    public void print_all_transactions() {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        StatementPrinter printer = new StatementPrinter(new PrintStream(bas));

        List<Transaction> transactions = List.of(
                new Transaction(LocalDate.of(2014, 4, 02), new BigDecimal(100.0)),
                new Transaction(LocalDate.of(2014, 4, 10), new BigDecimal(-50.0))
        );

        printer.printAll(transactions);

        Assert.assertEquals(
                "DATE       |    AMOUNT |   BALANCE\n" +
                        "10/04/2014 |    -50.00 |     50.00\n" +
                        "02/04/2014 |    100.00 |    100.00\n",
                bas.toString()
        );
    }
}