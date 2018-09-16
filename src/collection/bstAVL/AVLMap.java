package collection.bstAVL;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>> {
    private int size;
    private AVLEntry<K, V> root;
    private Comparator<K> comp;

    private int compare(K a, K b) {
        if (comp != null) {
            return comp.compare(a, b);
        } else {
            Comparable<K> c = (Comparable<K>) a;
            return c.compareTo(b);
        }
    }

    public AVLMap(Comparator<K> comp) {
        this.comp = comp;
    }

    public AVLMap() {
        super();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        if (root == null) {
            root = new AVLEntry<K, V>(key, value);
            size++;
        } else {
            AVLEntry<K, V> p = root;
            while (p != null) {
                int comp = compare(key, p.key);
                if (comp == 0) {
                    p.value = value;
                    break;
                } else if (comp < 0) {
                    if (p.left == null) {
                        p.left = new AVLEntry<K, V>(key, value);
                        size++;
                        break;
                    } else {
                        p = p.left;
                    }
                } else if (comp > 0) {
                    if (p.right == null) {
                        p.right = new AVLEntry<K, V>(key, value);
                        size++;
                        break;
                    } else {
                        p = p.right;
                    }
                }
            }
        }
        return value;
    }

    @Override
    public Iterator<AVLEntry<K, V>> iterator() {
        return new AVLIterator<>(root);
    }

    private AVLEntry<K, V> getEntry(K key) {
        AVLEntry<K, V> p = root;
        while (p != null) {
            int comp = compare(key, p.key);
            if (comp == 0) {
                return p;
            } else if (comp < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        AVLEntry<K, V> entry = getEntry(key);
        return entry != null;
    }

    public V get(K key) {
        AVLEntry<K, V> entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    public boolean containsValue(V value) {
        Iterator<AVLEntry<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 分3种情况：
     * 1. p是叶子节点，直接删除
     * 2. p只有左子树或右子树，直接用左子树或右子树替换
     * 3. p既有左子树又有右子树，找到右子树的最小节点rightMin，用rightMin的值替换p的值，再根据以上两种情况删除rightMin
     * @param p
     * @param key
     * @return
     */
    private AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p, K key) {
        if (p == null) {
            return null;
        }
        int comp = compare(key, p.key);
        if (comp == 0) {
            //
            if (p.left == null && p.right == null) {
                p = null;
            } else if (p.left != null && p.right == null) {
                p = p.left;
            } else if (p.left == null && p.right != null) {
                p = p.right;
            } else {
                //为了让BST更平衡一些
                if ((size & 1) == 0) {
                    AVLEntry<K, V> rightMin = getFirstEntry(p.right);
                    p.key = rightMin.key;
                    p.value = rightMin.value;
                    AVLEntry<K, V> newRight = deleteEntry(p.right, p.key);
                    p.right = newRight;
                } else {
                    AVLEntry<K, V> leftMax = getLastEntry(p.left);
                    p.key = leftMax.key;
                    p.value = leftMax.value;
                    AVLEntry<K, V> newLeft = deleteEntry(p.left, p.key);
                    p.left = newLeft;
                }
            }
        } else if (comp < 0) {
            AVLEntry<K, V> newLeft = deleteEntry(p.left, key);
            p.left = newLeft;
        } else {
            AVLEntry<K, V> newRight = deleteEntry(p.right, key);
            p.right = newRight;
        }
        return p;
    }

    public V remove(K key) {
        AVLEntry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        V oldValue = entry.getValue();
        root = deleteEntry(root, key);
        size--;
        return oldValue;
    }

    public void levelOrder() {
        Queue<AVLEntry<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while (!queue.isEmpty()) {
            AVLEntry<K, V> entry = queue.poll();
            System.out.println(entry + " ");
            if (entry.left != null) {
                queue.offer(entry.left);
                pCount++;
            }
            if (entry.right != null) {
                queue.offer(entry.right);
                pCount++;
            }
        }
    }

}
