package dev.qwe.asmutil.example;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class TestVisitor extends ClassVisitor {

    public TestVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

}
