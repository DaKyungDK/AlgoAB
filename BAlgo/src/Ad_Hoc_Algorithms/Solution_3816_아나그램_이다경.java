package Ad_Hoc_Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3816_아나그램_이다경 {
	static String str;
	static char[] s1, s2;
	static int[] alphaarr1,alphaarr2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int ans =0;
			
			str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			s1 = st.nextToken().toCharArray();
			s2 = st.nextToken().toCharArray();
			
			alphaarr1 = new int[27];
			alphaarr2 = new int[27];
			int alphacnt1 = 0;
			int len1 = s1.length;
			int len2 = s2.length;
			
			for (int i = 0; i < len1; i++) {
				if(alphaarr1[s1[i]-'a']==0) {
					alphacnt1++;
				}
				alphaarr1[s1[i]-'a']++;
			}
			
			int alphacnt2 = 0;
			
			//처음 1번은 해줘야함
			for (int i = 0; i < len1; i++) {
				alphaarr2[s2[i]-'a']++;
				if(alphaarr2[s2[i]-'a']==alphaarr1[s2[i]-'a']) {
					alphacnt2++;
				}
			}
			if(alphacnt1==alphacnt2) ans++;
			
			
			for (int j = 1; j < len2-len1+1; j++) {
				alphaarr2[s2[j-1]-'a']--;
				if(alphaarr2[s2[j-1]-'a']+1==alphaarr1[s2[j-1]-'a']) {
					alphacnt2--;
				}
				
				alphaarr2[s2[j+len1-1]-'a']++;
				if(alphaarr2[s2[j+len1-1]-'a']==alphaarr1[s2[j+len1-1]-'a']) {
					alphacnt2++;
				}
				
				if(alphacnt1==alphacnt2) ans++;
			}
			
			System.out.println("#"+tc+" "+ ans);
		}//end tc..
	}

}
