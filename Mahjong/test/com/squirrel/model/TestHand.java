package com.squirrel.model;



//import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;
import org.junit.Test;


import com.squirrel.model.Hand;

public class TestHand {

	@Test
	public void testTri (){
		/**
		 *Ce test crée les tuiles du jeu de Mahjong (elles sont mélangées lors de leur création)
		 *On teste la capacité de notre fonction triTuile() à les ranger par famille et par valeur 
		 *Test validé le 04/08/2016 16:30
		 **/
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();

		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour le test.
		List<Tuile> list= hand.tuilesListOfHand;

		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		//on affiche list avant de la trier
		System.out.println(list);


		//On trie notre main  
		hand.triTuiles((List<Tuile>) list);

		//On visualise ce que contient la liste rentrée en paramètre.
		System.out.println(list);
		//On visualise ce que contient liste remplie.
		System.out.println(hand.tuilesListOfHand);
		for (int i=0; i<144;i++){
			System.out.print(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getType().getName());
			System.out.print(" ");
			System.out.println(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getValeur().getName());
		}


	}



	@Test 	
	/** On crée une main dont on vérifie le remplissage, 
	 * on vérifie que la demande d'ajout d'une 15eme tuile renvoie l'exception correspondante
	 * validé le 04/08/2016 17:30
	 */
	public void testRemplissageMain() {
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();
		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour piocher dedans .
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);

		try {

			for(int i=0; i<14 ; i++){
				hand.fillHand(facadeTuile.getITuile(i,list));
			}
			hand.fillHand(facadeTuile.getITuile(14,list));

		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			for (Tuile t : hand.tuilesListOfHand) {
				System.out.print(t.getType().getName());
				System.out.println(t.getValeur().getName()); 
			}

		}

	}

}



