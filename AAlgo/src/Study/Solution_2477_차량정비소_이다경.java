package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Customer implements Comparable<Customer>{
	int personNum;
	int ANum;
	int inTime;
	
	public Customer(int personNum, int aNum, int inTime) {
		super();
		this.personNum = personNum;
		ANum = aNum;
		this.inTime = inTime;
	}

	@Override
	public int compareTo(Customer o) {
		if(this.inTime-o.inTime==0) return this.ANum-o.ANum;
		return this.inTime-o.inTime;
	}
}
public class Solution_2477_차량정비소_이다경 {
	static int N,M,K,A,B,ans;
	static int[] a = new int[10];
	static int[] b = new int[10];
	static int[] t;
	static Queue<Integer> qu1 = new LinkedList<Integer>();
	static PriorityQueue<Customer> qu2 = new PriorityQueue<>();
	static int[][] checkN = new int[10][2];
	static int[][] checkM = new int[10][2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				checkN[i][0]=-1;
				checkN[i][1]=a[i];
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				b[j] = Integer.parseInt(st.nextToken());
				checkM[j][0]=-1;
				checkM[j][1]=b[j];
			}
			st = new StringTokenizer(br.readLine());
			int maxtime=0;
			t = new int[K+1];
			for (int k = 1; k <= K; k++) {
				t[k] = Integer.parseInt(st.nextToken());
				if(t[k]>maxtime) maxtime=t[k];
			}
			
			Arrays.sort(t);
			int index=1;
			int time=0;
			ans=0;
			while(time<40000) {
				//1.M에서 나갈 애 있나 확인
				for (int j = 1; j <= M; j++) {
					if(checkM[j][0]!=-1) {//Mj에 누가 있으면
						checkM[j][1]--;
						if(checkM[j][1]==0) {
							if(j==B && t[checkM[j][0]]==A) ans+=checkM[j][0];
							checkM[j][0]=-1;
							checkM[j][1]=b[j];
						}
					}
				}
				//2. qu2기다리는 애 있나 확인
A:				while(!qu2.isEmpty()) {
					for (int j = 1; j <= M; j++) {
						if(checkM[j][0]==-1) {//Mj에 누가 없으면 들어가기
							Customer cu = qu2.poll();
							checkM[j][0]= cu.personNum;
							continue A;
						}
					}
					break;
				}
				//3. N에서 나갈애 있나 확인
				for (int i = 1; i <= N; i++) {
					if(checkN[i][0]!=-1) {//Ni에 누가 있으면
						checkN[i][1]--;
						if(checkN[i][1]==0) {
							t[checkN[i][0]]=i;
							qu2.add(new Customer(checkN[i][0],i,time));
							checkN[i][0]=-1;
							checkN[i][1]=a[i];
						}
					}
				}
				//4. qu1에 기다리는 애 있나 확인
B:				while(!qu1.isEmpty()) {
					for (int i = 1; i <= N; i++) {
						if(checkN[i][0]==-1) {//Ni에 누가 없으면 들어가기
							checkN[i][0]=qu1.poll();
							continue B;
						}
					}
					break;
				}
				//5. t에서 나올 애 있나 확인
				while(index<=K) {
					if(t[index]==time) qu1.add(index++);
					else break;
				}
				
				//2. qu2기다리는 애 있나 확인
A:				while(!qu2.isEmpty()) {
					for (int j = 1; j <= M; j++) {
						if(checkM[j][0]==-1) {//Mj에 누가 없으면 들어가기
							Customer cu = qu2.poll();
							checkM[j][0]= cu.personNum;
							continue A;
						}
					}
					break;
				}
				
				//4. qu1에 기다리는 애 있나 확인
B:				while(!qu1.isEmpty()) {
					for (int i = 1; i <= N; i++) {
						if(checkN[i][0]==-1) {//Ni에 누가 없으면 들어가기
							checkN[i][0]=qu1.poll();
							continue B;
						}
					}
					break;
				}
				time++;
			}
			
			if(ans==0) System.out.println("#"+tc+" -1");
			else System.out.println("#"+tc+" "+ans);
		}//end tc..
	}

}
