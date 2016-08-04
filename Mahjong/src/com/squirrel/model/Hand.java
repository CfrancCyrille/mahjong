package com.squirrel.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class Hand {

	public List<Tuile> tuilesListOfHand;
	final int TAILLE_MAIN=14; // constante taille de la main (maximum)
	
	//constructeur
	public Hand(){
		
		this.tuilesListOfHand = new ArrayList<Tuile>();
	}

	//fillHand() permet d'ajouter une tuile t à la liste tuilesListOfHand 
	// Dans le jeu la tuile vient du Mur
	Collection<Tuile> fillHand(Tuile t) throws MainPleineException{
		if(tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{
			throw new MainPleineException();
		}
		return this.tuilesListOfHand;
		
		

	}
	
	//Affichage
	@Override
	public String toString() {
		return   tuilesListOfHand.toString() ;
	}

	// pickDefausse() permet d'ajouter une tuile t à la liste tuilesListOfHand depuis la défausse 
	// Seulement si la tuile de la défausse forme une combinaison avec celles dans tuilesListOfHand (isCombi)
	Collection<Tuile> pickDefausse(Tuile t, boolean isCombi){
		if (isCombi == true && tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{ System.out.println("Vous ne pouvez pas prendre cette tuile");
		//TODO Ajout une pénalité au score du joueur
		}
		return this.tuilesListOfHand;
	}
	// TODO : test :  on peut ajouter des tuiles / on ne peut pas ajouter une 15eme tuile  / si pas de combi, on ne peut pas la prendre
	
	//triTuiles(hand) tri les tuiles selon leur type puis leurs valeurs
	public Collection<Tuile> triTuiles(List<Tuile> hand){
		//Range les tuiles par orde alphabetique selon le type
	 	//Dans une famille, range par valeur
	 Collections.sort(hand, new TuileComparatorVal());
	return hand;
	 
	}
	 
	 
	 // TODO : parcourt de la liste si besoin à définir
	/*Iterator itr = al.iterator();
    while(itr.hasNext()) {
       Object element = itr.next();
       System.out.print(element + " ");
    }*/
	
	
	// TODO identification des groupes de tuiles (2,3,4) et suites
	// TODO : test
	

	// TODO identification des combinaisons parmis les groupes
	// TODO : test
}




