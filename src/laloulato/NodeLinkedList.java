package laloulato;

import commons.Movie;

public class NodeLinkedList implements Comparable{

	/*
	 * Node head;
	 * 
	 * class Node{ int data; Node next;
	 * 
	 * Node(int d){ this.data=d; this.next=null; } }
	 */
	
	public Movie movie;
	public NodeLinkedList next;
	
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
