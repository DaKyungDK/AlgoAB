package Success;
//0은 빈칸, 1은 벽, 2는 virus
// => 2는 비활성 바이러스 3이상은 활성 바이러스
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17142_연구소3_이다경 {
	static int N,M,MM,min,wall;
	static int[] virusindex;
	static boolean[] virusactivate;
	static int[][] map,mapcopy;
	static ArrayList<Integer> arrl = new ArrayList<>();
	static Queue<Integer> qu = new LinkedList<Integer>();
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};//상하좌우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		mapcopy = new int[N][N];
		MM=0;
		wall=0;
		min = -1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapcopy[i][j] = map[i][j];
				if(map[i][j]==2) {
					arrl.add(i*N+j);
					MM++;
				}else if(map[i][j]==1) {
					wall++;
				}
			}
		}
		
		virusindex = new int[MM];
		virusactivate = new boolean[MM];
		
		for (int i = 0; i < MM; i++) {
			virusindex[i] = arrl.get(i);
		}
		
		//MM개 중 M개 뽑기
		combi(0, 0);
		
		System.out.println(min);
	}

	private static void combi(int selectedcnt, int totalcnt) {
		if(selectedcnt==M) {
			int r,c;
			for (int i = 0; i < MM; i++) {
				if(virusactivate[i]) {
					r=virusindex[i]/N;
					c=virusindex[i]%N;
					map[r][c] = 3;
					qu.add(virusindex[i]);
				}
			}
			calc();
			qu.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = mapcopy[i][j];
				}
			}
			return;
		}
		if(M-selectedcnt<MM-totalcnt) {
			virusactivate[totalcnt]=false;
			combi(selectedcnt,totalcnt+1);
		}
		if(selectedcnt<M) {
			virusactivate[totalcnt]=true;
			combi(selectedcnt+1, totalcnt+1);
			virusactivate[totalcnt]=false;
		}
	}

	private static void calc() {
		int virus = MM;
		int time =0;
		int nextR, nextC;
		boolean yellowcard=false, redcard=false;
		int v = N*N-wall;
		int index,i,j;
		
		while(virus<v) {
			int lastvirus=virus;
			int size = qu.size();
			for (int a = 0; a < size; a++) {
				index = qu.poll();
				i=index/N;
				j=index%N;
				//상하좌우 복제
				for (int k = 0; k < 4; k++) {
					nextR=i+dir[k][0];
					nextC=j+dir[k][1];
					if(nextR>=0 && nextR<N && nextC>=0 && nextC<N) {
						if(map[nextR][nextC]==0) {
							qu.add(nextR*N+nextC);
							map[nextR][nextC]=time+4;
							virus++;
						}else if(map[nextR][nextC]==2) {
							qu.add(nextR*N+nextC);
							map[nextR][nextC]=time+4;
						}
					}
				}
			}	
			if(lastvirus==virus) {
				if(yellowcard) {
					redcard=true;
					break;
				}
				else yellowcard=true;
			}
			time++;
		}//end while...
		
		if(!redcard) {
			if(min==-1 || time<min) min = time;
		}
		
	}

}
