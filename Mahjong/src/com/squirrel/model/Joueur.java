package com.squirrel.model;

import java.util.ArrayList;

import com.squirrel.model.TuileVen.VenTuile;

public class Joueur {
	
	HandFacade hand;
	VenTuile vent;
	boolean appel=false;
	int penalite =0;
	ArrayList<Tuile> bonus;
	
	public void incremPenalite() {
		this.penalite ++;
	}

	public Joueur(VenTuile vent){
		 super();
		 this.vent=vent;
	}
	
	public ArrayList<Tuile> getBonus() {
		return bonus;
	}

	public void setHand(HandFacade hand) {
		this.hand = hand;
	}

	public void setBonus(ArrayList<Tuile> bonus) {
		this.bonus = bonus;
	}

	public void appel(){
		appel=true;
	}
	public void nAppel(){
		appel=false;
	}

	public HandFacade getHand() {
		return hand;
	}
	
	public VenTuile getVent() {
		return vent;
	}

	public void setAppel(boolean appel) {
		this.appel = appel;
	}

	public boolean isAppel() {
		return appel;
	}
}
