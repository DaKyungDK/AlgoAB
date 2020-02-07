package fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1504_특정한최단경로_이다경 {
	static class Node {
		int start;
		int end;
		int distance;
		public Node(int start, int end, int distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
	}
	static int N,E;
	static LinkedList<Node>[] list;
	static Queue<Node> qu = new LinkedList<>();
	static int[][] dis;
	static boolean[][] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		dis=new int[3][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dis[i][j]=1009;
			}
		}
		selected = new boolean[3][N];
		list = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new LinkedList<Node>();
		}
		
		int s,e,d;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			s=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(s,e,d));
		}
		st = new StringTokenizer(br.readLine());
		int A= Integer.parseInt(st.nextToken());
		int B= Integer.parseInt(st.nextToken());
		
		dis[0][0]=0;
		dis[1][A]=0;//A번이 출발지점
		dis[2][B]=0;//B번이 출발지점
		int size;
		for (int i = 0; i < N; i++) {
			size = list[i].size();
			if(size==0) continue;
			for (int j = 0; j < size; j++) {
				Node nextNode = list[i].get(j);
				int end = nextNode.end;
				
			}
		}
		
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
	}

}
