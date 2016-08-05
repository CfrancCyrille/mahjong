package com.squirrel.model;

import com.squirrel.model.TuileFactory.TypeTuile;

public class TuileSai implements Tuile {
	TuileFactory.TypeTuile type;
	Valuable valeur;

	
	 TuileSai(TypeTuile type, int i){
		super();
		this.type = type;
		SaiTuile[] tabNumTuile=SaiTuile.values();
		this.valeur=tabNumTuile[i];
			
	}
	
	public TuileFactory.TypeTuile getType() {
		return type;
	}
	public Valuable getValeur() {
		return valeur;
	}

	
	
	public enum SaiTuile implements Valuable{
		PRI("printemps"),ETE("été"),AUT("automne"),HIV("hiver");
		String nomNum; 
		int value;
		private SaiTuile(String nomNum){
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
