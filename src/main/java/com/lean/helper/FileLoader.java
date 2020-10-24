package com.lean.helper;

import com.lean.data.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Consumer;

public class FileLoader {
    public static void loadFile(final String file,
                                final Consumer<String> consumer) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    file));
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("#")) {
                    line = reader.readLine();
                    continue;
                }
                consumer.accept(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading file: " + file);
            e.printStackTrace();
        }
    }
}
