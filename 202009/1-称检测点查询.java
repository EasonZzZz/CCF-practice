import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int[][] locs = new int[n][2];
		for (int[] i : locs) {
			i[0] = sc.nextInt();
			i[1] = sc.nextInt();
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0 ; i < n; i++) {
			map.put(i+1, (locs[i][0]-x)*(locs[i][0]-x)+(locs[i][1]-y)*(locs[i][1]-y));
		}
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		for(int i = 0; i < 3; i++) {
			System.out.println(list.get(i).getKey());
		}
		
		sc.close();
	}
}
