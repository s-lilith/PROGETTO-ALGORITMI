package oulatolal;

import commons.Person;

import java.util.ArrayList;
import java.util.HashMap;

import commons.Collaboration;
import commons.Movie;

/**
 * 	G = (V, E), quindi facciamo che i nodi del grafo siano le persone
 * 	mentre gli Edges siano le collaborazioni.
 * 
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

	private ArrayList <Person> getTeamArrayList (Person actor){
		ArrayList<Person> teamarraylist = new ArrayList<>();
		Person [] team = getTeam(actor);
		if (team!=null) {
			for (int i=0; i<team.length; i=i+1) {
				teamarraylist.add(team[i]);
			}
		}
		return teamarraylist;
	}



	private ArrayList<Collaboration> getCollaborationlist (Person p) {
		ArrayList<Collaboration> result = new ArrayList<>();
		for (int i=0; i<graph.get(p).size(); i=i+1) {
			if (graph.get(p).get(i).getActorA().equals(p)) {
				result.add(graph.get(p).get(i));
			}
		}
		return result;
	}



	private ArrayList<Collaboration> orderCollabs (ArrayList<Collaboration> array_list){
		Collaboration[] arr = new Collaboration[array_list.size()];
		ArrayList<Collaboration> result = new ArrayList<>();
		for (int i=0; i<array_list.size(); i=i+1) {
			arr[i] = array_list.get(i);
		}
		//li voglio ordinare dal più grande al più piccolo 
		for (int i= 0; i<arr.length-1; i=i+1) {
			for (int j=0; j < arr.length-i-1; j=j+1)
				if ((arr[j].getScore() < arr[j+1].getScore()) || ((arr[j].getScore() == arr[j+1].getScore()))){
					Collaboration tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
		}	
		for (int i=0; i<arr.length; i=i+1) {
			result.add(arr[i]);
		}
		return result;

	}

	//prim algorithm 
	//per implementare questa funzione è stato usato l'algoritmo di Prim
	public Collaboration[] maximizeCollaborationsInTheTeamOf (Person actor) {
		ArrayList<Person> team = getTeamArrayList(actor);
		ArrayList<Person> visited = new ArrayList<>();
		ArrayList<Collaboration> collaborations = new ArrayList<>();
		ArrayList<Person> adjacent_nodes = new ArrayList<>();
		Collaboration[] maxcollabs = null;
		ArrayList<Collaboration> unreached_nodes = new ArrayList<>();
		ArrayList<Collaboration> tmp_visited = new ArrayList<>();
		ArrayList<Collaboration> tmp_indexpoint = new ArrayList<>();
		
		Person indexpoint = null;
		boolean done = false;

		indexpoint = actor;
		visited.add(indexpoint);
		
		while (!done) {
			if (visited.containsAll(team)) {
				done = true;
			} else {
				//get adjacent nodes 
				adjacent_nodes = getCollaborators_arrayList(indexpoint);

				//controllo che i nodi adiacenti non facciano parte dei nodi visitati
				for (int i=0; i<adjacent_nodes.size(); i=i+1) {
					if (!(visited.contains(adjacent_nodes.get(i)))) {
						
						//ottengo la lista delle collaborazioni in ordine decescente
						//(da cui naturalmente posso ottenere i nodi che posso raggiungere
						
						for (int j=0; j<visited.size(); j=j+1) {
							tmp_visited.addAll(getCollaborationlist(visited.get(j)));
						}
						//uso le liste d'appoggio per ottenere le collaborazioni dei nodi visitati
						//e una lista d'appoggio delle collaborazioni del punto nodo preso in considerazione
						tmp_indexpoint = getCollaborationlist(indexpoint);
						
						tmp_indexpoint.addAll(tmp_visited);
						
						unreached_nodes = orderCollabs(tmp_indexpoint);

					}
				}
				
				//devo scegliere quale nodo aggiungere al MST
				//di default scelgo il primo elemento (perché essendo unreached_nodes
				//una lista ordinata in ordine decrescente, secondo il valore dello score
				
				//nel caso il primo elemento della lista ottenuta, faccia già parte
				//della lista visited, cerco l'elemento che non appartenga a visited
				boolean found = false;
				if (!(visited.contains(unreached_nodes.get(0).getActorB()))) {
					collaborations.add(unreached_nodes.get(0));
					visited.add(unreached_nodes.get(0).getActorB());
				
					indexpoint = (unreached_nodes.get(0).getActorB());
				
				} else {
					int i = 1;
					while ((i<unreached_nodes.size()) && (!found)) {
						if (!(visited.contains(unreached_nodes.get(i).getActorB()))) {
							collaborations.add(unreached_nodes.get(i));
							visited.add(unreached_nodes.get(i).getActorB());
							indexpoint = (unreached_nodes.get(i).getActorB());
							found = true;
						}

						i=i+1;
					}
				}
			}
		}

		if (!collaborations.isEmpty()) {
			maxcollabs = new Collaboration[collaborations.size()];
			for (int i=0; i<collaborations.size(); i=i+1) {
				maxcollabs[i] = collaborations.get(i);
			}
		}

		return maxcollabs;

	}

}
