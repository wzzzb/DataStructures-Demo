package atguigu.stack;

import java.util.Scanner;
import java.util.Stack;

public class SingleListStackDemo {
    public static void main(String[] args) {
        //测试一下SingleListStack是否正确
        //先创建一个SingleListStack对象->表示一个栈
        SingleListStack stack = new SingleListStack(3);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈（入栈）");
            System.out.println("pop:表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.reversePrint(stack.getHead());
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    ListNode ls = new ListNode(value);
                    stack.push(ls);
                    break;
                case "pop":
                    stack.pop();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出~~");



    }
}


//定义一个链表栈
class SingleListStack{
    //初始化一个头节点
    private ListNode head = new ListNode(0);

    //定义一个栈的最大容量maxSize
    private int maxSize;

    public SingleListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public ListNode getHead() {
        return head;
    }

    //定义方法判断栈是否为空
    public boolean isEmpty(){
        return head.getNext() == null;
    }


    //定义方法，确定栈中元素个数
    public int Num(){
        ListNode temp = head.getNext();
        //判断栈是否为空
        if(isEmpty()){//栈为空返回栈中元素个数为0
            return 0;
        }
        int num = 0; //定义一个计数器
        while(temp != null){
            num++;
            temp = temp.getNext();
        }
        return num;
    }

    //反向打印链表,即是正向打印栈
    public  void reversePrint(ListNode head){
        if(head.getNext() == null){
            System.out.println("空栈，无法打印");
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head.getNext();
        //将链表的所有节点压入栈中
        while(cur != null){
            stack.push(cur); //压栈
            cur = cur.getNext(); //让cur后移，遍历
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());  //stack的特点是先进后出
        }
    }

    //定义方法判断栈是否已满
    public boolean isFull(){
        return Num() == maxSize;
    }

    //定义方法向栈中添加元素
    public void push(ListNode listNode){
        ListNode temp = head;
        //判断栈是否已满
        if(isFull()){//栈已满，无法添加元素
            System.out.println("栈已满，无法添加");
            return;
        }
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(listNode);
    }

    //定义方法从栈中弹出一个元素
    public void pop(){
        if(isEmpty()){
            System.out.println("栈已空，无法弹出");
            return;
        }
        ListNode temp = head;
        while(temp.getNext().getNext() != null){
            temp = temp.getNext();
        }
        System.out.println(temp.getNext());
        temp.setNext(null);
    }
}

//创建结点类
class ListNode{
    private int value;
    private ListNode next;
    public ListNode(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "value=" + value +
                '}';
    }
}

