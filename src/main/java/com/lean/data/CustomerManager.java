package com.lean.data;

import com.lean.helper.FileLoader;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    Map<Integer, Customer> customerMap = new HashMap<>();

    private static CustomerManager instance = null;
    private CustomerManager() {}
    // create during startup, called by one thread
    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }

    public void LoadData(final String file) {
        FileLoader.loadFile(file, (line) -> {
            try {
                Customer customer = Customer.parse(line);
                System.out.println("adding ..." + customer.getCustomerId());
                customerMap.put(customer.getCustomerId(), customer);
            } catch (Exception e) {
                // should put in log
                System.out.println("Error parsing line: " + line + " " + e.getMessage());
            }
        });
    }

    public Customer getCustomer(final int id) {
        return customerMap.get(id);
    }
}
