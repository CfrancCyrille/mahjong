package com.squirrel.app;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.squirrel.app.MahjongInitialisation;
import com.squirrel.model.Tuile;
import com.squirrel.model.TuileFactory.TypeTuile;

public class TestMahjongInitialisation {

	@Test
	public void testDistributionMain() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		mahjong.initialiserUnePartie();

		assertEquals(14, mahjong.jEst.getHand().handSize());
		assertEquals(13, mahjong.jOuest.getHand().handSize());
		assertEquals(13, mahjong.jNord.getHand().handSize());
		assertEquals(13, mahjong.jSud.getHand().handSize());
		
		boolean bol = true;
		for (int i = 0; i < mahjong.jEst.getHand().handSize(); i++) {
			if (mahjong.jEst.getHand().get(i).getType().equals(TypeTuile.FLEU) || mahjong.jEst.getHand().get(i).getType().equals(TypeTuile.SAIS)){
				bol = false;
			}
		}
		for (int i = 0; i < mahjong.jOuest.getHand().handSize(); i++) {
			if (mahjong.jOuest.getHand().get(i).getType().equals(TypeTuile.FLEU) || mahjong.jOuest.getHand().get(i).getType().equals(TypeTuile.SAIS)){
				bol = false;
			}
		}
		for (int i = 0; i < mahjong.jNord.getHand().handSize(); i++) {
			if (mahjong.jNord.getHand().get(i).getType().equals(TypeTuile.FLEU) || mahjong.jNord.getHand().get(i).getType().equals(TypeTuile.SAIS)){
				bol = false;
			}
		}
		for (int i = 0; i < mahjong.jSud.getHand().handSize(); i++) {
			if (mahjong.jSud.getHand().get(i).getType().equals(TypeTuile.FLEU) || mahjong.jSud.getHand().get(i).getType().equals(TypeTuile.SAIS)){
				bol = false;
			}
		}
		assertTrue(bol);
		
		System.out.println(mahjong.jEst.getBonus().size());
		System.out.println(mahjong.jOuest.getBonus().size());
		System.out.println(mahjong.jNord.getBonus().size());
		System.out.println(mahjong.jSud.getBonus().size());

	}

	@Test
	public void testIntegrationTuiles() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		mahjong.initialiserUnePartie();
		
		try{
			ArrayList<Tuile> listeTuiles= new ArrayList<Tuile>();
			for (int i = -2; i < 34; i++) {
				Tuile tuile1 = mahjong.murEst.piocherTuile();
				Tuile tuile2 = mahjong.murNord.piocherTuile();
				Tuile tuile3 = mahjong.murOuest.piocherTuile();
				Tuile tuile4 = mahjong.murSud.piocherTuile();
				if (tuile1!=null){
					listeTuiles.add(tuile1);
				}
				if (tuile2!=null){
					listeTuiles.add(tuile2);
				}
				if (tuile3!=null){
					listeTuiles.add(tuile3);
				}
				if (tuile4!=null){
					listeTuiles.add(tuile4);
				}	
			}
			for (int i =0;i<13;i++){
				Tuile tuile1=mahjong.jEst.getHand().get(i);
				Tuile tuile2=mahjong.jOuest.getHand().get(i);
				Tuile tuile3=mahjong.jNord.getHand().get(i);
				Tuile tuile4=mahjong.jSud.getHand().get(i);
				listeTuiles.add(tuile1);
				listeTuiles.add(tuile2);
				listeTuiles.add(tuile3);
				listeTuiles.add(tuile4);
			}
			listeTuiles.add(mahjong.jEst.getHand().get(13));
			assertEquals(144,listeTuiles.size());
			for (Tuile t : listeTuiles) {
				assertTrue(mahjong.listeTuiles.contains(t));
			}
			
		}
		catch(Exception e){
		}
	}


}


