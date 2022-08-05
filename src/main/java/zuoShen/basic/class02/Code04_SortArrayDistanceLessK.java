package zuoShen.basic.class02;

import java.util.PriorityQueue;
/**
 * @Author 小郭同学
 * @Description todo 堆排序扩展题目   Java中优先级队列实际上默认就是小根堆
 * @Date 2022/8/2 14:03
 * @return null
 */
public class Code04_SortArrayDistanceLessK {

    public void sortedArrDistanceLessK(int[] arr, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // k是最大移动距离
        // 正常情况是将前k+1数加入堆中 math.min是为了防止用例中k比数组长度大，取长度和k中的较小值
        // 因为下个循环先加后弹出，所以该循环只加入了前k个
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        //加入变成小根堆，再弹出堆顶最小值
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
