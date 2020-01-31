package fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17140_이차원배열과연산_이다경 {
	static class Node implements Comparable<Node>{
		int num;
		int cnt;
		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
		public int compareTo(Node o) {
			if(this.cnt-o.cnt==0) return this.num-o.num;
			return this.cnt-o.cnt;
		}
	}
	
	
	static int r,c,k,time;
	static int[][] A = new int[101][101];
//	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static int[] cnt = new int[100000];
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static PriorityQueue<Node> pqNode = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		time = 0;
		
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int R=3, C=3;//행의 개수:C 열의 개수:R
		
		while(A[r][c]!=k) {
			Arrays.fill(cnt, 0);
			time++;
			if(C >= R) {//행
				int c = C;
				for (int i = 1; i <= R; i++) {
					for (int j = 1; j <= c; j++) {
						if(A[i][j]==0) break;
						if(pq.contains(A[i][j])) cnt[A[i][j]]++;
						else {
							pq.add(A[i][j]);
							cnt[A[i][j]]=1;
						}
					}
					
					while(!pq.isEmpty()) {
						int n = pq.poll();
						Node node = new Node(n,cnt[n]);
						pqNode.add(node);
					}
					
					
					int index=1;
					while(!pqNode.isEmpty()) {
						if(index>100) {
							pqNode.clear();
							break;
						}
						Node node = pqNode.poll();
						A[i][index++]=node.num;
						A[i][index++]=node.cnt;
					}
					if(c<index-1) C=index-1;
					else if(c>index-1) {
						for (int j = index; j <= c; j++) {
							A[i][j] = 0;
						}
					}
				}
			}else {//열
				int r = R;
				for (int j = 1; j <= C; j++) {
					for (int i = 1; i <= r; i++) {
						if(A[i][j]==0) break;
						if(pq.contains(A[i][j])) cnt[A[i][j]]++;
						else {
							pq.add(A[i][j]);
							cnt[A[i][j]]=1;
						}
					}
					while(!pq.isEmpty()) {
						int n = pq.poll();
						Node node = new Node(n,cnt[n]);
						pqNode.add(node);
					}
					
					
					int index=1;
					while(!pqNode.isEmpty()) {
						if(index>100) {
							pqNode.clear();
							break;
						}
						Node node = pqNode.poll();
						A[index++][j]=node.num;
						A[index++][j]=node.cnt;
					}
					if(R<index-1) R=index-1;
					else if(r>index-1) {
						for (int i = index; i <= r; i++) {
							A[i][j] = 0;
						}
					}
					}
				}
			
		}
		System.out.println(time);
	}//end main..

}
