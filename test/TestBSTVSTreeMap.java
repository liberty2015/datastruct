import collection.bstAVL.AVLMap;
import org.junit.Test;

import java.util.Random;
import java.util.TreeMap;

public class TestBSTVSTreeMap {

    public static final int MAX=20480;
    private Random random=new Random();

    @Test
    public void testBSTRandom(){
        AVLMap<Integer,String> map=new AVLMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(random.nextInt(MAX),random.nextInt(MAX)+"");
        }
        map.checkBalance();
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testTreeMapRandom(){
        TreeMap<Integer,String> map=new TreeMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(random.nextInt(MAX),random.nextInt(MAX)+"");
        }
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    /**
     * 在升序和降序序列的存取这两种情况下二叉查找树的效率非常差，因为
     * 二叉查找树在极端情况下会退化为链表，
     * O(logN) 变成了 O(N)
     */
    @Test
    public void testBSTIncrement(){
        AVLMap<Integer,String> map=new AVLMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(i,random.nextInt(MAX)+"");
        }
        System.out.println("isBalance: "+map.checkBalance());
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testTreeMapIncrement(){
        TreeMap<Integer,String> map=new TreeMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(i,random.nextInt(MAX)+"");
        }
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testAVLMapWithParent(){
        AVLMap<Integer,String> map=new AVLMap<>();
        for (int i = 0; i < MAX; i++) {
            map.putWithParent(i,random.nextInt(MAX)+"");
        }
        System.out.println("isBalance: "+map.checkBalance());
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }
}
