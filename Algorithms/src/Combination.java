//M���߿� m�� ����. i:���� � ������� ��ü ����, selectcnt:���� ���� ���� �� ����
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



