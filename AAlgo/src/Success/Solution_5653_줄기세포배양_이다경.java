package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int X;
	int cur;
	int time;
	public Node(int x, int cur,int time) {
		X = x;
		this.cur = cur;
		this.time = time;
	}
}

public class Solution_5653_줄기세포배양_이다경 {
	static int N,M,K, cnt;
	static Node[][] map = new Node[900][900];
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};//상하좌우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int temp;
			cnt=0;
			
			for (int i = 0; i < 900; i++) {
				for (int j = 0; j < 900; j++) {
					map[i][j] = null;
				}
			}
			
			for (int i = 400; i < 400+N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 400; j < 400+M; j++) {
					temp = Integer.parseInt(st.nextToken());
					if(temp!=0) {
						map[i][j] = new Node(temp,temp*2,0);
						cnt++;
					}
				}
			}
			int n=0,m=0;
			for (int time = 1; time <= K; time++) {
				n++; m++;
				for (int i = 400-n; i < 400+N+n; i++) {
					for (int j = 400-m; j < 400+M+m; j++) {
						if(map[i][j]!=null) {
							if(map[i][j].cur==0){
								continue;
							}
							if(map[i][j].time!=time) {
								if(map[i][j].X >= map[i][j].cur && map[i][j].cur>0) {
									for (int k = 0; k < 4; k++) {
										int nextI = i+dir[k][0];
										int nextJ = j+dir[k][1];
										if(map[nextI][nextJ]==null) {
											map[nextI][nextJ] = new Node(map[i][j].X, map[i][j].X*2,time);
											cnt++;
										}else if(map[nextI][nextJ].cur>0 && map[nextI][nextJ].time==time){
											if(map[nextI][nextJ].X <= map[i][j].X) {
												map[nextI][nextJ] = new Node(map[i][j].X, map[i][j].X*2,time);
											}
										}
									}
								} 
								
								map[i][j].cur--;
								if(map[i][j].cur==0) cnt--;
							}
						}
					}
				}
			}
			
			System.out.println("#"+tc+" "+cnt);
		}//end tc..
	}

}
