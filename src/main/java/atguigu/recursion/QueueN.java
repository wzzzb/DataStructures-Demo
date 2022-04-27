package atguigu.recursion;

/*
 * 任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * */
public class QueueN {
    //先定义一个max表示共用多少个皇后
    static int max = 8;
    //定义一个数组array，保存皇后摆放位置的结果，比如：arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}
    static int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        //测试一把，8皇后是否正确
        QueueN queue8 = new QueueN();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);


    }

    //编写一个方法，放置第n+1个皇后
    //特别注意：check是每一次递归时，进入到check中都有一套for循环，因此会有回溯
    private void check(int n) {
        if (n == max) {//n == 8是已经在放第九个皇后了，其实前8个皇后依然放好了
            print();
            return;
        }

        //将每个皇后都从第一个位置开始摆放，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后 n（实际上是第n+1个皇后了），放到该行的第i列
            array[n] = i;
            //判断当放置第n+1个皇后到i列时，是否冲突
            if (judge(n)) { //说明不冲突
                //接着放n+1个皇后（即是第n+2个皇后），即开始递归
                check(n + 1);
            }

            //如果冲突，就继续执行array[n] = i;即将n个（即是第n+1）个皇后，放置在本行的后移一列
        }
    }


    //查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
    /*
            n表示放第n+1个皇后
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
//说明：
//1.array[i] == array[n] || Math.abs(n - i) 表示判断第n个皇后是否和前面n-1个皇后在同一列
//2.Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示第n个皇后跟第i个皇后是否在同一斜线
//可以理解为x轴坐标和y轴坐标，abs(n-i)是y值的差值的绝对值,abs(array[n] - array[i])是x值差值的绝对值
//第n个皇后和第i个皇后构成的直线斜率不能为+1或-1，否则，第n个皇后和第i个皇后在同一条斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;//要全部不冲突才能接着摆下一个位置，所以true必须放在循环外部
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
