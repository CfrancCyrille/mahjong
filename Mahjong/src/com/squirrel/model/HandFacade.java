package com.squirrel.model;

import java.util.ArrayList;
import java.util.List;

public class HandFacade {

	private Hand hand;
	boolean mahjongPossible=false;
	List<List<Tuile>> res;

/**
 * Constructeur de la facade
 */
	public HandFacade() {
		this.hand = new Hand();
		this.res=new ArrayList<List<Tuile>>();
	}

	/**
	 * Fonction permettant de trier les tuiles dans une liste de tuiles
	 * Tri�es par familles (ordre alphabetique) puis par valeur
	 */
	public void triLesTuiles(List<Tuile> l){
		hand.triTuiles(l);
	}	

	/**
	 * Fonction permettant de trier les tuiles de la main, de former des groupes et de chercher des recombinaisons afin de maximiser l'efficacite de la recherche
	 * Renvoie une liste des combinaisons trouv�es (chacune est une liste)
	 */
	public List<List<Tuile>> getCombinaison(){
		return res = hand.findCombinaisons(hand.tuilesListOfHand);
	}
	/**
	 * Fonction permettant de remplir de tuiles un objet de type main
	 */
	public Hand remplirMain(Tuile t){
		try {
			hand.fillHand(t);
		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  hand;
	}
	/**
	 * Fonction permettant de rajouter une tuile d�fauss�e � la main, si elle forme une combinaison
	 */
	public Hand piocheDefausse(Tuile t){
		boolean isCombi = false;
		//TODOisCombi=fnction de test qui renvoi isCombi
		hand.pickDefausse(t,isCombi);
		return hand;
	}
	/**
	 * Fonction permettant de r�cup�rer la taille de la main
	 */
	public int handSize() {
		return this.hand.tuilesListOfHand.size();
	}
	/**
	 * Fonction permettant de r�cup�rer la i�me tuile de la main
	 */
	public Tuile get(int i){
		return this.hand.tuilesListOfHand.get(i);
	}
	/**
	 * Fonction permettant de retirer une tuile de la main
	 */
	public void remove(Tuile t){
		this.hand.tuilesListOfHand.remove(t);
	}
}