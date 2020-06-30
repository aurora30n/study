package study.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者
 */
public class ProdAndCust2 {
    public static Integer goods = 0;
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        goods = warehouse.get();
                        if (goods!=null) {
                            System.out.println("Customer get " + goods);
                        }
                        Thread.sleep(1000);
                        if (goods==null) {
                            System.out.println("Customer sleep");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        boolean isPut = warehouse.put(++goods);
                        if (isPut) {
                            System.out.println("Producer put " + goods + " " + isPut);
                        }
                        Thread.sleep(1000);
                        if (!isPut) {
                            System.out.println("Producer sleep");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class Warehouse {
    private List<Integer> queue = new ArrayList<Integer>();
    private int size = 3;

    synchronized public boolean put(Integer goods) {

        if (queue.size()<size) {
            queue.add(goods);
            return true;
        } else {
            System.out.println("Warehouse is full");
            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    synchronized public Integer get() {
        Integer goods = null;
        if (queue.isEmpty()) {
            System.out.println("Warehouse is empty");
            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            goods = queue.get(0);
            queue.remove(0);
            return goods;
        }
    }
}
