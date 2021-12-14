### Description

Load, modify and save JAR file

Uses [ASM](https://asm.ow2.io/)

### Usage

Build and add to classpath
```shell
mvn clean install
```

Current run
```java
var processHandler = new FileProcessHandler().loadModifiers("dev/Main", new ClassModifier());      // Load custom modifier
                                             .loadFile(new FileInputStream("asmtext.jar"))         // Load file
                                             .editFile()                                           // Modify file
                                             .saveFile(new FileOutputStream("asmtest-out.jar"));   // Save file
```

Modify/add new 

Add file to classpath if necessary (?)