import collection.bstAVL.AVLEntry;
import collection.bstAVL.AVLMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestBST {

    private Random random = new Random();

    private final int MAX_1 = 16;

    @Test
    public void testAndPut() {
        AVLMap<Integer, String> map = new AVLMap<>();
        for (int i = 0; i < MAX_1; i++) {
            map.put(random.nextInt(MAX_1), random.nextInt(MAX_1) + "");
        }
        Iterator<AVLEntry<Integer, String>> iterator = map.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().key + " ");
        }
    }

    public final int MAX_2 = 65535;

    @Test
    public void testPutAndItrWithJDK() {
        AVLMap<Integer, String> map = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();

        for (int i = 0; i < MAX_2; i++) {
            int key = random.nextInt(MAX_2);
            String value = random.nextInt(MAX_2) + "";
            map.put(key, value);
            map2.put(key, value);
        }
        Assert.assertTrue(map.getSize() == map2.size());
        System.out.println(map.getSize());
        Iterator<AVLEntry<Integer, String>> it1 = map.iterator();
        Iterator<Map.Entry<Integer, String>> it2 = map2.entrySet().iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }

        Assert.assertTrue(!it1.hasNext() && !it2.hasNext());
    }

    @Test
    public void testQuery() {
        AVLMap<Integer, String> map = new AVLMap<>();
        map.put(4, "a");
        map.put(5, "b");
        map.put(6, "c");
        map.put(7, "d");
        map.put(8, "e");
        map.put(9, "f");
        map.put(1, "g");
        Assert.assertTrue(map.get(4).equals("a"));
        Assert.assertTrue(map.get(1).equals("g"));
        Assert.assertTrue(map.get(10) == null);
        Assert.assertTrue(map.containsKey(6));
        Assert.assertTrue(!map.containsKey(-1));
        Assert.assertTrue(map.containsValue("d"));
        Assert.assertTrue(!map.containsValue("xxxx"));
    }

    @Test
    public void testQueryWithJDK() {
        AVLMap<Integer, String> map = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();

        for (int i = 0; i < MAX_2; i++) {
            int key = random.nextInt(MAX_2);
            String value = random.nextInt(MAX_2) + "";
            map.put(key, value);
            map2.put(key, value);
        }

        for (int i = 0; i < MAX_2; i++) {
            int key = random.nextInt(MAX_2);
            Assert.assertTrue(map.containsKey(key) == map2.containsKey(key));
            if (map.get(key) == null) {
                Assert.assertTrue(map2.get(key) == null);
            } else {
                Assert.assertTrue(map.get(key).equals(map2.get(key)));
            }
        }

        for (int i = 0; i < 255; i++) {
            String value = random.nextInt(MAX_2) + "";
            Assert.assertTrue(map.containsValue(value) == map2.containsValue(value));
        }
    }

    @Test
    public void testRemoveCase1() {
        AVLMap<Integer, String> map = new AVLMap<>();
        //int[] array = {5, 2, 6, 1, 4, 7, 3};
        int[] array = {6,2,7,1,4,8,3,5};
        for (int key :
                array) {
            map.put(key,key+"");
        }
        System.out.println(map.remove(2));
        map.levelOrder();
        Iterator<AVLEntry<Integer, String>> iterator = map.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next().key+" ");
        }
        System.out.println();
    }

    @Test
    public void testRemoveWithJDK(){
        AVLMap<Integer, String> map = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();

        for (int i = 0; i < MAX_2; i++) {
            int key = random.nextInt(MAX_2);
            String value = random.nextInt(MAX_2) + "";
            map.put(key, value);
            map2.put(key, value);
        }
        Assert.assertTrue(map.getSize() == map2.size());
        System.out.println(map.getSize());
        for (int i = 0; i < MAX_2 >>> 1; i++) {
            int key=random.nextInt(MAX_2);
            if (map.containsKey(key)){
                Assert.assertTrue(map.remove(key).equals(map2.remove(key)));
            }else {
                Assert.assertTrue(map.remove(key)==null&&map2.remove(key)==null);
            }
        }
        System.out.println(map.getSize());
        Assert.assertTrue(map.getSize()==map2.size());

        Iterator<AVLEntry<Integer, String>> it1 = map.iterator();
        Iterator<Map.Entry<Integer, String>> it2 = map2.entrySet().iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }

        Assert.assertTrue(!it1.hasNext() && !it2.hasNext());
    }

    @Test
    public void testCal() {
        int n = 9;
        System.out.println(n & 1);
    }
}
