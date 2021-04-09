import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        Site[] train = new Site[20];
        for (int i = 0;  i < 20; i++) {
            train[i] = new Site(i * 5 + 1);
        }
        int n = sc.nextInt();
        while (n-- > 0) {
            int buy = sc.nextInt();
            boolean bought = false;
            for (Site site : train) {
                if (site.rest >= buy) {
                    for (int i = 0; i < buy - 1; i++) {
                        System.out.print(site.base + 5 - site.rest + i + " ");
                    }
                    System.out.println(site.base + 5 - site.rest + buy - 1);
                    site.rest -= buy;
                    bought = true;
                    break;
                }
            }

            // 没有买到连续的
            if (!bought) {
                for (Site site : train) {
                    while (buy > 0 && site.rest > 0) {
                        if (buy == 1) {
                            System.out.println(site.base + 5 - site.rest);
                        } else {
                            System.out.print(site.base + 5 - site.rest + " ");
                        }
                        buy--;
                        site.rest--;
                    }
                }
            }
        }
    }
}

class Site {
    int base;
    int rest;

    public Site(int base) {
        this.base = base;
        rest = 5;
    }
}
