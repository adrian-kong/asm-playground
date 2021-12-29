package dev.qwe.asmutil.handler;

import dev.qwe.asmutil.utils.ClassUtil;
import dev.qwe.asmutil.utils.PrinterUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.ASM9;

public class ClassEditHandler {

    private final Map<String, Class<? extends ClassVisitor>> classVisitorMap = new HashMap<>();

    public void loadMap() {
        //TODO: something funky here
    }

    public void addModifier(String name, Class<? extends ClassVisitor> classVisitor) {
        classVisitorMap.put(name, classVisitor);
        PrinterUtils.log("Editing class " + name);
    }

    public byte[] modifyClass(ZipEntry entry, byte[] bytes) {
        if (classVisitorMap.containsKey(entry.getName()))
            return ClassUtil.visit(bytes, classVisitorMap.get(entry.getName()));
        return bytes;
    }
}
