package atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小郭同学
 * @Description TODO 二分查找（递归形式）含重复变量也可找到
 * @date 2022/4/28 17:03
 * @project DataStructures-Demo
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 1, 1, 8, 10, 89, 89, 89, 1000, 1234, 1234, 1234};

        List<Integer> IndexList = binarySearch(arr, 0, arr.length - 1, 1);
        System.out.println("IndexList=" + IndexList);
    }

    /**
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 需要找的值
     * @return int    如果找到就返回下标，如果没有找到，就返回-1
     * @Author 小郭同学
     * @Description 二分查找算法
     * @Date 2022/4/28 17:06
     */
    public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明 递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }

        //找到中间的下标
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {   //向右递归

            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            //建立一个集合用来装找到的索引
            List<Integer> IndexList = new ArrayList();
            IndexList.add(mid);
            //找到相等的值的索引，向两边寻找是否还有相同的
            for (int i = mid - 1; i >= 0 && arr[i] == findVal; i--) {
                IndexList.add(i);
            }
            for (int i = mid + 1; i <= arr.length - 1 && arr[i] == findVal; i++) {
                IndexList.add(i);
            }
            return IndexList;
        }

    }
}

