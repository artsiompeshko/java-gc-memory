package com.apeshko.javamemory.task_03;

import com.apeshko.javamemory.task_01.ErrorGenerator;

import java.util.ArrayList;
import java.util.List;

/*
 * -Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:/data
 */
public class OutOfMemory implements ErrorGenerator {
    private List<Object> objects = new ArrayList<Object>();

    public void generate() {
        while(true) {
            objects.add(new ArrayList<>(1_000_000));
        }
    }

    public static void main(String[] args) {
        new OutOfMemory().generate();
    }
}

