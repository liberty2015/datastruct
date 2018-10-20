import org.junit.Test;

import java.util.TreeMap;

public class testCase {

    @Test
    public void testTreeMap(){
        TreeMap<testObj,Integer> treeMap=new TreeMap<>();
        treeMap.put(new testObj(1),2);
        treeMap.put(new testObj(2),3);
        treeMap.put(new testObj(3),4);
        treeMap.put(new testObj(4),5);
        treeMap.put(new testObj(6),7);

    }

    class testObj{
        int key;

        public testObj(int key) {
            this.key = key;
        }
    }
}
