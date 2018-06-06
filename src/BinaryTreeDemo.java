import java.util.Scanner;

public class BinaryTreeDemo {

    //创建二叉树
    static TreeNode createBinaryTree(char[] treeStr,int index){
        char c=treeStr[index];
        TreeNode node=null;
        if (c=='#'){
            node=null;
        }else {
            node=new TreeNode();
            node.data=c;
            TreeNode lNode=createBinaryTree(treeStr,index+1);
            TreeNode rNode=createBinaryTree(treeStr,index+2);
            node.lNode=lNode;
            node.rNode=rNode;
        }
        return node;
    }

    //递归版前序遍历
    static void preOrderTraverse(TreeNode tree){
        if (tree==null)
            return;
        System.out.println(tree.data);
        preOrderTraverse(tree.lNode);
        preOrderTraverse(tree.rNode);
    }

    //递归版中序遍历
    static void inOrderTraverse(TreeNode tree){
        if (tree==null)
            return;
        inOrderTraverse(tree.lNode);
        System.out.println(tree.data);
        inOrderTraverse(tree.rNode);
    }

    //递归版后序遍历
    static void postOrderTraverse(TreeNode tree){
        if (tree==null)
            return;
        postOrderTraverse(tree.lNode);
        postOrderTraverse(tree.rNode);
        System.out.println(tree.data);
    }


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String treeStr=scanner.nextLine();
        TreeNode tree=null;
        tree=createBinaryTree(treeStr.toCharArray(),0);
        System.out.println("前序遍历");
        preOrderTraverse(tree);
        System.out.println("中序遍历");
        inOrderTraverse(tree);
        System.out.println("后序遍历");
        postOrderTraverse(tree);
    }
}

class TreeNode{
    char data;
    TreeNode lNode;
    TreeNode rNode;
}
