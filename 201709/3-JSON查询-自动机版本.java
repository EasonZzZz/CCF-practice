import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Map<String, String> map = new HashMap<>();
	static int state = 0;
	static String key;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < n; i++) {
			jsonToMap(sc.nextLine());
		}
		
		for (int i = 0; i < m; i++) {
			String line = sc.nextLine();
			System.out.println(map.containsKey(line) ? map.get(line) : "NOTEXIST");
		}
	}
	
	public static void jsonToMap(String line) {
		char[] chars = line.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '{') {
				if (state == 0) {
					key = "";
				} else {
					map.put(key, "OBJECT");
				}
				state = 1;
			} else if (chars[i] == '}') {
				int j;
				for (j = key.length() - 1; j >= 0; j--) {
					if (key.charAt(j) == '.') {
						break;
					}
				}
				if (j >= 0) {
					key = key.substring(0, j);
				} else {
					key = "";
				}
				state = 1;
			} else if (chars[i] == '"') {
	            StringBuilder tmp = new StringBuilder();
	            for (i++; i < chars.length; i++) {
	            	if (chars[i] == '\\') {
						tmp.append(chars[++i]);
					} else if (chars[i] == '"') {
						break;
					} else {
						tmp.append(chars[i]);
					}
	            }
	            if (state == 1) {
					if (key == "") {
						key = tmp.toString();
					} else {
						key += "." + tmp.toString();
					}
					state = 2;
				} else if (state == 2) {
					map.put(key, "STRING " + tmp.toString());
					int j;
					for (j = key.length() - 1; j >= 0; j--) {
						if (key.charAt(j) == '.') {
							break;
						}
					} 
					if (j >= 0) {
						key = key.substring(0, j);
					} else {
						key = "";
					}
					state = 1;
				}
			}
		}
	}
}

/**
10 5
{
"firstName": "John",
"lastName": "Smith",
"address": {
"streetAddress": "2ndStreet",
"city": "NewYork",
"state": "NY"
},
"esc\\aped": "\"hello\""
}
firstName
address
address.city
address.postal
esc\aped
*/
