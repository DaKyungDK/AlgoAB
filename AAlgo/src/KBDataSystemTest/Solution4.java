package KBDataSystemTest;

import java.util.Scanner;

public class Solution4 {
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int level = sc.nextInt();
		cnt=0;
		hanoi(1,3,2,level);
		
		System.out.println(cnt);
	}

	private static void hanoi(int from, int to, int center, int level) {
		if(level==0) return;
		else if(level==1) {
			cnt++;
		} 
		else {
			hanoi(from,center,to,level-1);
			cnt++;
			hanoi(center,to,from,level-1);
		}
	}

}
