package com.squirrel.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMahjongPartie {
	
	MahjongInitialisation mah;
	MahjongPartie mahp;

	@Test
	public void test() {
		mah=new MahjongInitialisation();
		mah.initialiserUnePartie();
		mahp = new MahjongPartie();
		
		mahp.jetteTuile(mah.jEst, mah.jEst.getHand().get(1));
		assertEquals(13, mah.jEst.getHand().handSize());
		assertEquals(1, mahp.defausse.size());
	}

}
