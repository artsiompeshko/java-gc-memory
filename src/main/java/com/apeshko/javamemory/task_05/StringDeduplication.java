package com.apeshko.javamemory.task_05;

import java.util.ArrayList;
import java.util.List;

/*
 * -Xms2000m -Xmx2000m -XX:+UseG1GC -XX:+UseStringDeduplication -XX:StringDeduplicationAgeThreshold=1 -Xlog:stringdedup*=debug
 */
public class StringDeduplication {
    private List<Person> collection = new ArrayList<>();

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void generateStrings() throws InterruptedException {
        for(int i = 0; i < 1_000_000_000; i += 1) {
            String name = String.valueOf(getRandomNumber(1, 10));

            collection.add(new Person(name));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);

        new StringDeduplication().generateStrings();

        Thread.sleep(200000);
    }
}
