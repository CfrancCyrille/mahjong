package com.squirrel.model;

import com.squirrel.model.TuileFactory.TypeTuile;


public class TuileDra implements Tuile {
	TuileFactory.TypeTuile type;
	Valuable valeur;

	
	 TuileDra(TypeTuile type, int i){
		super();
		this.type = type;
		DraTuile[] tabNumTuile=DraTuile.values();
		this.valeur=tabNumTuile[i];
			
	}
	
	public TuileFactory.TypeTuile getType() {
		return type;
	}
	public Valuable getValeur() {
		return valeur;
	}

	
	public enum DraTuile implements Valuable{
		BLA("blanc"),VER("vert"),ROU("rouge");
		String nomNum; 
		int value;
		private DraTuile(String nomNum){
			this.nomNum=nomNum;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomNum;
		}
	}
}
