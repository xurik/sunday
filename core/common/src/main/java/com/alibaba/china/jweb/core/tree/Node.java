package com.alibaba.china.jweb.core.tree;

/**
 * com.alibaba.china.jweb.core.tree:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 11:36 AM
 */
public class Node<T> {
    private Node<T> left;   //左节点
    private Node<T> right;  //右节点
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
