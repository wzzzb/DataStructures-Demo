package zuoShen.basic.class01;
//todo  该问题虽然数组无序但是仍然可以二分，
// 前提条件 无序数组中相邻数不相等，要求找到一个局部最小数（即该数比相邻的所有数小），局部最大也可用该方法
// 可将数组想象成一条连续的波浪曲线
public class Code09_FindOneLessValueIndex {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        //零点定理的推广： f'(a)*f'(b)<0,则有f'(x)=0。
        //两端都不是局部最小，且相邻比不相等，所以可以想象成两端都是向下趋势的
        //然后取中值，如果该值不是局部最小，则其两端必有一段小构成向下趋势
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 4, 6, 7, 8};
        printArray(arr);
        int index = getLessIndex(arr);
        System.out.println("index: " + index + ", value: " + arr[index]);

    }

}
