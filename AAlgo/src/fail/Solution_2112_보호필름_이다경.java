package fail;
//완탐+가지치기..ㅜ 미완
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름_이다경 {
	static int D,W,K;
	static int[][] map = new int[15][25];
	static boolean[] line;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			line = new boolean[D];

			for (int d = 0; d < D; d++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[d][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			int d = D/2;
			combi(d,0);
			
		}//end tc..
	}

	private static boolean check(int d) {
		int cntA=0;
		int cntB=0;
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < D; j++) {
				if(map[i][j]==0) {
					cntA++;
					cntB=0;
				}else {
					cntB++;
					cntA=0;
				}
			}
			if(cntA<K && cntB<K) {
				return false;
			}
		}
		return true;
	}

	private static boolean combi(int d,int i) {
		if(i==d) {
			//검사
			for (int j = 0; j < D; j++) {
				if(line[j]) {
					for (int k = 0; k < W; k++) {
						map[j][k]=1;
						//0인경우도 해야하는데 그러면 2^d 만큼 해야되네....코드구현이 가능한지도 모르겠어 지금...
					}
				}
			}
			check(d);
		}
		if(D-i>D/2-d) {
			line[i] = false;
			combi(d,i+1);
		}
		if(D/2>d) {
			line[i] = true;
			combi(d+1,i+1);
		}
		return false;
	}

}
