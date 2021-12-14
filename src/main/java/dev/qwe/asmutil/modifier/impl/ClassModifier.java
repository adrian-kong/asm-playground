package dev.qwe.asmutil.modifier.impl;

import dev.qwe.asmutil.modifier.IClassModifier;
import org.objectweb.asm.tree.*;

public class ClassModifier implements IClassModifier {

    @Override
    public void modify(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            if (methodNode.name.equals("main")) {
                for (AbstractInsnNode insnNode : methodNode.instructions.toArray()) {
                    switch (insnNode) {
                        case LineNumberNode i -> System.out.println(i);
                        case TypeInsnNode i -> System.out.println(i);
                        case InsnNode i -> System.out.println(i);
                        case MethodInsnNode i -> System.out.println(i.name);
                        case LabelNode i -> System.out.println(i);
                        case LdcInsnNode i -> {
                            System.out.println(i.cst);
                            i.cst = i.cst.toString().toUpperCase();
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + insnNode);
                    }
                }
            }
        }
    }
}
