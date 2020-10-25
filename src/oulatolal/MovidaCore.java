package oulatolal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import commons.Collaboration;
import commons.IMovidaCollaborations;
import commons.IMovidaConfig;
import commons.IMovidaDB;
import commons.IMovidaSearch;
import commons.MapImplementation;
import commons.MovidaFileException;
import commons.Movie;
import commons.Person;
import commons.SortingAlgorithm;

public class MovidaCore implements IMovidaDB, IMovidaSearch, IMovidaConfig, IMovidaCollaborations{

	StrutturaDati movies = new BTree(); 
	StrutturaDati movies2= new LinkedList();
	Sorting sorting_algorithm = new BubbleSort();
	Sorting sorting_algorithm2 = new MergeSort();
	
	@Override
	public Person[] getDirectCollaboratorsOf(Person actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person[] getTeamOf(Person actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collaboration[] maximizeCollaborationsInTheTeamOf(Person actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setSort(SortingAlgorithm a) {
		// TODO Auto-generated method stub
		if (a==SortingAlgorithm.BubbleSort) {
			sorting_algorithm = new BubbleSort();
		}
		else if (a==SortingAlgorithm.MergeSort) {
			sorting_algorithm2 = new MergeSort();
		}
		else {
			System.out.println("L'algoritmo scelto non è presente");
		}
		return false;
	}

	
	@Override
	public boolean setMap(MapImplementation m) {
		// TODO Auto-generated method stub
		if (m==MapImplementation.BTree && this.movies instanceof BTree)
			this.movies = new BTree();
		//else if (m==MapImplementation.ListaNonOrdinata && this.movies instanceof LinkedList)
				//this.movies = new LinkedList();
		else {
			System.out.println("L'implementazione passata non è corretta!");
		}
		return false;
	}

	@Override
	public Movie[] searchMoviesByTitle(String title) {
		// TODO Auto-generated method stub
		ArrayList<Movie> tmpResult = new ArrayList<Movie>();
		Movie resultMovie = getMovieByTitle(title);
		if (resultMovie!=null) {
			tmpResult.add(resultMovie);
		}
		if (tmpResult.isEmpty())
			System.out.println("Non esiste il film con il nome cercato");
		Movie [] resultSearched = new Movie[tmpResult.size()];
		if (resultSearched!=null) {
			for (int i=0; i<tmpResult.size(); i=i+1) {
			resultSearched[i] = tmpResult.get(i);
			}
		return resultSearched;
		} else
			return null;
	}

	
	
	@Override
	public Movie[] searchMoviesInYear(Integer year) {
		// TODO Auto-generated method stub
		Movie [] result = movies.getMovies();
		ArrayList<Movie> tmpResult = new ArrayList<Movie>();
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				if (result[i].getYear().equals(year))
					tmpResult.add(result[i]);
			}
			if (tmpResult.isEmpty())
				System.out.println("Non ci sono film presenti nell'anno indicato");
			Movie [] searchedMovies = new Movie[tmpResult.size()];
			for (int i=0; i<tmpResult.size(); i=i+1) {
				searchedMovies[i] = tmpResult.get(i);
			}
			return searchedMovies;
		}
		return null;
	}

	@Override
	public Movie[] searchMoviesDirectedBy(String name) {
		// TODO Auto-generated method stub
		Movie result[] = movies.getMovies();
		ArrayList<Movie> tmpResult = new ArrayList<>();
		Movie searchedMovies[];
		if (result!=null) {
		for (int i=0; i<result.length; i=i+1) {
			if (result[i].getDirector().getName().equals(name))
				tmpResult.add(result[i]);
		}
		if (tmpResult.isEmpty())
			System.out.println("Non ci sono film diretti dal regista indicato");
		searchedMovies = new Movie[tmpResult.size()];
			for (int i=0; i<tmpResult.size(); i=i+1) {
				searchedMovies[i] = tmpResult.get(i);
			}
			return searchedMovies;
		}
		return null;
	}

	@Override
	public Movie[] searchMoviesStarredBy(String name) {
		// TODO Auto-generated method stub
		Movie result[] = movies.getMovies();
		ArrayList<Movie> tmpResult = new ArrayList<>();
		Movie searchedMovies[];
		if (result!=null) {
		for (int i=0; i<result.length; i=i+1) {
			for (int j=0; j<result[i].getCast().length; j=j+1) {
				if (result[i].getCast()[j].equals(name))
					tmpResult.add(result[i]);
			}
		}
		if (tmpResult.isEmpty())
			System.out.println("Non ci sono film con l'attore indicato");
		searchedMovies = new Movie[tmpResult.size()];
			for (int i=0; i<tmpResult.size(); i=i+1) {
				searchedMovies[i] = tmpResult.get(i);
			}
			return searchedMovies;
		}
		return null;
	}

	@Override
	public Movie[] searchMostVotedMovies(Integer N) {
		// TODO Auto-generated method stub
		Movie[] result = movies.getMovies();
		Movie[] tmpVotedMovies = new Movie[movies.getMovies().length];
		Movie[] mostVotedMovies = new Movie[N];
		if (result!=null) {
		for (int i=0; i<result.length; i=i+1) {
			for (int j=0; j<result.length; j=j+1) {
				if (result[j].getVotes()>result[i].getVotes()) {
					tmpVotedMovies[j] = result[j];
				}
			}
			
		}
		for (int k=0; k<N; k=k+1) {
			mostVotedMovies[k] = tmpVotedMovies[k];
		}
		return mostVotedMovies;
		}else 
			return null;
	}
	@Override
	public Movie[] searchMostRecentMovies(Integer N) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person[] searchMostActiveActors(Integer N) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	/**
	 * questa parte del codice serve per leggere valori dal file
	 * e poterli poi salvare in una struttura dati
	 * creo delle variabili temporanee che mi servono per potere salvare i dati 
	 * e a seconda della necessità poi vengono convertiti.
	 *  1)Inizio con la lettura della stringa titolo, t;
	 *  
	 *  2)Leggo poi l'anno. Essendo year: xxxx (valore numerico) una stringa,
	 *  dovevo "rimuovere" ciò che non era necessario dalla stringa, per poi 
	 *  castarla in un intero. 
	 *  
	 *  3)Per il cast, che è un vettore di persone, ho pensato di usare le 
	 *  virgole per avere una lunghezza. Nel senso, se ho 4 attori nel cast,
	 *  questi verranno delimitati da 3 virgole, quindi la lunghezza sarà
	 *  virgole+1. 
	 *  Quindi una volta letta la stringa degli attori:
	 *  	-conto le virgole
	 *  	-creo la lunghezza del vettore persone[virgole+1]
	 *  	-uso un vettore d'appoggio stringa, per separare i valori salvati
	 *  		nella stringa, tramite string.split(",");
	 *  	-assegno poi al vettore persona iesimo, il valore iesmio
	 *  		del vettore stringa. 
	 *  
	 *  4) Alla fine per leggere l'ultimo valore relativo ai voti,
	 *  è stata eseguita la stessa procedura fatta con l'anno, dove veniva
	 *  letta la stringa, successivamente usando replaceAll, trim e replaceAll
	 *  veniva "pulita" dai caratteri e poi castata come intero.
	 */
		
		
		//////////////////////////////////
		/**
		 * il problema che mi sto ponendo è se usare bufferreader o scanner
		 * se scanner consuma i caratteri letti e li "brucia"
		 * pensavo di usare un count, in modo tale d'avere un'idea di quanto
		 * deve skippare, ma credo che scanner consumi
		 * e forse quindi devo usare il bufferreader 
		 * 
		 * quindi forse bisogna fare una versione alternativa con buffer
		 */
		
		

		
		////////////////////////
		/**
		 * Uso il bufferreader, invece dello scanner, perché sembra che con 
		 * scanner io bruci le righe e non riesca ad utilizzarle (vedo se 
		 * cambiare scanner e tenere tutto con buffer) 
		 * Uso il bufferedReader, per poter contare il numero delle righe
		 * nel file. In modo tale da poter costruire un vettore movie, di 
		 * numero di righe nel file / il numero delle componenti
		 * ovvero, vedendo la struttura del file del testo so che c'è
		 * un titolo
		 * un anno
		 * un direttore
		 * un cast (si suppone che i vari nomi degli attori vengano tutti 
		 * 	scritti in una riga sola).
		 *  il voto
		 *  e uno spazio bianco
		 *  (Quindi sei righe).
		 *
		 * 
		 */
		
	@Override
	public void loadFromFile(File f) {
		// TODO Auto-generated method stub
		Scanner scan;
		try {
			scan = new Scanner(f);
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			int lines = 0;
			try {
				while (br.readLine() != null) lines = lines+1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(lines);
			/////////////////////////////////////////////////////////
			int lunghezzaMovieVettore = lines/6;
			//creo una variabile vettore movie per poter salvare i film letti dal file
			Movie [] movie = new Movie[lunghezzaMovieVettore];
			int k=0;
		
				while((scan.hasNext())) {
					for (k=0; k<lunghezzaMovieVettore; k=k+1) {
						String title_string = scan.nextLine();
						title_string = title_string.replaceAll("Title:", " "); 
				  
						// Remove extra spaces from the beginning 
						// and the ending of the string 
						title_string = title_string.trim(); 
			  
						// Replace all the consecutive white 
						// spaces with a single space 
						title_string = title_string.replaceAll(" +", " "); 
				
						String year_string = scan.nextLine();
						// Replacing every non-digit number 
						// with a space(" ") 
						year_string = year_string.replaceAll("Year:", " "); 
		  
						// Remove extra spaces from the beginning 
						// and the ending of the string 
						year_string = year_string.trim(); 
		  
						// Replace all the consecutive white 
						// spaces with a single space 
						year_string = year_string.replaceAll(" +", " "); 
		  
						int year_integer = Integer.parseInt(year_string); ///<--- per castare
						String director_string = scan.nextLine();
						director_string = director_string.replaceAll("Director:", " "); 
				  
						// Remove extra spaces from the beginning 
						// and the ending of the string 
						director_string = director_string.trim(); 
			  
						// Replace all the consecutive white 
						// spaces with a single space 
						director_string = director_string.replaceAll(" +", " "); 
						Person director = new Person(director_string);
				
						///leggiamo il cast
						String cast_string = scan.nextLine();
				
						// with a space(" ") 
						cast_string = cast_string.replaceAll("Cast:", " "); 
		  
						// Remove extra spaces from the beginning 
						// and the ending of the string 
						cast_string = cast_string.trim(); 
		  
						// Replace all the consecutive white 
						// spaces with a single space 
						cast_string = cast_string.replaceAll(" +", " "); 
				
						String[] cast_array;
				
						int commas = 0;
						//conto le virgole presenti nel file
						//nella riga degli attori/cast 
						//in modo da poter dare una lunghezza al vettore person
						for(int i = 0; i < cast_string.length(); i++) {
							if(cast_string.charAt(i) == ',') 
								commas++;
							}
						System.out.println(commas);
						//creo il vettore di lunghezza commas+1 per il cast
						Person cast[] = new Person[commas+1];
						cast_array = cast_string.split(","); //<--- serve per separare i valori 
						//dalle virgole
			
				
						//per l'iesima persona del cast, assegno l'iesimo valore salvato 
						//nel vettore stringa
						for (int i=0; i<commas+1; i=i+1) {
							cast[i] = new Person(cast_array[i]);
						}
						///leggo i valori riguardo ai voti del film
						String vote_string = scan.nextLine();
						vote_string = vote_string.replaceAll("Votes:", " "); 
				  
						// Remove extra spaces from the beginning 
						// and the ending of the string 
						vote_string = vote_string.trim(); 
			  
						// Replace all the consecutive white 
						// spaces with a single space 
						vote_string = vote_string.replaceAll(" +", " "); 
			       
						int vote_integer = Integer.parseInt(vote_string);
						String whitespace = scan.nextLine();
						System.out.println(whitespace);
			
						movie[k] = new Movie(title_string,year_integer,vote_integer,cast, director);

					}
		} 	//una volta riempito il vettore, si chiama la funziona insert, grazie alla
			//quale i film letti e memorizzati nel vettore movie, vengono caricati
			//sulla strutturadati (a seconda del caso, linked list o btree)
			for (int i=0; i<lunghezzaMovieVettore; i=i+1) {
				movies.insert(movie[i]);
			}
			scan.close();
		}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("The file doesn't exist");
			} catch (MovidaFileException message) {
				// TODO Auto-generated catch block
				System.out.println(message.getMessage());
			}
		
	}

	@Override
	public void saveToFile(File f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		movies.clear();
	}

	@Override
	public int countMovies() {
		// TODO Auto-generated method stub
		if (movies.getMovies().length > 0)
			return movies.getMovies().length;
		else
			return 0;
	}

	@Override
	public int countPeople() {
		// TODO Auto-generated method stub
		int conta = 0;
		Movie [] result = movies.getMovies();
		//ok, la dimensione del vettore è giusta
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				//per ogni film i-esimo, si prende il vettore cast
				//si prende la lunghezza e la si aggiunge a conta, così
				//per ogni film
				conta = result[i].getCast().length + conta;
				//qui viene aggiunto il direttore del film 
				//suppongo che sia uno
				conta = conta+1;
			}
				
			return conta;
		}
		else  
			return 0;
		
	}

	@Override
	public boolean deleteMovieByTitle(String title) {
		// TODO Auto-generated method stub
		Movie [] result = movies.getMovies();
		
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				if (result[i].getTitle().equals(title)) {
						movies.delete(result[i]);
					if (movies.searchMovie(result[i])==null)
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Movie getMovieByTitle(String title) {
		// TODO Auto-generated method stub
		Movie[] result = movies.getMovies();
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {	
				if (result[i].getTitle().equals(title))
					return result[i];	
					}
			} 
		return null;
	}

	@Override
	public Person getPersonByName(String name) {
		// TODO Auto-generated method stub
		Movie [] result = movies.getMovies();
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				for (int j=0; j<result[i].getCast().length; j=j+1) {
					if (result[i].getCast()[j].getName().equals(name))
						return result[i].getCast()[j];
				}
			}
		}
		return null;
	}

	@Override
	public Movie[] getAllMovies() {
		// TODO Auto-generated method stub
		Movie[] result = movies.getMovies();
		if (result!=null) {
			return result;
		}
		else
			return null;
	}

	
	//è necessario che ci sia il doppio controllo?
	@Override
	public Person[] getAllPeople() {
		// TODO Auto-generated method stub
		Person[] people = new Person[countPeople()];
		int k=0;
		if (people!=null) {
			for (int i=0; i<movies.getMovies().length; i=i+1) {
				for (int j=0; j<movies.getMovies()[i].getCast().length;j=j+1) {
				people[k] = movies.getMovies()[i].getCast()[j];
				k=k+1;
			}
			people[k] = movies.getMovies()[i].getDirector();
			k=k+1;
		}
		return people;
		}
		return null;
	}


	}	