package com.bank;

import com.bank.business.Account;
import com.bank.business.Statement;

import static com.bank.business.Amount.amountOf;

import java.io.PrintStream;
import java.text.ParseException;

public class ApplicationStarter {
    private static final PrintStream printer = System.out;
    public static void main(String[] args) throws ParseException {
        Account account = new Account(new Statement(), printer);

        account.deposit(amountOf(100), "02/01/2019");
        account.deposit(amountOf(1000), "09/01/2019");
        account.withdrawal(amountOf(400),"14/01/2019");
        account.deposit(amountOf(50), "20/01/2019");
        account.withdrawal(amountOf(100),"27/01/2019");
        account.printStatement();
    }

}
