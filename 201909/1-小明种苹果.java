import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		int m = Reader.nextInt();
		int[][] in = new int[n][m+1];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m+1; j++) {
				in[i][j] = Reader.nextInt();
			}
		}
		
		int[] tree = new int[n];
		for (int i = 0; i < n; i++) {
			tree[i] = in[i][0];
		}
		int[] cnt = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < m+1; j++) {
				tree[i] += in[i][j];
				cnt[i] -= in[i][j];
			}
		}
		int index = 0;
		for (int i = 1; i < n; i++) {
			if (cnt[i] > cnt[index]) {
				index = i;
			}
		}
		index++;
		int sum = 0;
		for (int i : tree) {
			sum += i;
		}
		System.out.println(sum + " " + index + " " + cnt[index-1]);
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
