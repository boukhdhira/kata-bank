package com.bank;

import static com.bank.helper.TransactionBuilder.aTransaction;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

import com.bank.business.Account;
import com.bank.business.Amount;
import com.bank.business.Statement;
import com.bank.business.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    public final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final String DEFAULT_OPERATION_DATE = "22/05/2015";
    @Mock
    private Statement statement;
    private PrintStream printStream;
    private Account account;

    @Before
    public void initialise() {
        printStream = System.out;
        account = new Account(statement, printStream);
    }

    @Test
    public void
    should_add_deposit_line_to_statement() throws Exception {
        Amount depositAmount = Amount.amountOf(1000);

        account.deposit(depositAmount, DEFAULT_OPERATION_DATE);

        verify(statement).addLineContaining(aTransaction()
                        .with(formatter.parse(DEFAULT_OPERATION_DATE))
                        .with(depositAmount).build(),
                currentBalanceEqualsTo(depositAmount));
    }

    @Test
    public void
    should_add_withdraw_line_to_statement() throws Exception {
		String dateDepot = "01/01/2015";
        account.deposit(Amount.amountOf(2000), dateDepot);
        account.withdrawal(Amount.amountOf(100), DEFAULT_OPERATION_DATE);

        verify(statement, times(2)).addLineContaining(any(Transaction.class), any(Amount.class));
    }

	@Test
	public void
	should_not_add_statement_When_insufficient_balance() throws Exception{
		String withdrawalDate = DEFAULT_OPERATION_DATE;

		account.withdrawal(Amount.amountOf(500), withdrawalDate);

		verify(statement, times(0)).addLineContaining(any(Transaction.class), any(Amount.class));
	}

    @Test
    public void
    should_print_statement() {
		account.printStatement();

		verify(statement).printTo(printStream);
    }

    private Amount currentBalanceEqualsTo(Amount amount) {
        return amount;
    }

}
