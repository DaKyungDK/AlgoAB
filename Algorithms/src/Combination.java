//M개중에 m개 고르기. i:현재 몇개 골랐는지 전체 개수, selectcnt:현재 내가 뽑은 애 개수
public class Combination {
	static int M=10;
	static boolean[] maxinfobool = new boolean[10];
	private static void combi1(int m, int selectcnt, int totcnt) {
		if(M==totcnt) {
			
			return;
		}
		if(M-totcnt>m-selectcnt) {
			maxinfobool[totcnt] = false;
			combi1(m,selectcnt,totcnt+1);
		}
		if(m>selectcnt) {
			maxinfobool[totcnt] = true;
			combi1(m,selectcnt+1,totcnt+1);
		}
	}
}



