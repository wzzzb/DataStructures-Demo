package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 基数排序
 * @date 2022/4/25 19:25
 * @project DataStructures-Demo
 */
public class RadixSort {
    public static void main(String[] args) {
        //int arr[] = {53,3,542,748,14,214};
        //创建80000个的随机数的随机数组组
        //8千万个数据，有80000000 * 11 * 4 /1024 /1024 /1024 = 3.3个g的内存，内存不足
        //java.lang.OutOfMemoryError
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 800000000);  //会生成[0,8000000)数
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);

        radixSort(arr);

        //System.out.println(Arrays.toString(arr));

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);

    }


    //基数排序方法
    public static void radixSort(int[] arr) {

        //根据前面的过程，可以得到最终基数排序的代码

        //1.得到数组中最大数的位数
        int max = arr[0];    //假设第一个数就是最大数
        for (int i = 0; i < arr.length; i++) {
            max = max > arr[i] ? max : arr[i];
        }

        //2.得到最大数是几位数
        int maxLength = (max + "").length();    //将最大数变成一个字符串，得到该字符串的长度即是最大位数

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出。认为每个一维数组（桶）的大小为arr.length
        //3.基数排序是经典的空间换时间的算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组，来记录各个桶每次放入的数据个数
        //可以这样理解，bucketElementCounts[0]记录的就是bucket[0]这个桶，每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //每一轮
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //元素个位是digitOfElement；所以将元素放入到对应的bucket[digitOfElement]桶中
                //而bucketElementCounts[digitOfElement]指定了放入桶中的具体位置
                bucketElementCounts[digitOfElement]++;
            }

            //把二维数组中的数依次取出放回原来的arr数组中
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组
            for (int k = 0; k < 10; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //说明k桶中有数据，循环第k个桶(即第k个一维数组)，放入arr中
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出数据放入arr中
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //每一轮处理后，要将每个bucketElementCounts[k] = 0清零
                bucketElementCounts[k] = 0;
            }

            //System.out.println("第" + (i+1)+"轮，对个位的排序");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
