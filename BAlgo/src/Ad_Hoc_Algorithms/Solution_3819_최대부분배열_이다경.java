package Ad_Hoc_Algorithms;

import java.util.Scanner;
//백준 1912
public class Solution_3819_최대부분배열_이다경 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T ; tc++) {
			int N = sc.nextInt();
			int max = Integer.MIN_VALUE;
			int[] A = new int[N+1];
			int[] sum = new int[N+1];//1~i가지의 합
			
			for (int n = 1; n <= N; n++) {
				A[n]= sc.nextInt();
				sum[n]=sum[n-1]+A[n];
			}
			
			int[] min = new int[N+1];
			int minval = sum[0];
			for (int i = 1; i <= N; i++) {
				if(minval > sum[i]) minval=sum[i];
				min[i] = minval;
			}
			
			int temp=0;
			for (int i = 1; i <= N; i++) {
				temp = sum[i]-min[i-1];
				if(max<temp) max=temp;
			}
			
			
			System.out.println("#"+tc+" "+max);
		}//end tc..
	}
}
