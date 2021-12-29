package dev.qwe.asmutil.utils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.ASM9;

public class ClassUtil {

    public static byte[] visit(byte[] bytes, Class<? extends ClassVisitor> clazzVisitor) {
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, COMPUTE_FRAMES);
        try {
            reader.accept(clazzVisitor.getDeclaredConstructor(int.class, ClassVisitor.class).newInstance(ASM9, writer), 0);
            return writer.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
