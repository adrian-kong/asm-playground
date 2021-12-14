package dev.qwe.asmutil;

import dev.qwe.asmutil.handler.FileProcessHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        new FileProcessHandler().loadFile(new FileInputStream("spigot.jar"))
//                .editFile()
                .saveFile(new FileOutputStream("asmtest-out.jar"))
        ;
    }
}
