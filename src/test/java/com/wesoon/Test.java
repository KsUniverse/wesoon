package com.wesoon;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static final ConcurrentHashMap<Long, AtomicInteger> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        map.put(1L, new AtomicInteger(0));

        for (int x = 0; x < 20; x++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AtomicInteger in = map.get(1L);
                if (in.intValue() >= 10) {
                    return;
                }
                in.compareAndSet(in.intValue(), in.intValue() + 1);
            }).start();
        }

        Thread.sleep(2000L);
        System.out.println(map.get(1L).intValue());
    }
}
