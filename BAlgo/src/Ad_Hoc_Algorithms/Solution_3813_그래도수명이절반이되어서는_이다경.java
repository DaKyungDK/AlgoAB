package Ad_Hoc_Algorithms;
//런타임에러
import java.util.Scanner;

public class Solution_3813_그래도수명이절반이되어서는_이다경 {

	static int N,K,maxW,minW;
	static int[] W = new int[200000];
	static int[] S = new int[200000];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			
			
			maxW =0;
			minW =1;
			for (int i = 1; i <= N; i++) {
				W[i] = sc.nextInt();
				if(maxW<W[i]) maxW=W[i];
			}
			
			for (int i = 1; i <= K; i++) {
				S[i] = sc.nextInt();
			}
			
			int x=0;
			boolean[] available = new boolean[N+9];
			
					
			
			
			while(minW<maxW) {
				//available 초기화
				for (int i = 0; i < N+9; i++) {
					available[i]=false;
				}
				x  = (minW+maxW)/2;
				for (int i = 1; i <= N; i++) {
					if(W[i]<=x) available[i] = true;
				}
				
				int k=1;
				int cnt=0;
				for (int i = 1; i <= N; i++) {
					
					if(available[i]==true) {
						cnt++;
						if(S[k]==cnt) {
							k++;
							if(k>K) break;
							cnt=0;
						}
					}else {
						cnt=0;
					}
				}
				if(k>K) maxW=x;
				else {
					x++;
					minW=x;
				}
			}//end while..
			
			System.out.println("#"+tc+" "+x);
		}//end tc..
	}
}
