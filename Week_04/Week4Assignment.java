package io.gateway.ass;


import java.util.concurrent.*;

public class Week4Assignment {
    public static void main(String[] args) {
       // method1
       Thread task1 = new Thread(() -> {
           System.out.println(sum());
       });
       task1.start();
       // method2
       ExecutorService es = Executors.newCachedThreadPool();
       Future<Integer> ft = es.submit(() -> sum());
       try {
           System.out.println(ft.get());
       } catch (Exception e) {
           e.printStackTrace();
       }

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
