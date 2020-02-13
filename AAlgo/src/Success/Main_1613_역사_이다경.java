package Success;

import java.util.Scanner;

public class Main_1613_역사_이다경 {
	static int n,k,s;
	static int[][] afterThanMe = new int[409][409];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n=sc.nextInt();
		k=sc.nextInt();
		
		
		int prev, next;
		for (int i = 0; i < k; i++) {
			prev = sc.nextInt();
			next = sc.nextInt();
			
			afterThanMe[prev][next]=-1;
			afterThanMe[next][prev]=1;
		}
		
		for (int i = 1; i < n+1; i++) {
			for (int j = 0; j < n+1; j++) {
				if(afterThanMe[i][j]==-1) {
					for (int k = 0; k < n+1; k++) {
						if(afterThanMe[j][k]==-1) {
							afterThanMe[i][k]=-1;
						}
					}
				}else if(afterThanMe[i][j]==1) {
					for (int k = 0; k < n+1; k++) {
						if(afterThanMe[j][k]==1) {
							afterThanMe[i][k]=1;
						}
					}
				}
				
			}
		}
		//한번 더돌려주기
		for (int i = 1; i < n+1; i++) {
			for (int j = 0; j < n+1; j++) {
				if(afterThanMe[i][j]==-1) {
					for (int k = 0; k < n+1; k++) {
						if(afterThanMe[j][k]==-1) {
							afterThanMe[i][k]=-1;
						}
					}
				}else if(afterThanMe[i][j]==1) {
					for (int k = 0; k < n+1; k++) {
						if(afterThanMe[j][k]==1) {
							afterThanMe[i][k]=1;
						}
					}
				}
				
			}
		}
		s=sc.nextInt();
		for (int i = 0; i < s; i++) {
			prev = sc.nextInt();
			next = sc.nextInt();
			System.out.println(afterThanMe[prev][next]);
		}
	}
}
