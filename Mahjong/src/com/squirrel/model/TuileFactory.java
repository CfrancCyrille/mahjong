package com.squirrel.model;

import static com.squirrel.model.TuileFactory.TypeTuile.BAMB;
import static com.squirrel.model.TuileFactory.TypeTuile.CARA;
import static com.squirrel.model.TuileFactory.TypeTuile.DRAG;
import static com.squirrel.model.TuileFactory.TypeTuile.FLEU;
import static com.squirrel.model.TuileFactory.TypeTuile.ROND;
import static com.squirrel.model.TuileFactory.TypeTuile.SAIS;
import static com.squirrel.model.TuileFactory.TypeTuile.VENT;

import java.util.ArrayList;
import java.util.Collections;

import com.squirrel.model.Valuable.DraTuile;
import com.squirrel.model.Valuable.FleTuile;
import com.squirrel.model.Valuable.NumTuile;
import com.squirrel.model.Valuable.SaiTuile;
import com.squirrel.model.Valuable.VenTuile;

public class TuileFactory {
	//int numero;
	//String famille;		//indice pour differencier les 4 exemplaires identiques
	
	//private static TypeTuile[] tabType=new TypeTuile[144];
	//private static NumTuile[] tabNum=new NumTuile[144];
	
	static ArrayList<Tuile> tuilesList;
	
	public static void initialize(){
		 //Création de la liste de tuiles.
	    ArrayList<Tuile> tuilesList = new ArrayList<Tuile>();
		for (int i = 0; i < 4; i++) {
			for (Valuable.NumTuile Num : Valuable.NumTuile.values() ) {
				Tuile tuile=new Tuile(BAMB, Num);
				tuilesList.add(tuile);
				Tuile tuile2=new Tuile(CARA, Num);
				tuilesList.add(tuile2);
				Tuile tuile3=new Tuile(ROND, Num);
				tuilesList.add(tuile3);
			}
			for (Valuable.VenTuile Num : Valuable.VenTuile.values() ) {
				Tuile tuile=new Tuile(VENT, Num);
				tuilesList.add(tuile);
			}
			for (Valuable.DraTuile Num : Valuable.DraTuile.values() ) {
				Tuile tuile=new Tuile(DRAG, Num);
				tuilesList.add(tuile);
			}
			
		}
		for (Valuable.SaiTuile Num : Valuable.SaiTuile.values() ) {
			Tuile tuile=new Tuile(SAIS,Num);
			tuilesList.add(tuile);
		}
		for (Valuable.FleTuile Num : Valuable.FleTuile.values() ) {		
			Tuile tuile=new Tuile(FLEU, Num);
			tuilesList.add(tuile);
		}
		Collections.shuffle(tuilesList);
		TuileFactory.tuilesList=tuilesList;
	}
	
	

	public static void main(String[] args) {
		
		System.out.println("Gros Stress oulala");
		initialize();
		System.out.println(tuilesList);
		
		for (int i = 0; i < 144; i++) {
			System.out.println(tuilesList.get(i).getType().getName()+" "+tuilesList.get(i).getValeur().getName());
		}
		Collections.shuffle(tuilesList);

	}
	
	
	
	public enum TypeTuile{
		BAMB("bambou"),ROND("rond"),CARA("caractère"),VENT("vent"),DRAG("dragon"),SAIS("saison"),FLEU("fleur");
		
		String nomType;
		
		private TypeTuile(String nomType){
			this.nomType=nomType;
		}
		public String getName(){
			return nomType;
		}
		
	}
	
	

	
}
