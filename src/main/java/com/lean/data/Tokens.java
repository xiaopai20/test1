package com.lean.data;

import com.lean.helper.FileLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tokens {
    Set<String> whitelist = new HashSet<>();
    private static Tokens instance = null;
    private Tokens() {}
    // create during startup, called by one thread
    public static Tokens getInstance() {
        if (instance == null) {
            instance = new Tokens();
        }
        return instance;
    }

    public void LoadData(final String file) {
        FileLoader.loadFile(file, (line) -> {
            whitelist.add(line);
        });
    }

    public boolean isWhitelist(final String token ) {
        return whitelist.contains(token);
    }
}
