package fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main_16637_괄호추가하기_이다경 {
	static int N,max;
	static int[] open, close;
	static boolean[] openb, closeb;
	static char[] charr;
	static Stack<Character> stack = new Stack();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		charr = new char[N*2];
		Arrays.fill(charr, 'a');
		open= new int[N/2];
		close= new int[N/2];
		openb= new boolean[N/2];
		closeb= new boolean[N/2];
		
		int index = 1;
		for (int i = 0; i < N/2; i++) {
			open[i]=index;
			index+=4;
		}
		index = 5;
		for (int i = 0; i < N/2; i++) {
			close[i]=index;
			index+=4;
		}
		
		for (int i = 0; i < N; i++) {
			charr[i*2+1] = str.charAt(i);
		}
		
		
		for (int i = 0; i < N/2; i++) {
			combiopen(i,0,0);
		}
		System.out.println(max);
	}

	//(N/2)개 중에서 n개 뽑기.
	private static void combiopen(int n, int selectcnt, int totcnt) {
		if(n==selectcnt) {
			combiclose(n,0,0);
			return;
		}
		if(N/2-totcnt>n-selectcnt) {
			openb[totcnt] = false;
			combiopen(n,selectcnt, totcnt+1);
		}
		if(n>selectcnt) {
			openb[totcnt] = true;
			combiopen(n,selectcnt+1, totcnt+1);
		}
	}

	private static void combiclose(int n, int selectcnt, int totcnt) {
		if(n==selectcnt) {
			for (int i = 0; i < N/2; i++) {
				if(open[i]>=close[i]) return;
				if(i<N/2-1) {
					if(close[i]>=open[i+1]) return;
				}
			}
			for (int i = 0; i < N/2; i++) {
				charr[open[i]]='(';
				charr[close[i]]=')';
			}
			clac();
			for (int i = 0; i < N/2; i++) {
				charr[open[i]]='a';
				charr[close[i]]='a';
			}
			return;
		}
		if(N/2-totcnt>n-selectcnt) {
			closeb[totcnt] = false;
			combiclose(n,selectcnt, totcnt+1);
		}
		if(n>selectcnt) {
			closeb[totcnt] = true;
			combiclose(n,selectcnt+1, totcnt+1);
		}
	}

	private static void clac() {
		int result=0,a=-1,b=-1;
		for (int i = 0; i < N*2; i++) {
			if(charr[i]=='a') continue;
			switch(charr[i]) {
			case '+':
				stack.add('+');
				break;
			case '-':
				stack.add('-');
				break;
			case '*':
				stack.add('*');
				break;
			case '(':
				break;
			case ')':
				break;
			default : //숫자
				if(a<0) {
					a=charr[i]-'0';
				}
				else {
					b=charr[i]-'0';
					int r=0;
					switch(stack.pop()) {
					case '+':
						r = a+b;
						break;
					case '-':
						r = a-b;
						break;
					case '*':
						r = a*b;
						break;
					}
					a=r; b=-1;
				}
				break;	
			}
		}
		
		if(result>max) max=result;
	}

}
