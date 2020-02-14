package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11559_PuyoPuyo_이다경 {
	static char[][] map = new char[12][6];
	static char[][] copymap = new char[12][6];
	static boolean[][] selected= new boolean[12][6];
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};///상하좌우
	static Queue<Integer> qu = new LinkedList<Integer>();
	static boolean pang;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int cnt=0;
		
		for (int i = 0; i < 12; i++) {
			str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
				copymap[i][j] = map[i][j];
			}
		}
		
		
		char cur;
		
		do {
			pang = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(map[i][j]!='.') {
						cur = map[i][j];
						bfs(i*12+j,cur);
						
					}
				}
			}
			if(pang) {
				cnt++;
				down();
			}
		}while(pang);
		
		
		
		System.out.println(cnt);
	}

	private static void down() {
		for (int j = 0; j < 6; j++) {
			for (int i = 10; i >=0; i--) {
				if(map[i][j]=='.') continue;
				else {
					if(map[i+1][j]=='.') {
						map[i+1][j] = map[i][j];
						map[i][j]='.';
						i=11;
					}
				}
			}
		}
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				copymap[i][j] = map[i][j];
			}
		}
		
	}

	private static void bfs(int d, char cur) {
		qu.add(d);
		map[d/12][d%12]='.';
		int same=1;
		int curd, curI, curJ, nextI, nextJ;
		
		while(!qu.isEmpty()) {
			curd = qu.poll();
			curI = curd/12;
			curJ = curd%12;
			
			for (int i = 0; i < 4; i++) {
				nextI = curI+dir[i][0];
				nextJ = curJ+dir[i][1];
				
				if(nextI>=0 && nextI<12 && nextJ>=0 && nextJ<6) {
					if(!selected[nextI][nextJ] && map[nextI][nextJ]==cur) {
						same++;
						qu.add(nextI*12+nextJ);
						map[nextI][nextJ]='.';
					}
				}
			}
		}//end while..
		if(same<4) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					map[i][j] = copymap[i][j];
				}
			}
		}else {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					copymap[i][j] = map[i][j];
				}
			}
			pang=true;
		}
	}

}
