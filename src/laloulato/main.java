package laloulato;

import java.io.File;

import commons.Person;

public class main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
			MovidaCore movida = new MovidaCore();
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

			//////////////////prova con graph
			
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

			//prova con searchMostRecentMovies - testato - ok
			System.out.println("\n Prova con searchMostRecentMovies");
			for(int i=0; i<movida.searchMostRecentMovies(9).length; i++){
				System.out.println(movida.searchMostRecentMovies(9)[i].getTitle()+ " "+movida.searchMostRecentMovies(9)[i].getYear());
			}

			//prova con searchMostActiveActors
			System.out.println("\n Prova con searchMostActiveActors");
			/**for (int i=0; i<movida.searchMostActiveActors(9).length;i++){
				System.out.println(movida.searchMostActiveActors(9)[i].getName());
			} */


			
	}

}
