### Description

Load, modify and save JAR file

Uses [ASM](https://asm.ow2.io/)

### Usage

Build and add as dependencies
```shell
mvn clean install
```

Current run
```java
// jar inputs
String jarIn = "asmtext.jar";                                           
String jarOut = "asmtest-out.jar

String className = "dev/Main.class";                                         // Class name to visit
ClassVisitor classVisitor = new MainClassVisitor();                          // Override a class visitor implementation
        
new FileProcessHandler().loadModifiers(className, new ClassModifier());      // Load custom modifier
                        .loadFile(new FileInputStream(jarIn))                // Load file
                        .editClasses()                                       // Modifies classes
                        .saveFile(new FileOutputStream(jarOut));             // Save file
```

Add files to classpath if necessary (?)