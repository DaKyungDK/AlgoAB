package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1003_피보나치함수_이다경 {

	static int[][] fibo = new int[3][41];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N;
		Arrays.fill(fibo[0], -1);
		Arrays.fill(fibo[1], -1);
		Arrays.fill(fibo[2], -1);
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			fibo(N);
			System.out.println(fibo[0][N]+" "+fibo[1][N]);
		}//end tc..
	}

	private static int fibo(int n) {
		if(n==0) {
			fibo[0][0]=1;
			fibo[1][0]=0;
			return fibo[2][0]=0;
		}else if(n==1) {
			fibo[0][1]=0;
			fibo[1][1]=1;
			return fibo[2][1]=1;
		}else {
			if(fibo[2][n-1]!=-1 && fibo[2][n-2]!=-1) {
				fibo[0][n] = fibo[0][n-1]+fibo[0][n-2];
				fibo[1][n] = fibo[1][n-1]+fibo[1][n-2];
				return fibo[2][n] = fibo[2][n-1]+fibo[2][n-2];
			}else {
				fibo[2][n] = fibo(n-1)+fibo(n-2);
				fibo[0][n] = fibo[0][n-1]+fibo[0][n-2];
				fibo[1][n] = fibo[1][n-1]+fibo[1][n-2];
				return fibo[2][n];
			}
		}
	}
}
