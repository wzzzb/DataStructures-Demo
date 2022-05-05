package atguigu.linkedlist;

/**
 *
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "关胜", "大刀");
        HeroNode2 hero6 = new HeroNode2(6, "呼延灼", "双鞭");
        HeroNode2 hero7 = new HeroNode2(7, "秦明", "霹雳火");
        HeroNode2 hero8 = new HeroNode2(8, "武松", "行者");
        HeroNode2 hero9 = new HeroNode2(9, "鲁智深", "花和尚");


        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero6);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.addByOrder(hero9);
        doubleLinkedList.addByOrder(hero7);
        doubleLinkedList.addByOrder(hero8);


        doubleLinkedList.list();

       /* //修改测试
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表");
        doubleLinkedList.list();*/
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头结点，头结点不要动,不存放具体数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while(true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表最后
    public void add(HeroNode2 heroNode2){
        //因为head结点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向链表最后
        //形成一个双向链表
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //按顺序向双向链表中添加节点
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助变量（指针）来帮助找到添加的位置
        //因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
        boolean flag = false;   //标识添加的编号是否存在，默认是false
        while (true){
            if(temp.next == null){//说明temp已经在链表最后了
                break;
            }
            if(temp.next.no > heroNode.no){ //位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){//说明希望添加的heroNode编号已经存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag 的值
        if(flag){//不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号%d 已经存在了，不能加入\n",heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            heroNode.pre = temp;
            if(temp.next!=null){//插入双向链表最后位置不需要这步
                temp.next.pre=heroNode;
            }
            temp.next = heroNode;
        }
    }

    //修改一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样，只是节点的类型改成了HeroNode2
    public void update(HeroNode2 newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //先定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if(temp == null){
                break;//已经遍历完链表了
            }
            if(temp.no == newHeroNode.no){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{//没有找到要修改的节点
            System.out.printf("没有找到编号为%d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    //从双向链表中删除一个节点
    //说明 可以参考单链表的删除优化代码（不需要flag也可以），这个代码不如自己写的
    //1.对于双向链表，我们可以直接找到要删除的节点
    //2.找到之后自我删除即可
    public void del(int no){

        //判断当前链表是否为空
        if(head.next == null){//空链表
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next; //辅助指针（变量）
        boolean flag = false;//标志是否找到待删除节点的前一个节点
        while (true){
            if(temp == null){//已经到链表的最后
                break;
            }
            if(temp.no == no){//找到了待删除节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if(flag){//找到
            //可以删除
            //temp.next = temp.next.next; 单向链表删除方式
            temp.pre.next = temp.next;
            //这里的代码有问题？
            //如果是最后一个节点，就不需要执行下面这句话，否则会出现空指针异常
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else{//没有找到
            System.out.printf("要删除的%d 节点不存在\n",no);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点,默认为null
    //构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //重写toString()方法

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

