package laloulato;

import commons.Movie;

public class ListaNonOrdinata extends StrutturaDati{

  private NodeLinkedList head, tail;

  public ListaNonOrdinata() {

  	makeEmpty();
  }
  
  public void makeEmpty() {

  	head=tail=new NodeLinkedList();
  }
  
  public boolean isEmpty() {

  	return(head==tail);
  }
  
  //inserisco film all'inizio dello lista se questa � vuota
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
  public void deleteMovie() throws EmptyLinkedListException {
	  Movie m=getLastFilm();
	  //cerco fino ad arrivare al penultimo nodo
	  NodeLinkedList temp=head;
	  while (temp.getNext()!=tail)
		  temp=temp.getNext();
	  tail=temp;
	  tail.setNext(null);
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
  public NodeLinkedList search(NodeLinkedList n, Movie m){
	  if(isEmpty()) {
		  return null;
	  }
	  else {
			if (n.getMovie().getTitle().equals(m.getTitle()))
				return n;
			else return search(n.next, m);
	  }
  }
  
  //inserimento film in array
  public void getMovies_array(NodeLinkedList n, Movie[] array, int index) {
	  //TODO
	  //controllo che la lista non sia vuota
	  //se non è vuota faccio in modo che l'array sia vuoto

	  if (n!=null){
		array[index]=n.movie;
		getMovies_array(n.next, array, checkIndex(array));
		/*for (int i=0;i< array.length; i++){
			n=n.next;
			checkIndex(array);*/
		}

	  }

  
  //numero nodi nella lista
	public int CountNodes(NodeLinkedList n){
		int i=0;
		if (n== null)
			return 0;
		else{

			NodeLinkedList curr= this.head;
			while (curr!= null){
				i++;
				curr=curr.next;
			}
			return i;
		}
	}

	public int checkIndex(Movie[] array){
  	int ind=0;
  	for (int i=0; i< array.length; i++){
  		if (array[i]==null)
  			ind=i;
	}
		return ind;
	}
	
	@Override
	public void insert(Movie movie) {
		//  Auto-generated method stub
		insertMovie(movie);
	}

	@Override
	public void delete(Movie movie) {
		//  Auto-generated method stub
		try {
			deleteMovie();
		} catch (EmptyLinkedListException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Movie searchMovie(Movie movie){
		//  Auto-generated method stub
		NodeLinkedList n=search(this.head, movie);
		if (n!= null)
			return n.movie;
		return null;
		
	}
	//questa funzione restituisce tutti i film presenti nella lista.
	//Crea un vettore che ha lunghezza pari al numero di nodi presente nella lista
	//chiama la funzioen getArray e restituisce il vettore con i film salvati

	@Override
	public Movie[] getMovies() {
		//  Auto-generated method stub
		Movie[] result= new Movie[CountNodes(this.head)];
		getMovies_array(this.head, result, checkIndex(result));
		return null;
	}

	@Override
	public void clear() {
		//  Auto-generated method stub
		head=null;
		
	}
	
	
		
}
