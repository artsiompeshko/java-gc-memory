package com.apeshko.javamemory.task_01;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorWithCollections implements ErrorGenerator {
    private List<Object> objects = new ArrayList<Object>();

    public void generate() {
        while(true) {
            objects.add(new ArrayList<>(1_000_000_000));
        }
    }

    public static void main(String[] args) {
        new OutOfMemoryErrorWithCollections().generate();
    }
}
