package laloulato;

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
  public Movie search(NodeLinkedList n, Movie m){
	  if(isEmpty()) {
		  return null;
	  }
		
		/*
		 * if(n.getMovie().getTitle()!=m.getTitle()) { throw new
		 * Exception("Invalid Movie."); } else {
  }
		 */
			  while(n.movie.getTitle()!=m.getTitle()) {
				 int cmp=n.compareTo(n.movie);
				 if(cmp!=0) {
					 System.out.println("Invalid Movie.");
				 }
				 else 
					 return n.movie;
			  }
			return m;

  }
  
  //inserimento film in array
  public void getMovies_array(NodeLinkedList n, Movie[] array) {
	  
  }
  
  
	
	@Override
	public void insert(Movie movie) {
		// TODO Auto-generated method stub
		insertMovie(movie);
	}

	@Override
	public void delete(Movie movie) {
		// TODO Auto-generated method stub
		try {
			deleteMovie();
		} catch (EmptyLinkedListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Movie searchMovie(Movie movie){
		// TODO Auto-generated method stub
		
		return null;
		
	}

	@Override
	public Movie[] getMovies() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		//ciclo in cui prendo i film da get movie poi li elimino uno a uno
		
	}
	
	
		
}
