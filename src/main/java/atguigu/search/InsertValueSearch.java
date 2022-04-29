package atguigu.search;

import java.util.Arrays;

/**
 * @author 小郭同学
 * @Description TODO 插值查找算法（即优化二分查找的中点：从无脑取中值，转化为以等比例的方式预测目标值在数组中的大致位置）；
 * TODO 因此差值查找在数据分布均匀的表中查找速度有优势，不均匀的情况下则不一定比二分折半有优势
 * @date 2022/4/29 12:41
 * @project DataStructures-Demo
 */
public class InsertValueSearch {
    public static void main(String[] args) {
       /* int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/

        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};

        System.out.println(Arrays.toString(arr));

        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        System.out.println(index);
    }

    /**
     * @param arr     传入的数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 查找的值
     * @return int    如果找到就返回对应的下标，如果没有找到就返回-1
     * @Author 小郭同学
     * @Description 编写插值查找算法, 插值查找算法也要求数组是有序的
     * @Date 2022/4/29 12:43
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("查找次数~~");

        //注意：findVal < arr[0] || findVal > arr[arr.length -1]不但能够起到优化的作用，也是必须需要的
        //否则得到的mid值，可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            //要找到的数比最大的数大，比最小的数小
            return -1;
        }

        //预测目标值所在位置，
        //加号后面的式子为以等比例的方式预测目标值所在位置占数组长度的比例
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            //说明应该项右递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //说明应该向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

        //return -1;  返回值填写在这里也行，或者返回值填写在里面
    }
}

