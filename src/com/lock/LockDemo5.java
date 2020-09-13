package com.lock;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

class Phone{

    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("********sendEmail*********");
    }

    public static synchronized void sendMSG() throws Exception{
        System.out.println("********sendMSG*********");
    }

    public void sayHello() throws Exception{
        System.out.println("Hello!");
    }
}

/**
 *   8  LOCK
 *
 *   1.标准访问， 先打印邮件还是短信
 *              mail
 *              MSG
 *
 *   2.在邮件方法暂停4秒钟， 先打印邮件还是短信
 *              mail
 *              MSG
 *
 *     * 一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *     * 其他线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *     *
 *     * 锁的是当前对象 this （对象锁）， 被锁定后，其他线程都不能进入到当前对象的其他synchronized方法
 *
 *
 *
 *   3.新增普通sayHello方法， 先打印邮件还是Hello
 *              Hello
 *              mail
 *
 *     * 普通方法和同步锁无关
 *
 *
 *
 *   4.两部手机， 先打印邮件还是短信
 *              MSG
 *              mail
 *
 *     * 两个对象下，不是同一把锁，”各回各家“
 *
 *
 *
 *   5.两个静态同步方法， 同一部手机， 先打印邮件还是短信
 *              mail
 *              MSG
 *
 *   6.两个静态同步方法， 两部手机， 先打印邮件还是短信
 *              mail
 *              MSG
 *
 *      * synchronized实现同步的基础: Java中的每一个对象都可以作为锁，
 *      * 具体表现为以下三种形式:
 *      *   1.对于普通同步方法,锁的是当前实例对象 this（对象锁）
 *      *   2.对于同步方法块, 锁的是synchronized括号里的配置对象
 *      *
 *      *   3.对于静态同步方法, 锁的是当前类的Class对象（全局锁）
 *
 *
 *
 *   7.一个静态同步方法， 一个普通同步方法， 同一部手机， 先打印邮件还是短信
 *              MSG
 *              mail
 *
 *   8.一个静态同步方法， 一个普通同步方法， 两部手机， 先打印邮件还是短信
 *              MSG
 *              mail
 *
 *      * 静态同步方法和非静态同步方法不是同一把锁
 *        全局锁Class   对象锁this
 *      * 因此两者不会有竞态条件
 *
 *
 */

public class LockDemo5 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
//                phone.sendMSG();
//                phone.sayHello();
                phone2.sendMSG();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();

    }
}
