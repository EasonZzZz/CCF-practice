/**
 * 零分代码，样例通过，想不到错误
*/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		sc.nextLine();
		StringBuilder sb = new StringBuilder();
		String line;
		for (int i = 0; i < n-2; i++) {
			line = sc.nextLine();
			if (line.contains("\\")) {
				StringBuilder sbBuilder = new StringBuilder();
				char[] chars = line.toCharArray();
				for (int j = 0; j < chars.length; j++) {
					if (chars[j] == '\\') {
						j++;
					}
					sbBuilder.append(chars[j]);
				}
				line = sbBuilder.toString();
			}
			sb.append(line + "\n");
		}
		sc.nextLine();
		Map<String, Object> map = jsonToMap(sb.toString());
		
		String[] query = new String[m];
		for (int i = 0; i < m; i++) {
			query[i] = sc.nextLine();
		}
		
		for (String string : query) {
			if (!string.contains(".")) {
				Object object = map.getOrDefault(string, null);
				if (object == null) {
					System.out.println("NOTEXIST");
				} else if (object instanceof String) {
					System.out.println("STRING " + object.toString());
				} else {
					System.out.println("OBJECT");
				}
			} else {
				String[] loc = string.split("\\.");
				Map<String, Object> current = map;
				for (int i = 0; i < loc.length - 1; i++) {
					current = (Map<String, Object>) current.getOrDefault(loc[i], null);
				}
				if (current == null) {
					System.out.println("NOTEXIST");
				} else {
					Object object = current.getOrDefault(loc[loc.length-1], null);
					if (object == null) {
						System.out.println("NOTEXIST");
					} else if (object instanceof String) {
						System.out.println("STRING " + object.toString());
					} else {
						System.out.println("OBJECT");
					}
				}
			}
		}
	}
	
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = new HashMap<>();
		
		String[] strs = json.split("\n");
		int i = 0;
		String name;
		while (i < strs.length) {
			if (strs[i].contains("{")) {
				name = strs[i].substring(1, strs[i].length() - 4);
				i++;
				StringBuilder sb = new StringBuilder();
				while (!strs[i].contains("}")) {
					sb.append(strs[i] + "\n");
					i++;
				}
				map.put(name, jsonToMap(sb.toString()));
				i++;
			} else {
				String[] mapping = strs[i].split("\":\\s*\"");
				if (mapping[1].contains(",")) {
					map.put(mapping[0].substring(1), mapping[1].substring(0, mapping[1].length()-2));
				} else {
					map.put(mapping[0].substring(1), mapping[1].substring(0, mapping[1].length()-1));
				}
				i++;
			}
		}
		
		return map;
	}
}
