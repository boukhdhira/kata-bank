package com.bank.domain.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bank.Amount;
import com.bank.Transaction;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {
	public final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Mock PrintStream printer;
	
	@Test public void
	should_print_credit_trasanction() throws Exception {
		Transaction transaction = new Transaction(Amount.amountOf(1000), formatter.parse("10/01/2012"));
		
		transaction.printTo(printer, Amount.amountOf(1000));
		
		verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");
	}
	
	@Test public void
	should_print_debit_trasanction() throws Exception{
		Transaction transaction = new Transaction(Amount.amountOf(-1000), formatter.parse("10/01/2012"));
		
		transaction.printTo(printer, Amount.amountOf(-1000));
		
		verify(printer).println("10/01/2012 |          | 1000.00  | -1000.00");
	}
	
	@Test public void
	should_calculate_current_balance_after_deposit() throws Exception {
		Transaction transaction = new Transaction(Amount.amountOf(1000), formatter.parse("10/01/2012"));
		
		Amount currentValue = transaction.balanceAfterTransaction(Amount.amountOf(100));
		
		assertThat(currentValue, Is.is(Amount.amountOf(1100)));
	}

	@Test public void
	should_calculate_current_balance_after_withdrawal() throws Exception {
		Transaction transaction = new Transaction(Amount.amountOf(-1000), formatter.parse("10/01/2012"));
		
		Amount currentValue = transaction.balanceAfterTransaction(Amount.amountOf(100));
		
		assertThat(currentValue, Is.is(Amount.amountOf(-900)));
	}
	
	@Test public void
	should_be_equal_to_other_transaction_with_same_value_and_date() throws Exception {
		Date depositDate = formatter.parse("10/01/2012");
		Transaction depositOfOneHundred = new Transaction(Amount.amountOf(1000), depositDate);
		Transaction anotherDepositOfOneHundred = new Transaction(Amount.amountOf(1000), depositDate);
		
		assertThat(depositOfOneHundred, is(equalTo(anotherDepositOfOneHundred)));
	}
	
}
