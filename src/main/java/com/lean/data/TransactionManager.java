package com.lean.data;

import com.lean.helper.FileLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {
    Map<Long, Transaction> trxMap = new HashMap<>();
    Map<Integer, List<Transaction>> accMap = new HashMap<>();

    private static TransactionManager instance = null;
    private TransactionManager() {}
    // create during startup, called by one thread
    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    public void LoadData(final String file) {
        FileLoader.loadFile(file, (line) -> {
            try {
                Transaction trx = Transaction.parse(line);
                trxMap.put(trx.getTrxId(), trx);

//                System.out.println("adding accID: " + trx.getAccountId());
                accMap.putIfAbsent(trx.getAccountId(), new ArrayList<>());
                accMap.get(trx.getAccountId()).add(trx);
//                System.out.println(" accID len: " + accMap.get(trx.getAccountId()).size());
            } catch (Exception e) {
                // should put in log
                System.out.println("Error parsing line: " + line + " " + e.getMessage());
            }
        });

        for (Map.Entry<Integer, List<Transaction>> entry : accMap.entrySet()) {
            entry.getValue().sort((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
        }
    }

    public Transaction getTrx(final long id) {
        return trxMap.get(id);
    }

    public List<Transaction> getTrxByAccount(final int id) {
        return accMap.get(id);
    }
}
