package collection.bstAVL;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * leetcode 173.Binary Search Tree Iterator
 * https://leetcode.com/problems/binary-search-tree-iterator/description/
 * @param <T>
 */
public class BSTIterator<T> {

    private Iterator<T> itr;
    public BSTIterator(TreeNode<T> root) {
        ArrayList<T> list=new ArrayList<>();
        inOrder(root,list);
        itr=list.iterator();
    }

    private void inOrder(TreeNode<T> p,ArrayList<T> list){
        if (p!=null){
            inOrder(p.getLeft(),list);
            list.add(p.getVal());
            inOrder(p.getRight(),list);
        }
    }



    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return itr.hasNext();
    }

    /** @return the next smallest number */
    public T next() {
        return itr.next();
    }
}
