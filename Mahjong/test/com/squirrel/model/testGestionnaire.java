package com.squirrel.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.squirrel.ctrl.Gestionnaire;

public class testGestionnaire {

	@Test
	public void testDes() {
		int resultatDes;
		resultatDes = Gestionnaire.lancerDes();

		boolean scoreDes;
		if(0 < resultatDes && resultatDes <7 ){

			scoreDes = true;
		}

		else{scoreDes=false;}
		assertTrue(scoreDes);

	}



	@Test
	public void testMurOuestaDetruire() {
		String mur;
		mur = Gestionnaire.muraDetruire(4, 3);
		assertEquals("Ouest", mur);

	}

	@Test
	public void testMuraEstDetruire() {
		String mur;
		mur = Gestionnaire.muraDetruire(6, 3);
		assertEquals("Est", mur);

	}

	@Test
	public void testMurSudaDetruire() {
		String mur;
		mur = Gestionnaire.muraDetruire(4, 2);
		assertEquals("Sud", mur);

	}
	@Test
	public void testMurNordaDetruire() {
		String mur;
		mur = Gestionnaire.muraDetruire(6, 6);
		assertEquals("Nord", mur);

	}

	@Test
	public void testPremierTirage() {
		int premierJoueur;
		premierJoueur = Gestionnaire.premierTirage();

		boolean scoreDes;
		if(0 < premierJoueur && premierJoueur <5 ){

			scoreDes = true;
		}

		else{scoreDes=false;}
		assertTrue(scoreDes);

	}


	@Test
	public void testMurBrechable() {
		String mur;
		mur = Gestionnaire.murBrechable(12, "Nord");
		assertEquals("Nord", mur);
	}


	@Test
	public void testMurBrechable2() {
		String mur;
		mur = Gestionnaire.murBrechable(22, "Nord");
		assertEquals("Ouest", mur);
	}

	@Test
	public void testbreche() {
		int breche;
		breche = Gestionnaire.breche(23);
		assertEquals(8, breche);

	}

}
