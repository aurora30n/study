package study.alg;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产者消费者
 */
public class ProdAndCust {
    public static Integer goods = 0;
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        queue.put(++ProdAndCust.goods);
                        System.out.println("Produce " + ProdAndCust.goods);
                        if(queue.remainingCapacity()==0) {
                            System.out.println("Warehouse is full");
                        }
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Integer goods = queue.take();
                        System.out.println("Custom " + goods);
                        if(queue.isEmpty()) {
                            System.out.println("Warehouse is empty");
                        }
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
