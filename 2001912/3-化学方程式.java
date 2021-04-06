import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {

    static String formula;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int n = sc.nextInt();
        while (n-- > 0) {
            formula = sc.next();
            map.clear();
            int k = formula.indexOf('=');
            expression(0, k - 1, 1);
            expression(k + 1, formula.length() - 1, -1);
            System.out.println(checkMap() ? "Y" : "N");
        }
    }

    public static void expression(int start, int end, int e) {
        for (int i = start, j = start; i <= end; i = j + 1) {
            j = formula.indexOf('+', i);
            if (j == -1 || j > end) {
                j = end + 1;
            }
            putIntoMap(i, j - 1, e);
        }
    }

    public static void putIntoMap(int start, int end, int e) {
        // 只有原子，如 O、Ba
        if (start == end || (end - start == 1 && Character.isLowerCase(formula.charAt(end)))) {
            map.put(formula.substring(start, end + 1), map.getOrDefault(formula.substring(start, end + 1), 0) + e);
            return;
        }
        e *= getDigit(start, end);
        for (int i = start; i <= end; i++) {
            if (Character.isUpperCase(formula.charAt(i))) {
                int j = i + 1;
                if (j <= end && Character.isLowerCase(formula.charAt(j))) {
                    j++;
                }
                expression(i, j - 1, e * getDigit(j, end));
                i = j - 1;
            } else if (formula.charAt(i) == '(') {
                int j = i + 1, left = 1;
                // 找到匹配的括号，由于会嵌套，下一个 ) 可能不匹配的
                while (left != 0) {
                    if (formula.charAt(j) == '('){
                        left++;
                    } else if (formula.charAt(j) == ')'){
                        left--;
                    }
                    j++;
                }
                expression(i + 1, j - 1, e * getDigit(j, end));
                i = j - 1;
            }
        }
    }

    public static int getDigit(int start, int end) {
        int digit = 0;
        while (start <= end && Character.isDigit(formula.charAt(start))) {
            digit = digit * 10 + formula.charAt(start++) - '0';
        }
        return digit == 0 ? 1 : digit;
    }

    public static boolean checkMap() {
        for (Integer value : map.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
