package zuoShen.basic.class02;

import java.util.Arrays;

/**
 * @Author 小郭同学
 * @Description 堆排序 大根堆
 * @Date 2022/7/30 13:01
 * @return null
 */
public class Code03_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 将数组整个变为大根堆
        //方法1 按从前往后的顺序，将数组变成大根堆
        for (int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i);  // O(logN)
        }
//  方法2 按从后往前的顺序逐步将数组变成大根堆，也即是将完全二叉树从最后一层的叶子节点逐个进行heapify
//        for (int i = arr.length-1; i >= 0  ; i--) {
//            heapify(arr,i, arr.length);
//        }

        int heapSize = arr.length; // 堆的大小
        swap(arr, 0, --heapSize); // 将堆顶元素（最大值）与堆上最后一个位置的数进行交换

        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN) 将剩余元素重新调整成大根堆
            swap(arr, 0, --heapSize); // O(1) 将堆顶元素（最大值）与堆上最后一个位置的数进行交换
        }
    }

    // 某个数现在处于index位置，满足条件就往上继续移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            //当前数比父节点大则与父节点交换，0没有父节点但是（0-1）/2 为0
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 某个数在index位置，能否向下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左子节点的下标
        while (left < heapSize) { // 下方还有子节点，因为左节点越界说明一定没有子节点了
            //************此处注意判断条件范围，下面一行代码是错误的，条件范围出错
            //int largest = left + 1 < heapSize && arr[left] > arr[left+ 1] ? left : left+ 1 ;
            // 注意只有右节点存在且右节点>左节点才选右节点，其他情况全选左节点
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大子节点中，最大的，把下标交出来
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break; // 父节点是最大的
            }
            swap(arr, largest, index);
            index = largest; // 父子交换位置后，index变更，继续向下比较
            left = index * 2 + 1;
        }
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
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

}
