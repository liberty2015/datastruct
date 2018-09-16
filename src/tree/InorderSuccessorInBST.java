package tree;

/**
 * Created by liberty on 2018/6/18.
 */

/**
 * 获取二叉查找树某一点的中序直接后继节点
 * 情况：
 * 0. p为最大节点，因此没有后继，返回空
 * 1. p有右子树
 * 2. p没有右子树
 */
public class InorderSuccessorInBST {


    private static class TreeNode {
        char data;
        TreeNode lNode;
        TreeNode rNode;
    }

    private TreeNode getLastEntry(TreeNode p){
        while (p.rNode!=null){
            p=p.rNode;
        }
        return p;
    }

    private TreeNode getFirstEntry(TreeNode p){
        while (p.lNode!=null){
            p=p.lNode;
        }
        return p;
    }

    public TreeNode inorderSuccessor(TreeNode root,TreeNode p){
        if (p==null)
            return null;
        //0. p为最大节点，因此没有后继，返回空
        if (getLastEntry(root)==p)
            return null;
        //1. p有右子树
        if (p.rNode!=null)
            return getFirstEntry(p.rNode);
        TreeNode parent=root;
        TreeNode temp=root;
        //2. p没有右子树
        while (parent!=null){
            if (parent.data==p.data){
                break;
            }else if (p.data<parent.data){
                temp=parent;
                parent=parent.lNode;
            }else {
                parent=parent.rNode;
            }
        }

        return temp;
    }

}
