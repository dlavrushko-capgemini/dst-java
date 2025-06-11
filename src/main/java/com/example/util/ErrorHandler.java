package com.example.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorHandler {
    private static final String LOG_FILE = "error.log";

    public static void logError(String message) {
        File file = new File(LOG_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileWriter fileWriter = new FileWriter(file, true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}