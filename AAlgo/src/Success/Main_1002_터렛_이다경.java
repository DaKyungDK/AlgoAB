package Success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1002_≈Õ∑ø_¿Ã¥Ÿ∞Ê {
	static double x1,y1,r1,x2,y2,r2,d,r;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x1 = Double.parseDouble(st.nextToken());
			y1 = Double.parseDouble(st.nextToken());
			r1 = Double.parseDouble(st.nextToken());
			x2 = Double.parseDouble(st.nextToken());
			y2 = Double.parseDouble(st.nextToken());
			r2 = Double.parseDouble(st.nextToken());
			
			d = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
			r = r1+r2;
			if(r1==r2 && x1==x2 && y1==y2) {
				ans=-1;
			}else if(r==d) {
				ans=1;
			}else if(r1>d || r2>d) {
				if(d+r1==r2 || d+r2==r1) {
					ans=1;
				}else if(d+r1<r2 || d+r2<r1) {
					ans=0;
				}else {
					ans=2;
				}
			}else if(r>d) {
				ans=2;
			}else {
				ans=0;
			}
			
			System.out.println(ans);
		}//end tc..
	}
}
