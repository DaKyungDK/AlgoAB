package Success;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main_1655_가운데를말해요_이다경 {
	static ArrayList<Integer> arrl = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arrl = new ArrayList<Integer>();
		int N = sc.nextInt();
		int e,length=1,print;
		
		e=sc.nextInt();
		arrl.add(e);
		System.out.println(e);
		
		for (int i = 1; i < N; i++) {
			e=sc.nextInt();
			
			divide(e, 0, length-1);
			
			length++;
			if(length%2==0) print=arrl.get(length/2-1);
			else print=arrl.get(length/2);
			
			System.out.println(print);
		}
	}

	private static void divide(int e, int start, int end) {
		if(start==end) {
			if(arrl.get(start)<e) {
				arrl.add(start+1, e);
			}else {
				arrl.add(start,e);
			}
			return;
		}
		int mid = (start+end)/2;
		if(arrl.get(mid)<e) {//뒤
			divide(e, mid+1, end);
		}else {//앞
			divide(e, start, mid);
		}
	}
}
