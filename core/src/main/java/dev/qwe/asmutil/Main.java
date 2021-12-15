package dev.qwe.asmutil;

import dev.qwe.asmutil.example.MainClassVisitor;
import dev.qwe.asmutil.handler.FileProcessHandler;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {


    public static void main(String[] args) throws Exception {
        // Java 9+ can't add to classpath on runtime anymore (?) need custom classloader program args
        // or add to -cp jars + rt.jar for jre8

//        Class.forName("org.apache.logging.log4j.core.layout.PatternLayout")
        new FileProcessHandler()
                .loadEditors("net/minecraft/server/v1_8_R3/ChatComponentScore$ServerCommand.class", MainClassVisitor.class)
                .loadFile(new FileInputStream("test.jar"))
                .editClasses()
                .saveFile(new FileOutputStream("asmtest-out.jar"))
        ;
    }
}
