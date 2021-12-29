package dev.qwe.asmutil;

import dev.qwe.asmutil.handler.ClassEditHandler;
import dev.qwe.asmutil.utils.ZipCollectTask;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileProcessCore {

    private final Map<ZipEntry, byte[]> fileEntry = new HashMap<>();

    private final ClassEditHandler editHandler = new ClassEditHandler();

    public FileProcessCore loadEditors(String name, Class<? extends ClassVisitor> classVisitor) {
        // TODO: editors ?
        // editHandler.loadMap();
        editHandler.addModifier(name, classVisitor);
        return this;
    }

    /**
     * @param inputStream of jar
     * @throws IOException should be handled by caller
     * @see <a href="https://www.javadoc.io/static/org.ow2.asm/asm/5.2/org/objectweb/asm/ClassReader.html">ClassReader</a>
     */
    public FileProcessCore loadFile(InputStream inputStream) throws IOException {
        fileEntry.clear();
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                putFileEntry(entry, zipInputStream.readAllBytes());
            }
        }
        return this;
    }

    public Optional<Entry<ZipEntry, byte[]>> getEntryByName(String name) {
        return fileEntry.entrySet().stream().filter(entry -> entry.getKey().getName().equals(name)).findFirst();
    }

    public FileProcessCore editClasses() {
        fileEntry.replaceAll(editHandler::modifyClass);
        return this;
    }

    public void saveFile(OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            fileEntry.forEach(new ZipCollectTask(zipOutputStream));
        }
    }

    public void putFileEntry(ZipEntry entry, byte[] bytes) {
        fileEntry.put(entry, bytes);
    }
}
