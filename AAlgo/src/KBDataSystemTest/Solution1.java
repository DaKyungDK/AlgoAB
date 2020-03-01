package KBDataSystemTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		int N = Integer.parseInt(input);
		input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		int min=Integer.MAX_VALUE;
		int[] nums = new int[N];
		nums[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if(nums[i]-nums[i-1]<min) min = nums[i]-nums[i-1];
		}
		System.out.println(min);
	}

}
