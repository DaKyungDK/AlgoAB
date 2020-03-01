package Success;

import java.util.Scanner;

public class Main_1022_소용돌이예쁘게출력하기_이다경 {
	static int r1,c1,r2,c2;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r1 = sc.nextInt();
		c1 = sc.nextInt();
		r2 = sc.nextInt();
		c2 = sc.nextInt();
		
		int r = Math.max(Math.abs(r1), Math.abs(r2));
		int c = Math.max(Math.abs(c1), Math.abs(c2));
		int m = Math.max(r, c);

		int[][] map = new int[r2-r1+1][c2-c1+1];
		//소용돌이 만들기
		int curR=r, curC=c;
		int num=1;
		int cnt=1;
		int d=0;//오위왼아
		int howmany=0;
		int max=1;
		if(curR>=r1+r && curR<=r2+r && curC>=c1+c && curC<=c2+c) {
			map[curR-r1-r][curC-c1-c] = num;
		}
		num++;

		while(curR<=m*2 && curC<=m*2) {
			if(howmany==2) {
				cnt++;
				howmany=0;
			}
			switch (d) {
			case 0:
				for (int i = 0; i < cnt; i++) {
					if(curR==m*2 && curC==m*2) {
						curC++;
						break;
					}
					curC++;
					if(curR>=r1+r && curR<=r2+r && curC>=c1+c && curC<=c2+c) {
						map[curR-r1-r][curC-c1-c] = num;
						if(max<num) max = num;
					}
					num++;
				}
				howmany++;
				break;
			case 1:
				for (int i = 0; i < cnt; i++) {
					curR--;
					if(curR>=r1+r && curR<=r2+r && curC>=c1+c && curC<=c2+c) {
						map[curR-r1-r][curC-c1-c] = num;
						if(max<num) max = num;
					}
					num++;
				}
				howmany++;
				break;
			case 2:
				for (int i = 0; i < cnt; i++) {
					curC--;
					if(curR>=r1+r && curR<=r2+r && curC>=c1+c && curC<=c2+c) {
						map[curR-r1-r][curC-c1-c] = num;
						if(max<num) max = num;
					}
					num++;
				}
				howmany++;
				break;
			case 3:
				for (int i = 0; i < cnt; i++) {
					curR++;
					if(curR>=r1+r && curR<=r2+r && curC>=c1+c && curC<=c2+c) {
						map[curR-r1-r][curC-c1-c] = num;
						if(max<num) max = num;
					}
					num++;
				}
				howmany++;
				break;
			}
			d=(d+1)%4;
		}//end while..소용돌이 완성
		
		if(max>=100000000) cnt=9;
		else if(max>=10000000) cnt=8;
		else if(max>=1000000) cnt=7;
		else if(max>=100000) cnt=6;
		else if(max>=10000) cnt=5;
		else if(max>=1000) cnt=4;
		else if(max>=100) cnt=3;
		else if(max>=10) cnt=2;
		else cnt=1;
		
		int cnt1=0;
		for (int i = 0; i <= r2-r1; i++) {
			for (int j = 0; j <= c2-c1; j++) {
				if(map[i][j]>=100000000) cnt1=9;
				else if(map[i][j]>=10000000) cnt1=8;
				else if(map[i][j]>=1000000) cnt1=7;
				else if(map[i][j]>=100000) cnt1=6;
				else if(map[i][j]>=10000) cnt1=5;
				else if(map[i][j]>=1000) cnt1=4;
				else if(map[i][j]>=100) cnt1=3;
				else if(map[i][j]>=10) cnt1=2;
				else cnt1=1;
				for (int k = 0; k < cnt-cnt1; k++) {
					System.out.print(" ");
				}
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
