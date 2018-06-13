/**
 * Created by Liberty on 2018/6/7.
 */

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
        r.lNode=tree;
        tree=r;
        return tree;
    }

    /**
     * 左平衡操作
     * @param tree
     * @return
     */
    static TreeNode LeftBalance(TreeNode tree){
        TreeNode l,lr;
        l=tree.lNode;
        switch (l.bf){
            case LH:/*新节点插入在tree的左孩子的左子树上，要作单右旋操作**/
                tree.bf=l.bf=EH;
                tree=R_Rotate(tree);
                break;
            case RH:/*新节点插入在tree的左孩子的右子树上，要作双旋操作*/
                lr=l.rNode;
                switch (lr.bf){/*根据tree的左孩子的右子树的平衡因子修改tree以及其左孩子的平衡因子*/
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
                tree.lNode=L_Rotate(tree.lNode);
                tree=R_Rotate(tree);
                break;
        }
        return tree;
    }

    /**
     * 右平衡操作
     * @param tree
     * @return
     */
    static TreeNode RightBalance(TreeNode tree){
        TreeNode r,rl;
        r=tree.rNode;
        switch (r.bf){
            case RH:/*新节点插入在tree的右孩子的右子树上，要作单左旋操作**/
                tree.bf=r.bf=EH;
                tree=L_Rotate(tree);
                break;
            case LH:/*新节点插入在tree的右孩子的左子树上，要作双旋操作*/
                rl=r.lNode;
                switch (rl.bf){/*根据tree的右孩子的左子树的平衡因子修改tree以及其右孩子的平衡因子*/
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
                tree.rNode=R_Rotate(tree.rNode);
                tree=L_Rotate(tree);
                break;
        }
        return tree;
    }

    /**
     * 插入操作
     * @param tree
     * @param data
     * @param taller
     * @return
     */
    static TreeNode InsertAVL(TreeNode tree,int data,Taller taller){
        if(tree==null){
            tree=new TreeNode();
            tree.data=data;
            tree.bf=EH;
            taller.taller=true;
            return tree;
        }else {
            if (data==tree.data){//已存在则不插入
                taller.taller=false;
                return null;
            }
            if (data<tree.data){
                TreeNode n=InsertAVL(tree.lNode,data,taller);
                if (n==null){//未插入
                    return null;
                }
                tree.lNode=n;
                if (taller.taller){//已插入到左子树中并且左子树“长高”
                    switch (tree.bf){
                        case LH:/*原本tree的左子树比右子树高，现左子树更高，需要作左平衡处理*/
                            tree=LeftBalance(tree);
                            taller.taller=false;
                            break;
                        case EH:/*原本左右子树等高，现因左子树增高而树增高*/
                            tree.bf=LH;
                            taller.taller=true;
                            break;
                        case RH:/*原本右子树比左子树高，现左右字数等高*/
                            tree.bf=EH;
                            taller.taller=false;
                            break;
                    }
                }
            }else {
                TreeNode n=InsertAVL(tree.rNode,data,taller);
                if (n==null){//未插入
                    return null;
                }
                tree.rNode=n;
                if (taller.taller){//已插入到右子树中并且右子树“长高”
                    switch (tree.bf){
                        case LH:/*原本右子树比左子树高，现左右字数等高*/
                            tree.bf=EH;
                            taller.taller=false;
                            break;
                        case EH:/*原本左右子树等高，现右子树增高而树增高*/
                            tree.bf=RH;
                            taller.taller=true;
                            break;
                        case RH:/*原本tree的右子树比左子树高，现右子树更高，需要作右平衡处理*/
                            tree=RightBalance(tree);
                            taller.taller=false;
                            break;
                    }
                }
            }
        }
        return tree;
    }

    static class Taller{
        boolean taller;
    }



    public static void main(String[] args) {
        //不平衡情况：
//        1. 左左
//        int[] n={6,3,7,1,4,2};
//        2. 左右
//        int[] n={6,2,7,1,4,3};
//        3. 右左
//        int[] n={2,1,5,3,6,4};
//        4. 右右
        int[] n={2,1,4,3,6,5};
        TreeNode node=null;
        for (int i :
                n) {
            Taller taller=new Taller();
            node=InsertAVL(node,i,taller);
        }

    }
}
