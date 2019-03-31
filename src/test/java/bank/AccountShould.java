package bank;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountShould {

    @Test
    public void make_deposit_transaction() {

        StatementPrinter statementPrinterMock = mock(StatementPrinter.class);
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Clock clockMock = mock(Clock.class);
        Account account = new Account(statementPrinterMock, transactionRepositoryMock, clockMock);

        var amount = 100.0;
        var date = LocalDate.of(2019, 4, 1);
        var dateInstant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();

        when(clockMock.getZone()).thenReturn(ZoneId.systemDefault());
        when(clockMock.instant()).thenReturn(dateInstant);

        account.deposit(amount);

        verify(transactionRepositoryMock).submit(new Transaction(date, new BigDecimal(amount)));
    }

    @Test
    public void make_withdrawal_transaction() {

        StatementPrinter statementPrinterMock = mock(StatementPrinter.class);
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Clock clockMock = mock(Clock.class);
        Account account = new Account(statementPrinterMock, transactionRepositoryMock, clockMock);

        var amount = 50.0;
        var date = LocalDate.of(2019, 4, 1);
        var dateInstant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();

        when(clockMock.getZone()).thenReturn(ZoneId.systemDefault());
        when(clockMock.instant()).thenReturn(dateInstant);

        account.withdraw(amount);

        verify(transactionRepositoryMock).submit(new Transaction(date, new BigDecimal(-amount)));
    }

    @Test
    public void print_statement() {
        StatementPrinter statementPrinterMock = mock(StatementPrinter.class);
        TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
        Clock clockMock = mock(Clock.class);
        Account account = new Account(statementPrinterMock, transactionRepositoryMock, clockMock);

        var date = LocalDate.of(2019, 4, 1);
        List<Transaction> transactions = List.of(new Transaction(date, new BigDecimal(100.0)));

        when(transactionRepositoryMock.findAll()).thenReturn(transactions);

        account.printStatement();

        verify(statementPrinterMock).printAll(transactions);
    }
}