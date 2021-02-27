package com.apeshko.javamemory.task_01;

import java.util.ArrayList;

public class OutOfMemoryErrorNoCollections implements ErrorGenerator {
    @Override
    public void generate() {
        Node parent = new Node(new ArrayList<>(1_000_000_000));
        Node current = parent;

        while(true) {
            Node child = new Node(new ArrayList<>(1_000_000_000));

            current.setNext(child);
            current = child;
        }
    }

    public static void main(String[] args) {
        new OutOfMemoryErrorNoCollections().generate();
    }
}
