//N���߿� num�� ����. i:���� � ������� ����
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
