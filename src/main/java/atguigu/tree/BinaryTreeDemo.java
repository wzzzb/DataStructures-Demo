package atguigu.tree;

/**
 * @author 小郭同学
 * @Description TODO 二叉树的前 中 后序遍历；定义二叉树 确定了一个根节点就确定了一颗二叉树；
 * TODO 前序遍历：每一个子树都是先输出自己，再输出左子树，最后输出右子树
 * TODO 中序遍历：每一个子树都是先输出左子树，再输出自己，最后输出右子树
 * TODO 后序遍历：每一个子树都是先输出左子树，再输出右子树，最后输出自己
 * 尝试同时推送到Gitee和GitHub上，第二次尝试
 * @date 2022/5/6 15:38
 * @project DataStructures-Demo
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建所需的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

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

        binaryTree.qianOrderSearch(5);
        binaryTree.zhongOrderSearch(5);
        binaryTree.houOrderSearch(5);

    }

}

//定义二叉树 确定了一个根节点就确定了一颗二叉树
class BinaryTree {
    private HeroNode root;

    //提供一个set方法，方便后续将某个节点定义为根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void qianOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    public HeroNode qianOrderSearch(int id) {
        if (this.root != null) {
            HeroNode rsHeroNode = this.root.preOrderSearch(id);
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

    public HeroNode zhongOrderSearch(int id) {
        if (this.root != null) {
            HeroNode rsHeroNode = this.root.midOrderSearch(id);
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

    public HeroNode houOrderSearch(int id) {
        if (this.root != null) {
            HeroNode rsHeroNode = this.root.postOrderSearch(id);
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

    public HeroNode preOrderSearch(int id) {
        System.out.println("前序调用了。。。");
        //这里才是判断是与否的语句，其他位置还是查找的语句，所以只需记录这里即可知道比较了几次，
        if (this.id == id) {
            return this;
        }
        //用来记录查找的返回值
        HeroNode rsHeroNode = null;
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

    public HeroNode midOrderSearch(int id) {
        //用来记录查找的返回值
        HeroNode rsHeroNode = null;
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

    public HeroNode postOrderSearch(int id) {
        //用来记录查找的返回值
        HeroNode rsHeroNode = null;
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
