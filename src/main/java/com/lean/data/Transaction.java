package com.lean.data;

import java.security.InvalidParameterException;

// transcation_id,account_id,type,description,amount,currency_code,timestamp
public class Transaction {
    private final long trxId;
    private final int accountId;
    private final String type;
    private final String description;
    private final double amount;
    private final String currencyCode;
    private final String timestamp; // probably should convert to datetime object

    private Transaction(final long trxId,
                        final int accountId,
                        final String type,
                        final String description,
                        final double amount,
                        final String currencyCode,
                        final String timestamp) {
        this.trxId = trxId;
        this.accountId = accountId;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.timestamp = timestamp;
    }

//    transcation_id,account_id,type,description,amount,currency_code,timestamp
//1,1,Transfer,Transfer from Savings Account,33.256,BHD,2020-05-01 11:37:45
    public static Transaction parse(final String line) throws Exception {
        String[] fields = line.split(",");
        if (fields.length != 7) {
            throw new InvalidParameterException("invalid data: " + line);
        }
        return new Transaction(
                Long.parseLong(fields[0]),
                Integer.parseInt(fields[1]),
                fields[2],
                fields[3],
                Double.parseDouble(fields[4]),
                fields[5],
                fields[6]);
    }

    public long getTrxId() {return trxId;}
    public int getAccountId() { return accountId;}
    public String getType() { return type;}
    public String getDescription() { return description;}
    public double getAmount() {return amount;}
    public String getCurrencyCode() {return currencyCode;}
    public String getTimestamp() {return timestamp;}
}
