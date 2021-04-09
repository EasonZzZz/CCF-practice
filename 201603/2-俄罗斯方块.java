import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author EasonZz
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int[][] matrix = new int[15 + 1][10];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        // 底边放 1
        for (int i = 0; i < 10; i++) {
            matrix[15][i] = 1;
        }
        // 计算出小方块的横纵坐标
        int index = 0;
        int[][] coords = new int[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (sc.nextInt() == 1) {
                    coords[index][0] = i;
                    coords[index][1] = j;
                    index++;
                }
            }
        }

        // 开始模拟
        int row = 0, col = sc.nextInt() - 1;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                if (matrix[row + coords[i][0] + 1][col + coords[i][1]] == 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
            row++;
        }

        for (int i = 0; i < 4; i++) {
            matrix[row + coords[i][0]][col + coords[i][1]] = 1;
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                if (j != 9) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.println(matrix[i][j]);
                }
            }
        }
    }
}
