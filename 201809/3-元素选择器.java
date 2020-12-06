/**
 * 不知道为什么错误，只有10分
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static String[] content;
	public static Elem dom;
	public static int index;
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		scanner.nextLine();
		content = new String[n];
		for (int i = 0; i < content.length; i++) {
			content[i] = scanner.nextLine();
		}
		
		dom = new Elem("html", 0);
		dom.level = 0;
		
		index = 1;
		addElem(dom);
		
		String[] selectors = new String[m];
		for (int i = 0; i < m; i++) {
			selectors[i] = scanner.nextLine();
		}
		
		for (int i = 0; i < m; i++) {
			String[] selector = selectors[i].split(" ");
			
			List<Elem> elems = new ArrayList<>();
			elems.add(dom);
			
			for (int j = 0; j < selector.length; j++) {
				List<Elem> newElems = new ArrayList<>();
				for (Elem elem : elems) {
					if (selector[j].contains("#")) {
						newElems.addAll(findId(elem, selector[j].substring(1)));
					} else {
						newElems.addAll(findName(elem, selector[j].toLowerCase()));
					}
				}
				elems = newElems;
			}

			Collections.sort(elems, new Comparator<Elem>() {

				@Override
				public int compare(Elem o1, Elem o2) {
					return o1.line - o2.line;
				}
			});
			
			System.out.print(elems.size());
			for (Elem e : elems) {
				System.out.print(" " + e.line);
			}
			System.out.println();
		}
	}
	
	public static void addElem(Elem parent) {
		while(index < content.length) {
			int level = (content[index].lastIndexOf('.') + 1) / 2;
			if (level > parent.level + 1) {
				addElem(parent.child.get(parent.child.size()-1));
			} else if (level < parent.level + 1){
				return;
			} else {
				String name = content[index].substring(level * 2).split(" ")[0].toLowerCase();
				Elem elem = new Elem(name, dom, index);
				elem.level = level;
				if (content[index].contains("#")) {
					elem.id = content[index].split("#")[1];
				}
				parent.child.add(elem);	
				index++;
			}
		}
	}
	
	public static List<Elem> findName(Elem parent, String name) {
		List<Elem> res = new ArrayList<>();
		
		if (parent.child.size() > 0) {
			for (Elem iElem : parent.child) {
				if (iElem.name.equals(name)) {
					res.add(iElem);
				} else {
					res.addAll(findName(iElem, name));
				}
			}
		}
		
		return res;
	}
	
	public static List<Elem> findId(Elem parent, String id) {
		List<Elem> res = new ArrayList<>();
		
		if (parent.child.size() > 0) {
			for (Elem iElem : parent.child) {
				if (iElem.id != null && iElem.id.equals(id)) {
					res.add(iElem);
				} else {
					res.addAll(findId(iElem, id));
				}
			}
		}
		
		return res;
	}
	
	/**
11 5
html
..head
....title
..body
....h1
....p #subtitle
....div #main
......h2
......p #one
......div
........p #two
p
#subtitle
body #main #tWo
DIV p
DIV DIV p
	 */
}

class Elem {
	String name;
	String id;
	Elem parent;
	List<Elem> child;
	int level;
	int line;
	
	public Elem(String name, int line) {
		this.name = name;
		this.line = line + 1;
		this.child = new ArrayList<>();
	}
	
	public Elem(String name, Elem parent, int line) {
		this.name = name;
		this.parent = parent;
		this.line = line + 1;
		this.child = new ArrayList<>();
	}
}
