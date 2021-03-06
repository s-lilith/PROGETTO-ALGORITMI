package laloulato;

import commons.Movie;

public class NodeLinkedList implements Comparable<Object>{

	
	public Movie movie;
	public NodeLinkedList next;
	public NodeLinkedList (Movie m) {
		this.movie = m;
		next = null;
	}
	
	public NodeLinkedList(Movie m, NodeLinkedList n){
		this.movie=m;
		this.next=n;
	}
	
	public NodeLinkedList() {

		this(null,null);
	}
	
	public Movie getMovie() {

		return movie;
	}
	
	public NodeLinkedList getNext() {

		return next;
	}
	
	public void setMovie(Movie m) {

		this.movie=m;
	}
	
	public void setNext(NodeLinkedList n) {

		this.next=n;
	}
	
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Movie tmp = (Movie)arg0;
		return this.movie.getTitle().compareTo(tmp.getTitle());
	}
}
