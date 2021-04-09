import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		
		String[] path = new String[n];
		String[] name = new String[n];
		for (int i = 0; i < n; i++) {
			path[i] = sc.next();
			name[i] = sc.next();
		}
		
		for (int i = 0; i < m; i++) {
			String in = sc.next();
			boolean matched = false;
			for (int j = 0; !matched && j < n; j++) {
				if (match(in, path[j])) {
					matched = true;
					System.out.print(name[j]);
					printInfo(in, path[j]);
				}
			}
			
			if (!matched) {
				System.out.println("404");
			}
		}
	}
	
	public static boolean match(String inS, String pathS) {
		int inLen = inS.length();
		int pathLen = pathS.length();
		int i = 0, p = 0;
		
		char[] in = inS.toCharArray();
		char[] path = pathS.toCharArray();
		
		while (i < inLen && p < pathLen) {
			if (in[i] == path[p]) {
				i++;
				p++;
			} else {
				if (path[p++] != '<') {
					return false;
				}
				if (path[p] == 'i') {
					boolean flag = false;
					while (i < inLen && Character.isDigit(in[i])) {
						if (in[i] != '0') {
							flag = true;
						}
						i++;
					}
					if (!flag) {
						return false;
					}
					p += 4;
				} else if (path[p] == 's') {
					boolean flag = false;
					while (i < inLen && in[i] != '/') {
						flag = true;
						i++;
					}
					if (!flag) {
						return false;
					}
					p += 4;
				} else if (path[p] == 'p') {
					return true;
				}
			}
		}
		
		return p == pathLen && i == inLen;
	}
	
	public static void printInfo(String inS, String pathS) {
		int inLen = inS.length();
		int pathLen = pathS.length();
		int i = 0, p = 0;
		
		char[] in = inS.toCharArray();
		char[] path = pathS.toCharArray();
		
		while (i < inLen && p < pathLen) {
			if (in[i] == path[p]) {
				i++;
				p++;
			} else {
				if (path[p++] == '<') {
					System.out.print(" ");
				}
				if (path[p] == 'i') {
					StringBuilder num = new StringBuilder();
					while (i < inLen && Character.isDigit(in[i])) {
						num.append(in[i++]);
					}
					System.out.print(Integer.parseInt(num.toString()));
					p += 4;
				} else if (path[p] == 's') {
					while (i < inLen && in[i] != '/') {
						System.out.print(in[i++]);
					}
					p += 4;
				} else if (path[p] == 'p') {
					while (i < inLen) {
						System.out.print(in[i++]);
					}
				}
			}
		}
		
		System.out.println();
	}
}