package com.alibaba.china.jweb.core.tree;

/**
 * com.alibaba.china.jweb.core.tree:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 11:35 AM
 */
public class BinaryTree {
    private static Node root;  //¸ù½Úµã

    public static Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public BinaryTree(Node r) {
        this.root = r;
    }

}
