package atguigu.sort;

import java.text.SimpleDateFormat;

/**
 * @author 小郭同学
 * @Description TODO 插入排序
 * @date 2022/4/25 16:19
 * @project DataStructures-Demo
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1};   //实现由小到大的排序
        //创建80000个的随机数的随机数组组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()* 8000000);  //会生成[0,8000000)数
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println("排序前的时间是=" + date1Str);
        //System.out.println(Arrays.toString(arr));

        insertSort(arr);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(System.currentTimeMillis());
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int[] arr){

        //使用for循环简化代码
//外部循环指的是从第二个元素开始，每个元素都向前找合适自己的位置，向前找位置的过程（即内部循环）
        for (int i = 1; i < arr.length; i++) {
            //第一轮{101,34,119,1}  => {34,101,119,1}

            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;   //即arr[1]的前面这个数的下标

            //给insertVal找到插入的位置
            //说明
            //1.insertIndex >= 0 保证在给insertVal找插入位置时，不越界
            //2.insertVal < arr[insertIndex]待插入的数还没有找到插入位置，需要向前搜索插入的位置
            //3.就需要将arr[insertIndex] 后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];  //arr[insertIndex]后移
                //相当于是前面的数都往后退，把要插入的位置空出来
                insertIndex --;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
//说明插入序号位置的数要比待插入数要小，所以待插入的数位置在插入序号后一步长
            //如果插入位置是自己原位置，则不需要插入操作并没有提升多少速度,判断也消耗性能
//（这步判断可以不写，插入操作要写）
            if(insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            //System.out.println("第" + i + "轮插入后");
            //System.out.println(Arrays.toString(arr));
        }
//-----------------------------------------------------------------------------------------------

        /*//使用逐步推导的方式来讲解，便于理解
        //第一轮{101,34,119,1}  => {34,101,119,1}

        //定义带插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;   //即arr[1]的前面这个数的下标

        //给insertVal找到插入的位置
        //说明
        //1.insertIndex >= 0 保证在给insertVal找插入位置时，不越界
        //2.insertVal < arr[insertIndex]待插入的数还没有找到插入位置，需要向前搜索插入的位置
        //3.就需要将arr[insertIndex] 后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];  //arr[insertIndex]后移
            //相当于是前面的数都往后退，把要插入的位置空出来
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第1轮插入后");
        System.out.println(Arrays.toString(arr));

        //第二轮{101,34,119,1}  => {34,101,119,1}

        //定义带插入的数
        insertVal = arr[2];
        insertIndex = 2 - 1;   //即arr[1]的前面这个数的下标

        //给insertVal找到插入的位置
        //说明
        //1.insertIndex >= 0 保证在给insertVal找插入位置时，不越界
        //2.insertVal < arr[insertIndex]待插入的数还没有找到插入位置，需要向前搜索插入的位置
        //3.就需要将arr[insertIndex] 后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];  //arr[insertIndex]后移
            //相当于是前面的数都往后退，把要插入的位置空出来
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第2轮插入后");
        System.out.println(Arrays.toString(arr));

        //第三轮{101,34,119,1}  => {1,34,101,119}

        //定义带插入的数
        insertVal = arr[3];
        insertIndex = 3 - 1;   //即arr[1]的前面这个数的下标

        //给insertVal找到插入的位置
        //说明
        //1.insertIndex >= 0 保证在给insertVal找插入位置时，不越界
        //2.insertVal < arr[insertIndex]待插入的数还没有找到插入位置，需要向前搜索插入的位置
        //3.就需要将arr[insertIndex] 后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];  //arr[insertIndex]后移
            //相当于是前面的数都往后退，把要插入的位置空出来
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第3轮插入后");
        System.out.println(Arrays.toString(arr));*/

    }

}

