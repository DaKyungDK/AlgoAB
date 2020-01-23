package 증명의중요성;
//Merge Sort
import java.util.Scanner;

public class Solution_3820_롤러코스터_이다경 {
	static int N;
	static double a,b;
	static double[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr=new double[N][3];
			
			a=0; b=0;
			
			for (int i = 0; i < N; i++) {
				a = sc.nextDouble();
				b = sc.nextDouble();
				arr[i][0]=a;
				arr[i][1]=b;
				
				arr[i][2] = (a-1)/b;
			}
			arr = mergeSort(arr);
			
			long v=1;
			for (int i = 0; i < N; i++) {
				v=v*(long)arr[i][0]+(long)arr[i][1];
				v%=1000000007;
			}
			v%=1000000007;
			
			System.out.println("#"+tc+" "+v);
		}//end tc..
	}

	
	
	private static double[][] mergeSort(double[][] arr) {
		if(arr.length<2) {//하나인경우
			return arr;
		}
		else {
			int m=arr.length/2;
			double[][] low_arr = new double[m][3];
			double[][] high_arr = new double[arr.length-m][3];
			
			for (int i = 0; i < m; i++) {
				low_arr[i][0] = arr[i][0];
				low_arr[i][1] = arr[i][1];
				low_arr[i][2] = arr[i][2];
			}
			for (int i = m; i < arr.length; i++) {
				high_arr[i-m][0] = arr[i][0];
				high_arr[i-m][1] = arr[i][1];
				high_arr[i-m][2] = arr[i][2];
			}
			
			low_arr=mergeSort(low_arr);
			high_arr=mergeSort(high_arr);
			
			double[][] mergedArr = new double[arr.length][3];
			int v=0, l=0, h=0;
			
			while(l<low_arr.length && h<high_arr.length) {
				if(low_arr[l][2] > high_arr[h][2]) {
					mergedArr[v][0] = low_arr[l][0];
					mergedArr[v][1] = low_arr[l][1];
					mergedArr[v++][2] = low_arr[l++][2];
				} else {
					mergedArr[v][0] = high_arr[h][0];
					mergedArr[v][1] = high_arr[h][1];
					mergedArr[v++][2] = high_arr[h++][2];
				}
			}
			while(l<low_arr.length) {
				mergedArr[v][0] = low_arr[l][0];
				mergedArr[v][1] = low_arr[l][1];
				mergedArr[v++][2] = low_arr[l++][2];
			}
			while(h<high_arr.length) {
				mergedArr[v][0] = high_arr[h][0];
				mergedArr[v][1] = high_arr[h][1];
				mergedArr[v++][2] = high_arr[h++][2];
			}
			
			return mergedArr; 
		}
	}

}
