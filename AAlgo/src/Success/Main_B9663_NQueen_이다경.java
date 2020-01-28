package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B9663_NQueen_¿Ã¥Ÿ∞Ê {
	static int N,cnt=0;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		for (int i = 1; i <= N; i++) {
			arr[0] = i;
			nqueen(1);
		}
		
		System.out.println(cnt);
	}

	private static void nqueen(int row) {
		if(row==N) {
			cnt++;
			return;
		}
		
A:		for (int j = 1; j <= N; j++) {
			for (int i = 0; i < row; i++) {
				if(arr[i]==j || (row-i)==Math.abs(arr[i]-j)) {
					continue A;
				}
			}
			arr[row] = j;
			nqueen(row+1);
		}
	}



}
