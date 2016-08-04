package com.squirrel.model;


import static org.junit.Assert.*;

import org.junit.Test;

import com.squirrel.model.TuileFactory.TypeTuile;

public class testMur {

	@Test
	public void testAjouterTuile() {
		Mur leMur=new Mur();
		//test le remplissage des 36 cases
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			boolean actualRes=leMur.ajouterTuile(tuile);
			assertTrue(actualRes);
		}
		//Cas où on essaie de mettre une 37ème tuile dans le mur
		Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
		boolean actualRes=leMur.ajouterTuile(tuile);
		assertFalse(actualRes);
	}
	
	@Test
	public void testPiocherTuile() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);

		}
		int breche = 31;
		leMur.setBreche(breche);
		//On pioche une tuile et on vérifie que la brèche s'est déplacée et que la case du mur est vide
		leMur.piocherTuile();
		assertEquals(32, leMur.breche);
		Tuile d = new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
		d = leMur.getTuilesDuMur()[33];
		assertEquals(null, d);
	}
	
	@Test
	public void testExceptionSetBreche() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);
		}
		int breche = 36;	
		try {
			leMur.setBreche(breche);
		} catch (MurException e) {
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testExceptionBrecheNonDefinie() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);
		}
		int breche = leMur.breche;
		try {
			leMur.setBreche(breche);
			leMur.piocherTuile();
		} catch (MurException e) {
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testExceptionPiocheVide() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);
		}
		int breche = 35;
		try {
			leMur.setBreche(breche);
			leMur.piocherTuile();
			leMur.piocherTuile();
		} catch (MurException e) {
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testPiocherTuileSpeciale() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);

		}
		int brecheSpeciale = 31;
		leMur.setBrecheSpeciale(brecheSpeciale);
		//On pioche une tuile et on vérifie que la brèche s'est déplacée et que la case du mur est vide
		leMur.retirerTuile();
		assertEquals(30, leMur.brecheSpeciale);
		Tuile d = new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
		d = leMur.getTuilesDuMur()[32];
		assertEquals(null, d);
	}
	
	@Test
	public void testExceptionSetBrecheSpeciale() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);
		}
		int brecheSpeciale = 36;	
		try {
			leMur.setBrecheSpeciale(brecheSpeciale);
		} catch (MurException e) {
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testExceptionBrecheSpecialeNonDefinie() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);
		}
		int brecheSpeciale = leMur.brecheSpeciale;
		try {
			leMur.setBrecheSpeciale(brecheSpeciale);
			leMur.retirerTuile();
		} catch (MurException e) {
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testExceptionPiocheSpecialeVide() throws MurException {
		//Génération d'un mur de 36 tuiles identiques
		Mur leMur=new Mur();
		for (int i = 0; i < 36; i++) {
			Tuile tuile=new Tuile(TypeTuile.BAMB, Valuable.NumTuile.UN);
			leMur.ajouterTuile(tuile);
		}
		int brecheSpeciale = 1;
		try {
			leMur.setBrecheSpeciale(brecheSpeciale);
			leMur.retirerTuile();
			leMur.retirerTuile();
		} catch (MurException e) {
			assertTrue(e.getMessage(), true);
		}
	}
	
}
