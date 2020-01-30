package NaverAIHackerton;

public class Solution1 {

	public static void main(String[] args) {
		int[] A = {0, 1, 1, 0 };
		int cnt1 = 0;
		int cnt2 = 0;
		int state =0;
		for (int i = 0; i < A.length; i++) {
			if(A[i]!=state) cnt1++;
			if(state==0) state=1;
			else state=0;
		}
		state=1;
		for (int i = 0; i < A.length; i++) {
			if(A[i]!=state) cnt2++;
			if(state==0) state=1;
			else state=0;
		}
		
		System.out.println(Math.min(cnt1, cnt2));
	}

}
