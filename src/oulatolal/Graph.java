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

public class Graph {
	 private HashMap<Person, ArrayList<Collaboration>> graph;
	 Graph(){
		 graph = new HashMap<Person, ArrayList<Collaboration> >();
	 }
	 
	 public void addNode(Person p) {
		 if (!graph.containsKey(p))
		 graph.put(p, new ArrayList<Collaboration>());	 
	 }

	 public void insertMovieGraph(Movie m) {
		 for (int i=0; i<m.getCast().length; i=i+1) {
			 for (int j=0; j<m.getCast().length; j=j+1) {
				 if (m.getCast()[i].equals(m.getCast()[j])) {
					 for (int k=0; k<m.getCast().length; k=k+1) {
						 if (!(m.getCast()[i].equals(m.getCast()[k]))) {
							 setCollaborations(m.getCast()[i], m.getCast()[k]);
						 }
					 }
				 }
			 }
		 }
	 }
	 
	 
	 public void setCollaborations(Person p, Person p1) {
	    if (!(graph.containsKey(p))) {
	    	addNode(p);
	    }
	    if (!graph.containsKey(p1)) {
	    	addNode(p1);
	    }
	    Collaboration collaboration = new Collaboration(p, p1);
	    graph.get(p).add(collaboration);
	    graph.get(p1).add(collaboration);
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
	 
	private ArrayList<Person> starting_list (ArrayList<Person[]> collaborators, Person actor, ArrayList<Person> collaborators_list){
		ArrayList<Person> tmp_people = new ArrayList<>();
		for (Person[] p_iterator: collaborators) {
			if (p_iterator!=null) {
			for (int i=0; i<p_iterator.length; i=i+1) {
				if (!(p_iterator[i].equals(actor)))
				tmp_people.add(p_iterator[i]);
				}
			}
		}
		if (tmp_people!=null) {
		tmp_people.removeAll(collaborators_list);	
		}
		return tmp_people;
	}
	
	
	public Person [] getTeam(Person actor) {
		ArrayList<Person []> tmp = new ArrayList<>();
		ArrayList<Person> tmp_people = new ArrayList<>();
		ArrayList<Person> people_team = new ArrayList<>();
		Person [] collaborators = getCollaborators(actor);
		ArrayList<Person> collaborators_list = new ArrayList<>();
		Person[] teamOf = null;
 		for (int i=0; i<collaborators.length; i=i+1) {
			collaborators_list.add(collaborators[i]);
		}
		
			for (int i=0; i<collaborators_list.size(); i=i+1) 
			tmp.add(getCollaborators(collaborators_list.get(i)));
			
			tmp_people.addAll(starting_list(tmp, actor, collaborators_list));
			if (tmp_people!=null) {
				for (int i=0; i<tmp_people.size(); i=i+1) {
					people_team.add(tmp_people.get(i));
				}
		
				for (int i=0; i<tmp_people.size(); i=i+1) {
					collaborators_list.add(tmp_people.get(i));
				}
				for (int i=0; i<people_team.size(); i=i+1) {
					tmp.add(getCollaborators(people_team.get(i)));
					}
				}
			
			tmp_people.addAll(starting_list(tmp, actor, collaborators_list));
			
			tmp_people.removeAll(collaborators_list);
			
			for (int i=0; i<tmp_people.size(); i=i+1) {
				people_team.add(tmp_people.get(i));
			}
			if (people_team!=null) {
				people_team.add(actor);
				teamOf = new Person[people_team.size()];
				for (int i=0; i<teamOf.length; i=i+1) {
					teamOf[i] = people_team.get(i);
				}
			}
		
		return teamOf;
	}

	
	
	 
}
