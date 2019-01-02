package linearTable.linkedList;

import java.util.Scanner;

/**
 * Created by Peter on 2019/1/1.
 */

/**
 * 基于链表的LRU淘汰算法实现
 *
 * 1. 越靠近链表尾部的节点是越早访问的
 * 2. 若数据已经被缓存到链表中，遍历这个数据对应的结点，并将其从原来的位置移动到链表的头部
 * 3. 若此数据没有缓存到链表中，则分两种情况：
 *      - 若此时缓存未满，将此节点直接插入到链表头部
 *      - 若此时缓存已满，则删除链表尾部节点，将新数据节点插入到链表头部
 */
public class LRUBasedLinkedList<T> {

    private TreeNode first;
    private TreeNode last;
    private TreeNode current;

    private int length;

    private static final int DEFAULT_CAPACITY = 20;
    private int capacity;

    class TreeNode {
        T data;
        TreeNode next;
    }

    public LRUBasedLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedLinkedList(int capacity) {
        this.capacity = capacity;
        init();
    }

    private void init() {
        first = new TreeNode();
        first.data = null;
        last = new TreeNode();
        last.data = null;
        last.next = null;
        first.next = last;
        current = first;
        length = 0;
    }

    public void put(T val) {
        TreeNode node = findTreeNode(val);
        if (node == null) {
            if (length >= capacity) {
                removeOldestTreeNode();
            }
            node = new TreeNode();
            node.data = val;
            node.next = first.next;
            first.next = node;
            length++;
        } else {
            current.next = node.next;
            node.next = first.next;
            first.next = node;
        }
    }

    private TreeNode findTreeNode(T data) {
        TreeNode p = first.next;
        TreeNode findNode = null;
        while (p != null && p.next != null) {
            if (p.data == data) {
                findNode = p;
                break;
            }
            current = p;
            p = p.next;
        }
        return findNode;
    }

    private void removeOldestTreeNode() {
        System.out.println("--removeOldestTreeNode--");
        TreeNode p = first.next;
        TreeNode pre = first;
        while (p != null && p.next != last) {
            pre = pre.next;
            p = pre.next;
        }
        pre.next = last;
    }

    public T get() {
        TreeNode node = first.next;
        return node == null ? null : node.data;
    }

    private void printAll() {
        TreeNode p = first.next;
        System.out.print("->");
        while (p.next != null) {
            System.out.print(p.data + "->");
            p = p.next;
        }
        System.out.println(" NULL");
    }

    public static void main(String[] args) {
        LRUBasedLinkedList<Integer> lruBasedLinkedList = new LRUBasedLinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            lruBasedLinkedList.put(sc.nextInt());
            lruBasedLinkedList.printAll();
        }
    }

}
