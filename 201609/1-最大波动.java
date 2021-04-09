import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int pre = sc.nextInt(), cur, max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            cur = sc.nextInt();
            max = Math.max(max, Math.abs(cur - pre));
            pre = cur;
        }
        System.out.println(max);
    }
}
