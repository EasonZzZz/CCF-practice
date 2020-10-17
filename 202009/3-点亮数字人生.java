import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static String[] func;
	static List<int[]> input = new ArrayList<>();
	static List<int[]> output = new ArrayList<>();
	// 邻接矩阵: 前 m 个为输入（抽象为节点），m~m+n-1 为元器件编号
	static boolean[][] graph = new boolean[501][501];
	static int[] inDegree = new int[501];
	static boolean[] w = new boolean[10010];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int q = sc.nextInt();
		while (q-- > 0) {
			for(boolean[] i : graph) {
				Arrays.fill(i, false);
			}
			Arrays.fill(inDegree, 0);
			input.clear();
			output.clear();
			
			int m = sc.nextInt();
			int n = sc.nextInt();
			func = new String[n+m];
			
			for (int i = m; i < m+n; i++) {
				func[i] = sc.next();
				int k = sc.nextInt();
				
				// 直接构造图
				for (int j = 0; j < k; j++) {
					String str = sc.next();
					int start = Integer.parseInt(str.substring(1)) - 1;
					if (str.charAt(0) != 'I') {
						// 如果是输出点  则加上偏移量
						start += m;
					}
					graph[start][i] = true;
					inDegree[i]++;
				}
			}
			
			int s = sc.nextInt();
			for (int i = 0; i < s; i++) {
				int[] in = new int[m];
				for (int j = 0; j < m; j++) {
					in[j] = sc.nextInt();
				}
				input.add(in);
			}

			for (int i = 0; i < s; i++) {
				int outNum = sc.nextInt();
				int[] out = new int[outNum];
				for (int j = 0; j < outNum; j++) {
					// 修正偏移
					out[j] = sc.nextInt()+m-1;
				}
				output.add(out);
			}
			
			if (topologicalSort(m, n) == false) {
				System.out.println("LOOP");
			} else {
				for (int i = 0; i < s; i++) {
					Arrays.fill(w, false);
					for(int j = 0; j < input.get(i).length; j++) {
						w[j] = input.get(i)[j]==1?true:false;
					}
					
					calculateValue(m, n);
					
					for (int j = 0; j < output.get(i).length; j++) {
						if (j != 0)
							System.out.print(" ");
						System.out.print(w[output.get(i)[j]]?1:0);
					}
					System.out.println("");
				}
			}
		}
		sc.close();
	}
	
	public static boolean topologicalSort(int m, int n) {
		int num = 0;
		Queue<Integer> queue = new LinkedList<>();
		int[] tempInDegree = Arrays.copyOf(inDegree, m+n);
		boolean[] inQueue = new boolean[m+n];
		Arrays.fill(inQueue, false);
		
		for (int i = 0; i < n+m; i++) {
			if (tempInDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for(int i = 0; i < m + n;i++){
				if (!inQueue[i] && graph[u][i]) {
					tempInDegree[i]--;
					if (tempInDegree[i] == 0) {
						queue.add(i);
						inQueue[i] = true;
					}
				}
			}
			num++;
		}
		return num == n+m ? true : false;
	}
	
	public static void calculateValue(int m, int n) {
		Queue<Integer> queue = new LinkedList<>();
		int[] tempInDegree = Arrays.copyOf(inDegree, m+n);
		boolean[] inQueue = new boolean[m+n];
		Arrays.fill(inQueue, false);
		boolean[] visited = new boolean[10010];
		
		for (int i = 0; i < n+m; i++) {
			if (tempInDegree[i] == 0) {
				queue.add(i);
				inQueue[i] = true;
			}
		}
		while (!queue.isEmpty()) {
			int u = queue.poll();
			
			for(int i = 0; i < m + n;i++){
				if (inQueue[i] == false && graph[u][i] != false) {
					tempInDegree[i]--;
					
					String type = func[i];
					if (visited[i] == false) {
						w[i] = w[u];
						if(type.equals("NOT")) {
							w[i] = (!w[i]);
						}
						visited[i] = true;
					} else {
						if (type.equals("AND") || type.equals("NAND")) {
							w[i] &= w[u];
						} else if (type.equals("OR") || type.equals("NOR")) {
							w[i] |= w[u];
						} else if (type.equals("XOR")) {
							w[i] ^= w[u];
						}
					}
					
					if (tempInDegree[i] == 0) {
						if(type.equals("NAND") || type.equals("NOR")) {
							w[i] = (!w[i]);
						}
						queue.add(i);
						inQueue[i] = true;
					}
				}
			}
		}
		
	}
}
