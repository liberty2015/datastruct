/**
 * Created by Liberty on 2018/6/13.
 */

/**
 * 红黑树
 * 定义：
 * 1. 任何节点都有颜色，红色或黑色
 * 2. 根节点是黑色的
 * 3. 父子节点之间不能出现两个连续的红节点
 * 4. 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等
 * 5. 空节点被认为是黑色的
 */
public class RedBlackTreeDemo {

    private static class TreeNode {
        int data;
        public boolean isRed;

        TreeNode parent;
        TreeNode lNode;
        TreeNode rNode;
    }
}
