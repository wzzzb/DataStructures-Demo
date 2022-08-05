package zuoShen.basic.class03;

import java.util.Arrays;

public class Code02_RadixSort {
    //基数排序
    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // 找出数组中最大的位数
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE; // 先给系统最小值
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]); // 找到数组中的最大值
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10; // 计算最大值的位数
        }
        return res;
    }

    // arr[begin...end]排序
    //入桶
    public static void radixSort(int[] arr, int begin, int end, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数，准备多少个辅助空间，用来存储出桶的数
        int[] bucket = new int[end - begin + 1];
        // 每次循环只管一个位上的进出桶
        for (int d = 1; d <= digit; d++) { //有多少位就进出桶几次，d是位（1是个位，2是十位，以此类推）
            // 10个空间
            // count(0) 当前位（d位） 是0的数字有多少个
            // count(1) 当前位（d位） 是0、1的数字有多少个
            // count(2) 当前位（d位） 是0、1、2的数字有多少个
            // count(i) 当前位（d位） 是(0~i)的数字有多少个 即数组中所有数的d位置<=i的有多少个数
            int[] count = new int[radix]; // count[0...9]
            for (i = begin; i <= end; i++) {
                j = getDigit(arr[i], d); // 拿出数组中所有数字的d位上的数字，如个位上的数字为5，则count[5]++,为7，则count[7]++
                count[j]++;
            }
            for (i = 1; i < radix; i++) { // 实现了数组中所有数的d位置<=i的有多少个数
                count[i] = count[i] + count[i - 1];
            }

            // 出桶，从右往左保证了后入后出（靠右边的数后出来放在右边）等价于保证了先入桶的先出桶（靠左边的数先出来放左边）
            for (i = end; i >= begin; i--) {
                j = getDigit(arr[i], d);   // 例 arr[i]的个位是 5 ，count[5]=12 个位<=5的还有12个数,后入后出说明arr[i]应该放在arr[12-1]处
                bucket[count[j] - 1] = arr[i]; //放进辅助数组里
                count[j]--; //剩余个位<=5的还有11个
            }
            // 把该次出桶的结果更新到原数组
            for (i = begin, j = 0; i <= end; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10); // Math.pow(10, d - 1) 返回10的(d-1)次方，要对10求余故少一次方
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
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
        radixSort(arr);
        printArray(arr);

    }

}
