package com.squirrel.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.squirrel.app.Mahjong;

public class testMahjong {

	@Test
	public void test() {
		Mahjong mahjong=new Mahjong();
		mahjong.initialiserUnePartie();
		
		assertEquals(14, mahjong.mainEst.handSize());
	
	}

}
