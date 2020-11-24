package oulatolal;

import commons.Movie;

public class LinkedList extends StrutturaDati{

  private NodeLinkedList head, tail;
  
  public LinkedList() {
	  makeEmpty();
  }
  
  public void makeEmpty() {
	  head=tail=new NodeLinkedList();
  }
  
  public boolean isEmpty() {
	  return(head==tail);
  }
  
  //inserisco film all'inizio dello lista se questa è vuota
  //altrimenti lo inserisco come nodo successivo
  public void insertMovie(Movie m) {
	  if(isEmpty()) {
	  //inserisco film nell'header
	  head.setMovie(m);
	  //Nodo con due riferimenti null
	  NodeLinkedList newNode= new NodeLinkedList();
	  //collego nodo a header
	  newNode.setNext(head);
	  head=newNode;
	  }
	  else {
		  tail.setNext(new NodeLinkedList(m,null));
		  tail=tail.getNext();
	  }
  }
  
  //elimina partendo dall'ultimo nodo
  public Movie deleteMovie() throws EmptyLinkedListException {
	  Movie m=getLastFilm();
	  //cerco fino ad arrivare al penultimo nodo
	  NodeLinkedList temp=head;
	  while (temp.getNext()!=tail)
		  temp=temp.getNext();
	  tail=temp;
	  tail.setNext(null);
	  return m;
  }
  
  public Movie deleteFirstMovie() throws EmptyLinkedListException {
	  Movie m= getFirstFilm();
	  
	  head=head.getNext();
	  head.setMovie(null);
	  return m;
  }
  
  public Movie getFirstFilm() throws EmptyLinkedListException {
	  if(isEmpty())
		  throw new EmptyLinkedListException();
	  return head.getNext().getMovie();
  }
  
  public Movie getLastFilm() throws EmptyLinkedListException {
	  if(isEmpty())
		  throw new EmptyLinkedListException();
	  return tail.getMovie();
  }
  
  //search
  public Movie search(NodeLinkedList n, Movie m) {
	  if(isEmpty()) {
		  return null;
	  }
	  //se non è presente ciò che cerco
		
		  if(n.getMovie().getTitle()!=m.getTitle()) { throw new Exception("Invalid Movie."); }
		 
		  for(Movie movie:m) {
			  if(movie.getTitle().equals(n)) {
				  return movie;
			  }
		  }
		  return null;
		  
		  /*
	  NodeLinkedList current=head;
		 * while(current!=null) { if(n.getMovie().getTitle()==m.getTitle())
		 * current=m.getTitle(); return current.getMovie(); current=current.getNext(); }
		 * return null;
		 */
  }
  
  //da lista ad array
  
  
  
	
	@Override
	public void insert(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movie searchMovie(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movie[] getMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	
		
}
