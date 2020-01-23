
public class MergeSort {
	private static int[] mergeSort(int[] arr) {//내림차순
		if(arr.length<2) {//하나인경우
			return arr;
		}
		else {
			int m=arr.length/2;
			int[] low_arr = new int[m];
			int[] high_arr = new int[arr.length-m];
			
			for (int i = 0; i < m; i++) {
				low_arr[i] = arr[i];
			}
			for (int i = m; i < arr.length; i++) {
				high_arr[i-m] = arr[i];
			}
			
			low_arr=mergeSort(low_arr);
			high_arr=mergeSort(high_arr);
			
			int[] mergedArr = new int[arr.length];
			int v=0, l=0, h=0;
			
			while(l<low_arr.length && h<high_arr.length) {
				if(low_arr[l] > high_arr[h]) {
					mergedArr[v++] = low_arr[l++];
				} else {
					mergedArr[v++] = high_arr[h++];
				}
			}
			while(l<low_arr.length) {
				mergedArr[v++] = low_arr[l++];
			}
			while(h<high_arr.length) {
				mergedArr[v++] = high_arr[h++];
			}
			
			return mergedArr; 
		}
	}
}
