import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        if (n < 3) {
            System.out.println(0);
            return;
        }
        int left = sc.nextInt(), mid = sc.nextInt(), right;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            right = sc.nextInt();
            if ((mid > left && mid > right) || (mid < left && mid < right)) {
                count++;
            }
            left = mid;
            mid = right;
        }
        System.out.println(count);
    }
}
