package com.lambda;

@FunctionalInterface  //只能有一个标准方法(default / static能有多个)
interface Foo{
//    public void sayHello();
    public int add(int x, int y);

    public default int mul(int x, int y){
        return x*y;
    }

    public static int div(int x, int y){
        return x/y;
    }
}

/**
 *
 *  1.函数式编程才能用Lambda表达式
 *
 *  拷贝小括号,  写死右箭头,  落地大括号
 *  @FunctionalInterface
 *  default
 *  static
 */
public class LambdaExpressDemo02 {

    public static void main(String[] args) {

        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("Hello World!");
            }

            @Override
            public int add(int x, int y) {
                return 0;
            }
        };
        foo.sayHello();*/

        /*Foo foo = () ->{System.out.println("Hello Lambda! ");};
        foo.sayHello();*/

        Foo foo = (int x, int y) -> {
            System.out.println("Add NUMBER!");
            return x+y;
        };
        System.out.println(foo.add(3, 5));

        System.out.println(foo.mul(3,5));

        System.out.println(Foo.div(10,5));
    }
}
