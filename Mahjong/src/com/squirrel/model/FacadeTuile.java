package com.squirrel.model;

import static com.squirrel.model.TuileFactory.tuilesList;

import java.util.ArrayList;

public class FacadeTuile {
	
	int i=0;
	// méthode permettant de recevoir la prochaine tuile de la liste
	public Tuile getNextTuile(){
		if(tuilesList.isEmpty()==true){
			TuileFactory.initialize();
		}
		if(i>tuilesList.size()){
			//TODO Gerer l'exception "plus de tuile"
		}
		Tuile tuile=tuilesList.get(i);
		i++;
		return tuile;
	}
	// méthode permettant de recevoir la liste des tuiles
	public ArrayList<Tuile> getTuilesList(){
		if(tuilesList.isEmpty()==true){
			TuileFactory.initialize();
		}
		return tuilesList;
	}

}
