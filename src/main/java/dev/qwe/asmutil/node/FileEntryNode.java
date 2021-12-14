
package dev.qwe.asmutil.node;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;

public record FileEntryNode(ZipEntry entry, byte[] data) {
    @Override
    public String toString() {
        return new StringJoiner(", ", FileClassNode.class.getSimpleName() + "[", "]")
                .add("entry=" + entry)
                .add("data=" + Arrays.toString(data))
                .toString();
    }
}
