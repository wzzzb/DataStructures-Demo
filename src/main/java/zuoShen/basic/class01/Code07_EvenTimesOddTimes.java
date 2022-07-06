package zuoShen.basic.class01;

public class Code07_EvenTimesOddTimes {

    /**
     * @param arr
     * @return void
     * @Author 小郭同学
     * @Description todo 在数组中（含1个出现奇数次的数，其他均出现偶数次）找到这个出现奇数次的数
     * @Date 2022/7/4 19:31
     */
    public static void printOddTimesNum1(int[] arr) {
        int eO = 0;
        for (int cur : arr) {
            //将数组中的数一起异或即 e0 = e0^a....^其他数 ,最后e0=a（根据异或运算的性质）
            eO ^= cur;
        }
        System.out.println(eO);
    }

    /**
     * @param arr
     * @return void
     * @Author 小郭同学
     * @Description todo 在数组中（含两个出现奇数次的数，其他均出现偶数次）找到这两个出现奇数次的数
     * @Date 2022/7/4 19:31
     */
    public static void printOddTimesNum2(int[] arr) {
        int eO = 0, eOhasOne = 0;
        for (int curNum : arr) {
            eO ^= curNum;
        }
        //eor = a ^ b
        //因为a!=b ,eor != 0
        //所以eor必然有一个位置上是1，说明该位置上a或者b一个为0一个为1
        int rightOne = eO & (~eO + 1); //提取出最右侧为1的最小值 即10011010 --> 00000010
        for (int cur : arr) {
            //只有当上述位置上为 1 的才进行^运算（这样必然会得到a或者b中的一个）;也可以改成为0才运算
            if ((cur & rightOne) != 0) {
                eOhasOne ^= cur;
            }
        }
        System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

    }

}
