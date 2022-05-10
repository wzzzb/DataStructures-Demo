package atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试

        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "杨云", "大胡子");

        //创建原始链表,创建的对象头结点是不同的
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();


        //加入
        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero5);
        System.out.println("======================");
        singleLinkedList1.list();
        System.out.println("======================");
        singleLinkedList2.list();
        System.out.println("===========按顺序合并两个排序好的链表===========");
        singleLinkedList.add(singleLinkedList.mergeTwoLists(hero1, hero2));
        singleLinkedList.list();
        System.out.println("==========更改了节点的指针域，故整条链表发生变化==========");
        singleLinkedList1.list();
        System.out.println("==========更改了节点的指针域，故整条链表发生变化============");
        singleLinkedList2.list();
        //加入按照编号的顺序
        /*singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        System.out.println("-------------------");
        //修改节点
        singleLinkedList.updata(hero5);
        singleLinkedList.list();
        System.out.println("-------------------");
        //删除节点
        singleLinkedList.delete(1);
        //显示一把
        singleLinkedList.list();
        //查询单链表中的节点个数（不含头结点）
        System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));
        //反转单链表
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/
        //删除节点
        System.out.println("------------------------");
        singleLinkedList.delete(12);
        //显示一把
        singleLinkedList.list();
    }
}

//链表
// 定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    //获取头结点
    public HeroNode getHead() {
        return head;
    }

    //5）合并两个有序的单链表，合并之后的链表依然有序。按照节点的no属性进行排序
    public static HeroNode mergeTwoLists(HeroNode l1, HeroNode l2) {
        HeroNode heroNode = new HeroNode(0, "", "");
        HeroNode firstNode = heroNode;//firstNode记录头结点位置，后续heroNode当做指针使用

        while (l1 != null && l2 != null) {
            if (l1.no <= l2.no) {
                heroNode.next = l1;
                l1 = l1.next;
            } else {
                heroNode.next = l2;
                l2 = l2.next;
            }
            heroNode = heroNode.next;//合并链表指针后移
        }
        while (l1 != null) {
            heroNode.next = l1;
            l1 = l1.next;
            heroNode = heroNode.next;
        }
        while (l2 != null) {
            heroNode.next = l2;
            l2 = l2.next;
            heroNode = heroNode.next;
        }
        return firstNode.next;//调用该方法之后，要将返回的节点接在对象本身的头接点上，所以不需要该方法自身的头节点
    }

    //反向打印链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;  //空链表，无法打印
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur); //压栈
            cur = cur.next; //让cur后移，遍历
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());  //stack的特点是先进后出
        }
    }


    //将单链表进行反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回；这步判断多余了
        /*if (head.next == null || head.next.next == null) {
            return;
        }*/
        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //用来指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next; //先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; //让要插入的节点连上上一个已插入的节点!!!!!
            reverseHead.next = cur; //让要插入的节点连上头结点
            //到此插入完成，让cur后移
            cur = next;
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }


    /*  查找单链表中导数第k个结点【新浪面试题】
  思路
  1.编写一个方法，接收head节点，同时接收一个index
  2.index表示导数第index个节点
  3.先把链表从到到尾遍历，得到链表的总的长度，调用getLength()方法
  4.得到size后，从链表的第一个开始遍历，遍历(size-index)个，就可以得到
  5.找到则返回该节点，否则返回null
  */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一次遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历size-index位置，就是倒数的第index个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量,for循环定位到倒数的index
        HeroNode cur = head.next; //3
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /*方法：获取到单链表的节点的个数（如果带头结点的链表，需要不统计头结点）
       head链表的头节点
      返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            //带头节点的空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量,这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /*添加节点到单向链表
        思路：当不考虑编号的顺序时
        1.找到当前链表的最后节点
        2.将最后这个节点的next指向新的结点
        */
    public void add(HeroNode heroNode) {
        //因为head结点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;//引用变量，这里给的是地址，即temp与head指向了同一块区域
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;//引用变量，这里给的是地址，即temp指向了下一个节点的区域
        }
        //当退出while循环时，temp指向链表最后
        temp.next = heroNode;
    }

    //第二种添加英雄的方式，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助变量（指针）来帮助找到添加的位置
        //因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;   //标识添加的编号是否存在，默认是false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表最后了
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode编号已经存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag 的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号%d 已经存在了，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /*
     * 根据newHeroNode的no来修改节点信息
     * */
    public void updata(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //找到需要修改的节点，根据no节点
        //定义辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//链表已经遍历完了
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("没有找到编号为" + newHeroNode.no + "的节点，无法进行修改！");

        }
    }

    /*
     * 删除节点：根据no来删除节点
     * 此处应用temp找到待删除的前一个节点位置，否则无法是链表连上
     * 即temp.next.no和需要删除的节点.no比较
     * */
    public void delete(int no) {
        HeroNode temp = head;

        while (temp.next != null) {

            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        //链表遍历结束
        System.out.println("没有编号为" + no + "的节点！");

    }

    //显示链表【遍历】

    public void list() {

        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//节点
// 定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点,引用变量赋值是地址传递

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //重写toString()方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

