package 증명의중요성;

import java.util.Arrays;

public class QuickSortTest {

	public static void quickSort(int[] list,int begin,int end) {
		if(begin<end) { // 吏묓빀�쓽 �겕湲곌� 2�씠�긽
			int p = fixPivot(list,begin,end);
			quickSort(list, begin, p-1);
			quickSort(list, p+1, end);
		}
	}
	private static int fixPivot(int[] list, int begin, int end) {
		int pivot,left,right,temp;
		left = begin+1;
		right = end;
		pivot = begin;
	
		do {
			while (left < end && list[left] < list[pivot]) left++;
			while (right > begin && list[right] >= list[pivot]) right--;
			if (left < right) {
				temp = list[left];
				list[left] = list[right];
				list[right] = temp;
			} 
		} while (left<right);
		
		temp = list[pivot];
		list[pivot] = list[right];
		list[right] = temp;
		
		return right;
	}

	public static void main(String[] args) {
		int[] list = {69,10,30,2,16,8,31,22,10,16};
//		int[] list = {1,2,3,4,5};
//		int[] list = {5,4,3,2,1};
		System.out.println(Arrays.toString(list));
		quickSort(list, 0, list.length-1);
		System.out.println(Arrays.toString(list));
		
	}

}















