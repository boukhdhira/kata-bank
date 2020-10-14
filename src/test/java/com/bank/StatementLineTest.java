package com.bank;

import static com.bank.helper.TransactionBuilder.aTransaction;
import static com.bank.business.Amount.amountOf;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

import com.bank.business.StatementLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementLineTest {
	public final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Mock PrintStream printer;
	
	@Test public void
	should_print_itself() throws Exception {
		StatementLine statementLine = new StatementLine(
											aTransaction()
												.with(amountOf(1000))
												.with(formatter.parse("10/01/2012")).build(),
											amountOf(1000));
		
		statementLine.printTo(printer);
		
		verify(printer).println("10/01/2012 | 1000,00  |          | 1000,00");
	}
	
	@Test public void
	should_print_withdrawal() throws Exception {
		StatementLine statementLine = new StatementLine(
											aTransaction()
												.with(amountOf(-1000))
												.with(formatter.parse("10/01/2012")).build(),
											amountOf(-1000));
		
		statementLine.printTo(printer);
		
		verify(printer).println("10/01/2012 |          | 1000,00  | -1000,00");
	}
	

}
