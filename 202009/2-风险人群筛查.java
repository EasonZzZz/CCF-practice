import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		int t = sc.nextInt();
		int xl = sc.nextInt();
		int yd = sc.nextInt();
		int xr = sc.nextInt();
		int yu = sc.nextInt();
		
		int[][][] locs = new int[n][t][2];
		for(int[][] i : locs) {
			for(int[] j : i) {
				j[0] = sc.nextInt();
				j[1] = sc.nextInt();
			}
		}
		
		int pass = 0, stay = 0;
		for(int i = 0; i < n; i++) {
			int cnt = 0;
			boolean flagP = false;
			
			for(int j = 0; j < t; j++) {
				int x = locs[i][j][0], y = locs[i][j][1];
				if(x >= xl && x <= xr && y >= yd && y<= yu) {
					flagP = true;
					cnt++;
					if (cnt >= k) {
						stay++;
						break;
					}
				} else {
					cnt = 0;
				}
			}
			
			if(flagP) {
				pass++;
			}
		}
		
		System.out.println(pass + "\n" + stay);
		
		sc.close();
	}
}
