package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 选择排序
 * @date 2022/4/25 16:16
 * @project DataStructures-Demo
 */
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1};
        //System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));

        //创建80000个的随机数的随机数组组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()* 8000000);  //会生成[0,8000000)数
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);


        //System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
    }

    //选择排序
//每次内层循环就是找到最小值的过程（即每次确定一个最小值）然后将最小值放在当次最前面，因此当做了arr.length-1次内部循环就可以将数组排列整齐。
//下次内部循环应往后推移一个位置开始，到最后结束
    public static void selectSort(int[] arr){
        //在推导的过程中，我们发现了规律，因此可以使用一个循环来解决
        //选择排序的时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if(min > arr[j]){  //说明假定的最小值，并不是最小的
                    min = arr[j];  //重置min
                    minIndex = j;  //重置minIndex
                }
            }

            //将最小值放在arr[0]的位置，即交换
            if(minIndex != i) {
                //需要一个临时变量来记录最小值所在位置索引
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            //System.out.println("第" + (i + 1) + "轮后");
            //System.out.println(Arrays.toString(arr));
        }
//*****************************************************************************************************************************-
        //使用逐步推导的方式，讲述选择排序
        //第一轮
        //原始数组：101,34,119,1
        //第一轮排序：1,34,119,101
        //算法：先简单-->再复杂，将一个复杂的问题拆分成一个简单的问题，再逐步解决

       /* //第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if(min > arr[j]){  //说明假定的最小值，并不是最小的
                min = arr[j];  //重置min
                minIndex = j;  //重置minIndex
            }
        }

        //将最小值放在arr[0]的位置，即交换
        if(minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第一轮后");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if(min > arr[j]){  //说明假定的最小值，并不是最小的
                min = arr[j];  //重置min
                minIndex = j;  //重置minIndex
            }
        }

        //将最小值放在arr[0]的位置，即交换
        if(minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮后");
        System.out.println(Arrays.toString(arr));

        //第三轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if(min > arr[j]){  //说明假定的最小值，并不是最小的
                min = arr[j];  //重置min
                minIndex = j;  //重置minIndex
            }
        }

        //将最小值放在arr[0]的位置，即交换
        if(minIndex != 2) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第三轮后");
        System.out.println(Arrays.toString(arr));    //1,34,101,119*/
    }
}

