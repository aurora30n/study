package study.alg;

/**
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 从小到大排序
     * @param data
     */
    public static void sortFromSmallToLarge(int[] data) {
        if (data==null || data.length<2) {
            return;
        }
        int temp;
        for (int i=0; i<data.length-1; i++) {
            for (int j=0; j<data.length-1-i; j++) {
                if (data[j]>data[j+1]) {
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                 }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        int maxVal = 100;
        System.out.print("origin data:");
        for (int i=0; i<data.length; i++) {
            data[i] = (int)(Math.random()*maxVal);
            System.out.print(" " + data[i]);
        }
        System.out.println();
        BubbleSort.sortFromSmallToLarge(data);
        System.out.print("sorted data:");
        for (int i=0; i<data.length; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println();
    }
}
