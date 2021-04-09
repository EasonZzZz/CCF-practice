import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int T = sc.nextInt(), S = 0;
        if (T <= 3500)
            S = T;
        else if (T > 3500 && T <= 4955)
            S = (T - 3500) * 100 / 97 + 3500;
        else if (T > 4955 && T <= 7655)
            S = (T - 4955) * 10 / 9 + 5000;
        else if (T > 7655 && T <= 11255)
            S = (T - 7655) * 10 / 8 + 8000;
        else if (T > 11255 && T <= 30755)
            S = (T - 11255) * 100 / 75 + 12500;
        else if (T > 30755 && T <= 44755)
            S = (T - 30755) * 10 / 7 + 38500;
        else if (T > 44755 && T <= 61005)
            S = (T - 44755) * 100 / 65 + 58500;
        else if (T > 61005)
            S = (T - 61005) * 100 / 55 + 83500;
        System.out.println(S);
    }
}
