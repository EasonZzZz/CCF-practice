import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt(), m = sc.nextInt();
        List<Integer> list = new LinkedList<>();
        list.add(0);
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt(), q = sc.nextInt();
            int index = list.indexOf(p);
            list.remove(index);
            index += q;
            list.add(index, p);
        }
        for (int i = 1; i <= n; i++) {
            if (i != n) {
                System.out.print(list.get(i) + " ");
            } else {
                System.out.println(list.get(i));
            }
        }
    }
}
