package 기타;
//왜틀린지 모르겠음 ㅠ dijkstra 문제
/*
1
7 12 1 6
1 2 1
1 4 3
1 3 4
2 4 2
2 5 1
3 4 2
3 6 2
4 5 1
4 6 3
4 7 1
5 7 4
6 7 2
 */
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node>{
	int end;
	int cost;
	
	public Node(int end, int cost) {
		super();
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost >= o.cost ? 1:-1;
	}
}


public class Solution_1803_ShortestPathFaster_이다경 {

	static LinkedList<Node>[] adjlist = new LinkedList[100009];
	static int[] dis = new int[100009];
	static boolean[] selected = new boolean[100009];
	static int INF = 100001;
	static PriorityQueue<Node> qu = new PriorityQueue<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N,M,start,end;
		
		for (int tc = 1; tc <= T; tc++) {
			int n=0;
			N = sc.nextInt();
			M = sc.nextInt();
			start = sc.nextInt();
			end = sc.nextInt();
			for (int i = 0; i < 100009; i++) {
				dis[i] = INF;
				selected[i] = false;
				adjlist[i] = new LinkedList<>();
			}
				
			
			dis[start]=0;
			int s,e,c;
			
			for (int i = 0; i < M; i++) {
				s=sc.nextInt();
				e=sc.nextInt();
				c=sc.nextInt();
				
				Node node = new Node(e,c);
				adjlist[s].add(node);
				node = new Node(s,c);
				adjlist[e].add(node);
			}
			
			selected[start] = true;
			n++;
			while(!adjlist[start].isEmpty()) {
				Node node = adjlist[start].poll();
				dis[node.end] = node.cost;
				qu.add(node);
			}
			//priority queue 사용해보자//cost기준 정렬??ㅠㅠ
			while(!qu.isEmpty()) {
				Node nextnode = qu.poll();
				int i = nextnode.end;
				
				if(!selected[i]) {
					selected[i] = true;
					while(!adjlist[i].isEmpty()) {
						Node node = adjlist[i].poll();
						if(dis[node.end]>dis[i]+node.cost) {
							dis[node.end] = dis[i]+node.cost;
							if(!selected[node.end]) {
								Node newnode = new Node(node.end,dis[i]+node.cost);
								qu.add(newnode);
							}
						}
					}
				}
			}//end while..
			
			System.out.println("#"+tc+" "+dis[end]);
		}//end tc..
	}
}
