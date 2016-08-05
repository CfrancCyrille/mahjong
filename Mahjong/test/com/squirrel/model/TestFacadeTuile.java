package com.squirrel.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.squirrel.model.TuileFactory.TypeTuile;
import com.squirrel.model.Tuile;


public class TestFacadeTuile extends FacadeTuile{

	@Test
	public void testNextTuile() {
		ArrayList<Tuile> tuilesList = new ArrayList<Tuile>();
		getNextTuile(tuilesList);
		assertEquals(tuilesList.isEmpty(),false);
		for (int i = 0; i < 146; i++) {
			Tuile tuile = getNextTuile(tuilesList);
			if (i>143) {
				Tuile resultat =null;
				assertEquals(tuile.getType(),resultat.getType());
				assertEquals(tuile.getValeur(),resultat.getValeur());
			}
		}
	}

	@Test
	public void testTuileList() {
		ArrayList<Tuile> tuilesList = new ArrayList<Tuile>();
		getTuilesList(tuilesList);
		assertEquals(144,tuilesList.size());
	}
	
	@Test
	public void testItuile() {
		ArrayList<Tuile> tuilesList = new ArrayList<Tuile>();
		getITuile(0,tuilesList);
		assertEquals(tuilesList.isEmpty(),false);
	}
	
}

