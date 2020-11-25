import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int r = Reader.nextInt();
		int y = Reader.nextInt();
		int g = Reader.nextInt();
		int n = Reader.nextInt();
		int[][] input = new int[n][2];
		for (int i = 0; i < n; i++) {
			input[i][0] = Reader.nextInt();
			input[i][1] = Reader.nextInt();
		}
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			switch(input[i][0]) {
			case 0:
				ans += input[i][1];
				break;
			case 1:
				ans += input[i][1];
				break;
			case 2:
				ans += input[i][1] + r;
				break;
			case 3:
				break;
			}
		}
		System.out.println(ans);
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
