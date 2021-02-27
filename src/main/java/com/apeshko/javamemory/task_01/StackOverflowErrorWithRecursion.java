package com.apeshko.javamemory.task_01;

public class StackOverflowErrorWithRecursion implements ErrorGenerator {
    @Override
    public void generate() {
        generate();
    }

    public static void main(String[] args) {
        new StackOverflowErrorWithRecursion().generate();
    }
}
