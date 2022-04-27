package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 归并排序
 * @date 2022/4/25 16:33
 * @project DataStructures-Demo
 */
public class MergeSort {
    public static void main(String[] args) {
        //int arr[] = {8,4,5,7,1,3,6,2};
        //创建80000个的随机数的随机数组组
        int[] arr = new int[80000]; //8->merger 7 80000-> merge 80000-1 冒泡 80000*O(n^2)
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()* 8000000);  //会生成[0,8000000)数
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);

        int temp[] = new int[arr.length];   //归并排序需要一个额外的空间，拿空间换时间

        mergeSort(arr,0,arr.length - 1,temp);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));


        //System.out.println("归并排序后=" + Arrays.toString(arr));

    }

    //分+合的方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid = (left + right)/2;   //中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);  //从最左边到中间
            //向右递归进行分解
            mergeSort(arr,mid + 1,right,temp);  //从中间+1到最右边
            //到合并
            merge(arr,left,mid,right,temp);
        }
    }

    //先写合并的方法
    /*
        arr 排序的原始数组
        left 左边有序数列的初始索引
        mid 中间索引
        right 最右边索引
        temp 做中转的数组

     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;  //初始化i，左边有序序列的初始索引，会移动的
        int j = mid + 1;   //初始化j，是右边有序数列的初始索引，会移动的
        int t = 0;  //是temp临时数组中的当前索引值，会移动的，只有最后一次合并才能将temp数组填满
        //(一)
        //先把左右两边（有序）的数据按规则填充到temp数组
        //直到左右两边的有序序列有一边填充完毕为止
        while(i <= mid && j <= right){   //说明此时两边都没有处理完，继续处理
            if(arr[i] <= arr[j]){
                //如果左边有序序列的当前元素，小于右边有序序列的当前元素，就把左边当前索引对应的数拷贝到temp数组中
                temp[t] = arr[i];
                t += 1;     //temp中的t指针后移
                i += 1;     //i指针右移
            }else{    //如果右边有序序列的当前元素，小于左边有序序列的当前元素，就把右边当前索引对应的数
                //拷贝到temp数组中
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //(二)
        //将有剩余数据的一边（有序数组）中剩余的数据全部按顺序填充到temp中
        while(i <= mid){
            //说明左边的有序序列还有剩余的元素，就全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right){
//说明右边的有序序列还有剩余的元素，就全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //(三)
        //将temp数组中已排列好的数据，拷贝到arr
        //注意并不是每次都拷贝所有的，每次合并都只动传进来左和右之间的原始数组的数据
        t = 0;
        while(left <= right){
//因为mergeSort先进行左递归所以
//第一次合并时，left = 0,right = 1;然后返回上一层递归；
//下一步进行右递归，第二次合并时，left = 2，right = 3
            //左右递归都结束，下一步合并，第三次合并时，left = 0,right = 3;再返回上一层递归，又到右递归，分裂到头再次合并，依旧是先左递归合并，再右递归合并，详见下图
            //最后一次，left = 0,right = 7
            arr[left] = temp[t];
            t += 1;
            left += 1;
        }
    }
}

