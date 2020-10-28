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
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Reader.nextInt();
		}
		
		int maxI = n-1, minI = 0;
		float mid = 0;
		
		if (nums[0] > nums[n-1]) {
			maxI = 0;
			minI = n-1;
		}
		
		if (n%2 == 0) {
			mid = (float) ((nums[n/2] + nums[n/2 - 1]) / 2.0);
		} else {
			mid = nums[n/2];
		}
		
		if ((int)mid - mid == 0) {
			System.out.println(nums[maxI] + " " + (int)mid + " " + nums[minI]);
		} else {
			System.out.println(nums[maxI] + " " + String.format("%.1f", mid) + " " + nums[minI]);
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
