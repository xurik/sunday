package com.alibaba.china.jweb.core.tree;

public class MyBinaryTree {
    private static Node<Integer> root;  //根节点

    public static Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public MyBinaryTree(Node r) {
        this.root = r;
    }

    public void addData(Integer n) {   //创建树
        if (root == null) {
            root = new Node(n);
        } else {
            boolean flag = true;
            Node<Integer> parent = root;
            Node<Integer> insertNode = new Node(n);
            while (flag) {  //死循环，直到节点插入到正确位置或元素已存在
                if (n <= parent.getData()) {
                    if (parent.getLeft() == null) {//如果没有左子节点，则或把新增元素设置成左子节点
                        parent.setLeft(insertNode);
                        flag = false;
                    } else {
                        parent = parent.getLeft();  //如果左子树不为空，则还要继续找添加点
                    }
                } else if (n > parent.getData()) {
                    if (parent.getRight() == null) {//如果没有右子节点，则或把新增元素设置成右子节点
                        parent.setRight(insertNode);
                        flag = false;
                    } else {
                        parent = parent.getLeft();  //如果右子树不为空，则还要继续找添加点
                    }
                }
            }
        }
    }

    public static void visit(Node p) {
        System.out.print(p.getData() + " ");
    }

    public static void preorder(Node p) {  //前序排列
        if (p != null) {
            visit(p);
            preorder(p.getLeft());
            preorder(p.getRight());
        }
    }

    protected static void inorder(Node p) { //中序排序
        if (p != null) {
            inorder(p.getLeft());
            visit(p);
            inorder(p.getRight());
        }
    }

    protected static void postorder(Node p) {  //后序排列
        if (p != null) {
            postorder(p.getLeft());
            postorder(p.getRight());
            visit(p);
        }
    }

    public static void main(String[] args) {
        Node node = new Node(10);
        MyBinaryTree bt = new MyBinaryTree(node);
        bt.addData(5);
        bt.addData(12);
        bt.addData(20);
        bt.addData(2);
        bt.addData(14);
        bt.addData(1);
        preorder(MyBinaryTree.getRoot());
        System.out.println();
        inorder(MyBinaryTree.getRoot());
        System.out.println();
        postorder(MyBinaryTree.getRoot());
    }
}