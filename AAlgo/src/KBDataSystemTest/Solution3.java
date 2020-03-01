package KBDataSystemTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		double H = Integer.parseInt(st.nextToken());//우물 높이
		double U = Integer.parseInt(st.nextToken());//첫날 올라가는 거리
		double D = Integer.parseInt(st.nextToken());//밤에 내려오는 거리
		double F = Integer.parseInt(st.nextToken());//피로도
		double decreaseU = (double)((double)U*(double)F/100);
		
		boolean success = false; 
		double cur=0;//초기높이
		double up=0;//올라간거리
		double next=0;//오른 후의 높이
		int dayCnt=0;//우물을 빠져나오는데 걸리는 일수
		
		do {
			up=U-dayCnt*decreaseU;
			if(up<0) {
				cur-=D;
				dayCnt++;
				continue;
			}
			dayCnt++;
			next=cur+up;
			if(next>H) {
				success=true;
				break;
			}
			cur=next-D;
		} while(cur>0);
		
		if(success) {
			System.out.println("Success " + dayCnt);
		}else {
			System.out.println("Failure "+dayCnt);
		}
	}

}
