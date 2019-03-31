package feature;

import bank.Account;
import bank.InMemoryTransactionRepository;
import bank.StatementPrinter;
import bank.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrintStatementsFeature {

    @Test
    public void
    should_print_all_statements() {
        //given
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StatementPrinter statementPrinter = new StatementPrinter(new PrintStream(bos));

        TransactionRepository transactionRepository = new InMemoryTransactionRepository();

        Clock clockMock = mock(Clock.class);
        var date1 = LocalDate.of(2014, 4, 1);
        var date2 = LocalDate.of(2014, 4, 2);
        var date3 = LocalDate.of(2014, 4, 10);
        when(clockMock.getZone()).thenReturn(ZoneId.systemDefault());
        when(clockMock.instant()).thenReturn(
                date1.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                date2.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                date3.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Account account = new Account(statementPrinter, transactionRepository, clockMock);

        //when
        account.deposit(1000.00);
        account.withdraw(100.00);
        account.deposit(500.00);

        account.printStatement();

        //then
        Assert.assertEquals(
                "DATE       |    AMOUNT |   BALANCE\n" +
                        "10/04/2014 |    500.00 |   1400.00\n" +
                        "02/04/2014 |   -100.00 |    900.00\n" +
                        "01/04/2014 |   1000.00 |   1000.00\n",
                bos.toString()
        );
    }
}
