package collection.bstAVL;

import java.util.*;

public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>> {
    private int size;
    private AVLEntry<K, V> root;
    private Comparator<K> comp;
    private LinkedList<AVLEntry<K, V>> stack = new LinkedList<>();

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

    /**
     * 采用stack进行回溯调整的添加函数
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        if (root == null) {
            root = new AVLEntry<>(key, value);
            stack.push(root);
            size++;
        } else {
            AVLEntry<K, V> p = root;
            while (p != null) {
                stack.push(p);
                int comp = compare(key, p.key);
                if (comp == 0) {
                    p.value = value;
                    break;
                } else if (comp < 0) {
                    if (p.left == null) {
                        p.left = new AVLEntry<>(key, value);
                        size++;
                        stack.push(p.left);
                        break;
                    } else {
                        p = p.left;
                    }
                } else if (comp > 0) {
                    if (p.right == null) {
                        p.right = new AVLEntry<>(key, value);
                        size++;
                        stack.push(p.right);
                        break;
                    } else {
                        p = p.right;
                    }
                }
            }
        }
        fixAfterInsertion(key);
        return value;
    }

    /**
     * 采用parent域进行回溯调整的添加函数
     *
     * @param key
     * @param value
     * @return
     */
    public V putWithParent(K key, V value) {
        if (root == null) {
            root = new AVLEntry<>(key, value);
            size++;
        } else {
            AVLEntry<K, V> p = root;
            AVLEntry<K, V> e = null;
            while (p != null) {
                int comp = compare(key, p.key);
                if (comp == 0) {
                    p.value = value;
                    e = p;
                    break;
                } else if (comp < 0) {
                    if (p.left == null) {
                        p.left = new AVLEntry<>(key, value, p);
                        e = p.left;
                        size++;
                        break;
                    } else {
                        p = p.left;
                    }
                } else if (comp > 0) {
                    if (p.right == null) {
                        p.right = new AVLEntry<>(key, value, p);
                        e = p.right;
                        size++;
                        break;
                    } else {
                        p = p.right;
                    }
                }
            }
            fixAfterInsertionWithParent(e);
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

    public int getHeight(AVLEntry<K, V> p) {
        return p == null ? 0 : p.height;
    }

    /**
     * 右旋
     *
     * @param p
     * @return
     */
    private AVLEntry<K, V> rotateRight(AVLEntry<K, V> p) {
        AVLEntry<K, V> left = p.left;
        p.left = left.right;
        left.right = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        left.height = Math.max(getHeight(left.left), p.height) + 1;
        return left;
    }

    private AVLEntry<K, V> rotateRightWithParent(AVLEntry<K, V> p) {
        AVLEntry<K, V> left = p.left;
        p.left = left.right;
        if (left.right != null) {
            left.right.parent = p;
        }
        left.right = p;
        left.parent = p.parent;
        p.parent = left;
        p.height = Math.max(getHeight(p.left), getHeight(p.right));
        left.height = Math.max(getHeight(left.left), p.height) + 1;

        return left;
    }

    /**
     * 左旋
     *
     * @param p
     * @return
     */
    private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> p) {
        AVLEntry<K, V> right = p.right;
        p.right = right.left;
        right.left = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        right.height = Math.max(p.height, getHeight(right.right)) + 1;
        return right;
    }

    private AVLEntry<K, V> rotateLeftWithParent(AVLEntry<K, V> p) {
        AVLEntry<K, V> right = p.right;
        p.right = right.left;
        if (right.left != null) {
            right.left.parent = p;
        }
        right.left = p;
        right.parent = p.parent;
        p.parent = right;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        right.height = Math.max(p.height, getHeight(right.right)) + 1;

        return right;
    }

    /**
     * 先左旋再右旋
     *
     * @param p
     * @return
     */
    private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> p) {
        p.left = rotateLeft(p.left);
        p = rotateRight(p);
        return p;
    }

    private AVLEntry<K, V> firstLeftThenRightWithParent(AVLEntry<K, V> p) {
        p.left = rotateLeftWithParent(p.left);
        p = rotateRightWithParent(p);
        return p;
    }

    /**
     * 先右旋再左旋
     *
     * @param p
     * @return
     */
    private AVLEntry<K, V> firstRightThenLeft(AVLEntry<K, V> p) {
        p.right = rotateRight(p.right);
        p = rotateLeft(p);
        return p;
    }

    private AVLEntry<K, V> firstRightThenLeftWithParent(AVLEntry<K, V> p) {
        p.right = rotateRightWithParent(p.right);
        p = rotateLeftWithParent(p);
        return p;
    }

    /**
     * 平衡二叉树插入调整函数
     *
     * @param key
     */
    private void fixAfterInsertion(K key) {
        AVLEntry<K, V> p = root;
        while (!stack.isEmpty()) {
            p = stack.pop();
            int newHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
            //这里由于叶子节点原来的高度和新高度还是1，不能用来判断叶子节点，所以需要判断是否为叶子节点
            //这里的情况应该是在遍历到弹出的节点之前已经将子树平衡化了，也就是说它的子树已经是平衡二叉树了，当遍历到弹出的节点时已经不需要再调整了，现象就是高度不变
            if (p.height > 1 && newHeight == p.height) {
                stack.clear();
                return;
            }
            p.height = newHeight;
            int d = getHeight(p.left) - getHeight(p.right);
            if (Math.abs(d) <= 1) {
                continue;
            } else {
                if (d == 2) {
                    if (compare(key, p.left.key) < 0) {
                        p = rotateRight(p);
                    } else {
                        p = firstLeftThenRight(p);
                    }
                } else {
                    if (compare(key, p.right.key) > 0) {
                        p = rotateLeft(p);
                    } else {
                        p = firstRightThenLeft(p);
                    }
                }
                /**
                 * 由于上面进行了旋转操作，导致父节点实际子节点已经发生了改变，所以需要修改父节点的子节点。
                 */
                if (!stack.isEmpty()) {
                    AVLEntry<K, V> parent = stack.peek();
                    if (parent != null) {
                        if (compare(key, stack.peek().key) < 0) {
                            parent.left = p;
                        } else {
                            parent.right = p;
                        }
                    }
                }
            }
        }
        root = p;
    }

    private void fixAfterInsertionWithParent(AVLEntry<K, V> entry) {
        AVLEntry<K, V> p = root;
        AVLEntry<K, V> e = entry;
        while (e != null) {
            int newHeight = Math.max(getHeight(e.left), getHeight(e.right)) + 1;
            if (e.height>1&&newHeight==e.height){
                return;
            }
            e.height = newHeight;
            int d = getHeight(e.left) - getHeight(e.right);
            if (Math.abs(d) <= 1) {
                e = e.parent;
                continue;
            } else {
                if (d == 2) {
                    if (compare(entry.key, e.left.key) < 0) {
                        p = rotateRightWithParent(e);
                    } else {
                        p = firstLeftThenRightWithParent(e);
                    }
                } else {
                    if (compare(entry.key, e.right.key) > 0) {
                        p = rotateLeftWithParent(e);
                    } else {
                        p = firstRightThenLeftWithParent(e);
                    }
                }
            }
            e = e.parent;
        }
        root = p;
    }

    private AVLEntry<K, V> parentOf(AVLEntry<K, V> p) {
        return p == null ? null : p.parent;
    }

    public boolean checkBalance() {
        return postOrderCheckBalance(root);
    }

    /**
     * 通过递归版后序遍历来判断传入的二叉树是否平衡
     *
     * @param p
     * @return
     */
    private boolean postOrderCheckBalance(AVLEntry<K, V> p) {
        if (p != null) {
            if(postOrderCheckBalance(p.left)){
                if (postOrderCheckBalance(p.right)){
                    return Math.abs(getHeight(p.left) - getHeight(p.right)) <= 1;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * --------------------------------------------------
     * 二叉查找树
     * 分3种情况：
     * 1. p是叶子节点，直接删除
     * 2. p只有左子树或右子树，直接用左子树或右子树替换
     * 3. p既有左子树又有右子树，找到右子树的最小节点rightMin，用rightMin的值替换p的值，再根据以上两种情况删除rightMin
     *
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
