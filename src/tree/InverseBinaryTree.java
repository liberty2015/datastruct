package tree;

import java.util.LinkedList;

/**
 * leetcode 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/description/
 * 二叉树反转
 */
public class InverseBinaryTree {

    /**
     * 二叉树反转--前序遍历递归版
     * @param root
     * @return
     */
    public TreeNode invertTreeRecursivePreOrder(TreeNode root){
        if (root!=null){
            TreeNode t=root.left;
            root.left=root.right;
            root.right=t;
            invertTreeRecursivePreOrder(root.left);
            invertTreeRecursivePreOrder(root.right);
        }
        return root;
    }

    /**
     * 二叉树反转--中序遍历递归版
     * @param root
     * @return
     */
    public TreeNode invertTreeRecursiveInOrder(TreeNode root){
        if (root!=null){
            invertTreeRecursiveInOrder(root.right);
            TreeNode t=root.left;
            root.left=root.right;
            root.right=t;
            invertTreeRecursiveInOrder(root.right);
        }
        return root;
    }

    /**
     * 二叉树反转--后序遍历递归版
     * @param root
     * @return
     */
    public TreeNode invertTreeRecursivePostOrder(TreeNode root){
        if (root!=null){
            invertTreeRecursivePostOrder(root.left);
            invertTreeRecursivePostOrder(root.right);
            TreeNode t=root.left;
            root.left=root.right;
            root.right=t;
        }
        return root;
    }


    /**
     * 二叉树反转--中序遍历
     * @param root
     * @return
     */
    public TreeNode invertTreeLevelOrder(TreeNode root){
        if (root!=null){
            LinkedList<TreeNode> queue=new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                if (node!=null){
                    TreeNode t=node.left;
                    node.left=node.right;
                    node.right=t;
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

}
