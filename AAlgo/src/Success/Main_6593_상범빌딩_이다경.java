package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6593_상범빌딩_이다경 {
	static class Node{
		int l;
		int r;
		int c;
		int time;
		public Node(int l, int r, int c,int time) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	static int L, R, C;
	static char[][][] map;
	static Queue<Node> qu;
	static int[][] dir = {{-1,0,0},{1,0,0},{0,-1,0},{0,1,0},{0,0,-1},{0,0,1}};//동서남북상하 L,R,C

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		StringBuilder answer = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L==0 && R==0 && C==0) break;
			qu = new LinkedList<Node>();
			map = new char[L][R][C];
			
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					str=br.readLine();
					for (int c = 0; c < C; c++) {
						map[l][r][c] = str.charAt(c);
						if(map[l][r][c]=='S') {
							qu.add(new Node(l,r,c,0));
						}
					}
				}
				br.readLine();
			}
			
			boolean escape = false;
			int time=0;
			int curL,curR,curC,nextL,nextR,nextC;
			
wh:			while(!qu.isEmpty()) {
				Node curnode = qu.poll();
				curL = curnode.l;
				curR = curnode.r;
				curC = curnode.c;
				
				for (int d = 0; d < 6; d++) {//동서남북상하
					nextL = curL+dir[d][0];
					nextR = curR+dir[d][1];
					nextC = curC+dir[d][2];
					if(nextL>=0 && nextL<L && nextR>=0 && nextR<R &&nextC>=0 && nextC<C) {
						if(map[nextL][nextR][nextC]=='E') {
							escape = true;
							time = curnode.time+1;
							break wh;
						}else if(map[nextL][nextR][nextC]=='.') {
							qu.add(new Node(nextL,nextR,nextC,curnode.time+1));
							map[nextL][nextR][nextC]='#';
						}
					}
				}
			}//end while..
			
			if(escape) answer.append("Escaped in "+time+" minute(s).").append("\n");
			else answer.append("Trapped!").append("\n");
		}//end tc..
		System.out.println(answer);
	}

}
