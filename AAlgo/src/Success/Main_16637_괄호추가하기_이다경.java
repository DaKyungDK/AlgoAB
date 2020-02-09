package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main_16637_괄호추가하기_이다경 {
	static int N,max;
	static int[] open, close;
	static boolean[] openb, closeb;
	static ArrayList<Integer> openarrl = new ArrayList<Integer>();
	static ArrayList<Integer> closearrl = new ArrayList<Integer>();
	static char[] charr;
	static Stack<Character> stack = new Stack();
	static Stack<Integer> stackInt = new Stack<Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		charr = new char[N*2+1];
		Arrays.fill(charr, 'a');
		open= new int[N/2];
		close= new int[N/2];
		openb= new boolean[N/2];
		closeb= new boolean[N/2];
		max = Integer.MIN_VALUE;
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
		if(N==1) max = charr[1]-'0';
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
			openb[totcnt] = false;
		}
	}

	private static void combiclose(int n, int selectcnt, int totcnt) {
		if(n==selectcnt) {
			openarrl.clear();
			closearrl.clear();
			for (int i = 0; i < N/2; i++) {
				if(openb[i]) {
					openarrl.add(open[i]);
				}
				if(closeb[i]) {
					closearrl.add(close[i]);
				}
			}
			for (int i = 0; i < n; i++) {
				if(openarrl.get(i)+4!=closearrl.get(i)) return;
				if(i<n-1) {
					if(closearrl.get(i)>=openarrl.get(i+1)) return;
				}
			}
			for (int i = 0; i < n; i++) {
				charr[openarrl.get(i)-1]='(';
				charr[closearrl.get(i)+1]=')';
			}
			clac();
			for (int i = 0; i < n; i++) {
				charr[openarrl.get(i)-1]='a';
				charr[closearrl.get(i)+1]='a';
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
			closeb[totcnt] = false;
		}
	}

	private static void clac() {
		int result=0,a=-1,b=-1,r=0;
		char op=' ';
		boolean avalue=false;
		
		for (int i = 0; i <= N*2; i++) {
			if(charr[i]=='a') continue;
			switch(charr[i]) {
			case '+':
				op='+';
				break;
			case '-':
				op='-';
				break;
			case '*':
				op='*';
				break;
			case '(':
				if(avalue) {
					stackInt.add(a);
					stack.add(op);
					avalue=false;
				}
				break;
			case ')':
				if(stack.isEmpty()) continue;
				b=a;
				op=stack.pop();
				a=stackInt.pop();
				r=0;
				switch(op) {
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
				a=r; b=-1; op=' ';
				break;
			default : //숫자
				if(!avalue) {
					a=charr[i]-'0';
					avalue=true;
				}
				else {
					b=charr[i]-'0';
					r=0;
					switch(op) {
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
					a=r; b=-1; op=' ';
				}
				break;
			}
		}
		result=r;
		if(result>max) max=result;
	}

}
