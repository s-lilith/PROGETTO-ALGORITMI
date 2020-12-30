package laloulato;

import commons.Movie;

public class BubbleSort extends Sorting{
	BubbleSort(){
		
	}
	void bubblesort(Movie[] movieArray) { 
        Movie tmp = null;
         for(int i=0; i < movieArray.length; i++){  
                 for(int j=1; j < (movieArray.length-i); j++){  
                          if(movieArray[j-1].compareTo(movieArray[j])>0){  
                                 //scambio degli elementi
                                 tmp = movieArray[j-1];  
                                 movieArray[j-1] = movieArray[j];  
                                 movieArray[j] = tmp;  
                         }  
                 }  
         }
	}
	@Override
	public void sort(Movie[] movieArray) {
		// TODO Auto-generated method stub
		bubblesort(movieArray);
	}
	@Override
	public void sort(Movie[] movieArray, int n) {
		// TODO Auto-generated method stub
		
	}

	
}
