package dev.qwe.asmutil.node;

import org.objectweb.asm.tree.ClassNode;

import java.util.StringJoiner;
import java.util.zip.ZipEntry;

public record FileClassNode(ZipEntry entry, ClassNode data) {

    @Override
    public String toString() {
        return new StringJoiner(", ", FileClassNode.class.getSimpleName() + "[", "]")
                .add("entry=" + entry)
                .add("data=" + data)
                .toString();
    }
}