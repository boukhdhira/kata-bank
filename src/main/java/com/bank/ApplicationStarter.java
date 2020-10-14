package com.bank;

import static com.bank.Amount.amountOf;

import java.io.PrintStream;
import java.text.ParseException;

public class StartApp {

    public static void main(String[] args) throws ParseException {
        PrintStream printer = System.out;
        Account account = new Account(new Statement(), printer);

        account.deposit(amountOf(1000), "10/01/2012");
        account.deposit(amountOf(2000), "13/01/2012");
        account.withdrawal(amountOf(400),"14/01/2012");

        account.printStatement();
    }

}
