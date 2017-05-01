package bank

import spock.lang.Specification


class ConsoleStatementPrinterTest extends Specification {

    def "should print out statement"() {
        given:
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        PrintStream printStream = new PrintStream(baos)
        StatementPrinter statementPrinter = new ConsoleStatementPrinter(printStream)

        when:
        statementPrinter.print(
                [
                        new Transaction(500.00, "10/04/2014"),
                        new Transaction(-100.00, "02/04/2014"),
                        new Transaction(1000.00, "01/04/2014")
                ])

        then:
        baos.toString() == '''
        DATE       | AMOUNT  | BALANCE
        10/04/2014 | 500.00  | 1400.00
        02/04/2014 | -100.00 | 900.00
        01/04/2014 | 1000.00 | 1000.00
        '''
    }

}
