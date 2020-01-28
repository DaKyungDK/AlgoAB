package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_수영장_이다경 {
	static int day, month, threeMonth, year;
	static int[] swim = new int[13];
	static int min,cost;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			threeMonth = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				swim[i] = Integer.parseInt(st.nextToken());
			}
			min = year;
			cost=0;
			dfs(1);
			
			System.out.println("#"+tc+" "+min);
		}
	}

	private static void dfs(int i) {
		if(i>=13) {
			if(cost<min) min = cost;
			return;
		}
		
		if(swim[i]==0) {
			dfs(i+1);
		}else {
			cost+=threeMonth;
			dfs(i+3);
			cost-=threeMonth;
			
			cost+=month;
			dfs(i+1);
			cost-=month;
			
			cost+=day*swim[i];
			dfs(i+1);
			cost-=day*swim[i];
		}
		
	}

}
