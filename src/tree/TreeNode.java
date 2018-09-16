package tree;

public class TreeNode<T> {

    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T val){
        this.val=val;
    }

    public TreeNode(T val,TreeNode left,TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }

    public TreeNode(){

    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
