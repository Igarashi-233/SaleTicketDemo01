package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{ //资源类 = 实例变量+实例方法
    private int number = 30;
    //List list = new ArrayList();
    Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "\t卖出第: " + (number--) + "张\t 还剩下: " + number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
/**
 *
 * 三个售票员    30张票
 * 编写企业级多线程代码
 *
 * 固定套路+模板
 *
 * 1.在高内聚低耦合得前提下， 线程     操作      资源类
 *      1.1先创建一个资源类
 *
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) { //主线程，一切程序的入口
        Ticket ticket = new Ticket();


        /*@FunctionalInterface
        public interface Runnable {
            public abstract void run();
        }*/

        /*   type1
        Thread t1 = new Thread();
        t1.start();
        */

        /*   type2
        new Thread(new Runnable() {
            @Override
            public void run() {
               {业务逻辑}
            }
        }, "A").start();
        */

        //Runnable为函数式接口
        /*
            new Thread(() -> {业务逻辑}, "A").start();
         */

        new Thread(() -> { for(int i=1 ; i<=40 ; i++) ticket.sale();}, "A").start();
        new Thread(() -> { for(int i=1 ; i<=40 ; i++) ticket.sale();}, "B").start();
        new Thread(() -> { for(int i=1 ; i<=40 ; i++) ticket.sale();}, "C").start();


        //Thread(Runnable target, String name) Allocates a new Thread Object
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i=1 ; i<=40 ; i++ ){
                    ticket.sale();
                }
            }
        }, "A").start();
//  start():    NEW, RUNNABLE, BLOCKED,
                WAITING, TIMED_WAITING, TERMINATED;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i=1 ; i<=40 ; i++ ){
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i=1 ; i<=40 ; i++ ){
                    ticket.sale();
                }
            }
        }, "C").start();

 */
    }
}
