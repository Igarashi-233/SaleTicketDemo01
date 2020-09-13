package com.safety;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * 1.故障现象
 *      java.util.ConcurrentModificationException  并发修改异常
 * 2.导致原因
 *
 * 3.解决方法
 *      3.1 Vector();
 *      3.2 Collections.synchronizedList(new ArrayList<>());
 *      3.3 CopyOnWriteArrayList<>();  写时复制技术
 *
 * 4.优化建议（同样的错误不犯2次）
 *
 */
public class NotSafeDemo03 {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        for ( int i=1 ; i<=30 ; i++ ){
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        Set<String> set =new CopyOnWriteArraySet<>();

        for ( int i=1 ; i<=30 ; i++ ){
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();    //Collections.synchronizedList(new ArrayList<>());    // new Vector<>();

        /*
        list.add("a");
        list.add("b");
        list.add("c");
        list.forEach(System.out::println);
        */

        for ( int i=1 ; i<=30 ; i++ ){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
