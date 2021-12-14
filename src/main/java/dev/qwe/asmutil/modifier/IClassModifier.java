package dev.qwe.asmutil.modifier;

import org.objectweb.asm.tree.ClassNode;

public interface IClassModifier {

    void modify(ClassNode classNode);

}
