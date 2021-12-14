package dev.qwe.asmutil.handler;

import dev.qwe.asmutil.node.FileClassNode;
import dev.qwe.asmutil.node.FileEntryNode;
import dev.qwe.asmutil.utils.ByteUtils;
import dev.qwe.asmutil.utils.PrinterUtils;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileProcessHandler {

    private final List<FileClassNode> classNodes = new ArrayList<>();

    private final List<FileEntryNode> entryNodes = new ArrayList<>();

    private final ClassEditHandler editHandler = new ClassEditHandler();

    public FileProcessHandler loadEditors() {
        editHandler.loadMap();
        return this;
    }

    /**
     * @param inputStream of jar
     * @throws IOException should be handled by caller
     * @see <a href="https://www.javadoc.io/static/org.ow2.asm/asm/5.2/org/objectweb/asm/ClassReader.html">ClassReader</a>
     */
    public FileProcessHandler loadFile(InputStream inputStream) throws IOException {
        classNodes.clear();
        entryNodes.clear();

        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".class")) {                               // is java
                    ClassReader reader = new ClassReader(zipInputStream);
                    ClassNode node = new ClassNode();
                    reader.accept(node, ClassReader.EXPAND_FRAMES);                     // expand frames
                    classNodes.add(new FileClassNode(entry, node));
                    continue;
                }
                entryNodes.add(new FileEntryNode(entry, zipInputStream.readAllBytes()));
            }
        }
        return this;
    }

    public void saveFile(OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            entryNodes.forEach(entryNode -> ByteUtils.writeBytesToZip(zipOutputStream, entryNode.entry(), entryNode.data()));
            classNodes.forEach(classNode -> {
                PrinterUtils.log("Saving " + classNode.entry().getName());
                // COMPUTE_FRAMES -> COMPUTE_MAX if ClassNotFound error, skip frames!
                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                classNode.data().accept(writer);
                ByteUtils.writeBytesToZip(zipOutputStream, classNode.entry(), writer.toByteArray());
            });
        }
    }

    public FileProcessHandler editFile() {
        classNodes.stream().map(FileClassNode::data).forEach(editHandler::modifyClassNode);
        return this;
    }
}
