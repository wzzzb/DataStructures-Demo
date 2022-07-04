package atguigu.tree;

/**
 * @author 小郭同学
 * @Description TODO 线索化二叉树 在节点中再加两条属性，用来记录指针指向的是左子树（右子树）还是前驱结点（后驱节点）
 * TODO 前中后序的线索化《遍历》只是顺序不同，前序和后序《线索化时》在不判断的时候会出现死循环而中序不会！！！
 * @date 2022/5/27 9:36
 * @project DataStructures-Demo
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        ThreadHeroNode root = new ThreadHeroNode(1, "tom");
        ThreadHeroNode node2 = new ThreadHeroNode(3, "jack");
        ThreadHeroNode node3 = new ThreadHeroNode(6, "smith");
        ThreadHeroNode node4 = new ThreadHeroNode(8, "marry");
        ThreadHeroNode node5 = new ThreadHeroNode(10, "king");
        ThreadHeroNode node6 = new ThreadHeroNode(14, "dim");

        //二叉树，后面要递归创建
        //目前先手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //先将二叉树所有节点进行中序线索化
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);
        System.out.println("前序遍历结果：");
        threadBinaryTree.qianOrder();
        System.out.println("中序遍历结果：");
        threadBinaryTree.zhongOrder();
        System.out.println("后序遍历结果");
        threadBinaryTree.houOrder();
//        threadBinaryTree.threadedNodes();
//
//        //测试中序线索化结果
//        //验证10节点的前驱和后继节点是否改变
//        ThreadHeroNode leftNode = node5.getLeft();
//        System.out.println("10号节点的前驱结点是=" + leftNode);   //指向3
//        ThreadHeroNode rightNode = node5.getRight();
//        System.out.println("10号节点的后继结点是=" + rightNode);  //指向1
//
//        System.out.println("中序线索化遍历结果：");
//        threadBinaryTree.threadedList();//8 3 10 1 14 6


        threadBinaryTree.threadedNodespre();
//测试前序线索化结果
//验证10节点的前驱和后继节点是否改变
        ThreadHeroNode leftNode = node5.getLeft();
        System.out.println("10号节点的前驱结点是=" + leftNode);   //指向8
        ThreadHeroNode rightNode = node5.getRight();
        System.out.println("10号节点的后继结点是=" + rightNode);  //指向6
        System.out.println("前序线索化遍历结果：");
        threadBinaryTree.threadedListpre();//1 3 8 10 6 14
    }
}

//定义二叉树 确定了一个根节点就确定了一颗二叉树
class ThreadBinaryTree {
    private ThreadHeroNode root;
    //当前节点与其前驱节点进行线索化时，需要一个指针指向前驱节点
    private ThreadHeroNode pre = null;

    //提供一个set方法，方便后续将某个节点定义为根节点
    public void setRoot(ThreadHeroNode root) {
        this.root = root;
    }

    //重载中序线索化方法-------------------------------------------------------------------
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * @param node 当前需要线索化的节点
     * @return void
     * @Author 小郭同学
     * @Description 编写对二叉树进行中序线索化的方法
     * @Date 2022/6/6 16:20
     */
    public void threadedNodes(ThreadHeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }
        //1、线索化左子树
        threadedNodes(node.getLeft());

        //2、线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //将当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，1表示指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点,当前节点不知道自己的后继节点是谁，但当前节点的前驱节点知道自己的后继节点是谁
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，将pre指向下一个节点的前驱节点
        pre = node;

        //3、线索化右子树
        threadedNodes(node.getRight());

    }

    /**
     * @return void
     * @Author 小郭同学
     * @Description todo 遍历中序线索化二叉树的方法,相比于普通中序遍历取消了递归
     * @Date 2022/6/6 19:45
     */
    public void threadedList() {
        //第一个变量，存储当前遍历的节点，从root开始
        ThreadHeroNode node = root;
        //节点为null则遍历结束了
        while (node != null) {
            //无线索时为保证是中序输出，则按照中序遍历的方式寻找线索并输出
            //因为是中序遍历所以从左边（左子树）找，第一次先找到起始节点（即当前子树的叶子）
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //找到了具有前驱的节点（当前子树的叶子），则输出当前节点，当前节点的前驱节点在上次大循环已输出
            System.out.println(node);

            //如果当前节点的右指针指向的是后继节点，则后继节点就一直输出即可（因为线索化的顺序没错）
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //上个循环结束，说明当前节点的右指针并不是后继节点，故不能输出右指针指向的节点
            //按照中序遍历的顺序，node的左边和自身已经输出了，则轮到其右子树按中序遍历输出
            node = node.getRight();
        }
    }

    //重载前序线索化方法------------------------------------------------
    public void threadedNodespre() {
        this.threadedNodespre(root);
    }

    /**
     * @param node 当前需要线索化的节点
     * @return void
     * @Author 小郭同学
     * @Description 编写对二叉树进行前序线索化的方法
     * @Date 2022/6/6 16:20
     */
    public void threadedNodespre(ThreadHeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }
        //1、线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //将当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，1表示指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点,当前节点不知道自己的后继节点是谁，但当前节点的前驱节点知道自己的后继节点是谁
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，将pre指向下一个节点的前驱节点
        pre = node;

        //2、线索化左子树
        //只有当前节点的左指针域的类型不为前驱节点时才能遍历，不然会导致无限循环
        //如果没判断，则当前节点指向前驱节点，然后前驱节点又指向当前节点（死循环在最开始的叶子和叶子的前驱节点）
        if (node.getLeftType() == 0) threadedNodespre(node.getLeft());

        //3、线索化右子树
        //只有当前节点的右指针域的类型不为后继节点时才能遍历，不然会导致无限循环
        //如果没有判断，则当前节点指向后继节点，然后后继节点指向当前节点（死循环在最后的叶子的前驱节点和叶子）
        if (node.getRightType() == 0) threadedNodespre(node.getRight());

    }

    /**
     * @return void
     * @Author 小郭同学
     * @Description todo 遍历前序线索化二叉树的方法
     * @Date 2022/6/6 19:45
     */
    public void threadedListpre() {
        //第一个变量，存储当前遍历的节点，从root开始
        ThreadHeroNode node = root;
        //节点为null则遍历结束了
        while (node != null) {
            //找到了具有前驱的节点（当前子树的叶子），则输出当前节点，当前节点的前驱节点在上次大循环已输出
            System.out.println(node);
            //无线索时为保证是中序输出，则按照中序遍历的方式寻找线索并输出
            //因为是中序遍历所以从左边（左子树）找，第一次先找到起始节点（即当前子树的叶子）
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            //如果当前节点的右指针指向的是后继节点，则后继节点就一直输出即可（因为线索化的顺序没错）
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //上个循环结束，说明当前节点的右指针并不是后继节点，故不能输出右指针指向的节点
            //按照中序遍历的顺序，node的左边和自身已经输出了，则轮到其右子树按中序遍历输出
            node = node.getRight();
        }
    }


    public void qianOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    public ThreadHeroNode qianOrderSearch(int id) {
        if (this.root != null) {
            ThreadHeroNode rsHeroNode = this.root.preOrderSearch(id);
            if (rsHeroNode != null) {
                System.out.println("前序查找找到的节点信息为：" + rsHeroNode);
                return rsHeroNode;
            } else {
                System.out.println("当前二叉树中没有id为" + id + "的节点");
                return null;
            }
        } else {
            System.out.println("当前二叉树为空！");
        }
        return null;

    }

    public void zhongOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    public ThreadHeroNode zhongOrderSearch(int id) {
        if (this.root != null) {
            ThreadHeroNode rsHeroNode = this.root.midOrderSearch(id);
            if (rsHeroNode != null) {
                System.out.println("中序查找找到的节点信息为：" + rsHeroNode);
                return rsHeroNode;
            } else {
                System.out.println("当前二叉树中没有id为" + id + "的节点");
                return null;
            }
        } else {
            System.out.println("当前二叉树为空！");
        }

        return null;
    }

    public void houOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    public ThreadHeroNode houOrderSearch(int id) {
        if (this.root != null) {
            ThreadHeroNode rsHeroNode = this.root.postOrderSearch(id);
            if (rsHeroNode != null) {
                System.out.println("后续查找找到的节点信息为：" + rsHeroNode);
                return rsHeroNode;
            } else {
                System.out.println("当前二叉树中没有id为" + id + "的节点");
                return null;
            }
        } else {
            System.out.println("当前二叉树为空！");
        }
        return null;
    }

}


//先创建二叉树节点
class ThreadHeroNode {
    private int id;
    private String name;
    private ThreadHeroNode left;
    private ThreadHeroNode right;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    //1、如果leftType=0表示指向的是左子树，如果1则表示指向前驱节点
    //2、如果rightType=0表示指向的是右子树，如果1则表示指向后继节点
    private int leftType;
    private int rightType;

    public ThreadHeroNode(int id, String name) {
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

    public ThreadHeroNode preOrderSearch(int id) {
        System.out.println("前序调用了。。。");
        //这里才是判断是与否的语句，其他位置还是查找的语句，所以只需记录这里即可知道比较了几次，
        if (this.id == id) {
            return this;
        }
        //用来记录查找的返回值
        ThreadHeroNode rsHeroNode = null;
        if (this.left != null) {
            rsHeroNode = this.left.preOrderSearch(id);
            //查找的返回值不为null（说明在当前的左子树中找到了），继续向上级树返回结果；如返回null，则查找继续进行
            if (rsHeroNode != null) {
                return rsHeroNode;
            }
        }
        if (this.right != null) {
            rsHeroNode = this.right.preOrderSearch(id);
            //查找的返回值不为null（说明在当前的右子树中找到了），继续向上级树返回结果；如返回null，则查找继续进行（实际该右子树的查找已经结束了）
            //无论是否为null，下一步紧跟着都要返回，所以直接省略这步判断，向上返回节点即可
            /*if (rsHeroNode!=null){
                return rsHeroNode;
            }*/
        }
        //运行到此，说明该子树内部已经查找完成，无论结果是否为null都要向上返回值
        return rsHeroNode;
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

    public ThreadHeroNode midOrderSearch(int id) {
        //用来记录查找的返回值
        ThreadHeroNode rsHeroNode = null;
        if (this.left != null) {
            rsHeroNode = this.left.midOrderSearch(id);
            //查找的返回值不为null（说明在当前的左子树中找到了），继续向上级树返回结果；如返回null，则查找继续进行
            if (rsHeroNode != null) {
                return rsHeroNode;
            }
        }
        System.out.println("中序调用了。。。");
        //这里才是判断是与否的语句，其他位置还是查找的语句，所以只需记录这里即可知道比较了几次，
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            rsHeroNode = this.right.midOrderSearch(id);
            //查找的返回值不为null（说明在当前的右子树中找到了），继续向上级树返回结果；如返回null，则查找继续进行（实际该右子树的查找已经结束了）
            //无论是否为null，下一步紧跟着都要返回，所以直接省略这步判断，向上返回节点即可
            /*if (rsHeroNode!=null){
                return rsHeroNode;
            }*/
        }
        //运行到此，说明该子树内部已经查找完成，无论结果是否为null都要向上返回值
        return rsHeroNode;
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

    public ThreadHeroNode postOrderSearch(int id) {
        //用来记录查找的返回值
        ThreadHeroNode rsHeroNode = null;
        if (this.left != null) {
            rsHeroNode = this.left.postOrderSearch(id);
            //查找的返回值不为null（说明在当前的左子树中找到了），继续向上级树返回结果；如返回null，则查找继续进行
            if (rsHeroNode != null) {
                return rsHeroNode;
            }
        }
        if (this.right != null) {
            rsHeroNode = this.right.postOrderSearch(id);
            //查找的返回值不为null（说明在当前的右子树中找到了），继续向上级树返回结果；如返回null，则查找继续进行
            if (rsHeroNode != null) {
                return rsHeroNode;
            }
        }
        System.out.println("后序调用了。。。");
        //这里才是判断是与否的语句，其他位置还是查找的语句，所以只需记录这里即可知道比较了几次，
        if (this.id == id) {
            return this;
        }
        //运行到此，说明该子树内部已经查找完成，无论结果是否为null都要向上返回值
        return null;
    }

    @Override
    public String toString() {
        return "ThreadHeroNode{" +
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

    public ThreadHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadHeroNode left) {
        this.left = left;
    }

    public ThreadHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadHeroNode right) {
        this.right = right;
    }
}













