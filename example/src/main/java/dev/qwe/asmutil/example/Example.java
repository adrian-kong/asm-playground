package dev.qwe.asmutil.example;

import dev.qwe.asmutil.FileProcessCore;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Example {

    public static void main(String[] args) throws Exception {
        // Java 9+ can't add to classpath on runtime anymore (?) need custom classloader program args
        // or add to -cp jars + rt.jar for jre8
//        Class.forName("org.apache.logging.log4j.core.layout.PatternLayout")
        new FileProcessCore()
                .loadEditors("net/minecraft/server/v1_8_R3/ChatComponentScore$ServerCommand.class", MainClassVisitor.class)
                .loadFile(new FileInputStream("test.jar"))
                .editClasses()
                .saveFile(new FileOutputStream("asmtest-out.jar"))
        ;
    }
}
