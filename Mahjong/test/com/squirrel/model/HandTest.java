package com.squirrel.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.squirrel.model.Hand;

public class HandTest {
	public LinkedList<Tuile> hand;
	@Test 	// On crée une main dont on vérifie le remplissage, 
	//on vérifie que la demande d'ajout d'une 15eme tuile renvoie l'exception correspondante
	public void testRemplissageMain() {
		Tuile tu1= new Tuile("bambous", "1");
		Tuile tu2= new Tuile("caracteres", "1");
		Tuile tu3= new Tuile("ronds", "1");
		Tuile tu4= new Tuile("bambous", "2");
		Tuile tu5= new Tuile("bambous", "3");
		Tuile tu6= new Tuile("bambous", "4");
		Tuile tu7= new Tuile("bambous", "5");
		Tuile tu8= new Tuile("bambous", "6");
		Tuile tu9= new Tuile("bambous", "7");
		Tuile tu10= new Tuile("bambous", "8");
		Tuile tu11= new Tuile("bambous", "9");
		Tuile tu12= new Tuile("bambous", "1");
		Tuile tu13= new Tuile("bambous", "1");
		Tuile tu14= new Tuile("bambous", "1");
		Tuile tu15 = new Tuile("ronds","2");

		Hand hand = new Hand();
		try {
			hand.fillHand(tu1);
			hand.fillHand(tu2);
			hand.fillHand(tu3);
			hand.fillHand(tu4);
			hand.fillHand(tu5);
			hand.fillHand(tu6);
			hand.fillHand(tu7);
			hand.fillHand(tu8);
			hand.fillHand(tu9);
			hand.fillHand(tu10);
			hand.fillHand(tu11);
			hand.fillHand(tu12);
			hand.fillHand(tu13);
			hand.fillHand(tu14);
		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		String actual =hand.toString();
		String expected = "[[bambous, 1], [caracteres, 1], [ronds, 1], [bambous, 2], [bambous, 3], [bambous, 4], [bambous, 5], [bambous, 6], [bambous, 7], [bambous, 8], [bambous, 9], [bambous, 1], [bambous, 1], [bambous, 1]]";
		assertEquals(expected,actual);
		//tuiles_identiques(hand);


		try {
			hand.fillHand(tu15);
		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual2 =hand.toString();
		String expected2 = "[[bambous, 1], [caracteres, 1], [ronds, 1], [bambous, 2], [bambous, 3], [bambous, 4], [bambous, 5], [bambous, 6], [bambous, 7], [bambous, 8], [bambous, 9], [bambous, 1], [bambous, 1], [bambous, 1]]";
		assertEquals(expected2,actual2);

	}
	@Test 	//On crée une main on vérifie que les tuiles sont bien rangées :
	//par famille, avec les familles par ordre alphabetique
	//par valeur
	public void testTriTuiles() {
		Tuile tu1= new Tuile("bambous", "1");
		Tuile tu2= new Tuile("caracteres", "1");
		Tuile tu3= new Tuile("ronds", "1");
		Tuile tu4= new Tuile("bambous", "2");
		Tuile tu5= new Tuile("bambous", "3");
		Tuile tu6= new Tuile("bambous", "4");
		Tuile tu7= new Tuile("bambous", "5");
		Tuile tu8= new Tuile("bambous", "6");
		Tuile tu9= new Tuile("bambous", "7");
		Tuile tu10= new Tuile("bambous", "8");
		Tuile tu11= new Tuile("bambous", "9");
		Tuile tu12= new Tuile("bambous", "1");
		Tuile tu13= new Tuile("bambous", "1");
		Tuile tu14= new Tuile("bambous", "1");


		Hand hand = new Hand();

		try {
			hand.fillHand(tu1);
			hand.fillHand(tu2);
			hand.fillHand(tu3);
			hand.fillHand(tu4);
			hand.fillHand(tu5);
			hand.fillHand(tu6);
			hand.fillHand(tu7);
			hand.fillHand(tu8);
			hand.fillHand(tu9);
			hand.fillHand(tu10);
			hand.fillHand(tu11);
			hand.fillHand(tu12);
			hand.fillHand(tu13);
			hand.fillHand(tu14);
		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hand.triTuiles();


		String actual =hand.toString();
		String expected = "[[bambous, 1], [bambous, 1], [bambous, 1], [bambous, 1], [bambous, 2], [bambous, 3], [bambous, 4], [bambous, 5], [bambous, 6], [bambous, 7], [bambous, 8], [bambous, 9], [caracteres, 1], [ronds, 1]]";
		assertEquals(expected,actual);
		System.out.println(hand.toString());

	}
	@Test 	//On crée une main on vérifie que les tuiles sont bien rangées :
	//par famille, avec les familles par ordre alphabetique
	public void testTriTuilesSpeFamilles() {
		Tuile tu1= new Tuile("bambous", "1");
		Tuile tu2= new Tuile("caracteres", "1");
		Tuile tu3= new Tuile("ronds", "1");
		Tuile tu4= new Tuile("dragon", "2");
		Tuile tu5= new Tuile("vent", "3");
		Tuile tu6= new Tuile("fleur", "4");
		Tuile tu7= new Tuile("saison", "5");
		Tuile tu8= new Tuile("bambous", "1");
		Tuile tu9= new Tuile("caracteres", "1");
		Tuile tu10= new Tuile("ronds", "1");
		Tuile tu11= new Tuile("dragon", "2");
		Tuile tu12= new Tuile("vent", "3");
		Tuile tu13= new Tuile("fleur", "4");
		Tuile tu14= new Tuile("saison", "5");
		Hand hand = new Hand();

		try {
			hand.fillHand(tu1);
			hand.fillHand(tu2);
			hand.fillHand(tu3);
			hand.fillHand(tu4);
			hand.fillHand(tu5);
			hand.fillHand(tu6);
			hand.fillHand(tu7);
			hand.fillHand(tu8);
			hand.fillHand(tu9);
			hand.fillHand(tu10);
			hand.fillHand(tu11);
			hand.fillHand(tu12);
			hand.fillHand(tu13);
			hand.fillHand(tu14);
		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hand.triTuiles();


		String actual =hand.toString();
		//String expected = "[[bambous, 1], [bambous, 1], [bambous, 1], [bambous, 1], [bambous, 2], [bambous, 3], [bambous, 4], [bambous, 5], [bambous, 6], [bambous, 7], [bambous, 8], [bambous, 9], [caracteres, 1], [ronds, 1]]";
		assertEquals(expected,actual);
		System.out.println(hand.toString());

	}

	@Test 	//On crée une main on vérifie que les tuiles sont bien rangées :
	//par famille, avec les familles par ordre alphabetique
	public void testTriTuilesSpeValeurs() {
		Tuile tu1= new Tuile("bambous", "1");
		Tuile tu2= new Tuile("bambous", "2");
		Tuile tu3= new Tuile("bambous", "1");
		Tuile tu4= new Tuile("bambous", "2");
		Tuile tu5= new Tuile("bambous", "3");
		Tuile tu6= new Tuile("bambous", "9");
		Tuile tu7= new Tuile("bambous", "9");
		Tuile tu8= new Tuile("bambous", "1");
		Tuile tu9= new Tuile("caracteres", "2");
		Tuile tu10= new Tuile("caracteres", "5");
		Tuile tu11= new Tuile("caracteres", "7");
		Tuile tu12= new Tuile("caracteres", "3");
		Tuile tu13= new Tuile("caracteres", "1");
		Tuile tu14= new Tuile("caracteres", "5");
		Hand hand = new Hand();

		try {
			hand.fillHand(tu1);
			hand.fillHand(tu2);
			hand.fillHand(tu3);
			hand.fillHand(tu4);
			hand.fillHand(tu5);
			hand.fillHand(tu6);
			hand.fillHand(tu7);
			hand.fillHand(tu8);
			hand.fillHand(tu9);
			hand.fillHand(tu10);
			hand.fillHand(tu11);
			hand.fillHand(tu12);
			hand.fillHand(tu13);
			hand.fillHand(tu14);
		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hand.triTuiles();


		String actual =hand.toString();
		//String expected = "[[bambous, 1], [bambous, 1], [bambous, 1], [bambous, 1], [bambous, 2], [bambous, 3], [bambous, 4], [bambous, 5], [bambous, 6], [bambous, 7], [bambous, 8], [bambous, 9], [caracteres, 1], [ronds, 1]]";
		assertEquals(expected,actual);
		System.out.println(hand.toString());

	}

}



