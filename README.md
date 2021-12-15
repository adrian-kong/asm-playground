### Description

Load, modify and save JAR file

Uses [ASM](https://asm.ow2.io/), JDK 17 

### Usage

Build and add as dependencies

```shell
mvn clean install
```

Current run

```java
// jar inputs
String jarIn = "asmtext.jar";
String jarOut = "asmtest-out.jar";

String className = "dev/Main.class";                                        // Class name to visit
        
new FileProcessCore().loadModifiers(className,ClassModifier.class);      // Load custom modifier
        .loadFile(new FileInputStream(jarIn))                               // Load file
        .editClasses()                                                      // Modifies classes
        .saveFile(new FileOutputStream(jarOut));                            // Save file
```

Add files to classpath if necessary (?)

Currently, to modify classes create a custom ClassModifier;

### See [example](https://github.com/adrian-kong/asm-playground/tree/master/example)
```java

// Call #loadModifier with this class
new FileProcessCore().loadModifiers(className,CustomClassModifier.class);

/**
 * Custom sample class visitor
 */
public class CustomClassModifier extends ClassVisitor {

    public CustomClassModifier(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        // find name
        if (name.equals("U")) {
            // custom method visitor
            return new MethodVisitor(ASM9, methodVisitor) {
                @Override
                public void visitLdcInsn(Object o) {
                    // change string to uppercase
                    if (o instanceof String str) {
                        o = str.toUpperCase();
                    }
                    super.visitLdcInsn(o);
                }
            };
        }
        return methodVisitor;
    }
}
```

### See [agent](https://github.com/adrian-kong/asm-playground/tree/master/agent)

WIP, attach as `-javaagent` and to download JAR (?) maybe via runtime if possible
