package study.alg;

/**
 * 裴波那契数列
 */
public class FibonacciSequence {

    /**
     * 获取第n个值
     * @param n
     * @return
     */
    public static long getValOfN(long n) {
        if (n<=0) {
            return 0;
        } else if (n==1 || n==2) {
            return 1;
        } else {
            return getValOfN(n-1) + getValOfN(n-2);
        }
    }

    public static void main(String[] args) {
        for (int i=1; i<20; i++) {
            System.out.println(getValOfN(i));
        }
    }
}
