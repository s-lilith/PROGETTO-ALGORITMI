package oulatolal;///si trova nella versione 17

import java.io.File;

import commons.Person;

//devo ricordarmi nel file di testo di aggiungere uno spazio bianco alla fine della riga
//oppure provare ad aggiustare la cosa in un altro modo

public class main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
			MovidaCore movida = new MovidaCore();
			movida.loadFromFile(new File("esempio-formato-dati.txt"));
			
			//prova
			Person p1 = new Person ("Sela Ward");
			//CountPeople
			System.out.println("Sono countPeople(), dovrebbe essere 41, dal txt di prova:"+movida.countPeople());
			
			//CountMovies (dal file di partenza sono 10)
			System.out.println(movida.countMovies() + "sono conta film");
			
			//GetAllMovies
			for(int i=0; i<movida.getAllMovies().length; i=i+1) {
				System.out.println(movida.getAllMovies()[i].getTitle()+ " " + i);
			}
			System.out.println("\n");
			
			
			//getAllPeople - testato - ok
			System.out.println("getAllPeople");
			for (int i=0; i<movida.getAllPeople().length; i=i+1) {
				System.out.println(movida.getAllPeople()[i].getName()+ " " + i);
			}
			System.out.println("\n");
			
			//searchMoviesinYear - testato - ok
			System.out.println("SearchMoviesInYear");
			for (int i=0; i<movida.searchMoviesInYear(1954).length; i=i+1) {
				System.out.println(movida.searchMoviesInYear(1954)[i].getTitle()+ " " + i);
			}
			System.out.println("\n");
			
			//searchMoviesByTitle - testato ok
			System.out.println("SearchMoviesByTitle");
			for (int i=0; i<movida.searchMoviesByTitle("ciao").length; i=i+1) {
				System.out.println(movida.searchMoviesByTitle("ciao")+ " " + i);
			}
			System.out.println("\n");

			//////////////////prova con graph
			
			//ok - testato - ok
			System.out.println("Prova stampa grafo con getDirectCollaborators of: " + p1.getName());
			for (int i=0; i<movida.getDirectCollaboratorsOf(p1).length; i=i+1) {
			System.out.println(movida.getDirectCollaboratorsOf(p1)[i].getName()); 
			} 
			
			//prova con getTeam - testato - ok
			System.out.println("Prova con grafo, getTeamOf: "+ p1.getName());
			for (int i=0; i<movida.getTeamOf(p1).length; i=i+1) {
				System.out.println(movida.getTeamOf(p1)[i].getName());
			}
			

	}

}
