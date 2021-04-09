import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt(), k = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0, index = 0, get = 0;
        while (index < n) {
            if (get < k) {
                get += a[index++];
            } else {
                ans++;
                get = 0;
            }
        }
        if (get > 0) {
            ans++;
        }
        System.out.println(ans);
    }
}
