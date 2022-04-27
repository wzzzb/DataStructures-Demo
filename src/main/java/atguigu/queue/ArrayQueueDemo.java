package atguigu.queue;
import java.util.Scanner;

/**
 *
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列对象
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("head(head):查看队列头数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g'://取出
                    try{
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据
                    try{
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是:%d\n",res);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

    //使用数组模拟数列，编写一个ArrayQueue类
    static class ArrayQueue{
        private int maxSize;//表示数组的最大容量
        private int front;//指向队列头
        private int rear;//指向数据尾
        private int[] arr;//该数组用于存放数列，模拟队列

        //创建队列的构造器
        public ArrayQueue(int MaxSize){
            maxSize = MaxSize;
            arr = new int[maxSize];
            front = -1; //指向队列头部，不包含第一个数据（第一个数据之前的位置）
            rear = -1;  //指向队列的尾部，直接指向队列的尾部的具体位置（就是队列最后一个数据）
        }

        //判断队列是否满
        public boolean isFull(){
            return rear == maxSize - 1;
        }

        //判断队列是否为空
        public boolean isEmply(){
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n){
            //判断队列是否满了
            if(isFull()){
                System.out.println("队列满，不能加入数据");
                return;
            }
            rear++; //让rear后移
            arr[rear] = n;
        }

        //获取队列的数据，出队列
        public int getQueue(){
            //判断队列是否为空
            if(isEmply()){
                //通过抛出异常
                throw new RuntimeException("队列空，不能取数据");
            }
            front++;
            return arr[front];
        }

        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if(isEmply()){
                System.out.println("队列空的，没有数据");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n",i,arr[i]);
            }
        }

        //显示队列的头数据，不是取出数据
        public int headQueue(){
            //判断
            if(isEmply()){
                throw new RuntimeException("队列空，没有数据");
            }
            return arr[front+1];
        }
    }
}

