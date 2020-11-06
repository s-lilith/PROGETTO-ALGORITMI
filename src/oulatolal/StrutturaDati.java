package oulatolal;

import commons.Movie;

//in questa classe vengono gestite le funzioni in comune dalle due strutture dati

public abstract class StrutturaDati {
	public abstract void insert(Movie movie);
	public abstract Movie searchMovie(Movie movie);
	public abstract void delete(Movie movie);
	public abstract Movie[] getMovies();
	public abstract void clear();

}
