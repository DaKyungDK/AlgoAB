package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기_이다경 {
	static int N,M,x,y,K;
	static int[] dice = {0,0,0,0,0,0,0};
	static int[][] map;
	static int[] move;
	static int[][] dir = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};//동서북남=우좌상하
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		move = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		int k =0;
		int curx=x,cury=y;
		int nextx,nexty;
		
		while(k<K) {
			//이동
			nextx = curx+dir[move[k]][0];
			nexty = cury+dir[move[k]][1];
			if(nextx<0 || nexty<0 || nextx>=N || nexty>=M) {
				k++;
				continue;
			}
			nextdice(move[k]);
			
			//숫자 복사
			if(map[nextx][nexty]==0) {
				map[nextx][nexty]=dice[6];
			}else {
				dice[6]=map[nextx][nexty];
				map[nextx][nexty]=0;
			}
			//출력
			System.out.println(dice[1]);
			curx = nextx;
			cury = nexty;
			k++;
		}//end while..
		
		
	}//end main..

	private static void nextdice(int d) {
		switch(d) {
		case 1:
			dice[0] = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = dice[0];
			break;
		case 2:
			dice[0] = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = dice[0];
			break;
		case 3:
			dice[0] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = dice[0];
			break;
		case 4:
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = dice[0];
			break;
		}
	}

}
