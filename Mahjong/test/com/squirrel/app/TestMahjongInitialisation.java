package com.squirrel.app;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.squirrel.app.MahjongInitialisation;
import com.squirrel.model.Tuile;

public class TestMahjongInitialisation {

	@Test
	public void testDistributionMain() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		mahjong.initialiserUnePartie();

		assertEquals(14, mahjong.mainEst.handSize());
		assertEquals(13, mahjong.mainOuest.handSize());
		assertEquals(13, mahjong.mainNord.handSize());
		assertEquals(13, mahjong.mainSud.handSize());

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
				Tuile tuile1=mahjong.mainEst.get(i);
				Tuile tuile2=mahjong.mainOuest.get(i);
				Tuile tuile3=mahjong.mainNord.get(i);
				Tuile tuile4=mahjong.mainSud.get(i);
				listeTuiles.add(tuile1);
				listeTuiles.add(tuile2);
				listeTuiles.add(tuile3);
				listeTuiles.add(tuile4);
			}
			listeTuiles.add(mahjong.mainEst.get(13));
			assertEquals(144,listeTuiles.size());
			for (Tuile t : listeTuiles) {
				assertTrue(mahjong.listeTuiles.contains(t));
			}
			
		}
		catch(Exception e){
		}
	}


}


