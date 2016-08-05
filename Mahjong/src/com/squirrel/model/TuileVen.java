package com.squirrel.model;

import com.squirrel.model.TuileFactory.TypeTuile;

public class TuileVen implements Tuile {
	TuileFactory.TypeTuile type;
	Valuable valeur;

	
	 TuileVen(TypeTuile type, int i){
		super();
		this.type = type;
		VenTuile[] tabNumTuile=VenTuile.values();
		this.valeur=tabNumTuile[i];
			
	}
	
	public TuileFactory.TypeTuile getType() {
		return type;
	}
	public Valuable getValeur() {
		return valeur;
	}
	public enum VenTuile implements Valuable{
		EST("est"),SUD("sud"),OUE("ouest"),NOR("nord");
		String nomNum;
		int value;
		private VenTuile(String nomNum){
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
