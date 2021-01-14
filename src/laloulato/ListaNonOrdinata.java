package laloulato;

import java.util.ArrayList;

import commons.Movie;

public class ListaNonOrdinata extends StrutturaDati{
	public NodeLinkedList head, tail;

	/*public ListaNonOrdinata() {

		makeEmpty();
	}*/

	public void makeEmpty() {

		head=tail=new NodeLinkedList();
	}

	public boolean isEmpty() {

		return(head==tail);
	}

	public void insertMovie(Movie m) {

		NodeLinkedList new_node = new NodeLinkedList(m);

		/* 3. Make next of new Node as head */
		new_node.next = head;

		/* 4. Move the head to point to new Node */
		head = new_node;
	}


	public void getMovies_array(NodeLinkedList n, Movie[] array, int index) {

		if (n!=null) {
			array[index]=n.movie;
			getMovies_array(n.getNext(), array, checkIndex(array));
			n=n.next;
		}
		if (n==null) return;

	}
	public int checkIndex(Movie[] array){
		int ind=0;
		for (int i=0; i< array.length; i++){
			if (array[i]==null)
				ind=i;
		}
		return ind;
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

	public void deleteMovie(NodeLinkedList node, Movie m) throws EmptyLinkedListException {

		//controllo se il nodo da eliminare è in testa
		if(node.getMovie().getTitle().equals(m.getTitle())){
			if (node.next== null){
				System.out.println("List cannot be empty");
				return;
			}
			//il nodo successivo viene copiato in head
			node.movie= node.next.movie;
			m=node.next.movie;
			node.next=node.next.next; //rimuovo collegamento al nodo succ

			System.gc();
			return;
		}

		//se il nodo non è in testa
		NodeLinkedList prev= node;

		while (prev.next.movie!= null && prev.next.movie!= m){
			prev= prev.next;
		}

		//controllo se il nodo è presente nella lista
		if (prev.next.movie== null){
			System.out.println("Movie non present");
			return;
		}

		//rimuovo nodo
		prev.next=prev.next.next;

		System.gc();
		return;
	}

	public NodeLinkedList search(NodeLinkedList n, Movie m){
		/*if(isEmpty()) {
			return null;
		}
		else {
			if (n.getMovie().getTitle().equals(m.getTitle()))
				//(n.getMovie().getTitle().equals(m.getTitle()))
				return n;
			else return search(n.next, m);
		}*/


		//salvo nodo head
		NodeLinkedList tmp= head;
		int i=0;

		//scorro la lista
		while (!tmp.movie.getTitle().equals(m.getTitle()) && tmp.next!= null){
			i++;
			tmp=tmp.next; //aggiorno
		}
		//se m non c'è
		if (!tmp.movie.getTitle().equals(m.getTitle())) return null;
		return search(tmp.next, m);


	}




	@Override
	public void insert(Movie movie) {
		//  Auto-generated method stub
		insertMovie(movie);
	}

	@Override
	public Movie searchMovie(Movie movie) {
		//  Auto-generated method stub
		NodeLinkedList node=search(head, movie);
		if (node!= null)
			return node.movie;
		return null;
	}

	@Override
	public void delete(Movie movie) {
		//  Auto-generated method stub
		try {
			deleteMovie(this.head, movie);
		} catch (EmptyLinkedListException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public Movie[] getMovies() {
		// TODO Auto-generated method stub
		Movie[] result= new Movie[CountNodes(this.head)];
		getMovies_array(this.head, result, checkIndex(result));
		return result;
	}

	@Override
	public void clear() {
		//  Auto-generated method stub
		Movie[] result= getMovies();
		for (Movie movie : result) {
			delete(movie);
		}
	}











  /*private NodeLinkedList head, tail;

  public ListaNonOrdinata() {

  	makeEmpty();
  }

  public void makeEmpty() {

  	head=tail=new NodeLinkedList();
  }

  public boolean isEmpty() {

  	return(head==tail);
  }

  //inserisco film all'inizio dello lista se questa ? vuota
  //altrimenti lo inserisco come nodo successivo
  public void insertMovie(Movie m) {

	  NodeLinkedList n = new NodeLinkedList();
	  n.movie = m;
	  n.next = null;

	  if (head == null) {
		  head = n;
		  tail = n;
		  head.prev = null;
		  return;
	  }
	  if (n.compareTo(head.movie) < 1) {
		  n.prev = null;
		  head.prev = n;
		  n.next = head;
		  head = n;
		  return;
	  }

	  if (n.compareTo(tail.movie) > 1) {
		  n.prev = tail;
		  tail.next = n;
		  tail = n;
		  return;
	  }
	  NodeLinkedList tmp = head.next;
	  while ((tmp.movie.compareTo(n.movie)< 1)){
		  tmp = tmp.next;
		  n.prev = tmp.prev;
		  tmp.prev = n;
		  n.next = tmp;
	  }


  }
  public void insert_Movie(Movie m) {
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

	 /* if (n!=null){
		array[index]=n.movie;
		getMovies_array(n.next, array, checkIndex(array));
	  //}
	  if(n!= null){
	  	array[index]=n.movie;
	  	getMovies_array(n, array, checkIndex(array));
	  	n=n.next;
		} else return;

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
		ArrayList<Movie> res = new ArrayList<>();
		Movie[] result = null;
		NodeLinkedList n = this.head;
		while (n!=null) {
			res.add(n.movie);
			n.setNext(n);
		}
		result = new Movie[res.size()];
		for (int i=0; i<res.size(); i=i+1) {
			result[i] = res.get(i);
		}
		return result;


	}

	@Override
	public void clear() {
		//  Auto-generated method stub
		head=null;

	}

	*/

}
