import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) throws IOException {		
		
        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int m = sc.nextInt(), ans = -1;
        ArrayList<int[]> list = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
        	int[] temp = new int[] {sc.nextInt(), sc.nextInt()};
        	list.add(temp);
        }
        Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
        
        // 求出未挂科的前缀和
        int[] sum = new int[m+1];
        sum[0] = 0;
        for (int i = 1; i <= m; i++) {
        	sum[i] = sum[i-1] + list.get(i-1)[1];
        }

        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= m; i++) {
        	int theta = list.get(i-1)[0];
        	if (set.add(theta)) {
        		// 预测为 1 = sum[m] - sum[i-1]
        		// 预测为 0 = (i-1) - sum[i-1]
        		// 两者相加得到predict
				int predict = sum[m] + i - 2 * sum[i-1] -1;
				if (predict >= max) {
					max = predict;
					ans = theta;
				}
			}
        }
        
        System.out.println(ans);
	}
}
