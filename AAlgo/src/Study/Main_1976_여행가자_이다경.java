package Study;
/*
5
5
0 1 0 1 1
1 0 1 1 0
0 1 0 0 0
1 1 0 0 0
1 0 0 0 0
5 3 2 3 4
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1976_여행가자_이다경 {
	static int N,M;
	static int[][] map;
	static int[] trip,parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N+1][N+1];
		trip = new int[M+1];
		parents = new int[N+1];
		Arrays.fill(parents, -1);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			trip[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = i; j <= N; j++) {
				if(map[i][j]==1) {
					union(i,j);
				}
			}
		}
		
		int group = trip[1];
		int cur;
		boolean flag=true;
		for (int i = 2; i <= M; i++) {
			cur = trip[i];
			if(find(cur)!=find(group)) {
				System.out.println("NO");
				flag = false;
				break;
			}
		}
		if(flag) System.out.println("YES");
		
	}//end main

	private static void union(int a, int b) {
		int RootA = find(a);
		int RootB = find(b);
		if(RootA!=RootB) {
			parents[RootB] = RootA;
		}
	}

	private static int find(int a) {
		if(parents[a]<0) return a;
		return parents[a] = find(parents[a]);
	}

}
