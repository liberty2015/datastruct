package tree;

/**
 * Created by Administrator on 10/20/2018.
 */

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildFromSorted(0,nums.length-1,nums);
    }


    /**
     * 参照TreeMap 的buildFromSorted 函数，通过类似快排、二分排序的方式，将一个排好序的数组转化为平衡二叉树
     * @param lo
     * @param hi
     * @param nums
     * @return
     */
    private TreeNode<Integer> buildFromSorted(int lo,int hi,int[] nums){
        //为什么不能是>=，因为当lo==hi的时候说明遍历到了叶子节点，mid=(lo+hi)/2得到的值和lo和hi相等，等于遍历到了叶子节点。
        if (lo>hi){
            return null;
        }
        int mid=(lo+hi)>>>1;
        TreeNode<Integer> left=null;
        //为什么不能是<=，因为lo==mid的时候递归进去后会lo>hi(mid-1)，说明[lo,mid-1]已经没有节点可以遍历了
        if (lo<mid){
            left=buildFromSorted(lo,mid-1,nums);
        }

        int key=nums[mid];
        TreeNode<Integer> middle=new TreeNode<>(key);


        if (left!=null){
            middle.left=left;
        }
        TreeNode<Integer> right=null;
        //为什么不能是<=，和上面的道理相同，因为hi==mid的时候递归进去后会lo(mid+1)>hi，说明[mid+1,hi]已经没有节点可以遍历
        if (mid<hi){
            right=buildFromSorted(mid+1,hi,nums);
        }

        if (right!=null){
            middle.right=right;
        }
        return middle;
    }
}
