package dev.qwe.asmutil;

import dev.qwe.asmutil.handler.FileProcessHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class Main {


    public static void main(String[] args) throws Exception {
        // Java 9+ can't add to classpath on runtime anymore (?) need custom classloader program args
        // or add to -cp jars + rt.jar for jre8

//        Class.forName("org.apache.logging.log4j.core.layout.PatternLayout")
        new FileProcessHandler().loadFile(new FileInputStream("spigot.jar"))
//                .editFile()
                .saveFile(new FileOutputStream("asmtest-out.jar"))
        ;
    }
}
