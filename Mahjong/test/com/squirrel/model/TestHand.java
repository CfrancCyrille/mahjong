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
		 *Ce test cr�e les tuiles du jeu de Mahjong (elles sont m�lang�es lors de leur cr�ation)
		 *On teste la capacit� de notre fonction triTuile() � les ranger par famille et par valeur 
		 *Test valid� le 04/08/2016 16:30
		 **/
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();

		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour le test.
		List<Tuile> list= hand.tuilesListOfHand;

		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		//on affiche list avant de la trier
		System.out.println(list);


		//On trie notre main  
		hand.triTuiles((List<Tuile>) list);

		//On visualise ce que contient la liste rentr�e en param�tre.
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
	/** On cr�e une main dont on v�rifie le remplissage, 
	 * on v�rifie que la demande d'ajout d'une 15eme tuile renvoie l'exception correspondante
	 * valid� le 04/08/2016 17:30
	 */
	public void testRemplissageMain() {
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();
		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour piocher dedans .
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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



