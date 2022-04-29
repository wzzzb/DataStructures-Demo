package atguigu.search;

import java.util.Arrays;

/**
 * @author 小郭同学
 * @Description TODO 斐波那契查找算法 斐波那契数列的值是用来分割数组的下标 参考word里的图解
 * @date 2022/4/29 13:34
 * @project DataStructures-Demo
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 1234);
        System.out.println(index);
    }

    /**
     * @return int[]
     * @Author 小郭同学
     * @Description 用非递归的方式得到一个斐波那契数列
     * @Date 2022/4/29 15:27
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param arr 数组
     * @param key 需要查找的值
     * @return int 返回对应的下标，如果没有就返回-1
     * @Author 小郭同学
     * @Description 编写斐波那契查找算法(非递归)
     * @Date 2022/4/29 15:28
     */
    public static int fibonacciSearch(int arr[], int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;    //表示斐波那契分割数值的下标
        int mid = 0;  //存放分割数组的下标
        int f[] = fib();   //获取到斐波那契数列
        //获取到斐波那契分割数字的下标
        while (high > f[k] - 1) {
            k++;
        }

        //因为f[k]值可能大于数组arr的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int temp[] = Arrays.copyOf(arr, f[k]);
        //实际上需要使用arr数组最后的数填充temp保证数组仍是有序的
        //举例
        //temp = {1,8,10,89,1000,1234,0,0,0} => {1,8,10,89,1000,1234,1234,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while来循环处理，找到我们的数key
        while (low <= high) {
            //只要这个条件满足就可以继续找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                //说明我们应该继续向数组的前面查找（左边）
                high = mid - 1;
                //为什么是k--
                //说明：
                //1.全部元素 = 前面的元素 + 后面的元素
                //2.f[k] = f[k - 1] + f[k - 2]
                //因为前面有f[k - 1]个元素，所以可以继续拆分 f[k - 1] = f[k - 2] + f[k - 3]
                //即在f[k - 1]的前面继续查找
                //即下次循环时mid = f[k - 1 - 1] - 1
                k--;
            } else if (key > temp[mid]) {
                //说明我们应该继续向数组的后面查找（右边）
                low = mid + 1;
                //为什么是k -= 2呢
                //说明：
                //1.全部元素 = 前面的元素 + 后面的元素
                //2.f[k] = f[k - 1] + f[k - 2]
                //3.因为后面有f[k - 2]个元素，所以可以继续拆分f[k -2] = f[k - 3] + f[k - 4]
                //4.即在f[k - 2]的前面可以继续查找
                //5.即下次循环mid = f[k -2-1] - 1
                k -= 2;
            } else {
                //找到
                //因为用了原数组的最后的值来填充了扩充后的数组，所以需要判断一下下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }
}

