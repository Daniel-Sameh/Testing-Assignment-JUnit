import de.tilman_neumann.util.Multiset;
import de.tilman_neumann.util.Multiset_HashMapImpl;
import de.tilman_neumann.util.Pair;
import de.tilman_neumann.util.StringUtil;

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

        String res =  StringUtil.repeat("", 2);

    }
}