package dev.qwe.asmutil.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PrinterUtils {
    private static final BufferedWriter LOGGER = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void log(String txt) {
        try {
            LOGGER.write(txt + "\n");
        } catch (IOException ignored) {
            // don't care lol
        }
    }
}
