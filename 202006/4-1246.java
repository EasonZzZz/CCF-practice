import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		String s = Reader.next();
		String[] dp = new String[n+4];
		
		dp[0] = "1";		
		dp[1] = "2";
		dp[2] = "4";
		dp[3] = "16";

		for (int i = 4; i <= n; i++) {
			dp[i] = dp[i-3] + dp[i-1].substring(dp[i-4].length()) + dp[i-2];
		}
		
		int ans = 0;
		String str = dp[n];	
		int pos = 0;
		while(true) {
			pos = str.indexOf(s, pos);
			if (pos < 0) {
				break;
			}
			pos++;
			ans++;
			if(ans >= 988244353) {
				ans %= 998244353;
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
