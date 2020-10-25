package oulatolal;///si trova nella versione 17

import java.io.File;

//devo ricordarmi nel file di testo di aggiungere uno spazio bianco alla fine della riga
//oppure provare ad aggiustare la cosa in un altro modo

public class main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
			MovidaCore movida = new MovidaCore();
			movida.loadFromFile(new File("esempio-formato-dati.txt"));
			
			//prova
			
			//CountPeople
			System.out.println("Sono countPeople(), dovrebbe essere 41, dal txt di prova:"+movida.countPeople());
			
			//CountMovies (dal file di partenza sono 10)
			System.out.println(movida.countMovies() + "sono conta film");
			
			//GetAllMovies
			for(int i=0; i<movida.getAllMovies().length; i=i+1) {
				System.out.println(movida.getAllMovies()[i].getTitle()+ " " + i);
			}
			System.out.println("\n");
			
			
			//getAllPeople 
			System.out.println("getAllPeople");
			for (int i=0; i<movida.getAllPeople().length; i=i+1) {
				System.out.println(movida.getAllPeople()[i].getName()+ " " + i);
			}
			System.out.println("\n");
			
			//searchMoviesinYear 
			System.out.println("SearchMoviesInYear");
			for (int i=0; i<movida.searchMoviesInYear(1954).length; i=i+1) {
				System.out.println(movida.searchMoviesInYear(1954)[i].getTitle()+ " " + i);
			}
			System.out.println("\n");
			
			//searchMoviesByTitle 
			System.out.println("SearchMoviesByTitle");
			for (int i=0; i<movida.searchMoviesByTitle("ciao").length; i=i+1) {
				System.out.println(movida.searchMoviesByTitle("ciao")+ " " + i);
			}
			System.out.println("\n");
			

			

	}

}
