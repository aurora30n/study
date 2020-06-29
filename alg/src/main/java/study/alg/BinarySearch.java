package study.alg;

/**
 * 二分查找法，要求有序数组
 */
public class BinarySearch {

    /**
     * 查询索引
     * @param data
     * @param val
     * @return
     */
    public static int findIndex(int[] data, int val) {
        if (data==null || data.length==0) {
            return -1;
        }
        int from = 0;
        int to = data.length-1;
        int mid = -1;
        while (true) {
            mid = (to+from)/2;
            if (val==data[mid]) {
                return mid;
            } else if (val>data[mid]) {
                from = mid;
            } else if (val<data[mid]) {
                to = mid;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        int maxVal = 100;
        // 生成随机数组
        System.out.print("origin data:");
        for (int i=0; i<data.length; i++) {
            data[i] = (int)(Math.random()*maxVal);
            System.out.print(" " + data[i]);
        }
        System.out.println();
        // 从小到大排序
        BubbleSort.sortFromSmallToLarge(data);
        System.out.print("sorted data:");
        for (int i=0; i<data.length; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println();

        // 随机选取一个数
        int randomIndex = (int)(Math.random()*data.length);
        int randomVal = data[randomIndex];
        System.out.println(String.format("Random val:%s, index:%s", randomVal, randomIndex));
        int findIndex = findIndex(data, randomVal);
        System.out.println(String.format("find val:%s, index:%s", data[findIndex], findIndex));

    }
}
