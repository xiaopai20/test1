package com.lean.data;


import java.security.InvalidParameterException;

// It is easier to use @builder, @Getter annotation, but I don't have Lombok’s on my IDE
public class Account {
    private final int accountId;
    private final int customerId;
    private final AccountType type;
    private final String number;
    private final String iban;
    private final String status;
    private final double balance; // probably need more accurate type
    private final String currencyCode;

    private Account(final int accountId,
                    final int customerId,
                    final AccountType type,
                    final String number,
                    final String iban,
                    final String status,
                    final double balance,
                    final String currencyCode) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.type = type;
        this.number = number;
        this.iban = iban;
        this.status = status;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    //    account_id,customer_id,type,account_number,iban,status,balance,currency_code
    //    1,3,Savings,789654123051,SA000000789654123051,ACTIVE,123.56,SAR
    public static Account parse(final String line) throws Exception {
        String[] fields = line.split(",");
        if (fields.length != 8) {
            throw new InvalidParameterException("invalid data: " + line);
        }
        return new Account(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[1]),
                AccountType.valueOf(fields[2]),
                fields[3],
                fields[4],
                fields[5],
                Double.parseDouble(fields[6]),
                fields[7]);
    }

    public int getAccountId() {
        return accountId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public AccountType getType() {
        return type;
    }
    public String getNumber() {
        return number;
    }
    public String getIban() {
        return iban;
    }
    public String getStatus() {
        return status;
    }
    public double getBalance() {
        return balance;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
}
