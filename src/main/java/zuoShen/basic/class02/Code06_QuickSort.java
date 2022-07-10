package zuoShen.basic.class02;

import java.util.Arrays;

/**
 * @param
 * @Author 小郭同学
 * @Description todo 快速排序，三区域法
 * @Date 2022/7/10 16:36
 * @return null
 */
public class Code06_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    //arr[l..r]排好序
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);//随机选一个位置，与最右侧的数做交换
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0]); // < 区域
            quickSort(arr, p[1], r); // > 区域
        }
    }

    //这是一个处理arr[l..r]的函数
    //默认以arr[r]做划分，arr[r]-> p  <p    ==p    >p
    //该方法的返回值是 等于区域的（左边界，有边界），所以返回一个长度为2的数组res, res[0]和 res[1]左右边界位置
    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1; // 初始情况 <区域右边界位置
        int more = r;     // 初始情况 >区域左边界
        while (l < more) { // l表示当前数的位置 arr[r] -> 划分值
            if (arr[l] < arr[r]) { // 当前数 < 划分值
                swap(arr, ++less, l++); // 将<区域边界扩大一位，并将当前数与<区域边界刚扩充的一位交换，之后在将当前指针后移（因为当前数的左侧判断过了，交换位置的这个数要么是本身要么是等于边界值）
            } else if (arr[l] > arr[r]) { // 当前数 > 划分值
                swap(arr, --more, l); // 将>区域边界扩大一位，并将当前数与>区域边界刚扩充的一位交换，因为是与当前数的右侧进行交换（右侧没判断）所以l不能++
            } else {
                l++;
            }
        }
        swap(arr, more, r); // 将分界值放到>区域的最左侧
        return new int[]{less, more + 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);

    }

}
