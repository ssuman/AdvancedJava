package com.rit.algos;

public class MergeSortWithoutSentinels {

	public static void main(String[] args) {

		int arr[] = { 3,41,52,26,38,9,57,49 };
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		
		System.out.println("");
		
		sort(arr);
		
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
	}

	private static void sort(int[] arr) {

		mergeSort(arr, 0, arr.length-1);
	}

	private static void merge(int[] arr, int low,int mid, int high) {
		
		
		int leftNbr = mid - low +1;
		int rightNbr = high - mid ;
		
		int left [] = new int [leftNbr];
		int right [] = new int [rightNbr];
		
		for(int i=low,j=0;i<=mid;i++,j++){
			left[j]=arr[i];
		}
		
		for(int i=mid+1,j=0;i<=high;i++,j++){
			right[j]=arr[i];
		}
		
		int i=0;
		int j=0;
		
		
		
		for(int iter=low;iter<=high;iter++){
			
			if(i == leftNbr){
				arr[iter]=right[j];
				j++;
			}
			else if(j==rightNbr){
				arr[iter]=left[i];
				i++;
			}
			
			else if(left[i]<=right[j]){
				arr[iter]=left[i];
				i++;
			}
			else {
				arr[iter]=right[j];
				j++;
			}
		}

	}

	private static void mergeSort(int[] arr, int p, int q) {

		if (p < q) {
			int mid =  (int)Math.floor((q + p )/ 2);
			mergeSort(arr,p,mid);
			mergeSort(arr,mid+1,q);
			merge(arr,p,mid,q);
		}
	}
	
	

}
