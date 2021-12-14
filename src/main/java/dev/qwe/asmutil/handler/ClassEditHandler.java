package dev.qwe.asmutil.handler;

import dev.qwe.asmutil.modifier.IClassModifier;
import dev.qwe.asmutil.modifier.impl.ClassModifier;
import org.objectweb.asm.tree.ClassNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ClassEditHandler {

    private final Map<String, IClassModifier> modifiedClassMap = new HashMap<>() {{
        put("dev/Main", new ClassModifier());
    }};

    public void loadMap() {
//        try (BufferedReader reader = Files.newBufferedReader(new File("config.txt").toPath())) {
//            reader.lines();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }


    public void modifyClassNode(ClassNode node) {
        if (modifiedClassMap.containsKey(node.name)) {
            modifiedClassMap.get(node.name).modify(node);
        }
    }

}
