import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            int left = 0, right = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    left++;
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (a[j] > a[i]) {
                    right++;
                }
            }
            if (left == right) {
                System.out.println(a[i]);
                return;
            }
        }
        System.out.println(-1);
    }
}
