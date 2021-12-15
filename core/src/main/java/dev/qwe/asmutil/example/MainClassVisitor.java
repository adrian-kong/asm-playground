package dev.qwe.asmutil.example;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

/**
 * Test class visitor
 * <p>
 * Doubling long value from 1600 to 3200
 */
public class MainClassVisitor extends ClassVisitor {

    public MainClassVisitor(ClassVisitor classVisitor) {
        super(ASM9, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // find name
        if (name.equals("U")) {
            return new MethodVisitor(ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                @Override
                public void visitLdcInsn(Object o) {
                    System.out.println(o);
                    // change value of ldc
                    super.visitLdcInsn(o instanceof Long && o.toString().startsWith("16") ? ((long) o) * 2 : o);
                }
            };
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
