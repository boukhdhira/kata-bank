Bank account kata
=================

Think of your personal bank account experience When in doubt, go for the simplest solution.

Requirements
------------

- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing


User Stories
------------

- **US 1:**
In order to save money
As a bank client
I want to make a deposit in my account
- **US 2:**
In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account
- **US 3:**
In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance) of my operations

### Solution output

Started from defining an acceptance test:

> Given a client makes a deposit of 1000 on 01-01-2016  
And a deposit of 2000 on 13-01-2016  
And a withdrawal of 500 on 27-01-2016  
When she prints her bank statement  
Then she would see  
date       | credit   | debit    | balance  
01/01/2016 |          | 500.00   | 2500.00   
13/01/2016 | 2000.00  |          | 3000.00  
27/01/2016 | 1000.00  |          | 1000.00   
