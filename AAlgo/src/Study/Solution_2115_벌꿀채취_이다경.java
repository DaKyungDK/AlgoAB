package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취_이다경 {
	static int N,M,C,max,max1,max2;
	static int[][] map = new int[15][15];
	static int[] maxinfo1 = new int[15];
	static int[] maxinfo2 = new int[15];
	static boolean[] maxinfobool = new boolean[15];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				maxinfo1[i] = 0;
				maxinfo2[i] = 0;
			}
			
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int one=0, two=0;
			max = 0;
			
			for (int i = 0; i < N; i++) {//1번 행
				for (int j = 0; j <= N-M; j++) {//1번 시작 열
					int n=0;
					one=0;
					for (int k = j; k < j+M; k++) {//1번 시작 열부터 M개 더하기
						maxinfo1[n++] = map[i][k];
						one += map[i][k];
					}
					max1 = 0;
					if(one>C) {
						for (int m = 1; m < M; m++) {
							for (int k = 0; k < M; k++) {
								maxinfobool[k] = false;
							}
							combi1(m,0,0);
						}
					}else {
						for (int k = 0; k < M; k++) {
							max1 += maxinfo1[k]*maxinfo1[k];
						}
					}
					int i2 = i;
					if(j+M<=N-M) {
						for (int j2 = j+M; j2 <= N-M; j2++) {//2번 시작 열
							max2 = 0;
							n=0;
							two=0;
							for (int k2 = j2; k2 < j2+M; k2++) {
								maxinfo2[n++] = map[i2][k2];
								two += map[i2][k2];
							}
							
							n=0;
							if(two>C) {
								for (int m = 1; m < M; m++) {
									for (int k = 0; k < M; k++) {
										maxinfobool[k] = false;
									}
									combi2(m,0,0);
								}
							}else {
								for (int k = 0; k < M; k++) {
									max2 += maxinfo2[k]*maxinfo2[k];
								}
							}
							
							//calc
							int cal = max1+max2;
							if(cal>max)	max = cal;
						}
					}
					
					if(i+1<N) {
						for (i2 = i+1; i2 < N; i2++) {//2번 행
							for (int j2 = 0; j2 <= N-M; j2++) {//2번 시작 열
								n=0;
								two=0;
								for (int k2 = j2; k2 < j2+M; k2++) {
									maxinfo2[n++] = map[i2][k2];
									two += map[i2][k2];
								}
								
								n=0;
								max2 = 0;
								if(two>C) {
									for (int m = 1; m < M; m++) {
										for (int k = 0; k < M; k++) {
											maxinfobool[k] = false;
										}
										combi2(m,0,0);
									}
								}else {
									for (int k = 0; k < M; k++) {
										max2 += maxinfo2[k]*maxinfo2[k];
									}
								}
								
								//calc
								int cal = max1+max2;
								if(cal>max)	max = cal;
							}
						}
					}
				}//end j..
			}//end i..
			
			System.out.println("#" + tc + " " + max);
		}//end tc..
	}

	


	private static void combi1(int m, int num, int i) {
		if(M==i) {
			int sum=0;
			int squareSum=0;
			for (int j = 0; j < M; j++) {
				if(maxinfobool[j]) {
					sum+=maxinfo1[j];
				}
			}
			if(sum<=C) {
				for (int j = 0; j < M; j++) {
					if(maxinfobool[j]) {
						squareSum += maxinfo1[j]*maxinfo1[j];
					}
				}
				if(max1<squareSum) max1 = squareSum;
			}
			
			return;
		}
		if(M-i>m-num) {
			maxinfobool[i] = false;
			combi1(m,num,i+1);
		}
		if(m>num) {
			maxinfobool[i] = true;
			combi1(m,num+1,i+1);
		}
	}
	private static void combi2(int m, int num, int i) {
		if(M==i) {
			int sum=0;
			int squareSum=0;
			for (int j = 0; j < M; j++) {
				if(maxinfobool[j]) {
					sum+=maxinfo2[j];
				}
			}
			if(sum<=C) {
				for (int j = 0; j < M; j++) {
					if(maxinfobool[j]) {
						squareSum += maxinfo2[j]*maxinfo2[j];
					}
				}
				if(max2<squareSum) max2 = squareSum;
			}
			return;
		}
		if(M-i>m-num) {
			maxinfobool[i] = false;
			combi2(m,num,i+1);
		}
		if(m>num) {
			maxinfobool[i] = true;
			combi2(m,num+1,i+1);
		}
	}
}


