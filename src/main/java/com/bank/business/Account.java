package com.bank.business;

import static com.bank.business.Amount.amountOf;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    public final static SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    private Statement statement;

    private Amount balance = amountOf(0);

	private PrintStream printer;

    public Account(Statement statement, PrintStream printer) {
        this.statement = statement;
		this.printer = printer;
    }

    public void deposit(Amount value, String date) throws ParseException {
        recordTransaction(value, FORMATTER.parse(date));
    }

    public void withdrawal(Amount value, String date) throws ParseException {
        recordTransaction(value.negative(), FORMATTER.parse(date));
    }

    public void printStatement() {
    	statement.printTo(this.printer);
    }

    private void recordTransaction(Amount value, Date date) {
        Transaction transaction = new Transaction(value, date);
        Amount balanceAfterTransaction = transaction.balanceAfterTransaction(balance);
        if (!balanceAfterTransaction.isGreaterThan(amountOf(0))) {
            //throw new IllegalArgumentException("insufficient balance");
			printer.println("[Refused operation] insufficient balance to withdrawal amount = "+value);
			return;
        }
        balance = balanceAfterTransaction;
        statement.addLineContaining(transaction, balanceAfterTransaction);
    }

}
