import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int n = Reader.nextInt();
		int a = Reader.nextInt();
		int b = Reader.nextInt();
		Map<Integer, Integer> u = new HashMap<>();
		Map<Integer, Integer> v = new HashMap<>();
		
		for(int i = 0; i < a; i++) {
			u.put(Reader.nextInt(), Reader.nextInt());
		}
		for(int i = 0; i < b; i++) {
			v.put(Reader.nextInt(), Reader.nextInt());
		}
		
		BigInteger prod = new BigInteger("0");
		for(Map.Entry<Integer, Integer> e : u.entrySet()) {
			BigInteger ui = BigInteger.valueOf(e.getValue());
			BigInteger vi = BigInteger.valueOf(v.getOrDefault(e.getKey(), 0));
			prod = prod.add(ui.multiply(vi));
		}
		
		System.out.println(prod);
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
