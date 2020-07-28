package study.test;

/**
 * ThreadLocal
 */
public class ThreadLocalExp1 {

    public static ThreadLocal<String> res = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "comm";
        }
    };

    public static void main(String[] args) {
        for (int i=0; i<2; i++) {
            final int ii = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ii + "->" + res.get());
                    res.set("my" + ii);
                    System.out.println(ii + "->" + res.get());
                }
            }).start();
        }
    }

}
