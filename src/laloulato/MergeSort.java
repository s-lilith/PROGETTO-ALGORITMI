package laloulato;

import commons.Movie;

public class MergeSort extends Sorting{
	MergeSort(){
		//unisco due subarray di movieArray
		
	}
	void mergesort(Movie[] movieArray, int n) {
		if (n<2) {
			return;
		}
		//divido l'array
		int middle=n/2;
		Movie[] left= new Movie[middle];
		Movie[] right=new Movie[n-middle];
		
		for(int i=0;i<middle;i++) {
			left[i]=movieArray[i];
		}
		for(int i=middle;i<n;i++) {
			right[i-middle]=movieArray[i];
		}
		
		mergesort(left,middle);
		mergesort(right, n-middle);
		
		merge(movieArray, left, right, middle, n-middle);
	}
	
	public void merge(Movie[]movieArray, Movie[]left, Movie[]right, int l, int r) {
		int i=0, j=0, k=0;
		while(i<l && j<r) {
			if(left[i]==right[j]) {
				movieArray[k++]=left[i++];
			}
			else {
				movieArray[k++]=right[j++];
			}
		}
		while(i<l) {
			movieArray[k++]=left[i++];
		}
		while(j<r) {
			movieArray[k++]=right[j++];
		}
	}
	
	
	@Override
	public void sort(Movie[] movieArray, int n) {
		// TODO Auto-generated method stub
		mergesort(movieArray, n);
	}
	@Override
	public void sort(Movie[] movieArray) {
		// TODO Auto-generated method stub
		
	}
}
