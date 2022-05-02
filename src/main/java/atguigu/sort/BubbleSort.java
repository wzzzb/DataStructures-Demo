package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 冒泡排序
 * @date 2022/4/25 16:06
 * @project DataStructures-Demo
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3,9,-1,10,20};
        //int[] arr = {1,2,3,4,5};

        //System.out.println("排序前的数组");
        //System.out.println(Arrays.toString(arr));

        //为了容易理解，我们把冒泡排序的演变过程展示出来

        //测试一下冒泡排序的速度O(n^2)，给80000个数据。测试运行时间
        //创建80000个的随机数的随机数组组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()* 8000000);  //会生成[0,8000000)
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);


        //测试冒泡排序
        bubbleSort(arr);
        //System.out.println("排序后的数组");
        //System.out.println(Arrays.toString(arr));  //数组是引用数据类型，所以在方法中对数组进行的处理，在方法外也会有影响
        //利用Arrays工具类的toString方法打印数组


        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);
//------------------------------------------------------------------------------------------------------
        /*
        //第一趟排序，就是将最大的数排在最后
        int temp = 0; //临时变量
        for (int j = 0; j < arr.length - 1; j++) {
            //如果前面的数比后面的数大，就交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的数比后面的数大，就交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第三大的数排在倒数第三位
        for (int j = 0; j < arr.length - 1 - 1 - 1; j++) {
            //如果前面的数比后面的数大，就交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是将第四大的数排在倒数第四位
        for (int j = 0; j < arr.length - 1 - 1 - 1 - 1; j++) {
            //如果前面的数比后面的数大，就交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组");
        System.out.println(Arrays.toString(arr));*/
//------------------------------------------------------------------------------------------------------
        /*//冒泡排序的时间复杂度O(n^2)
        int temp = 0; //临时变量
        boolean flag = false; //标识变量，标识是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，就交换
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if(!flag){  //说明在一趟排序中一次交换都没有发生过
                break;
            }else{
                flag = false;  //重置flag，进行下次判断
            }
        }*/
    }
    //*************************************************************************************************************************
    //将前面的冒泡排序算法封装成一个方法
//每次内层循环就是把最大值向后移动到最后的过程（即每次确定一个最大值），因此当做了arr.length-1次内部循环就可以将数组排列整齐。
//每完成一次内部循环，下次内部循环可提前一位结束。（即内层要多减去i）
    public static void bubbleSort(int[] arr){
        //冒泡排序的时间复杂度O(n^2)
        int temp = 0; //临时变量
        boolean flag = false; //标识变量，标识是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println("第" + (i + 1) + "趟排序后的数组");
            //System.out.println(Arrays.toString(arr));
            if (!flag) {  //说明在一趟排序中一次交换都没有发生过（flag==false）
                break;
            } else {
                flag = false;  //重置flag，进行下次判断
            }
        }
    }

}

