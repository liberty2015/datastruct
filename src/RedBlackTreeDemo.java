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

    static TreeNode root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class TreeNode {
        int data;
        public boolean color;

        TreeNode parent;
        TreeNode lNode;
        TreeNode rNode;
    }

    private static void rotateLeft(TreeNode p) {
        if (p != null) {
            TreeNode r = p.rNode;
            p.rNode = r.lNode;
            if (r.lNode != null) {
                r.lNode.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null) {
                root = r;
            } else if (p.parent.lNode == p)
                p.parent.lNode = r;
            else
                p.parent.rNode = r;
            r.lNode = p;
            p.parent = r;
        }
    }

    private static void rotateRight(TreeNode p) {
        if (p != null) {
            TreeNode l = p.lNode;
            p.lNode = l.rNode;
            if (l.rNode != null) {
                l.rNode.parent = p;
            }
            l.parent = p.parent;
            if (p.parent == null) {
                root = l;
            } else if (p.parent.rNode == p)
                p.parent.rNode = l;
            else
                p.parent.lNode = l;
            l.rNode = p;
            p.parent = l;
        }
    }

    private static TreeNode parentOf(TreeNode x) {
        return (x == null ? null : x.parent);
    }

    private static TreeNode leftOf(TreeNode x) {
        return (x == null ? null : x.lNode);
    }

    private static TreeNode rightOf(TreeNode x) {
        return (x == null ? null : x.rNode);
    }

    private static boolean colorOf(TreeNode x) {
        return (x == null ? BLACK : x.color);
    }

    private static void setColor(TreeNode x, boolean color) {
        if (x != null)
            x.color = color;
    }

    private static void fixAfterInsertion(TreeNode x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                TreeNode y = rightOf(parentOf(x));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                TreeNode y = leftOf(parentOf(x));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x),BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color=BLACK;
    }
}
