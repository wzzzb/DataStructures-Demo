package atguigu.caogao;

import java.util.Arrays;

public class caogao {

    public static void main(String[] args) {
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("排序前：" + Arrays.toString(arr));
        //bubbleSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        
        System.out.println("排序后：" + Arrays.toString(arr));

    }

    private static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i + 1];
            int index = i;
            while (index>=0&&min < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }

                arr[index + 1] = min;

            System.out.println(Arrays.toString(arr));
        }
    }


    private static void selectSort(int[] arr) {
        int min;
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    private static void bubbleSort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }


}