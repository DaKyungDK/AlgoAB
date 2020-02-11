package fail;//메모리 초과

import java.util.ArrayList;
import java.util.Scanner;

public class Main_1613_역사_이다경_메모리초과 {
	static int n,k,s;
	static ArrayList<Integer>[] afterThanMe = new ArrayList[409];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n=sc.nextInt();
		k=sc.nextInt();
		
		for (int i = 1; i < n+1; i++) {
			afterThanMe[i] = new ArrayList<>(n+1);
		}
		
		int prev, next;
		for (int i = 0; i < k; i++) {
			prev = sc.nextInt();
			next = sc.nextInt();
			
			afterThanMe[prev].add(next);
		}
		
		int temp,tempnum;
		for (int i = 1; i < n+1; i++) {
			for (int j = 0; j < afterThanMe[i].size(); j++) {
				temp = afterThanMe[i].get(j);
				for (int k = 0; k < afterThanMe[temp].size(); k++) {
					tempnum = afterThanMe[temp].get(k);
					if(!afterThanMe[i].contains(tempnum)) afterThanMe[i].add(tempnum);
				}
			}
		}
		//한번 더돌려주기
		for (int i = 1; i < n+1; i++) {
			for (int j = 0; j < afterThanMe[i].size(); j++) {
				temp = afterThanMe[i].get(j);
				for (int k = 0; k < afterThanMe[temp].size(); k++) {
					tempnum = afterThanMe[temp].get(k);
					if(!afterThanMe[i].contains(tempnum)) afterThanMe[i].add(tempnum);
				}
			}
		}
		
		
		s=sc.nextInt();
		
		for (int i = 0; i < s; i++) {
			prev = sc.nextInt();
			next = sc.nextInt();
			if(afterThanMe[prev].contains(next)) {
				System.out.println(-1);
			}else if(afterThanMe[next].contains(prev)) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
		}
		
	}

}
