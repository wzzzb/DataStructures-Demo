package atguigu.caogao;

import java.util.Arrays;

/**
 * @author 小郭同学
 * @Description TODO 左神对数器 自测用 测试自己的算法是否有误
 * @date 2022/7/6 17:32
 * @project DataStructures-Demo
 */
public class test {
    // for Test 系统提供的排序 TODO 用Java自带排序做对比判断自己的算法是否正确
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for Test 随机数组生成器
    public static int[] generateRandomArray(int maxSize, int maxValue) {

        // Math.random() -> [0,1) 所有小数等概率返回一个
        // Math.random()*N -> [0,N) 所有小数等概率返回一个
        // (int)(Math.random()*N) -> [0,N-1] 所有整数等概率返回一个

        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for Test 复制一个地址不同，值完全相同的数组
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

    // for Test 用来判断自己的算法与Java的算法结果是否相同
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

    // for Test 打印数组
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for Test 主方法
    public static void main(String[] args) {
        int testTime = 500000;      //测试次数
        int maxSize = 100;          // 数组最大长度
        int maxValue = 100;         // 最大值 生成数组的范围（-100,100）
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            // 要测试的算法 insertionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        // 要测试的算法 insertionSort(arr);
        printArray(arr);
    }
}
