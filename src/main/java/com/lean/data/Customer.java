package com.lean.data;

// customer_id,name,date_of_birth,nationality,email_address,address

import java.security.InvalidParameterException;

public class Customer {
    private final int customerId;
    private final String name;
    private final String birth;
    private final String nationality;
    private final String email;
    private final String address;

    private Customer(final int customerId,
                     final String name,
                     final String birth,
                     final String nationality,
                     final String email,
                     final String address) {
        this.customerId = customerId;
        this.name = name;
        this.birth = birth;
        this.nationality = nationality;
        this.email = email;
        this.address = address;
    }

//    customer_id,name,date_of_birth,nationality,email_address,address
//  1,John Doe,1990-09-15,SAUDI,user1@leantech.me,"1 National Arabic Towers, Riyadh, Saudi Arabia"
    public static Customer parse(final String line) throws Exception {
        String[] fields = line.split(",");
        if (fields.length <= 6) {
            throw new InvalidParameterException("invalid data: " + line);
        }
        return new Customer(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                fields[4],
                line.substring(line.indexOf("\"") + 1, line.length() - 1));
    }

    public int getCustomerId() {return customerId;}
    public String getName() {return name;}
    public String getBirthDay() {return birth;}
    public String getNationality() {return nationality;}
    public String getEmail() {return email;}
    public String getAddress() { return address;}
}
