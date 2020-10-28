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
		
		int n = Reader.nextInt();
		String[] in = new String[n];
		for (int i = 0 ; i < n; i++) {
			in[i] = Reader.next();
		}
		
		Stack<Integer> num = new Stack<Integer>();
		Stack<Character> opt = new Stack<Character>();
		
		for (String i : in) {
			for (char j : i.toCharArray()) {
				if (Character.isDigit(j)) {
					num.add(j - '0');
				} else {
					if (!opt.isEmpty()) {
						if (getPriority(j) > getPriority(opt.peek())) {
							opt.add(j);
						} else {
							int ans = operate(num.pop(), num.pop(), opt.pop());
							num.push(ans);
							opt.add(j);
						}
					} else {
						opt.add(j);
					}
				}
			}
			
			while (!opt.isEmpty()) {
				int ans = operate(num.pop(), num.pop(), opt.pop());
				num.push(ans);
			}
			
			if (num.pop() == 24) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
	
	public static int operate(int a, int b, char c) {
		if (c == '+') {
			return a+b;
		} else if (c == '-') {
			return b-a;
		} else if (c == 'x') {
			return a*b;
		} else if(c == '/'){
			return b/a;
		} else {
			return b;
		}
	}
	
	public static int getPriority (char x) {
		if (x == '+' || x == '-') {
			return 0;
		} else {
			return 1;
		}
	}
}
	
/*
	10
	9+3+4x3
	5+4x5x5
	7-9-9+8
	5x6/5x4
	3+5+7+9
	1x1+9-9
	1x9-5/9
	8/5+6x9
	6x7-3x6
	6x4+4/5
 */

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
