package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 希尔排序
 * @date 2022/4/25 16:25
 * @project DataStructures-Demo
 */
public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8,9,1,7,2,3,5,4,6,0};  //从小到大
        //创建80000个的随机数的随机数组组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()* 8000000);  //会生成[0,8000000)数
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);
        //System.out.println(Arrays.toString(arr));

        //shellSort(arr);  交换式
        shellSort2(arr); //移位式

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    //使用逐步推导的方式来编写希尔排序
    //希尔排序时， 对有序序列在插入时采用交换法（即分组加冒泡，每组内为冒泡排序）
    public static void shellSort(int[] arr){
        //根据逐步分析，使用循环处理
        int temp = 0;
        int count = 0;
//第一层进行分组
        for(int gap = arr.length / 2;gap > 0;gap /= 2){
//第二层循环可看做整个数组的冒泡排序（选择、插入都行）的外循环
//冒泡、选择、插入外部循环一样，次数都为arr.length-1，区别是插入排序从第二个位置开始而已
            for (int i = gap; i < arr.length; i++) {
                //第三层组内循环，遍历各组中所有的元素（共gap组，每组10/gap个元素），步长是gap，
//组内看做冒泡排序，
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    //如果当前这个元素大于加上步长后的那个元素，说明需要交换
                    if(arr[j] >arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++ count) + "轮后=" + Arrays.toString(arr));
        }
//*****************************************************************************************************************************
        /*int temp = 0;
        //希尔排序的第1轮排序
        //希尔第1轮排序，是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共五组，每组两个元素），步长是5
            for (int j = i - 5; j >= 0 ; j -= 5) {
                //如果当前这个元素大于加上步长后的那个元素，说明需要交换
                if(arr[j] >arr[j + 5]){
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("第一轮希尔排序后");
        System.out.println(Arrays.toString(arr));  //[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]

        //希尔排序的第2轮排序
        //希尔第2轮排序，是将10个数据分成了5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素（共两组，每组5个元素），步长是2
            for (int j = i - 2; j >= 0 ; j -= 2) {
                //如果当前这个元素大于加上步长后的那个元素，说明需要交换
                if(arr[j] >arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("第二轮希尔排序后");
        System.out.println(Arrays.toString(arr));  //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]

        //希尔排序的第3轮排序
        //希尔第3轮排序，是将10个数据分成了5/2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素（共1组，每组10个元素），步长是1
            for (int j = i - 1; j >= 0 ; j -= 1) {
                //如果当前这个元素大于加上步长后的那个元素，说明需要交换
                if(arr[j] >arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("第三轮希尔排序后");
        System.out.println(Arrays.toString(arr));  //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]*/

    }
    //**********************************************************************************************************************
    //对交换式的希尔排序进行优化->移位法(效率更高)即第二第三层为插入排序，第三层利用步长实现了组内插入排序
//插入排序增强版
    public static void shellSort2(int[] arr){
        //增量gap，并逐步缩小增量
        for(int gap = arr.length / 2;gap > 0;gap /= 2){
            //从第gap元素开始，逐个对其所在的组进行直接插入排序法
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i-gap;//待插入数的预备插入位置的序号
                int insertVal = arr[i];
                while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                    //将大的向后移动步长
                    arr[insertIndex+gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                //当退出while循环后，就给insertVal找到了插入位置，执行插入操作
//说明插入序号位置的数要比待插入数要小，所以待插入的数位置在插入序号后一步长
                arr[insertIndex+gap] = insertVal;
            }
        }
    }
}

