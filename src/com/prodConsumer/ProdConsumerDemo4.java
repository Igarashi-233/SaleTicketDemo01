package com.prodConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{

    private int number = 0;

    /*  老版本
    //+1
    public synchronized void increase() throws Exception{
        //判断
        while(number != 0){
            this.wait();
        }

        //执行
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }

    //-1
    public synchronized void decrease() throws Exception{
        //判断
        while(number == 0){
            this.wait();
        }

        //执行
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }
    */

    //+1
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() throws Exception{
        lock.lock();
        try{
            //判断
            while(number != 0){
                //this.wait();  wait()存在于synchronized中
                condition.await();
            }
            //执行
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            //this.notifyAll();  notifyAll()存在于synchronized中
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    //-1
    public void decrease() throws Exception{
        lock.lock();
        try{
            //判断
            while(number == 0){
                //this.wait();  wait()存在于synchronized中
                condition.await();
            }
            //执行
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            //this.notifyAll();  notifyAll()存在于synchronized中
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
}


/**
 *  题目: 两个线程, 可以操作初始值为 0 的一个变量
 *       实现一个线程对该变量+1, 一个线程对该变量-1,
 *       交替10轮
 *
 *   1      高聚低耦合的前提下, 线程操作资源类
 *   2      判断 + 执行 + 通知
 *   3      防止线程的虚假唤醒, 只要有wait需要用while判断
 *
 *
 *   总结 = 多线程编程套路 + while判断 + 新版写法
 */

public class ProdConsumerDemo4 {

    public static void main(String[] args) throws Exception{
        Aircondition aircondition = new Aircondition();

        new Thread(() -> {
            for( int i=1 ; i<=10 ; i++ ){
                try {
                    Thread.sleep(200);
                    aircondition.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for( int i=1 ; i<=10 ; i++ ){
                try {
                    Thread.sleep(200);
                    aircondition.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for( int i=1 ; i<=10 ; i++ ){
                try {
                    Thread.sleep(200);
                    aircondition.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for( int i=1 ; i<=10 ; i++ ){
                try {
                    Thread.sleep(200);
                    aircondition.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
