package oulatolal;

import commons.Movie;

public class LinkedList extends StrutturaDati{
	
	
	LinkedList(){
		
		this.next=null;
	}
	
	Node next;
	Movie movie;
	
	
	public Node head=null;
	public Node tail=null;
	
	Node insertMovie(Node t, Movie m) {
		
		if(t==null) {
			t=new Node();
			t.movie=m;
			//controllo se la lista è vuota
			if(head==null) head=t;
		}
		else {
			//voglio mettere in ordine alfabetico i film in base al titolo
			//in modo da avere una lista orinata
			int cmp=t.compareTo(m);
			//se l'oggetto in questione è più piccolo di quello con cui viene confrontato
			if(cmp<1)
				t.children[0]=insertMovie(t.children[0],m);
			else
				t.children[1]=insertMovie(t.children[1],m);
		}
		return t;
	}
	
	public Node search(Node t, Movie target) {
		int cmp=0;
		if(t==null) return t;
		if(t.movie.getTitle()==target.getTitle()) return t;
		else {
			cmp=t.compareTo(target);
			if(cmp<1) return search(t.children[0],target);
			else return search(t.children[1], target);
		}
	}
	
	//funzione per eliminare un nodo 
	public Node removeMovie(Node t, Movie m) {
		int cmp=0;
		if (head==null) return null;
		
		//scendo tutta la lista in cerca del nodo giusto
		cmp=t.compareTo(m);
		if(cmp<1)
			t.children[0]=removeMovie(t.children[0],m);
		else
			t.children[1]=removeMovie(t.children[1],m);
		return t;
	}
	
	//funzione per riordinare la lista
	void inorderMovie(Node t) {
		if (t==null) return;
		for (int i=0; i<t.order_M/2; i=i+1) {
			inorderMovie(t.children[i]);
		}
			System.out.println(t.movie.getTitle());
			//System.out.println(t.movie.getYear());
			//System.out.println(t.movie.getVotes());	
			//for (int i=0; i<t.movie.getCast().length; i=i+1) {
			//	System.out.println(t.movie.getCast()[i].getName());
			//}
			//System.out.println(t.movie.getDirector().getName());	
		
		for (int i=t.order_M/2; i<t.order_M; i=i+1) {
			inorderMovie(t.children[i]);
		}
	}
	
	//funzione per inserire i film in un array
	public void getMoviesInArray(Node t, Movie[] array, int i) {
		if(t==null) return;
		else {
			array[i]=t.movie;
			getMoviesInArray(t.children[0],array, whichIndex(array));
			getMoviesInArray(t.children[1],array, whichIndex(array));
		}
	}
	
	public int whichIndex(Movie[]array) {
		int ind=0;
		for(int i=0;i<array.length;i++) {
			ind=i;
		}
		return ind;
	}
	
	public void clearMovies() {
		head=null;
	}

	@Override
	public void insert(Movie movie) {

		insertMovie(this.head,movie);
	}

	@Override
	public Movie searchMovie(Movie movie) {
	
		Node n=search(this.head, movie);
		if(n!=null)return n.movie;
		return null;
	}

	@Override
	public void delete(Movie movie) {
		
		removeMovie(this.head, movie);
	}

	@Override
	public Movie[] getMovies() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void clear() {
		clearMovies();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
