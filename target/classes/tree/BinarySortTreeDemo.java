package tree; /**
 * Created by Liberty on 2018/6/7.
 */
/**
 * 二叉查找树（二叉排序树）
 */
public class BinarySortTreeDemo {

    /**
     * 实现二叉查找树的查找算法
     * @param tree
     * @param key
     * @param f
     * @return
     */
    static TreeNode searchBST(TreeNode tree,TreeNode f, int key){
        TreeNode p=null;
        if (tree==null){
            p=f;
            return p;
        }else if (key==tree.data){
            p=tree;
            return p;
        }else if (key<tree.data){
            return searchBST(tree.lNode,tree,key);
        }else {
            return searchBST(tree.rNode,tree,key);
        }
    }

    /**
     * 相等重复的值不需要重复插入
     * @param tree
     * @param key
     * @return
     */
    static TreeNode insertBST(TreeNode tree,int key){
        TreeNode p=null,s=null;
        p=searchBST(tree,null,key);
        if (p==null){
            s=new TreeNode();
            s.data=key;
            tree=s;
            return tree;
        }else if (p.data==key){
            return tree;
        }else {
            s=new TreeNode();
            s.data=key;
            if (p.data>key){
                p.lNode=s;
            }else if (p.data<key){
                p.rNode=s;
            }
            return tree;
        }
    }

    static boolean deleteBST(TreeNode tree,int key){
        if (tree==null)
            return false;
        if (key==tree.data)
            return delete(tree);
        else if (key<tree.data)
            return deleteBST(tree.lNode,key);
        else
            return deleteBST(tree.rNode,key);
    }

    static boolean delete(TreeNode node){
        TreeNode q,s;
        if (node.rNode==null){
            q=node;
            node=node.lNode;
            q=null;
        }else if (node.lNode==null){
            q=node;
            node=node.rNode;
            q=null;
        }else {
            q=node;
            s=node.lNode;
            //在左子树中找到值最大的结点
            while (s.rNode!=null){
                q=s;
                s=s.rNode;
            }
            node.data=s.data;
            if (node==q){
                q.lNode=s.lNode;
            }else {
                q.rNode=s.lNode;
            }
            s=null;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a={62,88,58,47,35,73,51,99,37,93};
        TreeNode tree=null;
        for (int i :
                a) {
            tree=insertBST(tree,i);
        }
        System.out.println();
    }

    /**
     * 寻找（中序）后继节点
     */

    private static class TreeNode {
        int data;
        TreeNode lNode;
        TreeNode rNode;
    }
}

