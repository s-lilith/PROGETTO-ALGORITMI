package oulatolal;

import java.io.File;

import commons.Person;

public class main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
			MovidaCore movida = new MovidaCore();
			movida.loadFromFile(new File("esempio-formato-dati.txt"));
			
			//prova
			Person p1 = new Person ("Sela Ward");

			//GetAllMovies 
			System.out.println("Prova con getAllMovies");
			for(int i=0; i<movida.getAllMovies().length; i=i+1) {
				System.out.println(movida.getAllMovies()[i].getTitle()+ " " + i);
			}
			
			//getAllPeople - testato - ok
			System.out.println("Prova con getAllPeople");
			for (int i=0; i<movida.getAllPeople().length; i=i+1) {
				System.out.println(movida.getAllPeople()[i].getName()+ " " + i);
			}
			System.out.println("\n");
			
			//searchMoviesinYear - testato - ok
			System.out.println("SearchMoviesInYear");
			for (int i=0; i<movida.searchMoviesInYear(1954).length; i=i+1) {
				System.out.println(movida.searchMoviesInYear(1954)[i].getTitle()+ " " + i);
			}
			
			//searchMoviesByTitle - testato ok
			System.out.println("SearchMoviesByTitle");
			for (int i=0; i<movida.searchMoviesByTitle("ciao").length; i=i+1) {
				System.out.println(movida.searchMoviesByTitle("ciao")+ " " + i);
			}

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
