package dev.qwe.asmutil.example;

import dev.qwe.asmutil.FileProcessCore;
import dev.qwe.asmutil.utils.ClassUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExampleMain {

    public static void main(String[] args) throws Exception {
        // Java 9+ can't add to classpath on runtime anymore (?) need custom classloader program args
        // or add to -cp jars + rt.jar for jre8
//        Class.forName("org.apache.logging.log4j.core.layout.PatternLayout")
        var a = new FileProcessCore()
//                .loadEditors("dev/Main.class", MainClassVisitor.class)
                .loadFile(new FileInputStream("test1.jar"))
//                .getEntryByName("dev/Main.class")
//                .editClasses()
//                .saveFile(new FileOutputStream("asmtest-out.jar"))
                ;

        var b = new FileProcessCore()
                .loadFile(new FileInputStream("test2.jar"));

        b.getEntryByName("dev/Hello.class").ifPresent(entry -> {
            var a_entry = a.getEntryByName("dev/Hello.class");
            a_entry.ifPresent(entry_2 -> {
                a.putFileEntry(entry_2.getKey(), entry.getValue());
                System.out.println("Overriding class!");

                try {
                    a.saveFile(new FileOutputStream("test1-out.jar"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

/*
        a.getEntryByName("dev/Main.class").ifPresent(entry -> {
            byte[] bytes = entry.getValue();
            ClassUtil.visit(bytes, TestVisitor.class);
        });
*/
    }
}
