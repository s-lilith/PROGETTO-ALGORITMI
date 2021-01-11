package laloulato;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
	StrutturaDati movies2= new ListaNonOrdinata();
	Sorting sorting_algorithm = new BubbleSort();
	Sorting sorting_algorithm2 = new MergeSort();
	Graph collaborationgraph = new Graph();


	@Override
	public Person[] getDirectCollaboratorsOf(Person actor) {
		//  Auto-generated method stub
		return collaborationgraph.getCollaborators(actor);
	}

	@Override
	public Person[] getTeamOf(Person actor) {
		//  Auto-generated method stub
		return collaborationgraph.getTeam(actor);
	}

	@Override
	public Collaboration[] maximizeCollaborationsInTheTeamOf(Person actor) {
		// TODO Auto-generated method stub
		return collaborationgraph.maximizeCollaborationsInTheTeamOf(actor);
	}

	@Override
	public boolean setSort(SortingAlgorithm a) {
		// TODO Auto-generated method stub
		if (a==SortingAlgorithm.BubbleSort) {
			sorting_algorithm = new BubbleSort();
			sorting_algorithm.sort(movies.getMovies());
			return true;
		}
		else if (a==SortingAlgorithm.MergeSort) {
			sorting_algorithm2 = new MergeSort();
			sorting_algorithm2.sort(movies.getMovies(), movies.getMovies().length);
			return true;
		}
		else {
			System.out.println("L'algoritmo scelto non e' presente");
		}
		return false;
	}


	@Override
	public boolean setMap(MapImplementation m) {
		// TODO Auto-generated method stub
		/*if (m==MapImplementation.BTree && this.movies instanceof BTree) {
			this.movies = new BTree();
			return true;
		}
		else*/ if (m==MapImplementation.ListaNonOrdinata && this.movies instanceof ListaNonOrdinata){
		this.movies = new ListaNonOrdinata();
		return true;
		}
		else {
			System.out.println("L'implementazione passata non e' corretta!");
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
		} 
		return resultSearched;
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
				if (result[i].getDirector().getName().equalsIgnoreCase(name))
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
		Movie[] result = movies.getMovies();
		ArrayList<Movie> tmpResult = new ArrayList<>();
		Movie[] searchedMovies;
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				for (int j=0; j<result[i].getCast().length; j=j+1) {
					if (result[i].getCast()[j].getName().equalsIgnoreCase(name))
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
		Movie[] result= movies.getMovies();
		Movie tmpResult;
		Movie[] recentMovies= new Movie[N];

		if(result!=null){
			int i=0;
			while (i<result.length) {
				for(int j=i+1;j<result.length;j++){
					if (result[i].getYear() < result[j].getYear()) {
						tmpResult=result[i];
						result[i]= result[j];
						result[j]= tmpResult;

					}
				}
				i++;
			}
			for (int k=0; k<N; k++){
				recentMovies[k]= result[k];

			}
			return recentMovies;
		}else
			return null;
	}


	@Override
	public Person[] searchMostActiveActors(Integer N) {
		// TODO Auto-generated method stub
		Movie[] result= movies.getMovies();
		Person[] actors = getActors();
		Person[] activeActors= new Person[N];

		for (Person i: actors){
			for (Movie j: result){
				for (Person k: j.getCast()){
					if (i.getName().equals(k.getName()))
						i.moviesCount();
				}
			}
		}
		for (int l=1; l<actors.length; l++){
			boolean change= false;
			for (int m=1; m<=actors.length-l; m++){
				if (actors[m-1].getPlayedMovies() < actors[m].getPlayedMovies()){
					Person tmp= actors[m-1];
					actors[m-1]= actors[m];
					actors[m]= tmp;
					change= true;
				}
			}
			if (!change) break;
		}
		for (int s=0; s<activeActors.length; s++){
			activeActors[s]= actors[s];
		}
		return activeActors;

		/*if(result!= null){
			for(int i=0; i< result.length; i++){
				for (int j=0; j<result.length; j++){
					if(!Objects.equals(actors[i].getName(), actors[i].getName())){
						tmp=actors[i];
						actors[i]= actors[j];
						actors[j]= (Person) tmp;
					}
				}
			}
			int z=0;
			while (z<N) {
				activeActors[z] = actors[z];
				z++;
			}
			return activeActors;

		} else
			return null;*/
	}

	public int countActors() {
		// TODO Auto-generated method stub
		int conta = 0;
		Movie [] result = movies.getMovies();
		Boolean Bool=false;
		List<String> people= new ArrayList<>();

		//if (result!=null) {
			for (int i=0; i<result.length; i++) {
				for (int j = 0; j < result[i].getCast().length; j++) {
					people.add(result[i].getCast()[j].getName());
				}
			}
			for (int k=0; k<people.size(); k++){
				for (int j=k+1; j<people.size(); j++){
					if (people.get(k).equals(people.get(j))){
						Bool= true;
						break;
					} else {
						Bool= false;
					}
				}
				if (!Bool)
					conta++;
				}
				/*if (result[i].equals(result[j])){
						Bool= true;
						break;
					}
					else { Bool=false;}

					conta = result[i].getCast().length + conta;
					conta = conta+1;*/

			//}
		return conta;
	}

	public Person[] getActors() {
		// TODO Auto-generated method stub
		int index= countActors();
		Person[] actors = new Person[index];
		List<String> newActors= new ArrayList<>();
		//int k=0;
		//lista attori
		for (int i=0; i<movies.getMovies().length; i++) {
			for (int j=0; j<movies.getMovies()[i].getCast().length;j++) {
				newActors.add(movies.getMovies()[i].getCast()[j].getName());
				//actors[k] = movies.getMovies()[i].getCast()[j];
				//++;
			}

		}
		//rimozione duplicati
		for (int i=0; i< newActors.size(); i++){
			for (int j=i+1; j<newActors.size(); j++){
				if (newActors.get(i).equals(newActors.get(j))){
					String remove = newActors.remove(j);
				}
			}
		}
		//aggiunta persone nell'array
		for (int i=0;i<actors.length; i++){
			Person newPeople= new Person(newActors.get(i));
			actors[i]= newPeople;
		}
		return actors;
	}


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
			/////////////////////////////////////////////////////////
			int lunghezzaMovieVettore = lines/6;
			//creo una variabile vettore movie per poter salvare i film letti dal file
			Movie [] movie = new Movie[lunghezzaMovieVettore];
			int k=0;

			while((scan.hasNext())) {
				for (k=0; k<lunghezzaMovieVettore; k=k+1) {
					String title_string = scan.nextLine();
					title_string = title_string.replaceAll("Title:", " "); 

					title_string = title_string.trim(); 

					title_string = title_string.replaceAll(" + ", " "); 

					String year_string = scan.nextLine();

					year_string = year_string.replaceAll("Year:", " "); 

					year_string = year_string.trim(); 

					year_string = year_string.replaceAll(" + ", " "); 

					int year_integer = Integer.parseInt(year_string); ///<--- per castare

					String director_string = scan.nextLine();

					director_string = director_string.replaceAll("Director:", " "); 

					director_string = director_string.trim(); 

					director_string = director_string.replaceAll(" + ", " "); 

					Person director = new Person(director_string);

					String cast_string = scan.nextLine();

					cast_string = cast_string.replaceAll("Cast: ", " "); 

					cast_string = cast_string.trim(); 

					cast_string = cast_string.replaceAll(" + ", " "); 

					String[] cast_array;

					int commas = 0;
					//conto le virgole presenti nel file
					//nella riga degli attori/cast 
					//in modo da poter dare una lunghezza al vettore person
					for(int i = 0; i < cast_string.length(); i++) {
						if(cast_string.charAt(i) == ',') 
							commas++;
					}
					//creo il vettore di lunghezza commas+1 per il cast
					Person cast[] = new Person[commas+1];
					cast_array = cast_string.split(", "); //<--- serve per separare i valori 
					//dalle virgole. E' importante che sia virgola e spazio, perchÃ© altrimenti
					//ci sono problemi con l'hashcode 

					for (int i=0; i<commas+1; i=i+1) {
						cast[i] = new Person(cast_array[i]);
					}

					String vote_string = scan.nextLine();
					vote_string = vote_string.replaceAll("Votes:", " "); 

					vote_string = vote_string.trim(); 

					vote_string = vote_string.replaceAll(" + ", " "); 

					int vote_integer = Integer.parseInt(vote_string);
					String whitespace = scan.nextLine(); //anche se il programma suggerisce di cancellarlo, no, non si deve cancellare la riga

					movie[k] = new Movie(title_string,year_integer,vote_integer,cast, director);

				}
			} 	//una volta riempito il vettore, si chiama la funziona insert, grazie alla
			//quale i film letti e memorizzati nel vettore movie, vengono caricati
			//sulla strutturadati (a seconda del caso, linked list o btree)
			for (int i=0; i<lunghezzaMovieVettore; i=i+1) {
				movies.insert(movie[i]);
				collaborationgraph.insertMovieGraph(movie[i]);
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
		FileWriter fw = null;
		try {
			try {
				fw = new FileWriter(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try (PrintWriter pw = new PrintWriter(fw)) {
				int lunghezzavettorefilm;
				lunghezzavettorefilm = movies.getMovies().length;
				String whitespace = "\n";
				for (int i=0; i<lunghezzavettorefilm; i=i+1) {
					String title = movies.getMovies()[i].getTitle();
					pw.print("Title: "); 
					pw.println(title);
					int year = movies.getMovies()[i].getYear();
					pw.print("Year: ");
					pw.println(year);
					String director;
					pw.print("Director: ");
					director = movies.getMovies()[i].getDirector().getName();
					pw.println(director);
					pw.print("Cast: ");
					int cast = movies.getMovies()[i].getCast().length;

					//uso lo stesso gioco delle virgole

					int commas = cast - 1; 
					for (int j=0; j<cast; j=j+1) {
						if (j==cast-1) {
							pw.println(movies.getMovies()[i].getCast()[j].getName());
						} else {
							pw.print(movies.getMovies()[i].getCast()[j].getName());
						}
						if (commas!=0) {
							pw.print(", "); //aggiungo lo spazio 
							commas = commas -1;
						}
					} 
					int votes;
					votes = movies.getMovies()[i].getVotes();
					pw.print("Votes: ");
					pw.println(votes);
					pw.print(whitespace);

				}
				pw.close();
			}
		} catch (MovidaFileException message) {
			// TODO Auto-generated catch block
			System.out.println(message.getMessage());
		}
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
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {	
				conta = result[i].getCast().length + conta;
				conta = conta+1;
			}
		}
		return conta;	
	}

	@Override
	public boolean deleteMovieByTitle(String title) {
		// TODO Auto-generated method stub
		Movie [] result = movies.getMovies();
		boolean deleted = false;
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				if (result[i].getTitle().equalsIgnoreCase(title)) {
					movies.delete(result[i]);
					if (movies.searchMovie(result[i])==null)
						deleted =  true;
				}
			}
		}
		return deleted;
	}

	@Override
	public Movie getMovieByTitle(String title) {
		// TODO Auto-generated method stub
		Movie[] result = movies.getMovies();
		Movie movie_result = null;
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {	
				if (result[i].getTitle().equalsIgnoreCase(title))
					movie_result =  result[i];	
			}
		} 
		return movie_result;
	}

	@Override
	public Person getPersonByName(String name) {
		// TODO Auto-generated method stub
		Movie [] result = movies.getMovies();
		Person person_result = null;
		if (result!=null) {
			for (int i=0; i<result.length; i=i+1) {
				for (int j=0; j<result[i].getCast().length; j=j+1) {
					if (result[i].getCast()[j].getName().equalsIgnoreCase(name))
						person_result = result[i].getCast()[j];
				}
			}
		}
		return person_result;
	}

	@Override
	public Movie[] getAllMovies() {
		// TODO Auto-generated method stub
		Movie[] result = movies.getMovies();
		return result;
	}



	@Override
	public Person[] getAllPeople() {
		// TODO Auto-generated method stub
		Person[] people = new Person[countPeople()];
		int k=0;
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


}	
