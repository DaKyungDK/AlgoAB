package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17822_원판돌리기_이다경 {
	static int N,M,T;
	static int[][] circlemap,tempcircle;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		circlemap = new int[N+1][M];
		tempcircle = new int[N+1][M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				circlemap[i][j]=Integer.parseInt(st.nextToken());
				tempcircle[i][j] = circlemap[i][j];
			}
		}
		
		int x,d,k;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			
			spin(x,d,k);
		}
		int totsum=0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				totsum+=circlemap[i][j];
			}
		}
		System.out.println(totsum);
	}

	private static void spin(int x, int d, int k) {
		int firstx=x;
		while(x<=N) {
			if(d==0) {//시계방향
				int[] temp = new int[M];
				for (int i = 0; i < M; i++) {
					temp[(i+k)%M] = circlemap[x][i];
				}
				for (int i = 0; i < M; i++) {
					circlemap[x][i]=temp[i];
				}
			}else {//반시계방향
				int[] temp = new int[M];
				for (int i = 0; i < M; i++) {
					if(i-k<0) {
						temp[M+i-k] = circlemap[x][i];
					}else {
						temp[(i-k)] = circlemap[x][i];
					}
				}
				for (int i = 0; i < M; i++) {
					circlemap[x][i]=temp[i];
				}
			}
			x+=firstx;
		}//end while..
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				tempcircle[i][j]=circlemap[i][j];
			}
		}
		if(!adj()) {
			calcavg();
		}
	}

	private static void calcavg() {
		double totsum=0,cnt=0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				totsum+=circlemap[i][j];
				if(circlemap[i][j]!=0) {
					cnt++;
				}
			}
		}
		double avg = totsum/cnt;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if(circlemap[i][j]==0) continue;
				if(circlemap[i][j]<avg) {
					circlemap[i][j]++;
				}else if(circlemap[i][j]>avg){
					circlemap[i][j]--;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				tempcircle[i][j]=circlemap[i][j];
			}
		}
	}

	private static boolean adj() {
		boolean ret=false;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if(circlemap[i][j]==0) continue;
				if(circlemap[i][j]==circlemap[i][(j+1)%M]) {
					tempcircle[i][j] = tempcircle[i][(j+1)%M]=0;
					ret=true;
				}
				if(i<N) {
					if(circlemap[i][j]==circlemap[i+1][j]) {
						tempcircle[i][j] = tempcircle[i+1][j]=0;
						ret=true;
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				circlemap[i][j] = tempcircle[i][j];
			}
		}
		return ret;
	}

}
