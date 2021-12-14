package dev.qwe.asmutil.utils;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ByteUtils {

    public static void writeBytesToZip(ZipOutputStream stream, ZipEntry entry, byte[] bytes) {
        try {
            stream.putNextEntry(entry);
            stream.write(bytes);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
