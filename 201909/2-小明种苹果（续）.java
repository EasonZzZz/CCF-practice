import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		List<int[]> oprates = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			int m = Reader.nextInt();
			int[] in = new int[m];
			for (int j = 0; j < m; j++) {
				in[j] = Reader.nextInt();
			}
			oprates.add(in);
		}
		
		int[] tree = new int[n];
		for (int i = 0; i < n; i++) {
			tree[i] = oprates.get(i)[0];
		}
		
		boolean[] fall = new boolean[n];
		Arrays.fill(fall, false);
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < oprates.get(i).length; j++) {
				int o = oprates.get(i)[j];
				if (o > 0) {
					if (tree[i] > o) {
						fall[i] = true;
						tree[i] = o;
					}
				} else if (o < 0) {
					tree[i] += o;
				}
			}
		}
		int sum = 0;
		for (int i : tree) {
			sum += i;
		}
		int cnt = 0;
		int e = 0;
		for (int i = 0; i < n; i++) {
			if (fall[i]) {
				cnt++;
				if (fall[(i+1)%n] && fall[(i+2)%n]) {
					e++;
				}
			}
		}
		
		System.out.println(sum + " " + cnt + " " + e);
	}
}
	
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call thReader method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
