package dev.qwe.asmutil.handler;

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
        try {
            if (classVisitorMap.containsKey(entry.getName())) {
                ClassReader reader = new ClassReader(bytes);
                ClassWriter writer = new ClassWriter(reader, COMPUTE_FRAMES);

                Class<? extends ClassVisitor> clazz = classVisitorMap.get(entry.getName());
                ClassVisitor classVisitor = clazz.getDeclaredConstructor(int.class, ClassVisitor.class).newInstance(ASM9, writer);
                reader.accept(classVisitor, 0);
                return writer.toByteArray();
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
