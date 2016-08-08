package com.squirrel.model;


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
	try {
		hand.fillHand(t);
	} catch (MainPleineException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return  hand;
}

public Hand piocheDefausse(Tuile t){
	boolean isCombi = false;
	//isCombi=fnction de test qui renvoi isCombi
	hand.pickDefausse(t,isCombi);
	return hand;
}

public int handSize() {
	return this.hand.tuilesListOfHand.size();
}
public Tuile get(int i){
	return this.hand.tuilesListOfHand.get(i);
}

}