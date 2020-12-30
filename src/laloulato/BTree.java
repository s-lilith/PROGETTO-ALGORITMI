package laloulato;

import commons.Movie;
/**
 * Implementazione della Struttura Dati BTree
 */
public class BTree extends StrutturaDati{
		NodeBTree root;
		BTree()
		{
			root = null;
		}

		NodeBTree InsertMovie(NodeBTree t, Movie k) {
			int cmp = 0;
			if (t==null) {
				t = new NodeBTree();
				t.movie = k;
				if (isEmpty()) 
				root = t;	
			}
			else {
				//salvo i film in maniera che ordino i film 
				//in maniera alfabetica, usando dei compare
				//che ordinano in modo, appunto alfabetico le stringhe
				cmp = t.compareTo(k);
				if (cmp < 1) {
					t.children[0] = InsertMovie(t.children[0], k);
				}
				else {
					t.children[1] = InsertMovie(t.children[1], k);
				}
			}
			return t;
		}
		
		boolean isEmpty() { //controlla se il puntatore root e' null
			boolean check = false;
			if (root ==null)
				check = true;
			return check;
		}
		
		//cerca il film in base al nome
		public NodeBTree search(NodeBTree t, Movie movie) {
			int cmp = 0;
			if (t==null) return t;
			if (t.movie.getTitle()==movie.getTitle())
				return t;
			else {
				cmp = t.compareTo(movie);
				if (cmp < 1)
					return search(t.children[0], movie);
				else
					 return search(t.children[1], movie);
			}
		}
		//Cerca il film piu' "grande" nel ramo sinistro
		//INORDER PREDECESSOR
		NodeBTree searchBiggestMovie(NodeBTree t) {
			int cmp = 0;
			if (t==null) {
				return t;
			}
			else {
				cmp = t.compareTo(t.movie);
				if (cmp < 1) {
					if (t.children[0]!=null)
						return searchBiggestMovie(t.children[0]);
					else return t;
				}
				else
					return t;
			}
		}
		
		//funzione che cerca il film piu' "piccolo" nel ramo destro
		//INORDER SUCCESSOR
		NodeBTree searchSmallestMovie(NodeBTree t) {
			int cmp = 0;
			if (t==null) {
				return t;
			}
			else {
				cmp = t.compareTo(t.movie);
				if (cmp > 1) {
					if (t.children[1]!=null)
						return searchSmallestMovie(t.children[1]);
					else return t;
				}
				else
					return t;
			}
		}

		 
			public NodeBTree deleteMovie(NodeBTree t, Movie target) {
			int cmp = 0;
			if (t==null) return null;
			cmp = t.compareTo(target);
			if (cmp < 1) {
				t.children[0] = deleteMovie(t.children[0], target);
			}
			if(cmp > 1) {
				t.children[1] = deleteMovie(t.children[1], target);
			//il nodo da cancellare si trova nel ramo dei children
			}
			
			else {
				//in t e' salvato il nodo che voglio cancellare, che e' il risultato della funzione search
				//guardo poi i vari casi
				t = search(t, target);
				if (t!=null) {
					//se t e' un nodo foglia
					if ((t.children[0]==null)&& (t.children[1]==null)){
						t = null;
					
						//se t ha entrambi i figli
					} else if ((t.children[0]!=null) && (t.children[1]!=null)) {
						System.out.println("ho entrambi i figli");
						NodeBTree tmp = t;
						//decido arbitrariamente che scelgo Inorder predecessor (max, from "left subtree")
						NodeBTree pointer_target = searchBiggestMovie(tmp.children[0]);
						System.out.println(searchBiggestMovie(t.children[0]).movie.getTitle()+ " sono pointer");
						if (pointer_target!=null) {
							t.movie = pointer_target.movie;
							t.children[0] = deleteMovie(t.children[0], pointer_target.movie);
						}
					}
					//altrimenti se t ha un figlio dx o sx
					else {
						//il nodo che vogliamo cancellare o ha un figlio sx o dx
						if (t.children[0]!=null) {
							t = t.children[0];
						}
						if (t.children[1]!=null) {
							t = t.children[1];
						}
					}
				}
			}
			if (t==null)
				if(!(isEmpty()))
					this.root = null;
			return t;
		}
	
		//ritorna il numero di nodi diversi da null che sono presenti all'interno dell'albero
		public int CountNodes(NodeBTree t) {
			if (t==null) return 0;
			else {
				return CountNodes(t.children[0]) + CountNodes(t.children[1])+1;
			}
		}
		

		//questa funzione, inserisce i film nell'array
		//ricorsivamente, uso una funzione d'appoggio calculateIndex 
		public void getMovies_array(NodeBTree t, Movie[] array, int index) {
			if (t==null) return;
			else {
				array[index] = t.movie;
				getMovies_array(t.children[0], array, calculateIndex(array));
				getMovies_array(t.children[1], array, calculateIndex(array));
			}
		}
		
		//questa funzione restituisce l'indice i-esimo del vettore e controlla
		//se e' null o meno e se e' null, posso inserire il film
		public int calculateIndex(Movie [] array) {
			int index = 0;
			for (int i=0; i<array.length; i=i+1) {
				if (array[i]==null)
					index = i;
			}
			return index;
		}

		@Override
		public void insert(Movie movie) {
			// TODO Auto-generated method stub
			InsertMovie(this.root, movie);
		}
		
		
		@Override
		public void delete(Movie movie) {
			// TODO Auto-generated method stub
			deleteMovie(this.root, movie);
			
		}

		//questa funzione restituisce tutti i film presenti nell'albero. 
		//Crea un vettore che ha lunghezza pari al numero di nodi presente nell'albero
		//chiama la funzioen getArray e restituisce il vettore con i film salvati
		@Override
		public Movie[] getMovies() {
			// TODO Auto-generated method stub
			Movie result[] = new Movie[CountNodes(this.root)];
			getMovies_array(this.root, result, calculateIndex(result));
			return result;
		}

		//tutte le informazioni caricate sul btree vengono cancellate
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			Movie[] result = getMovies();
			for (int i=0; i<result.length; i=i+1) {
				deleteMovie(this.root, result[i]);
				if(search(this.root, result[i])==null) {
					System.out.println(result[i].getTitle() + " "+ i+ " "+ "E' stato cancellato");
				}
			}
		}

		@Override
		public Movie searchMovie(Movie movie) {
			// TODO Auto-generated method stub
			NodeBTree t= search(this.root, movie);
			if (t!=null)
				return t.movie;
			return null;
		}			
		
	}

