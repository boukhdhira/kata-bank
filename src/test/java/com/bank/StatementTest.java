package com.bank;

import static com.bank.helper.TransactionBuilder.aTransaction;
import static com.bank.business.Amount.amountOf;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

import com.bank.business.Statement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementTest {
	public final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Mock PrintStream printer;
	@Mock private Statement statement;
	
	@Before
	public void initialise() {
		statement = new Statement();
	}
	
	@Test public void
	should_print_statement_header() {
		statement.printTo(printer);
		
		verify(printer).println(Statement.STATEMENT_HEADER);
	}
	
	@Test public void
	should_print_deposit() throws Exception{
		statement.addLineContaining(aTransaction()
										.with(amountOf(1000))
										.with(formatter.parse("10/01/2012")).build(),
										amountOf(1000));
		
		statement.printTo(printer);
		
		verify(printer).println("10/01/2012 | 1000,00  |          | 1000,00");
	}
	
	@Test public void
	should_print_withdrawal() throws Exception{
		statement.addLineContaining(aTransaction()
										.with(amountOf(-1000))
										.with(formatter.parse("10/01/2012")).build(),
										amountOf(-1000));
		
		statement.printTo(printer);
		
		verify(printer).println("10/01/2012 |          | 1000,00  | -1000,00");
	}
	
	@Test public void
	should_print_two_deposits_in_reverse_order() throws Exception {
		statement.addLineContaining(aTransaction()
										.with(amountOf(1000))
										.with(formatter.parse("10/01/2012")).build(),
										amountOf(1000));
		statement.addLineContaining(aTransaction()
										.with(amountOf(2000))
										.with(formatter.parse("13/01/2012")).build(),
										amountOf(3000));
		
		statement.printTo(printer);
		
		InOrder inOrder = Mockito.inOrder(printer);
		inOrder.verify(printer).println("13/01/2012 | 2000,00  |          | 3000,00");
		inOrder.verify(printer).println("10/01/2012 | 1000,00  |          | 1000,00");
		
	}
	
}
