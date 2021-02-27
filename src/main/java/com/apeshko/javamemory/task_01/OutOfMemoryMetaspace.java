package com.apeshko.javamemory.task_01;

import javassist.CannotCompileException;
import javassist.ClassPool;

public class OutOfMemoryMetaspace implements ErrorGenerator {
    @Override
    public void generate() {
        ClassPool classPool = ClassPool.getDefault();

        for (int i = 0; i < 1_000_000_000; i += 1) {
            try {
                Class clazz = classPool.makeClass(i + " outofmemory.OutOfMemoryErrorMetaspace ").toClass();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new OutOfMemoryMetaspace().generate();
    }
}
