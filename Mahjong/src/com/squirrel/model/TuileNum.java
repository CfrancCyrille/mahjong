package com.squirrel.model;

import com.squirrel.model.TuileFactory.TypeTuile;

public class TuileNum implements Tuile {
	TuileFactory.TypeTuile type;
	Valuable valeur;
	public static final int LENGTH=8;
	
	 TuileNum(TypeTuile type, int i){
		super();
		this.type = type;
		NumTuile[] tabNumTuile=NumTuile.values();
		this.valeur=tabNumTuile[i];
			
	}
	
	public TuileFactory.TypeTuile getType() {
		return type;
	}
	public Valuable getValeur() {
		return valeur;
	}
	
	public enum NumTuile implements Valuable{
		UN("un"),DEU("deux"),TRO("trois"),QUA("quatre"),CIN("cinq"),SIX("six"),SEP("sept"),HUI("huit"),NEU("neuf");
		String nomNum;
		int value;
		private NumTuile(String nomNum){
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
