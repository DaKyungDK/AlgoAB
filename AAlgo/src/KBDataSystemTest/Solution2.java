package KBDataSystemTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		int min = 0;
		int square =0;
		for (int i = (int)Math.sqrt(M); i <=100 ; i++) {
			square = i*i;
			if(square>N) break;
			if(min==0 && square>=M) min = i*i;
			if(square>=M && square<=N) sum+=square;
		}
		System.out.println(min +" "+ sum);
	}

}
