package dev.qwe.asmutil.agent;

import dev.qwe.asmutil.FileProcessCore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class InitializationAgent {

    public static void premain(String args, Instrumentation inst) {

        FileProcessCore core = new FileProcessCore();
        InitializationTransformer transformer = new InitializationTransformer(core);
        inst.addTransformer(transformer);

        try {
            core.saveFile(new FileOutputStream("test-out.jar"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
