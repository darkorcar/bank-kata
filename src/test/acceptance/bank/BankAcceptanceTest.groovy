package bank


import spock.lang.Specification

class BankAcceptanceTest extends Specification {

    def "should perform basic banking operations"() {
        given: "we have a bank"
        TransactionRepository transactionRepository = new InMemoryRepository()
        StatementPrinter statementPrinter = new ConsoleStatementPrinter()

        Bank bank = new Bank(transactionRepository, statementPrinter)

        and: "a client makes a deposit of 1000.00 on 01/04/2014"
        bank.deposit(10000.00, "01/04/2014")


        and: "a withdrawal of 100.00 on 02/04/2014"
        bank.withdraw(100.00, "02/04/2014")

        and: "a deposit of 500.00 on 10/04/2014"
        bank.deposit(500.00, "10/04/2014")

        when: "she prints her bank statement"
        bank.printStatement()

        then: "she would see"
        outputStream ==
        '''
        DATE       | AMOUNT  | BALANCE
        10/04/2014 | 500.00  | 1400.00
        02/04/2014 | -100.00 | 900.00
        01/04/2014 | 1000.00 | 1000.00
        '''
    }

}

