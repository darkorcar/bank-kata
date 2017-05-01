package bank

import spock.lang.Specification

class BankTest extends Specification {

    private TransactionRepository transactionRepositoryMock = Mock()
    private StatementPrinter statementPrinterMock = Mock()
    private Bank bank

    def setup() {
        bank = new Bank(transactionRepositoryMock, statementPrinterMock)
    }

    def "should be able to deposit money"() {
        when:
        bank.deposit(10000.00, "01/04/2014")

        then:
        1 * transactionRepositoryMock.store(new Transaction(10000.00, "01/04/2014"))
    }

    def "should be able to withdraw money"() {
        when:
        bank.withdraw(100.00, "02/04/2014")

        then:
        1 * transactionRepositoryMock.store(new Transaction(-100.00, "02/04/2014"))
    }

    def "should print statement"() {
        given:
        transactionRepositoryMock.getAllTransactions() >> [
                new Transaction(10000.00, "01/04/2014"),
                new Transaction(-100.00, "02/04/2014")
        ]

        when:
        bank.printStatement()

        then:
        1 * statementPrinterMock.print(
                [
                        new Transaction(10000.00, "01/04/2014"),
                        new Transaction(-100.00, "02/04/2014")
                ])
    }
}
