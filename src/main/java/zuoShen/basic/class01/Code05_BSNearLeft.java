package zuoShen.basic.class01;

public class Code05_BSNearLeft {

    // 在有序arr上，找满足>=value的最左位置，
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L < R) { // 二分到最后即可找到满足条件的最左侧
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

}
