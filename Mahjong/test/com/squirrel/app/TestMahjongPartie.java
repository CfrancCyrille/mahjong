package com.squirrel.app;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.squirrel.model.Joueur;

public class TestMahjongPartie {
	
	MahjongPartie mahp;

	@Test
	public void testJetteTuile() {
		mahp = new MahjongPartie();
		mahp.mah.initialiserUnePartie();
		
		
		mahp.jetteTuile(mahp.mah.jEst, mahp.mah.jEst.getHand().get(1));
		assertEquals(13, mahp.mah.jEst.getHand().handSize());
		assertEquals(1, mahp.defausse.size());
	}
	
	@Test
	public void testOrdreJoueurs() {
		mahp = new MahjongPartie();
		mahp.mah.initialiserUnePartie();
		ArrayList<Joueur> liste = new ArrayList<Joueur>();
		
		liste = mahp.ordreJoueurs(mahp.mah.jSud);
		assertEquals(mahp.mah.jSud, liste.get(0));
		assertEquals(mahp.mah.jOuest, liste.get(1));
		assertEquals(mahp.mah.jNord, liste.get(2));
		assertEquals(mahp.mah.jEst, liste.get(3));
	}
	

}
