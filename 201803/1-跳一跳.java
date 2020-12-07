import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int last = 0;
		int ans = 0;
		int input = 0;
		while ((input = sc.nextInt()) != 0) {
			if (input == 1) {
				ans += 1;
				last = 0;
			} else if (input == 2) {
				last += 2;
				ans += last;
			}
		}
		System.out.println(ans);
	}

}
