/**
 * Created by Administrator on 2018/6/7.
 */

import java.util.List;

/**
 * 平衡二叉树
 * 定义：一种每个节点的左右子树高度差不大于1的二叉排序树
 */
public class BalancedBinarySearchTreeDemo {

    private static final int LH=1;/*左高*/
    private static final int EH=0;/*登高*/
    private static final int RH=-1;

    private static class TreeNode{
        int data;
        int bf;//平衡因子
        TreeNode lNode;
        TreeNode rNode;
    }

    /**
     * 右旋操作
     * @param tree
     * @return
     */
    static TreeNode R_Rotate(TreeNode tree){
        TreeNode l;
        l=tree.lNode;
        tree.lNode=l.rNode;
        l.rNode=tree;
        tree=l;
        return tree;
    }

    /**
     * 左旋操作
     * @param tree
     * @return
     */
    static TreeNode L_Rotate(TreeNode tree){
        TreeNode r;
        r=tree.rNode;
        tree.rNode=r.lNode;
        r.rNode=tree;
        tree=r;
        return tree;
    }

    /**
     * 左平衡操作(还是没明白平衡因子的转换)
     * @param tree
     * @return
     */
    static TreeNode LeftBalance(TreeNode tree){
        TreeNode l,lr;
        l=tree.lNode;
        switch (l.bf){
            case LH:
                tree.bf=l.bf=EH;
                tree=R_Rotate(tree);
                break;
            case RH:
                lr=l.rNode;
                switch (lr.bf){
                    case LH:
                        tree.bf=RH;
                        l.bf=EH;
                        break;
                    case EH:
                        tree.bf=l.bf=EH;
                        break;
                    case RH:
                        tree.bf=EH;
                        l.bf=LH;
                        break;
                }
                lr.bf=EH;
                L_Rotate(tree.lNode);
                R_Rotate(tree);
                break;
        }
        return tree;
    }

    static TreeNode RightBalance(TreeNode tree){
        TreeNode r,rl;
        r=tree.rNode;
        switch (r.bf){
            case RH:
                tree.bf=r.bf=EH;
                tree=L_Rotate(tree);
                break;
            case LH:
                rl=r.lNode;
                switch (rl.bf){
                    case LH:
                        tree.bf=EH;
                        r.bf=RH;
                        break;
                    case EH:
                        tree.bf=r.bf=EH;
                        break;
                    case RH:
                        tree.bf= LH;
                        r.bf=EH;
                        break;
                }
                rl.bf=EH;
                tree=R_Rotate(tree.rNode);
                tree=L_Rotate(tree);
                break;
        }
        return tree;
    }

    static TreeNode InsertAVL(TreeNode tree,int data){

        return tree;
    }
}
