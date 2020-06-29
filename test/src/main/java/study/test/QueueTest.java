package study.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        //        int maxVal = 100;
//        boolean isProd = false;
//        boolean isCust = false;
//
//        // producer
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (!isProd) {
//                    return;
//                }
//                try {
//                    queue.put((int)(Math.random()*maxVal));
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        // customer
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (!isProd) {
//                    return;
//                }
//                try {
//                    int val = queue.take();
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
