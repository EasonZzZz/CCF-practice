import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		int[][] H = new int[n][2];
		int[][] W = new int[n][2];
		for (int i = 0; i < n; i++) {
			H[i][0] = Reader.nextInt();
			H[i][1] = Reader.nextInt();
		}
		for (int i = 0; i < n; i++) {
			W[i][0] = Reader.nextInt();
			W[i][1] = Reader.nextInt();
		}
		
		int ans = 0;
		for (int[] w : W) {
			for (int[] h : H) {
				ans += compute(w, h);
			}
		}
		
		System.out.println(ans);
	}
	
	public static int compute(int[] x, int[] y) {
		if (x[0] > y[0]) {
			int[] temp = x;
			x = y;
			y = temp;
		}
		
		if (x[1] < y[0]) {
			return 0;
		}
		
		if (x[1] <= y[1]) {
			return x[1] - y[0];
		} else if (x[1] > y[1]) {
			return y[1] - y[0];
		}
		
		return 0;
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
