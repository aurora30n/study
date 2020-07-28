package study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class FutureTaskExp1 {

    private static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        List<FutureTaskExp1> threads = new ArrayList<>();
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue);
        for (int i=0; i<10; i++) {
            FutureTaskExp1 exp = new FutureTaskExp1();
            exp.start();
            threads.add(exp);
        }
        List<Product> products = new ArrayList<>();
        threads.forEach(t->{
            Product product = t.getRes();
            if (product!=null) {
                products.add(product);
            }
            System.out.println(String.format("product name:%s, time:%s", product == null ? null : product.getName(), product == null ? null : product.getProdTime()));
        });
        System.out.println(String.format("product size:%s", products.size()));
        long et = System.currentTimeMillis();
        System.out.println("time:" + (et-st));
    }

    private final FutureTask<Product> futureTask = new FutureTask<>(new Callable<Product>() {
        @Override
        public Product call() throws Exception {
            Product product = new Product("test" + count.incrementAndGet(), System.currentTimeMillis());
            Thread.sleep(3000);
            return product;
        }
    });

    public void start() {
        new Thread(futureTask).start();
    }

    public Product getRes() {
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    class Product {
        private String name;

        private Long prodTime;

        public Product(String name, Long prodTime) {
            this.name = name;
            this.prodTime = prodTime;
        }

        public Long getProdTime() {
            return prodTime;
        }

        public void setProdTime(Long prodTime) {
            this.prodTime = prodTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
