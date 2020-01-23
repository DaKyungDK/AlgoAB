//N개중에 num개 고르기. i:현재 몇개 골랐는지 개수
public class Combination {
	static int N=10;
	private static void combi(int num,int i) {
		if(i==num) {
			
		}
		if(N-i>N/2-num) {
			combi(num,i+1);
		}
		if(N/2>num) {
			combi(num+1,i+1);
		}
	}
}
