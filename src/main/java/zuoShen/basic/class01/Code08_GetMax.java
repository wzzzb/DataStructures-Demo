package zuoShen.basic.class01;
// todo master公式求解时间复杂度：T（N）= a * T（N/b）+ o(N^d) 此处为指数运算符不是异或运算符; 只要子问题的规模相同时就满足该公式
// 该递归中子递归调用了两次（一左一右），所以a = 2
// 每次子递归的数据计算规模都减小一半，所以b = 2
// 除去递归调用外，每次递归都有三次以内的常数操作 所以o(N^d)=o(1),d=0
// 故该递归的时间复杂度为 T（N) = 2*T(N/2)+o(1)
public class Code08_GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值 N
    public static int process(int[] arr, int L, int R) {
        if (L == R) { // arr[L..R]范围上只有一个数，直接返回
            return arr[L];
        }
        int mid = L + ((R - L) >> 1); // 中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

}
