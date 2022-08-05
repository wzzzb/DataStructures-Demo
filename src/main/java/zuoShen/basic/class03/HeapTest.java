package zuoShen.basic.class03;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 小郭同学
 * @Description TODO
 * @date 2022/8/2 16:06
 * @project DataStructures-Demo
 */
public class HeapTest {

    public static class AComp implements Comparator<Integer> {

        //返回负数的时候，第一个参数排在上面
        //返回正数的时候，第二个参数排在下面
        //返回0的时候，谁在上面都可以
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        //默认优先队列为小根堆，用比较器改为大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new AComp());

        heap.add(34);
        heap.add(4);
        heap.add(390);
        heap.add(1);
        heap.add(24);
        heap.add(94);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }
}
