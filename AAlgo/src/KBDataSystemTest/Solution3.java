package KBDataSystemTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		double H = Integer.parseInt(st.nextToken());//�칰 ����
		double U = Integer.parseInt(st.nextToken());//ù�� �ö󰡴� �Ÿ�
		double D = Integer.parseInt(st.nextToken());//�㿡 �������� �Ÿ�
		double F = Integer.parseInt(st.nextToken());//�Ƿε�
		double decreaseU = (double)((double)U*(double)F/100);
		
		boolean success = false; 
		double cur=0;//�ʱ����
		double up=0;//�ö󰣰Ÿ�
		double next=0;//���� ���� ����
		int dayCnt=0;//�칰�� ���������µ� �ɸ��� �ϼ�
		
		do {
			up=U-dayCnt*decreaseU;
			if(up<0) {
				cur-=D;
				dayCnt++;
				continue;
			}
			dayCnt++;
			next=cur+up;
			if(next>H) {
				success=true;
				break;
			}
			cur=next-D;
		} while(cur>0);
		
		if(success) {
			System.out.println("Success " + dayCnt);
		}else {
			System.out.println("Failure "+dayCnt);
		}
	}

}
