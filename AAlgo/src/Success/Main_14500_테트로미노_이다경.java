package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노_이다경 {
	static int N,M,max;
	static int[][] map = new int[501][501];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sum=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//1번 길쭉이
				if(j+3<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i][j+2];
					sum+=map[i][j+3];
					if(sum>max) max = sum;
				}
				sum=0;
				if(i+3<N) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+2][j];
					sum+=map[i+3][j];
					if(sum>max) max = sum;
				}
				sum=0;
				//2번 네모
				if(i+1<N && j+1<M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i][j+1];
					sum+=map[i+1][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				//3번 ㄴ
				if(i+2<N && j+1<M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+2][j];
					sum+=map[i+2][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i-1>=0 && j+2<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i][j+2];
					sum+=map[i-1][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+2<N && j+1<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i+1][j+1];
					sum+=map[i+2][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+1<N && j+2<M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i][j+1];
					sum+=map[i][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+1<N && j+2<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i][j+2];
					sum+=map[i+1][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i-2>=0 && j+1<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i-1][j+1];
					sum+=map[i-2][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+2<N && j+1 <M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+2][j];
					sum+=map[i][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+1<N && j+2<M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+1][j+1];
					sum+=map[i+1][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				//4번 ㄴ+ㄱ
				if(i+2<N && j+1<M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+1][j+1];
					sum+=map[i+2][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i-1>=0 && j+2<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i-1][j+1];
					sum+=map[i-1][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+2<N && j-1>=0) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+1][j-1];
					sum+=map[i+2][j-1];
					if(sum>max) max = sum;
				}
				sum=0;
				if(i+1<N && j+2<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i+1][j+1];
					sum+=map[i+1][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				
				//5번 ㅗ
				if(i+1<N && j+2<M) {
					sum+=map[i][j];
					sum+=map[i+1][j+1];
					sum+=map[i][j+1];
					sum+=map[i][j+2];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+2<N && j+1<M) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+2][j];
					sum+=map[i+1][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i+2<N && j-1>=0) {
					sum+=map[i][j];
					sum+=map[i+1][j];
					sum+=map[i+1][j-1];
					sum+=map[i+2][j];
					if(sum>max) max = sum;
				}
				sum=0;
				
				if(i-1>=0 && j+2<M) {
					sum+=map[i][j];
					sum+=map[i][j+1];
					sum+=map[i][j+2];
					sum+=map[i-1][j+1];
					if(sum>max) max = sum;
				}
				sum=0;
			}
		}
		System.out.println(max);
	}//end main..

}
