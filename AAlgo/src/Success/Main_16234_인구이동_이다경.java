package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16234_인구이동_이다경 {
	static int N,L,R;
	static int[][] A = new int[57][57];
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};//상하좌우
	static int[] parents = new int[2600];
	static int[] sum= new int[2600];
	static ArrayList<Integer>[] country= new ArrayList[2600];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 2600; i++) {
			country[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		boolean move=true;
		int cnt=0;
		int nextR,nextC;
		int sub;
		
		while(move) {
			move=false;
			Arrays.fill(parents, -1);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < 4; k++) {
						nextR = i+dir[k][0];
						nextC = j+dir[k][1];
						if(nextR>=0 && nextR<N && nextC>=0 && nextC<N) {
							sub=Math.abs(A[i][j]-A[nextR][nextC]);
							if(sub<=R && sub>=L) {
								union(i*N+j,nextR*N+nextC);
							}
						}
					}
				}
			}
			
			for (int i = 0; i < N*N; i++) {
				if(parents[i]==-1) {
					int r = i/N;
					int c = i%N;
					sum[i] += A[r][c];
					country[i].add(i);
				}else {
					int a = find(i);
					int r = i/N;
					int c = i%N;
					sum[a] += A[r][c];
					country[a].add(i);
				}
			}
			
			
			for (int i = 0; i < N*N; i++) {
				if(parents[i]==-1) {
					int size = country[i].size();
					if(size>1) {
						int p = sum[i]/size;
						for (int j = 0; j < size; j++) {
							int index = country[i].get(j);
							int r = index/N;
							int c = index%N;
							A[r][c] = p;
						}
						move=true;
					}
					sum[i]=0;
					country[i].clear();
				}
			}
			
			if(move) cnt++;
		}
		
		
		System.out.println(cnt);
	}//end main..

	private static void union(int a, int b) {
		int RootA = find(a);
		int RootB = find(b);
		if(RootA!=RootB) {
			parents[RootA] = RootB;
		}
	}

	private static int find(int a) {
		if(parents[a]<0) return a;
		return parents[a] = find(parents[a]);
	}

}
