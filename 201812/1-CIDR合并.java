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
		IP[] ip = new IP[n];
		for (int i = 0; i < n; i++) {
			ip[i] = new IP(Reader.next());
		}
		Arrays.sort(ip, new Comparator<IP>() {
			@Override
			public int compare(IP o1, IP o2) {
				for (int i = 0; i < 4; i++) {
					if (o1.cidr[i] != o2.cidr[i]) {
						return Integer.compare(o1.cidr[i], o2.cidr[i]);
					}
				}
				return Integer.compare(o1.prefix, o2.prefix);
			}
		});
		ip = reduce(ip);
		for(int i = 0; i < ip.length; i++) {
			System.out.println(ip[i]);
		}
	}
	
	public static IP[] reduce(IP[] ip) {
		List<IP> list = new ArrayList<>();
		
		list.add(ip[0]);
		for (int i = 1; i < ip.length; i++) {
			IP a = list.get(list.size()-1);
			IP b = ip[i];
			if (!isSubSet(a, b)) {
				list.add(b);
			}
		}
		if (list.size() > 1) {
			for (int i = 1; i < list.size(); i++) {
				IP merged = merge(list.get(i-1), list.get(i));
				if (merged != null) {
					list.remove(0);
					list.remove(0);
					list.add(0, merged);
					i--;
				}
			}
		}
		return list.toArray(new IP[]{});
	}
	
	public static boolean isSubSet(IP a, IP b) {
		int prefix = a.prefix;
		if (prefix > b.prefix) {
			return false;
		}
		for (int j = 0; j < prefix; j++) {
			if (a.ip.charAt(j) != b.ip.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	public static IP merge(IP a, IP b) {
		if (a.prefix == b.prefix) {
			IP merged = new IP();
			merged.ip = a.ip;
			merged.cidr = a.cidr;
			merged.prefix = a.prefix - 1;
			for (int i = 0; i < merged.prefix; i++) {
				if (merged.ip.charAt(i) != b.ip.charAt(i)) {
					return null;
				}
			}
			return a.ip.charAt(a.prefix - 1) != b.ip.charAt(a.prefix - 1) ?
					merged : null;
		}
		return null;
	}
}

class IP{
	String ip;
	int prefix;
	int[] cidr;
	
	public IP(String str) {
		ip = "";
		String[] split = str.split("/");
		String[] dots = split[0].contains(".") ? split[0].split("\\.") : new String[] {split[0]};
		for (String s : dots) {
			String bits = Integer.toBinaryString(Integer.parseInt(s));
			int t = 8 - bits.length();
			while (t > 0) {
				bits = "0" + bits;
				t--;
			}
			ip += bits;
		}
		for (int i = 0; i < 4-dots.length; i++) {
			ip += "00000000";
		}
		if (split.length == 1) {		
			prefix = dots.length * 8;
		} else {
			prefix = Integer.parseInt(split[1]);
		}
		
		cidr = new int[4];
		for (int i = 0; i < 32; i+=8) {
			cidr[i/8] = Byte.parseByte(ip.substring(i, i+8), 2);
		}
	}
	public IP(){}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(this.cidr[i]);
			if (i == 3) {
				sb.append('/');
			} else {
				sb.append('.');
			}
		}
		sb.append(prefix);
		return sb.toString();
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
