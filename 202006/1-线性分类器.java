import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int[][] input;
	public static char[] type;
	public static int aCnt;
	public static int bCnt;

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		int m = Reader.nextInt();	
		
		aCnt = 0;
		bCnt = 0;
		
		input = new int[n][2];
		type = new char[n];
		for (int i = 0; i < n; i++) {
			input[i][0] = Reader.nextInt();
			input[i][1] = Reader.nextInt();
			type[i] = Reader.next().charAt(0);
			if (type[i] == 'A') {
				aCnt++;
			} else {
				bCnt++;
			}
		}
		
		int[][] theta = new int[m][3];
		for (int i = 0; i < m; i++) {
			theta[i][0] = Reader.nextInt();
			theta[i][1] = Reader.nextInt();
			theta[i][2] = Reader.nextInt();
		}
		
		for (int i = 0; i < m; i++) {
			System.out.println(classify(theta[i]));
		}
	}
	
	public static String classify(int[] theta) {
		int res =  theta[0] + theta[1]*input[0][0] + theta[2]*input[0][1];
		char up;
		if ((type[0] == 'A' && res > 0) || (type[0] == 'B' && res < 0)) {
			up = 'A';
		} else {
			up = 'B';
		}
		
		for (int i = 1; i < input.length; i++) {
			res =  theta[0] + theta[1]*input[i][0] + theta[2]*input[i][1];
			if (res > 0) {
				if (up != type[i]) {
					return "No";
				}
			} else {
				if(up == type[i]) {
					return "No";
				}
			}
		}
		
		return "Yes";
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
