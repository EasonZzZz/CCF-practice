import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int ans = 0;
        while (n > 0) {
            if (n >= 50) {
                n -= 50;
                ans += 7;
            } else if (n >= 30) {
                n -= 30;
                ans += 4;
            } else if (n >= 10) {
                n -= 10;
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
