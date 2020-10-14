package com.bank.domain.test;

import static com.bank.builders.TransactionBuilder.aTransaction;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

import com.bank.Account;
import com.bank.Amount;
import com.bank.Statement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	public final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	@Mock private Statement statement;
	private Account account;

	@Before
	public void initialise() {

//		account = new Account(statement);
	}
	
	@Test public void
	should_add_deposit_line_to_statement() throws Exception {
		String depositDate = "10/01/2012";
		Amount depositAmount = Amount.amountOf(1000);
		
		account.deposit(depositAmount, depositDate);
		
		verify(statement).addLineContaining(aTransaction()
												.with(formatter.parse(depositDate))
												.with(depositAmount).build(),
											currentBalanceEqualsTo(depositAmount));
	}
	
	@Test public void
	should_add_withdraw_line_to_statement() throws Exception {
		String withdrawalDate ="12/01/2012";
		
		account.withdrawal(Amount.amountOf(500), withdrawalDate);
		
		verify(statement).addLineContaining(aTransaction()
											.with(Amount.amountOf(-500))
											.with(formatter.parse(withdrawalDate)).build(),
											Amount.amountOf(-500));
	}
	
	@Test public void
	should_print_statement() {
		PrintStream printer = System.out;
		
//		account.printStatement(printer);
//
//		verify(statement).printTo(printer);
	}
	
	private Amount currentBalanceEqualsTo(Amount amount) {
		return amount;
	}

}
