package com.squirrel.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		
		//Pemettait de vérifier qu'on avait bien des fleurs et saisons lors de la distribution des mains et
		//qu'elles étaient bien parties dans la liste bonus
		/*System.out.println(mahjong.jEst.getBonus().size());
		System.out.println(mahjong.jOuest.getBonus().size());
		System.out.println(mahjong.jNord.getBonus().size());
		System.out.println(mahjong.jSud.getBonus().size());*/

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
			//Exception muette !!!
		}
	}
	
	@Test
	public void testAddJoueur() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		try {
			mahjong.addJoueur("crac");
			mahjong.addJoueur("hiita");
		} catch (MahjongInitialisationException e) {
			e.printStackTrace();
		}
		assertEquals(2, mahjong.player.size());
	}
	
	@Test
	public void testQuelEstMonVent() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		try {
			mahjong.addJoueur("crac");
			mahjong.addJoueur("hiita");
			mahjong.addJoueur("timbrimy");
			mahjong.addJoueur("Guest4681");
		} catch (MahjongInitialisationException e) {
			e.printStackTrace();
		}
		String vent = mahjong.quelEstMonVent("crac");
		boolean bol = false;
		if (vent.equals("Est") || vent.equals("Ouest") || vent.equals("Sud") || vent.equals("Nord")){
			bol = true;
		}
		assertTrue(bol);
	}
	@Test
	public void rechercheDeCombinaisonsDansMain() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		mahjong.initialiserUnePartie();
		
		
		try{
			
			
			List<List<Tuile>> resE = mahjong.jEst.getHand().getCombinaison();
			List<List<Tuile>> resW = mahjong.jOuest.getHand().getCombinaison();
			List<List<Tuile>> resN = mahjong.jNord.getHand().getCombinaison();
			List<List<Tuile>> resS = mahjong.jSud.getHand().getCombinaison();
			
			mahjong.jEst.getHand().res = resE;
			mahjong.jOuest.getHand().res = resW;
			mahjong.jNord.getHand().res = resN;
			mahjong.jSud.getHand().res = resS;
			
			System.out.println("-resS--------");
			System.out.println("----------");
			for (int j=0;j<resS.size();j++){
				for (Tuile t : resS.get(j)) {
					System.out.print(t.getType().getName()+" ");
					System.out.println(t.getValeur().getName()); 
				}
			}
			System.out.println("-resE--------");
			System.out.println("----------");
			for (int j=0;j<resE.size();j++){
				for (Tuile t : resE.get(j)) {
					System.out.print(t.getType().getName()+" ");
					System.out.println(t.getValeur().getName()); 
				}
			}
			System.out.println("-resW--------");
			System.out.println("----------");
			for (int j=0;j<resW.size();j++){
				for (Tuile t : resW.get(j)) {
					System.out.print(t.getType().getName()+" ");
					System.out.println(t.getValeur().getName()); 
				}
			}
			System.out.println("-resN--------");
			System.out.println("----------");
			for (int j=0;j<resN.size();j++){
				for (Tuile t : resN.get(j)) {
					System.out.print(t.getType().getName()+" ");
					System.out.println(t.getValeur().getName()); 
				}
			}
			
		}
		catch(Exception e){
		}
		
	}
}



