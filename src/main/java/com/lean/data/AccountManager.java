package com.lean.data;

import com.lean.helper.FileLoader;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    Map<Integer, Account> accountMap = new HashMap<>();
    private static AccountManager instance = null;
    private AccountManager() {}
    // create during startup, called by one thread
    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public void LoadData(final String file) {
        FileLoader.loadFile(file, (line) -> {
            try {
                Account account = Account.parse(line);
                accountMap.put(account.getAccountId(), account);
            } catch (Exception e) {
                // should put in log
                System.out.println("Error parsing line: " + line + " " + e.getMessage());
            }
        });
    }

    public Account getAccount(final int id) {
        return accountMap.get(id);
    }
}
