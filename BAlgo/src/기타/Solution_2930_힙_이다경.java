package 기타;
//나누기 대신 shift 쓰면 속도 훨씬 향상!
import java.util.Scanner;

public class Solution_2930_힙_이다경 {
	static long N, cal, newNode;
	static int cnt;
	static long[] arr= new long[100009];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextLong();
			
			for (int i = 1; i <= N; i++) {
				arr[i] = -1;
			}
			System.out.print("#"+tc);
			cnt=0;
			for (int i = 0; i < N; i++) {
				cal = sc.nextInt();
				if(cal==1) {
					newNode = sc.nextInt();
					push(newNode);
				}else {
					System.out.print(" "+poll());
				}
			}
			
			System.out.println();
		}//end tc..
	}

	private static long poll() {
		if(cnt==0) return -1;
		long ans = arr[1];
		arr[1]=arr[cnt];
		arr[cnt--]=-1;
		int cur = 1;
		while(cur*2<cnt) {
			if(cur*2==cnt) {
				if(arr[cur*2] > arr[cur]) {
					long temp = arr[cur];
					arr[cur] = arr[cur*2];
					arr[cur*2] = temp;
					cur*=2;
				}else {
					break;
				}
			}else {
				if(arr[cur*2] > arr[cur*2+1]) {
					if(arr[cur*2] > arr[cur]) {
						long temp = arr[cur];
						arr[cur] = arr[cur*2];
						arr[cur*2] = temp;
						cur*=2;
					}else {
						break;
					}
				}else {
					if(arr[cur*2+1] > arr[cur]) {
						long temp = arr[cur];
						arr[cur] = arr[cur*2+1];
						arr[cur*2+1] = temp;
						cur=cur*2+1;
					}else {
						break;
					}
				}
			}
			
		}
		return ans;
	}

	private static void push(long newNode) {
		arr[++cnt] = newNode;
		int cur = cnt;
		while(cur/2!=0) {
			if(arr[cur] > arr[cur/2]) {
				//swap
				long temp = arr[cur];
				arr[cur] = arr[cur/2];
				arr[cur/2] = temp;
				cur/=2;
			}else {
				break;
			}
		}
	}

}
