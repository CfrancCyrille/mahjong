package com.squirrel.model;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.squirrel.MainPleineException;
import com.squirrel.Tuile;
import com.squirrel.TuileComparatorType;
import com.squirrel.TuileComparatorVal;




public class Hand {

	//public Tuile[] hand;
	public LinkedList<Tuile> hand;
	//private List<Customer> listCustomer = new ArrayList<Customer>();
	final int TAILLE_MAIN=14; // constante taille de la main
	

	//private List<Tuile> listTuile = new ArrayList<Tuile>();
	//	public Hand() {
	//		hand=new Tuile[TAILLE_MAIN];
	//		
	//	}
	//constructeur
	public Hand(){
		
		this.hand = new LinkedList<Tuile>();
	}
	// TODO : test est une linkedlist?
	
	
	
	
	//permet d'ajouter une tuile à la liste main depuis le Mur
	Collection fillHand(Tuile t) throws MainPleineException{
		if(hand.size()<TAILLE_MAIN){
			this.hand.add(t);
		}else{
			throw new MainPleineException();
		}
		return this.hand;
		
		

	}
	// TODO : test : on peut ajouter des tuiles OK/ on ne peut pas ajouter une 15eme tuile OK
	
	@Override
	public String toString() {
		return   hand.toString() ;
	}




	// permet d'ajouter une tuile à la liste main depuis la défausse : conditions associées à cette fonction
	Collection pickDefausse(Tuile t, boolean isCombi){
		if (isCombi == true && hand.size()<TAILLE_MAIN){
			this.hand.add(t);
		}else{ System.out.println("Vous ne pouvez pas prendre cette tuile");
		//TODO Ajout une pénalité auscore du joueur
		}
		return this.hand;
	}
	// TODO : test :  on peut ajouter des tuiles / on ne peut pas ajouter une 15eme tuile  / si pas de combi, on ne peut pas la prendre
	
	
	public Collection triTuiles(){
		//Range les tuiles par orde alphabetique selon le type
	 Collections.sort(hand, new TuileComparatorType());
	 	//Dans une famille, range par valeur
	 Collections.sort(hand, new TuileComparatorVal());
	return hand;
	 
	}




	public void shuffle() {
		// TODO Auto-generated method stub
		
	}
	
	// TODO : test : rangement effectif par famille / rangement effectif par valeur dans une famille
	 
	 
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List tuilesIdentiques(Tuile[] hand){
//		int gap = 0;			 //distance entre début du tableau et élément comparé
//		//on compare la tuile uniquement avec les tuiles à droite d'elle
//		int count = 1;			 // nb de tuiles identiques trouvées
//		//Tuile sample; 		// tuile que l'on compare et qui est au moins en 2 exemplaires
//
//		List<StringBuffer> listBuffer = new ArrayList<StringBuffer>();
//
//		Arrays.sort(this.hand, new ComparatorTuile());
//
//		for (int i=gap; i<hand.length-1; i++){  		// parcourt la main de gauche à droite
//			StringBuffer buffer = new StringBuffer(i);
//			for (int j = gap+1; j < hand.length; j++) {  //comparaison des éléments à droite de la tuile à la place gap.
//
//				if (hand[i] == hand[j]){
//					count++;
//					//sample = hand[i];
//					buffer.append (",").append (j); 		// on enregistre les coordonnées des tuiles identiques sous forme d'une chaine de car
//					//on pourra donc récuperer les coordonnées des tuiles, leur valeur et leur nombre
//
//					if (count==4){  //max de 4 tuiles identiques dans le jeu, donc stop recherche si 4 trouvées.
//						break;
//					}
//				} 
//			} listBuffer.add(buffer);
//
//
//		} 
//
//		return listBuffer;
//
//	}


}




