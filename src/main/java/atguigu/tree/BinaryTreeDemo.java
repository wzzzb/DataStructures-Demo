package atguigu.tree;

/**
 * @author 小郭同学
 * @Description TODO 二叉树的前 中 后序遍历；定义二叉树 确定了一个根节点就确定了一颗二叉树
 * @date 2022/5/6 15:38
 * @project DataStructures-Demo
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建所需的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        //说明，我们先手动创建该二叉树，后面我们学习以递归的方式创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);


        //测试前序遍历
        System.out.println("前序遍历");
        binaryTree.qianOrder();  //12354

        //测试中序遍历
        System.out.println("中序遍历");
        binaryTree.zhongOrder();  //21534

        //测试后续遍历
        System.out.println("测试后序遍历");
        binaryTree.houOrder();   //25431

    }

}
//定义二叉树 确定了一个根节点就确定了一颗二叉树
class BinaryTree{
    private HeroNode root;

    //提供一个set方法，方便后续将某个节点定义为根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void qianOrder(){
        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("当前二叉树为空！");
        }
    }
    public void zhongOrder(){
        if (this.root!=null){
            this.root.midOrder();
        }else {
            System.out.println("当前二叉树为空！");
        }
    }
    public void houOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("当前二叉树为空！");
        }
    }
}


//先创建二叉树节点
class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void midOrder() {

        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    public void postOrder() {

        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}
