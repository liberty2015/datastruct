package tree;

/**
 * Created by liberty on 2018/6/18.
 */

import java.util.Stack;

/**
 * 获取二叉查找树某一点的中序遍历直接后继节点
 * 情况：
 * 0. p为最大节点，因此没有后继，返回空
 * 1. p有右子树，查找右子树中最小的节点
 * 2. p没有右子树，从root节点开始向下查找，查找第一个关键字比它大的节点（或者：从p的父节点开始向上查找，查找第一个“孩子是左孩子的父亲节点”）
 */
public class InorderSuccessorInBST {


    private static class TreeNode {
        char data;
        TreeNode lNode;
        TreeNode rNode;
    }

    private TreeNode getLastEntry(TreeNode p) {
        while (p.rNode != null) {
            p = p.rNode;
        }
        return p;
    }

    private TreeNode getFirstEntry(TreeNode p) {
        while (p.lNode != null) {
            p = p.lNode;
        }
        return p;
    }

    /**
     * 获取二叉查找树某一点的中序遍历直接后继节点
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null)
            return null;
        //0. p为最大节点，因此没有后继，返回空
        if (getLastEntry(root) == p)
            return null;
        //1. p有右子树
        if (p.rNode != null)
            return getFirstEntry(p.rNode);
        TreeNode parent = root;
        TreeNode temp = root;
        //2. p没有右子树
        while (parent != null) {
            if (parent.data == p.data) {
                break;
            } else if (p.data < parent.data) {
                temp = parent;
                parent = parent.lNode;
            } else {
                parent = parent.rNode;
            }
        }

        return temp;
    }

    /**
     * 获取二叉查找树某一点的中序遍历直接前驱节点
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (p == null)
            return null;
        if (getFirstEntry(root) == p)
            return null;
        if (p.lNode != null)
            return getLastEntry(p.lNode);
        TreeNode parent = root;
        TreeNode temp = root;
        while (parent != null) {
            if (parent.data == p.data) {
                break;
            } else if (p.data > parent.data) {
                temp = parent;
                parent = parent.rNode;
            } else {
                parent = parent.lNode;
            }
        }
        return temp;
    }
}
