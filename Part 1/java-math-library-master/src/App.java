import de.tilman_neumann.util.Multiset;
import de.tilman_neumann.util.Multiset_HashMapImpl;
import de.tilman_neumann.util.Pair;
import de.tilman_neumann.util.StringUtil;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        Pair<String, Integer> p = new Pair<String, Integer>("hassan", 10);
        Multiset<String> m = new Multiset_HashMapImpl<String>();
        int x = m.add("hassan");
        System.out.println(x);
        int y =m.add("hassan", -3);
        System.out.println(y);
        x = m.add("hassan", 33);
        System.out.println(x);
        System.out.println(m.get("hassann"));
        System.out.println(p.getFirst());

//        String res =  StringUtil.repeat("", 2);
//
//        Multiset_HashMapImpl<Integer> mp = new Multiset_HashMapImpl<Integer>();
//        Multiset_HashMapImpl<Integer> mp2 = new Multiset_HashMapImpl<Integer>();
//        mp.add(1);
//        mp.add(2);
//        mp.add(3);
//        mp.add(4);
//
//        mp2 = null;
//
////        mp2.add(null);
////        mp2.add(2);
////        mp2.add(8);
////        mp2.add(9);
//
//        mp.addAll(mp2);
//        for (Map.Entry<Integer,Integer> entry : mp.entrySet()){
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

    }
}