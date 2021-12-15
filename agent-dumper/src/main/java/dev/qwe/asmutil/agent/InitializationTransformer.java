package dev.qwe.asmutil.agent;

import dev.qwe.asmutil.FileProcessCore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.zip.ZipEntry;

public record InitializationTransformer(FileProcessCore core) implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        core.addFileEntries(new ZipEntry(className), classfileBuffer);
        return classfileBuffer;
    }
}