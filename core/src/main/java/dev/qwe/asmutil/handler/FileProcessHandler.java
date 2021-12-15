package dev.qwe.asmutil.handler;

import dev.qwe.asmutil.utils.PrinterUtils;
import dev.qwe.asmutil.utils.ZipCollectTask;
import org.objectweb.asm.ClassVisitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileProcessHandler {

    private final Map<ZipEntry, byte[]> fileEntry = new HashMap<>();

    private final ClassEditHandler editHandler = new ClassEditHandler();

    public FileProcessHandler loadEditors(String name, Class<? extends ClassVisitor> classVisitor) {
        // TODO: editors ?
        // editHandler.loadMap();
        editHandler.addModifier(name, classVisitor);
        return this;
    }

    /**
     * Loads jar file to process
     *
     * @param inputStream of jar
     * @throws IOException should be handled by caller
     * @see <a href="https://www.javadoc.io/static/org.ow2.asm/asm/5.2/org/objectweb/asm/ClassReader.html">ClassReader</a>
     */
    public FileProcessHandler loadFile(InputStream inputStream) throws IOException {
        fileEntry.clear();
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                byte[] bytes = zipInputStream.readAllBytes();
                fileEntry.put(entry, bytes);
            }
        }
        return this;
    }

    /**
     * Edits loaded jar file to process
     *
     * @return instance
     */
    public FileProcessHandler editClasses() {
        fileEntry.replaceAll(editHandler::modifyClass);
        return this;
    }

    /**
     * Saves processed jar file
     *
     * @param outputStream to save
     * @throws IOException should be handled by caller
     */
    public void saveFile(OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            fileEntry.forEach(new ZipCollectTask(zipOutputStream));
        }
    }
}
