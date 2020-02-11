package fail;//메모리초과->틀렸습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9328_열쇠_이다경 {
	static int h,w,cnt;
	static char[][] map = new char[109][109];
	static boolean[][] selected = new boolean[109][109];
	static ArrayList<Character> key = new ArrayList<Character>();
	static ArrayList<Integer> startpoint = new ArrayList<Integer>();
	static Queue<Integer> qu = new LinkedList<Integer>();
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};//상하좌우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			key.clear();
			clearSelected();
			startpoint.clear();
			cnt=0;
			
			for (int i = 0; i < h; i++) {
				str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);
					if(i==0 || j==0 || i==h-1 || j==w-1) {
						if(map[i][j]=='.' || map[i][j]=='$' || (map[i][j]>='A' && map[i][j]<='Z')) {
							startpoint.add(i*w+j);
						}else if(map[i][j]>='a' && map[i][j]<='z') {
							if(!key.contains(map[i][j])) {
								key.add(map[i][j]);
							}
							map[i][j]='.';
							startpoint.add(i*w+j);
						}
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			char first = st.nextToken().charAt(0);
			if(first!='0') {
				key.add(first);
			}
			while(st.hasMoreTokens()) {
				key.add(st.nextToken().charAt(0));
			}
			
			int point,x,y,nextX,nextY,startIndex=0;
			int size = startpoint.size();
			while(startIndex<size) {
				point = startpoint.get(startIndex++);
				qu.add(point);
				x=point/w;
				y=point%w;
				selected[x][y] = true;
				
				char cur = map[x][y];
				if(cur=='$') {
					cnt++;
					map[x][y] = '.';
				}else if(cur>='A'&& cur<='Z') {
					if(!key.contains((char)(cur+32))) {
						qu.poll();
					}
				}
				
A:				while(!qu.isEmpty()) {
					point = qu.poll();
					x=point/w;
					y=point%w;
					for (int d = 0; d < 4; d++) {
						nextX = x+dir[d][0];
						nextY = y+dir[d][1];
						
						if(nextX>=0 && nextX<h &&nextY>=0 &&nextY<w) {
							cur = map[nextX][nextY];
							if(cur=='.') {
								if(!selected[nextX][nextY]) {
									qu.add(nextX*w+nextY);
									selected[nextX][nextY] = true;
								}
							}else if(cur=='$') {
								if(!selected[nextX][nextY]) {
									qu.add(nextX*w+nextY);
									selected[nextX][nextY] = true;
									cnt++;
									map[nextX][nextY]='.';
								}
							}else if(cur>='a'&& cur<='z') {
								if(!key.contains(cur)) {
									key.add(cur);
									startIndex=0;
									clearSelected();
									qu.clear();
									map[nextX][nextY]='.';
									break A;//처음부터 다시 검사
								}else {
									map[nextX][nextY]='.';
									if(!selected[nextX][nextY]) {
										qu.add(nextX*w+nextY);
										selected[nextX][nextY] = true;
									}
								}
							}else if(cur>='A'&& cur<='Z') {
								if(key.contains((char)(cur+32))) {
									map[nextX][nextY]='.';
									if(!selected[nextX][nextY]) {
										qu.add(nextX*w+nextY);
										selected[nextX][nextY] = true;
									}
								}
							}
						}
						
					}//end dir for..
				}//end while..
			}//end while..
			
			System.out.println(cnt);
		}//end tc..
	}

	private static void clearSelected() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				selected[i][j]=false;
			}
		}
	}

}
