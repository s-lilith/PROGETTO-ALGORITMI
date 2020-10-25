package oulatolal;

import commons.Movie;

public class Node implements Comparable {
		int order_M=2;
		Node[] children = new Node [order_M];
		Movie movie;
		public Node next; 

		Node()
		{
		
			for (int i=0; i<order_M; i=i+1)
			{
				children[i] = null;
			}
			
		}

		//salvo i film in maniera che ordino i film 
		//in maniera alfabetica, usando dei compare
		//che ordinano in modo, appunto alfabetico le stringhe
		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			Movie tmp = (Movie)arg0;
			return this.movie.getTitle().compareTo(tmp.getTitle());
		}
		
	

}
