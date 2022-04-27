package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 快速排序
 * @date 2022/4/25 16:31
 * @project DataStructures-Demo
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {45,30,61,82,74,12,26,49};
        //创建80000个的随机数的随机数组组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random()* 8000000);  //会生成[0,8000000)数
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);
        //System.out.println(Arrays.toString(arr));

        quick(arr,0,arr.length-1);
        //System.out.println(Arrays.toString(arr));

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));

    }

    public static void quick(int[] arr,int left,int right){
        if(arr == null || arr.length == 0){
            return;
        }
        if(left > right){
            return;
        }

        int key = arr[left];   //基数在最左端
        int l = left;
        int r = right;
        int temp = 0;

        while (l != r){
//因为下面两个循环都加入了l<r的必要条件，所以l和r不会交错而过，总会相互靠拢到同一位置，总是把大于等于key的留在右边，小于等于key的放左边
            while(arr[r] >= key && l < r){
                r--;
            }
            while(arr[l] <= key && l < r){
                l++;
            }
//将找到的小于基准和大于基准值的两个进行位置互换
            if(l < r){
                temp =arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
//最后循环结束时，l和r同时指向的总是<=key的,原因是r- -先执行的（各种情形都满足，不要再考虑了）
//因为基准值总是选最左边，所以跟小于key的最后一个进行位置互换，这就使得基准值左边都是小于基准值的，基准值右边都是大于基准值的
        arr[left] = arr[l];
        arr[l] = key;

        quick(arr,left,l-1);
        quick(arr,l+1,right);

    }
}

