import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int L = sc.nextInt();
		int t = sc.nextInt();
		
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		boolean[] right = new boolean[n];
		Arrays.fill(right, true);
		
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < n; j++) {
				if (right[j]) {
					if (a[j] == L) {
						right[j] = false;
						a[j]--;
					} else {
						a[j]++;
					}
				} else {
					if (a[j] == 0) {
						right[j] = true;
						a[j]++;
					} else {
						a[j]--;
					}
				}
			}
			boolean[] thisTime = new boolean[n];
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (j != k && !thisTime[j] && !thisTime[k] && a[j] == a[k]) {
						right[j] = right[j] ? false : true;
						right[k] = right[k] ? false : true;
						thisTime[j] = thisTime[k] = true;
						
					}
				}
			}
		}
		
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

}
