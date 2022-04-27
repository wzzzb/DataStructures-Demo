package atguigu.queue;
import java.util.Scanner;

/**
 注意 ！！
 front=0,rear=0;
 rear == front [空]
 尾索引的下一个为头索引时表示队列满，即将队列容量空出一个作为约定,这个在做判断队列满的时候需要注意 (rear + 1) % maxSize == front [满]
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        System.out.println("测试数组模拟环形队列");
        //创建一个队列对象
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4 );
        //设置4，说明队列的有效数据最大是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            System.out.println("y(youxiao):查看有效数字的个数");
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
                        System.out.printf("队列头的数据是%d\n",res);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'y':
                    System.out.println(arrayQueue.size());
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
    static class CircleArrayQueue{
        private int maxSize;//表示数组的最大容量
        private int front;//front含义做了调整：front就指向队列的第一个元素，
        // 也就是说arr[front]，front的初始值=0
        private int rear;//rear的含义做一个调整：rear指向队列的最后一个元素的后一个位置，
        //rear的初始值=0
        private int[] arr;//该数组用于存放数列，模拟队列

        //创建队列的构造器
        public CircleArrayQueue(int MaxSize){
            maxSize = MaxSize;
            arr = new int[maxSize];
            /*front = 0;
            rear = 0; */
        }

        //判断队列是否满
        public boolean isFull(){
            return (rear + 1) % maxSize == front;
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
            //直接将数据加入
            arr[rear] = n;
            //将rear后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        //获取队列的数据，出队列
        public int getQueue(){
            //判断队列是否为空
            if(isEmply()){
                //通过抛出异常
                throw new RuntimeException("队列空，不能取数据");
            }
            //这里需要分析出front是指向队列的第一个元素
            //1.先把front对应的值保存到一个临时的变量
            //2.front后移
            //将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if(isEmply()){
                System.out.println("队列空的，没有数据");
                return;
            }
            //思路：从front开始遍历，遍历多少个元素
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n",i % maxSize ,arr[i % maxSize]);
            }
        }

        //求出当前数列有效数据的个数
        public int size(){
            return (rear + maxSize - front) % maxSize;
            //rear=1,front=0,maxSize=3,
        }

        //显示队列的头数据，不是取出数据
        public int headQueue(){
            //判断
            if(isEmply()){
                throw new RuntimeException("队列空，没有数据");
            }
            return arr[front];
        }
    }
}

