import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		int[] ans = new int[]{0,0,0,0};
		int i = 0;
		while (n > 0) {
			i++;
			if (seven(i)) {
				ans[(i-1)%4]++;
			} else {
				n--;
			}
		}
		
		for (int j : ans) {
			System.out.println(j);
		}
	}
	
	public static boolean seven(int i) {
		if (i % 7 == 0)
			return true;
		int j = 0;
		while (i != 0) {
			j = i % 10;
			if (j == 7)
				return true;
			i /= 10;
		}
		return false;
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
