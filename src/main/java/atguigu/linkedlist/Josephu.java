package atguigu.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        //测试一把看看构建和遍历环形链表是否OK
        CircleSingleLinkedlist circleSingleLinkedlist = new CircleSingleLinkedlist();
        circleSingleLinkedlist.addBoy(5);//加入5个小孩
        circleSingleLinkedlist.showBoy();

        //测试一把小孩出圈是否正确
        circleSingleLinkedlist.countBoy(1,2,5);//2->4->1->5->3
    }
}

    //创建一个环形的单向链表
    class CircleSingleLinkedlist{
        //创建一个first节点，当前没有编号
        private Boy first = null;
        //添加小孩节点，构建成一个环形链表
        public void addBoy(int nums){//nums是环形链表中小孩的个数
            //nums 做一个数据校验
            if(nums < 1){
                System.out.println("nums的值不正确");
                return;
            }
            Boy curBoy = null; //辅助指针（变量），帮助构建环形链表
            //使用for循环来创建环形链表
            for (int i = 1; i <= nums; i++) {
                //根据编号，创建小孩节点
                Boy boy = new Boy(i); //创建一个小孩节点
                //如果是第一个小孩
                if(i == 1){
                    first = boy;
                    first.setNext(first); //构成一个环
                    curBoy = first; //让curBoy指向第一个小孩
                }else{
                    curBoy.setNext(boy); //让curBoy指向新来的节点boy
                    boy.setNext(first);  //让boy指向头节点
                    curBoy = boy;        //curBoy后移
                }
            }
        }

        //遍历当前环形链表
        public void showBoy(){
            //判断链表是否为空
            if(first == null){
                System.out.println("链表为空");
                return;
            }
            //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
            Boy curBoy = first;
            while(true){
                System.out.printf("小孩的编号 %d \n",curBoy.getNo());
                if(curBoy.getNext() == first){//说明已经遍历完毕
                    break;
                }
                curBoy = curBoy.getNext(); //让curBoy后移
            }
        }

        //根据用户的输入计算出小孩出圈的顺序
    /*
    startNo：表示从第几个小孩开始数数
    countNum：表示数几下
    nums：表示最初有多少小孩在圈中
     */
        public void countBoy(int startNo,int countNum,int nums){
            //先对数据校验
            if(first == null || startNo < 1 || startNo > nums){
                System.out.println("参数输入有误，请重新输入");
            }
            //创建辅助指针，帮助完成小孩出圈
            //first用来指代第一个报数的节点，helper指向待删除节点的前一个节点（同p5单向列表的删除）!!!!!!!!!!!!!!!!
            Boy helper = first;
            //先需求创建一个辅助指针（变量）helper。事先应该指向环形链表的最后这个节点
            while(true){
                if(helper.getNext() == first){//说明helper指向最后小孩节点
                    break;
                }
                helper = helper.getNext();
            }
            //小孩报数前，先让first 和 helper 移动startNo - 1次
            for (int i = 0; i < startNo - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //当小孩报数时。让first和helper指针同时后移countNum - 1次，然后出圈
            //这里是一个循环的操作，直到圈中只有一个节点
            while(true){
                if(helper == first){ //说明圈中只有一个节点
                    break;
                }
                //让first 和 helper移动countNum - 1次
                for (int i = 0; i < countNum - 1; i++) {
                    first = first.getNext();
                    helper = helper.getNext();
                }
                //这时first指向的节点，就是要出圈的小孩节点
                System.out.printf("小孩%d出圈\n",first.getNo());
                //这时将first指向的小孩节点出圈
                first = first.getNext();
                helper.setNext(first);
            }
            System.out.printf("最后留在圈中的小孩编号是%d \n",first.getNo());
        }
    }


    //创建一个Boy类，表示一个节点
    class Boy{
    //私有化要用get set调用，否则可以直接.no .next来调用
        private int no;//编号
        private Boy next;//指向下一个节点（小孩），默认null
        public Boy(int no){
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }

