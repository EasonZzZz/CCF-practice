import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	// 将输入内容分行
	static List<Markdown> list = new ArrayList<>();
	static int w;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		w = Integer.parseInt(br.readLine());
		
		//记录上一行的类型
		int flag = 0;
		
		String str = null;
		while((str = br.readLine()) != null) {
			if(isSpace(str)) {
				if (flag != 0) {
					list.add(new Markdown(0, ""));
					flag = 0;
				}
				continue;
			}
			if (str.length() >= 2 && str.substring(0,2).equals("* ")) {
				if (flag == 3) {
					list.add(new Markdown(0, ""));
				}
				list.add(new Markdown(1, str.substring(2)));
				flag = 1;
			} else if(str.length() >= 2 && str.substring(0,2).equals("  ") && (flag == 1 || flag ==2)) {
				if (list.get(list.size()-1).str.equals("")) {
					// 项目列表第一行为空行
					list.get(list.size()-1).str = str.trim();
				} else {
					list.get(list.size()-1).str = list.get(list.size()-1).str + " " + str.trim();
				}
				flag = 2;
			} else {
				if(flag == 1 || flag == 2) {
					list.add(new Markdown(0, ""));
					list.add(new Markdown(3, str.trim()));
				} else if(flag == 3) {
					list.get(list.size()-1).str = list.get(list.size()-1).str + " " + str.trim();
				} else {
					list.add(new Markdown(3, str.trim()));
				}
				flag = 3;
			}
		}
		
		System.out.println(count());
	}
	
	static boolean isSpace(String str) {
		if(str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	static long count() {
		long res = 0;
		if (list.get(list.size()-1).type == 0) {
			//若最后一行是空行，就去掉，即令res从-1开始 
			res = -1;
		}
		for(Markdown i : list) {
			int type = i.type;
			String str = i.str;
			if (type == 0) {
				res++;
			} else if (type == 1 || type == 2){
				if(str.length() == 0) {
					res++;
					continue;
				}
				int t = 0;
				while (t < str.length()) {
					while(t < str.length() && str.charAt(t) == ' ')
                        t++;
					// 项目首部空三格
					t += (w-3);
					res++;
				}
			} else {
				int t = 0;
				while (t < str.length()) {
					while(t < str.length() && str.charAt(t) == ' ')
                        t++;
					t += w;
					res++;
				}
			}
		}
		return res;
	}
}

class Markdown{
	// 0 空行、1项目列表第一行、2项目列表其余行、3段落
	public int type;
	public String str;
	public Markdown(int type, String str){
		this.type = type;
		this.str = str;
	}
}