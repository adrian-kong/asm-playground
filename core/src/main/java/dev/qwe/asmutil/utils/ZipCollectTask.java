package dev.qwe.asmutil.utils;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public record ZipCollectTask(ZipOutputStream outputStream) implements BiConsumer<ZipEntry, byte[]> {

    @Override
    public void accept(ZipEntry entry, byte[] bytes) {
        try {
            outputStream.putNextEntry(entry);
            outputStream.write(bytes);
            PrinterUtils.log("Saved " + entry.getName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
