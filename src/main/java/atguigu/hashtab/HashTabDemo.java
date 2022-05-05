package atguigu.hashtab;

import java.util.Scanner;

/**
 * @author 小郭同学
 * @Description TODO 手写哈希表
 * @date 2022/5/4 20:43
 * @project DataStructures-Demo
 */
public class HashTabDemo {
    public static void main(String[] args) {

        //创建哈希表,其中有7条链表
        HashTab hashTab = new HashTab(7);

        //写一个简单菜单来测试
        String key = "";
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("del:删除雇员");
            System.out.println("exit:退出系统雇员");

            key = sc.next();

            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = sc.nextInt();
                    System.out.println("输入名字");
                    String name = sc.next();
                    //创建雇员
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入要查找的id");
                    id = sc.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除雇员的id");
                    id = sc.nextInt();
                    hashTab.delEmpById(id);
                    break;
                default:
                    break;
            }

        }



    }

}

//创建HashTab来管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;//链表的数组
    private int size;//表示有几条链表

    //给一个构造器
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //如果未初始化链表，则链表数组内均为null
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    public void add(Emp emp) {
        //利用员工id找到该员工节点应在第几条链表
        int empLinkedListNo = hashFun(emp.id);
        //加入到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历哈希表，即遍历所有链表、
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }

    }
    //根据输入的id,查找雇员
    public void findEmpById(int id){
        //先确定在哪条链表上查找
        int empLinkedListNo =hashFun(id);
        Emp empById = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (empById==null){
            System.out.println("在哈希表中没有找到该雇员");
        }else {
            System.out.printf("在第%d 条链表中找到雇员id = %d\n" ,empLinkedListNo +1,id);
        }
    }

    //根据输入的id，删除雇员
    public void delEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        empLinkedListArray[empLinkedListNO].deletEmpById(id);
    }


    //编写一个散列函数，使用一个简单的取模法
    public int hashFun(int id) {
        return id % size;
        //加入的成员是从上到下依次加入到不同的链表中，
        // 而不是在一个链表满了之后，加入到下一个链表的

    }
}

/**
 * @Author 小郭同学
 * @Description 雇员节点类
 * @Date 2022/5/5 14:22
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，指向第一个雇员,因此这个链表的head是有效的，是直接指向第一个Emp的
    private Emp head; //默认是空

    //添加雇员到链表
    //说明
    //1.假定当添加雇员时，id是自增长的，即id的分配总是从小到大
    //因此，我么将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;

    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空！");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表信息为：");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf("id=%d<=>name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println("");
    }

    //根据id查找雇员
    //如果查找到就返回emp，如果没有找到就返回空
    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        while (curEmp.id != id) {
            if (curEmp.next == null) {
                return null;
            } else {
                curEmp = curEmp.next;
            }
        }
        return curEmp;
    }

    //根据雇员id删除指定雇员
    public void deletEmpById(int id) {
        if (head.id == id) {
            head = head.next;
            return;
        }
        //做辅助指针，指向待删除节点的前一个
        Emp curEmp = head;
        //遍历到链表最后，
        while (curEmp.next != null) {
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                return;
            }
            curEmp = curEmp.next;
        }
        //遍历结束，没有找到
        System.out.printf("要删除的%d 节点不存在\n", id);

    }


}
