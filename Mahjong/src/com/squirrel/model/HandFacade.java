package com.squirrel.model;

import java.util.Collection;
import java.util.LinkedList;

public class HandFacade {
	private Hand hand;
	   //private ??? combi;

	   public HandFacade() {
		   hand = new Hand();
		 //??? combi;
	   }
	   

	
	
public void getCombinaison(){
	//fonction qui trie
	//fonction qui groupe
	//fonction qui voie les suites

	//TODO : hand.combinaison(); //fonction qui fait correspondre les combi
	//return combi; 
}

public Hand remplirMain(Tuile t){
	hand.fillHand(t);
	return  hand;
}

public Hand piocheDefausse(Tuile t){
	boolean isCombi = false;
	//isCombi=fnction de test qui renvoi isCombi
	hand.pickDefausse(t,isCombi);
	return hand;
}


}