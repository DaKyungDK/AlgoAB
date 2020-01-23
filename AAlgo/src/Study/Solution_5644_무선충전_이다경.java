package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Battery implements Comparable<Battery>{
	int a;
	int p;
	
	public Battery(int a, int p) {
		super();
		this.a = a;
		this.p = p;
	}

	public int compareTo(Battery o) {
		return this.p-o.p;
	}
	
	
}
public class Solution_5644_무선충전_이다경 {
	static int M,A;
	static int[] personA = new int[101];
	static int[] personB = new int[101];
	static int[][] BC = new int[10][4];
	static ArrayList<Battery>[][] map = new ArrayList[11][11];
	static int[][] dir = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};//상우하좌
	static int XA,YA,XB,YB,total;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < M; t++) {
				personA[t] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < M; t++) {
				personB[t] = Integer.parseInt(st.nextToken());
			}
			
			for (int a = 0; a < A; a++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < 4; i++) {
					BC[a][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					map[i][j] = new ArrayList<>();
					for (int a = 0; a < A; a++) {
						if((Math.abs(i-BC[a][0]) + Math.abs(j-BC[a][1]))<=BC[a][2]) {
							Battery temp = new Battery(a,BC[a][3]);
							map[i][j].add(temp);
						}
					}
					Collections.sort(map[i][j]);
				}
			}
			
			int time = 0;
			XA=1;
			YA=1;
			XB=10;
			YB=10;
			total=0;
			int curA,curB;
			while(time<=M) {
				if(map[XA][YA].size()==1) {//무조건 먹음.
					Battery b = map[XA][YA].get(0);
					curA = b.a;
					total+=b.p;
					if(!map[XB][YB].isEmpty()) {
						int size = map[XB][YB].size();
						Battery b2 = map[XB][YB].get(size-1);
						curB = b2.a;
						if(curA==curB) {
							if(size>1) {
								b2 = map[XB][YB].get(size-2);
								total+=b2.p;
							}
						}else {
							total+=b2.p;
						}
					}
				}else if(map[XA][YA].size()>1) {//B를 먼저 검사
					Battery b = map[XA][YA].get(map[XA][YA].size()-1);
					curA = b.a;
					if(map[XB][YB].size()==1) {//B 무조건 먹음
						Battery b2 = map[XB][YB].get(0);
						curB=b2.a;
						total+=b2.p;
						if(curA==curB) total+=map[XA][YA].get(map[XA][YA].size()-2).p;
						else total+=b.p;
					}else if(map[XB][YB].size()>1){
						if(!map[XB][YB].isEmpty()) {
							Battery b2 = map[XB][YB].get(0);
							curB=b2.a;
							if(curA==curB) {
								total+=b2.p;
								if(map[XA][YA].get(map[XA][YA].size()-2).p < map[XB][YB].get(map[XB][YB].size()-2).p) {
									total+=map[XB][YB].get(map[XB][YB].size()-2).p;
								}else {
									total+=map[XA][YA].get(map[XA][YA].size()-2).p;
								}
							}else {
								total+=b2.p;
								total+=b.p;
							}
						}else {//B가 비어있음
							total+=b.p;
						}
						
					}
				}else {//A 못먹음. B검사
					if(!map[XB][YB].isEmpty()) {
						Battery b2 = map[XB][YB].get(map[XB][YB].size()-1);
						total+=b2.p;
					}
				}
				
				
				XA += dir[personA[time]][0]; 
				YA += dir[personA[time]][1]; 
				XB += dir[personB[time]][0]; 
				YB += dir[personB[time]][1]; 
				time++;
			}//end while..
			
			System.out.println("#"+tc+" "+total);
		}//end tc..
	}

}
