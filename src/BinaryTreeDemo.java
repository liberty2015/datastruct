import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTreeDemo {

    //创建二叉树
    //输入实例：ABCD#EF####GH
    //          A
    //         /  \
    //        B    C
    //       / \  / \
    //       D #  E F
    //      /\ /\ /\
    //     # # ## GH
    static TreeNode createBinaryTree(char[] treeStr, int index) {
        if (index >= treeStr.length) {
            return null;
        }
        char c = treeStr[index];
        TreeNode node = null;
        if (c == '#') {
            node = null;
        } else {
            node = new TreeNode();
            node.data = c;
            TreeNode lNode = createBinaryTree(treeStr, 2 * index + 1);
            TreeNode rNode = createBinaryTree(treeStr, 2 * index + 2);
            node.lNode = lNode;
            node.rNode = rNode;
        }
        return node;
    }

    //递归版前序遍历
    static void preOrderTraverseRecursive(TreeNode tree) {
        if (tree == null)
            return;
        System.out.println(tree.data);
        preOrderTraverseRecursive(tree.lNode);
        preOrderTraverseRecursive(tree.rNode);
    }

    //非递归版前序遍历
    //思路：
    //  1. 输出结点
    //  2. 若左孩子不为空，则将当前结点压入栈，设置左孩子为当前节点，重复1~2步
    //  3. 若左孩子为空，将栈顶结点推出栈并设为当前结点，设置当前结点的右孩子为当前结点，判断是否为空
    //  4. 若不为空，则重复1~2步
    //  5. 若为空，则继续出栈，不输出，将出栈结点的右孩子作为当前结点，判断是否为空
    //  6. 直到当前结点为null并且栈为空
    static void preOrderTraverse(TreeNode tree) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tCur = tree;
        while (tCur != null || !stack.isEmpty()) {
            System.out.println(tCur.data);
            stack.push(tCur);
            tCur = tCur.lNode;
            while (tCur==null&&!stack.isEmpty()){
                tCur=stack.pop();
                tCur=tCur.rNode;
            }
        }
    }

    //递归版中序遍历
    static void inOrderTraverseRecursive(TreeNode tree) {
        if (tree == null)
            return;
        inOrderTraverseRecursive(tree.lNode);
        System.out.println(tree.data);
        inOrderTraverseRecursive(tree.rNode);
    }

    //非递归版中序遍历
    //思路：
    //  1. 若左孩子不为空，则将当前结点压入栈，设置左孩子为当前节点
    //  2. 若左孩子为空，输出数据，设置当前结点的右孩子为当前结点，判断是否为空
    //  3. 若不为空，则重复1步
    //  4. 若为空，则继续出栈，并输出数据，将出栈结点的右孩子作为当前结点，判断是否为空
    //  5. 直到当前结点为null并且栈为空
    static void inOrderTraverse(TreeNode tree){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tCur = tree;
        while (tCur!=null||!stack.isEmpty()){
            if (tCur.lNode!=null){
                stack.push(tCur);
                tCur=tCur.lNode;
            }else {
                System.out.println(tCur.data);
                tCur=tCur.rNode;
                while (tCur==null&&!stack.isEmpty()){
                    tCur=stack.pop();
                    System.out.println(tCur.data);
                    tCur=tCur.rNode;
                }
            }
        }
    }

    //递归版后序遍历
    static void postOrderTraverseRecursive(TreeNode tree) {
        if (tree == null)
            return;
        postOrderTraverseRecursive(tree.lNode);
        postOrderTraverseRecursive(tree.rNode);
        System.out.println(tree.data);
    }

    //非递归版后序遍历
    static void postOrderTraverse(TreeNode tree){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tCur = tree;
        TreeNode tPre=null;
        stack.push(tCur);
        while (!stack.isEmpty()){
            tCur=stack.peek();
            if ((tCur.lNode==null&&tCur.rNode==null)||
                    (tPre!=null&&(tCur.lNode==tPre||tCur.rNode==tPre))){
                System.out.println(tCur.data);
                stack.pop();
                tPre=tCur;
            }else {
                if (tCur.rNode!=null){
                    stack.push(tCur.rNode);
                }
                if (tCur.lNode!=null){
                    stack.push(tCur.lNode);
                }
            }
        }
    }

    //层序遍历
    static void levelOrderTraverse(TreeNode tree) {
        if (tree == null)
            return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.println(node.data);
                queue.add(node.lNode);
                queue.add(node.rNode);
            }
        }
    }


    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String treeStr = scanner.nextLine();
        TreeNode tree = null;
        tree = createBinaryTree(treeStr.toCharArray(), 0);
        System.out.println("递归版前序遍历");
        preOrderTraverseRecursive(tree);
        System.out.println("非递归版前序遍历");
        preOrderTraverse(tree);
        System.out.println("递归版中序遍历");
        inOrderTraverseRecursive(tree);
        System.out.println("非递归版中序遍历");
        inOrderTraverse(tree);
        System.out.println("递归版后序遍历");
        postOrderTraverseRecursive(tree);
        System.out.println("非递归版后序遍历");
        postOrderTraverse(tree);
        System.out.println("层序遍历");
        levelOrderTraverse(tree);
    }
}

class TreeNode {
    char data;
    TreeNode lNode;
    TreeNode rNode;
}
