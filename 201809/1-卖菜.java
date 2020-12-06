import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		int[] pre = new int[n];
		for (int i = 0; i < n; i++) {
			pre[i] = Reader.nextInt();
		}
		
		int[] today = new int[n];
		
		today[0] = (pre[0] + pre[1]) / 2;
		today[n-1] = (pre[n-1] + pre[n-2]) / 2;
		
		for (int i = 1; i < n-1; i++) {
			today[i] = (pre[i-1] + pre[i] + pre[i+1]) / 3;
		}
		
		for (int i : today) {
			System.out.print(i + " ");
		}
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
