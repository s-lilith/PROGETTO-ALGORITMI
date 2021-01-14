package laloulato;

import java.io.File;
import java.util.Arrays;

import commons.MapImplementation;
import commons.Person;
import commons.SortingAlgorithm;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovidaCore movida = new MovidaCore();

		//impostazione della struttura dati: Lista non ordinata
		System.out.println("La struttura dati caricata e' la lista ordinata: "+ movida.setMap(MapImplementation.ListaNonOrdinata));
		
		//impostazione della struttura dati: BTree
		//System.out.println("La struttura dati caricata e' il BTree: "+ movida.setMap(MapImplementation.BTree));

		movida.loadFromFile(new File("prova.txt")); //ho caricato il file scritto dalla funzione
		


		Person p1 = new Person("Sela Ward");

		//prova con linkedList

		//getAllMovies
		
		System.out.println("\n Prova con getAllMovies");
		for (int i = 0; i < movida.getAllMovies().length; i = i + 1) {
			System.out.println(movida.getAllMovies()[i].getTitle() + " " + i);
		}

		//getAllPeople - testato - ok
		System.out.println("\n Prova con getAllPeople");
		for (int i = 0; i < movida.getAllPeople().length; i = i + 1) {
			System.out.println(movida.getAllPeople()[i].getName() + " " + i);
		}
		System.out.println("\n");

		//searchMoviesinYear - testato - ok
		System.out.println("\n SearchMoviesInYear");
		for (int i = 0; i < movida.searchMoviesInYear(2000).length; i = i + 1) {
			System.out.println(movida.searchMoviesInYear(2000)[i].getTitle() + " " + i);
		}

		//searchMoviesByTitle - testato ok
		//Oltre al nome faccio stampare anche le altre informazioni riguardanti il film, in modo che sembri "realistico"
		System.out.println("\n SearchMoviesByTitle");
		for (int i = 0; i < movida.searchMoviesByTitle("Pulp Fiction").length; i = i + 1) {
			System.out.println(movida.searchMoviesByTitle("Pulp Fiction")[i].getTitle());
			for (int j=0; j<movida.searchMoviesByTitle("Pulp Fiction")[i].getCast().length; j = j + 1){
				System.out.print(movida.searchMoviesByTitle("Pulp Fiction")[i].getCast()[j].getName()+" ");
			}
			System.out.println("\n" + movida.searchMoviesByTitle("Pulp Fiction")[i].getYear()+ "\n" + movida.searchMoviesByTitle("Pulp Fiction")[i].getVotes());

		}

			//prova con searchMostRecentMovies - testato - ok
			System.out.println("\n Prova con searchMostRecentMovies");
			for(int i=0; i<movida.searchMostRecentMovies(9).length; i++){
				System.out.println(movida.searchMostRecentMovies(9)[i].getTitle()+ " "+movida.searchMostRecentMovies(9)[i].getYear());
			}

			System.out.println("\n Prova con getActors");
			for (int i=0; i<movida.getActors().length;i++)
				System.out.println(movida.getActors()[i].getName()+ ' '+ i);

			//prova con searchMostActiveActors - testato - ok
			System.out.println("\n Prova con searchMostActiveActors= ");
			for (int i=0; i<movida.searchMostActiveActors(9).length;i++)
				System.out.println(movida.searchMostActiveActors(9)[i].getName());


			System.out.println("\n Prova con deleteMovieByTitle");
				System.out.println(movida.deleteMovieByTitle("Cape Fear"));
			

				////////////Test grafi ////////////// Ok
				
				//ok - testato - ok
				System.out.println("\n Prova stampa grafo con getDirectCollaborators of: " + p1.getName());
				for (int i=0; i<movida.getDirectCollaboratorsOf(p1).length; i=i+1) {
				System.out.println(movida.getDirectCollaboratorsOf(p1)[i].getName()); 
				} 
				
				//prova con getTeam - testato - ok
				System.out.println("\n Prova con grafo, getTeamOf: "+ p1.getName());
				for (int i=0; i<movida.getTeamOf(p1).length; i=i+1) {
					System.out.println(movida.getTeamOf(p1)[i].getName());
				}
				System.out.println("\n");
				//prova con maximizeCollaboration - testato ok 
				System.out.println("prova con maximize");
				for (int i=0; i<movida.maximizeCollaborationsInTheTeamOf(p1).length; i=i+1) {
					System.out.println(movida.maximizeCollaborationsInTheTeamOf(p1)[i].getActorB().getName());
				}
				System.out.println("fine prova con maximize");
				System.out.println("\n");

				//impostazione degli algoritmi di ordinamento 
				//MergeSort
				//System.out.println("L'algoritmo di ordinamento MergeSort: "+ movida.setSort(SortingAlgorithm.MergeSort));
				
				//BubbleSort
				//System.out.println("L'algoritmo di ordinamento BubbleSort: "+ movida.setSort(SortingAlgorithm.BubbleSort));


				
			
			/*
			movida.loadFromFile(new File("prova.txt"));
			
			movida.saveToFile(new File("prova.txt")); //ok funziona
			//prova
			Person p1 = new Person ("Sela Ward");

			//GetAllMovies 
			System.out.println("\n Prova con getAllMovies");
			for(int i=0; i<movida.getAllMovies().length; i=i+1) {
				System.out.println(movida.getAllMovies()[i].getTitle()+ " " + i);
			}
			
			//getAllPeople - testato - ok
			System.out.println("\n Prova con getAllPeople");
			for (int i=0; i<movida.getAllPeople().length; i=i+1) {
				System.out.println(movida.getAllPeople()[i].getName()+ " " + i);
			}
			System.out.println("\n");
			
			//searchMoviesinYear - testato - ok
			System.out.println("\n SearchMoviesInYear");
			for (int i=0; i<movida.searchMoviesInYear(2000).length; i=i+1) {
				System.out.println(movida.searchMoviesInYear(2000)[i].getTitle()+ " " + i);
			}
			
			//searchMoviesByTitle - testato ok
			System.out.println("\n SearchMoviesByTitle");
			for (int i=0; i<movida.searchMoviesByTitle("ciao").length; i=i+1) {
				System.out.println(movida.searchMoviesByTitle("ciao")+ " " + i);
			}
		
			*/

	}

}

