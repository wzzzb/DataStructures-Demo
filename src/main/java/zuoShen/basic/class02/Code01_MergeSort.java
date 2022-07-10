package zuoShen.basic.class02;

import java.util.Arrays;

public class Code01_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //排序与合并
    public static void process(int[] arr, int l, int r) {
        if (l == r) { // 只有一个数，肯定是有序的
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid); //使左侧有序
        process(arr, mid + 1, r); //使右侧有序
        merge(arr, l, mid, r); // 将排好序的左右合并起来
    }

    //将排序后的进行有序合并
    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1]; //创建合并需要的数组空间
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) { //左右都没有拷贝完
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
//        下面两个循环只会用到一个
        while (p1 <= m) { //右侧拷贝完，但是左侧没拷贝完，把左侧剩下的全考过去
            help[i++] = arr[p1++];
        }
        while (p2 <= r) { //左侧拷贝完，但是右侧没拷贝完，把右侧剩下的全考过去
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) { //将辅助数组中排好序的拷贝回原数组
            arr[l + i] = help[i];
        }
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
            mergeSort(arr1);
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
        mergeSort(arr);
        printArray(arr);

    }

}
