package laloulato;

import commons.Movie;

public class ListaNonOrdinata extends StrutturaDati{
	public NodeLinkedList head, tail;

	public void makeEmpty() {

		head=tail=new NodeLinkedList();
	}

	public boolean isEmpty() {

		return(head==tail);
	}

	public void insertMovie(Movie m) {

		NodeLinkedList new_node = new NodeLinkedList(m);

		
		new_node.next = head;

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


}
