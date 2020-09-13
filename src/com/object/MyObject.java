package com.object;



public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
//        System.out.println(object.getClass().getClassLoader().getParent().getParent());
//        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());
    }
}
