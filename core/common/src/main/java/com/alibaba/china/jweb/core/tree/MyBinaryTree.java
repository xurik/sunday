package com.alibaba.china.jweb.core.tree;

public class MyBinaryTree {
    private static Node<Integer> root;  //���ڵ�

    public static Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public MyBinaryTree(Node r) {
        this.root = r;
    }

    public void addData(Integer n) {   //������
        if (root == null) {
            root = new Node(n);
        } else {
            boolean flag = true;
            Node<Integer> parent = root;
            Node<Integer> insertNode = new Node(n);
            while (flag) {  //��ѭ����ֱ���ڵ���뵽��ȷλ�û�Ԫ���Ѵ���
                if (n <= parent.getData()) {
                    if (parent.getLeft() == null) {//���û�����ӽڵ㣬��������Ԫ�����ó����ӽڵ�
                        parent.setLeft(insertNode);
                        flag = false;
                    } else {
                        parent = parent.getLeft();  //�����������Ϊ�գ���Ҫ��������ӵ�
                    }
                } else if (n > parent.getData()) {
                    if (parent.getRight() == null) {//���û�����ӽڵ㣬��������Ԫ�����ó����ӽڵ�
                        parent.setRight(insertNode);
                        flag = false;
                    } else {
                        parent = parent.getLeft();  //�����������Ϊ�գ���Ҫ��������ӵ�
                    }
                }
            }
        }
    }

    public static void visit(Node p) {
        System.out.print(p.getData() + " ");
    }

    public static void preorder(Node p) {  //ǰ������
        if (p != null) {
            visit(p);
            preorder(p.getLeft());
            preorder(p.getRight());
        }
    }

    protected static void inorder(Node p) { //��������
        if (p != null) {
            inorder(p.getLeft());
            visit(p);
            inorder(p.getRight());
        }
    }

    protected static void postorder(Node p) {  //��������
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