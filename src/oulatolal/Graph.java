package oulatolal;

import commons.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.AbstractCollection;
import commons.Collaboration;
import commons.Movie;

/**
 * Aggiornamento del 26 ottobre 2020
 * 	G = (V, E), quindi facciamo che i nodi del grafo siano le persone
 * 	mentre gli Edges siano le collaborazioni.
 * 
 * Idea: Fare una lista per gli vertici e fare una lista di edges
 *
 * @author alpaca
 *
 */
public class Graph {
	private HashMap<Person, ArrayList<Collaboration>> graph;
	Graph(){
		graph = new HashMap<Person, ArrayList<Collaboration> >();
	}

	public void addNode(Person p) {
		if (!graph.containsKey(p))
			graph.put(p, new ArrayList<Collaboration>());	 
	}


	/**
	 * Idea: aggiungere i nodi al grafo
	 * controllare se ci sono collaborazioni, se ci sono, bene, altrimenti vengono aggiunte
	 * (ricontrollareeee))
	 * @param p
	 * @param p1
	 */
	public void insertMovieGraph(Movie movie) {
		for (int i=0; i<movie.getCast().length; i=i+1) {
			for (int j=0; j<movie.getCast().length; j=j+1) {
				if (movie.getCast()[i].equals(movie.getCast()[j])) {
					for (int k=0; k<movie.getCast().length; k=k+1) {
						if (!(movie.getCast()[i].equals(movie.getCast()[k]))) {
							setCollaborations(movie.getCast()[i], movie.getCast()[k], movie);
						}
					}
				}
			}
		}
	}


	public void setCollaborations(Person p, Person p1, Movie m) {
		if (!(graph.containsKey(p))) {
			addNode(p);
		}
		if (!graph.containsKey(p1)) {
			addNode(p1);
		}
		Collaboration collaboration = new Collaboration(p, p1);
		graph.get(p).add(collaboration);
		graph.get(p1).add(collaboration);
		collaboration.addMovie(m);
	}

	public Person[] getCollaborators (Person actor) {
		Person[] people = null;
		ArrayList<Person> tmp = new ArrayList<>();
		if (graph.get(actor)!=null) {
			for (int i=0; i<graph.get(actor).size(); i=i+1) {
				if (graph.get(actor).get(i).getActorA().equals(actor)) {
					tmp.add(graph.get(actor).get(i).getActorB());
				}
			}
			if (tmp!=null) {
				people = new Person[tmp.size()];
				for (int i=0; i<tmp.size(); i=i+1) {
					people[i] = tmp.get(i);
				}
			}
		}
		return people;
	}



	private ArrayList<Person> getCollaborators_arrayList (Person actor){
		ArrayList<Person> collaboration_arrayList = new ArrayList<>();
		if (graph.get(actor)!=null) {
			for (int i=0; i<graph.get(actor).size(); i=i+1) {
				if (graph.get(actor).get(i).getActorA().equals(actor)) {
					collaboration_arrayList.add(graph.get(actor).get(i).getActorB());
				}
			}
		}
		return collaboration_arrayList;
	}


	public Person[] getTeam (Person actor) {
		boolean done = false;
		ArrayList<Person> collaborators = new ArrayList<>();
		collaborators = getCollaborators_arrayList(actor);
		ArrayList<Person> team = new ArrayList<>();
		team = getCollaborators_arrayList(actor);
		ArrayList<Person> tmp_array = new ArrayList<>();
		ArrayList<Person> different = new ArrayList<>();
		Person[] team_result = null;
		while (!(done)) {
			for (int i=0; i<collaborators.size(); i=i+1) {
				Person tmp = collaborators.get(i);
				tmp_array = getCollaborators_arrayList(tmp);
				for (int j=0; j<tmp_array.size(); j=j+1) {
					Person tmp_collaborator = tmp_array.get(j);
					if (!(team.contains(tmp_collaborator))) {
						team.add(tmp_collaborator);
						different.add(tmp_collaborator);
					}
				}	
			}
			collaborators.removeAll(collaborators);
			if (collaborators.isEmpty()) {
				if (!(different.isEmpty())) {
					collaborators.addAll(different);
					different.removeAll(different);
				} else {
					done = true;
				}
			}
		}

		if (!(team.isEmpty())) {
			team_result = new Person[team.size()];
			for (int i=0; i<team.size(); i=i+1) {
				team_result[i] = team.get(i);
			}
		}
		return team_result;
	}







}
