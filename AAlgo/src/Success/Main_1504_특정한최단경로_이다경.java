package Success;
//갔던 곳을 다시 가도 되기 때문에 selected 해주지 않아도 됨...!!!!!ㅠㅠㅠ
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
	static Queue<Integer> qu1 = new LinkedList<>();
	static Queue<Integer> qu2 = new LinkedList<>();
	static Queue<Integer> qu3 = new LinkedList<>();
	static int[][] dis;
//	static boolean[][] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		dis=new int[3][N+1];
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= N; j++) {
				dis[i][j]=1009;
			}
		}
//		selected = new boolean[3][N+1];
		list = new LinkedList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<Node>();
		}
		
		int s,e,d;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			s=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(s,e,d));
			list[e].add(new Node(e,s,d));
		}
		st = new StringTokenizer(br.readLine());
		int A= Integer.parseInt(st.nextToken());
		int B= Integer.parseInt(st.nextToken());
		
//		selected[0][0]=true;
		dis[0][1]=0;//1번이 출발지점
		int size;
		qu1.add(1);
		int cur;
		while(!qu1.isEmpty()) {
			cur = qu1.poll();
//			selected[0][cur] = true;
			size = list[cur].size();
			if(size==0) continue;
//			boolean AllSelected=true;
			int minIndex = 1009;
			for (int j = 0; j < size; j++) {
				Node nextNode = list[cur].get(j);
				int end = nextNode.end;
				if(dis[0][end]>dis[0][cur]+nextNode.distance) {
					dis[0][end] = dis[0][cur]+nextNode.distance;
//					AllSelected=false;
					qu1.add(end);
				}
			}
			
//			for (int i = 1; i <= N; i++) {
//				if(dis[0][i]<minIndex) {
//					minIndex = i;
//					AllSelected = false;
//				}
//			}
//			if(!AllSelected) qu1.add(minIndex);
		}
		
//		selected[1][0]=true;
		dis[1][A]=0;//A번이 출발지점
		qu2.add(A);
		//A에서 시작
		while(!qu2.isEmpty()) {
			cur = qu2.poll();
//			selected[1][cur] = true;
			size = list[cur].size();
			if(size==0) continue;
			for (int j = 0; j < size; j++) {
				Node nextNode = list[cur].get(j);
				int end = nextNode.end;
				if(dis[1][end]>dis[1][cur]+nextNode.distance) {
					dis[1][end] = dis[1][cur]+nextNode.distance;
					qu2.add(end);
				}
			}
//			boolean AllSelected=true;
//			int minIndex = 1009;
//			for (int i = 1; i <= N; i++) {
//				if(!selected[1][i] && dis[1][i]<minIndex) {
//					minIndex = i;
//					AllSelected = false;
//				}
//			}
//			if(!AllSelected) qu2.add(minIndex);
		}
			
			
//		selected[2][0]=true;
		dis[2][B]=0;//B번이 출발지점
		qu3.add(B);
		//B에서 시작
		while(!qu3.isEmpty()) {
			cur = qu3.poll();
//			selected[2][cur] = true;
			size = list[cur].size();
			if(size==0) continue;
			for (int j = 0; j < size; j++) {
				Node nextNode = list[cur].get(j);
				int end = nextNode.end;
				if(dis[2][end]>dis[2][cur]+nextNode.distance) {
					dis[2][end] = dis[2][cur]+nextNode.distance;
					qu3.add(end);
				}
			}
//			boolean AllSelected=true;
//			int minIndex = 1009;
//			for (int i = 1; i <= N; i++) {
//				if(!selected[2][i] && dis[2][i]<minIndex) {
//					minIndex = i;
//					AllSelected = false;
//				}
//			}
//			if(!AllSelected) qu3.add(minIndex);
		}	
		
		long one = dis[0][A]+dis[1][B]+dis[2][N];
		long two = dis[0][B]+dis[2][A]+dis[1][N];
		long ans = Math.min(one, two);
		if(dis[0][A]>1001 || dis[1][B]>1001 || dis[2][N]>1001) {
			ans=two;
			if(dis[0][B]>1001 || dis[2][A]>1001 || dis[1][N]>1001) {
				ans=-1;
			}
		}
		System.out.println(ans);
	}//end main..

}
