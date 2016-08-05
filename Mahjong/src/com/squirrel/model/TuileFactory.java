package com.squirrel.model;

import static com.squirrel.model.TuileFactory.TypeTuile.BAMB;
import static com.squirrel.model.TuileFactory.TypeTuile.CARA;
import static com.squirrel.model.TuileFactory.TypeTuile.DRAG;
import static com.squirrel.model.TuileFactory.TypeTuile.FLEU;
import static com.squirrel.model.TuileFactory.TypeTuile.ROND;
import static com.squirrel.model.TuileFactory.TypeTuile.SAIS;
import static com.squirrel.model.TuileFactory.TypeTuile.VENT;
/**import static com.squirrel.Valuable.NumTuile.UN;
import static com.squirrel.Valuable.NumTuile.DEU;
import static com.squirrel.Valuable.NumTuile.TRO;
import static com.squirrel.Valuable.NumTuile.QUA;
import static com.squirrel.Valuable.NumTuile.CIN;
import static com.squirrel.Valuable.NumTuile.SIX;
import static com.squirrel.Valuable.NumTuile.SEP;
import static com.squirrel.Valuable.NumTuile.HUI;
import static com.squirrel.Valuable.NumTuile.NEU;
import static com.squirrel.Valuable.VenTuile.NOR;
import static com.squirrel.Valuable.VenTuile.EST;
import static com.squirrel.Valuable.VenTuile.SUD;
import static com.squirrel.Valuable.VenTuile.OUE;
import static com.squirrel.Valuable.DraTuile.BLA;
import static com.squirrel.Valuable.DraTuile.VER;
import static com.squirrel.Valuable.DraTuile.ROU;
import static com.squirrel.Valuable.SaiTuile.PRI;
import static com.squirrel.Valuable.SaiTuile.ETE;
import static com.squirrel.Valuable.SaiTuile.AUT;
import static com.squirrel.Valuable.SaiTuile.HIV;
import static com.squirrel.Valuable.FleTuile.PRU;
import static com.squirrel.Valuable.FleTuile.ORC;
import static com.squirrel.Valuable.FleTuile.CHR;
import static com.squirrel.Valuable.FleTuile.BAM;
 **/

import java.util.ArrayList;
import java.util.Collections;

public class TuileFactory {
	public Tuile createTuile(TypeTuile type, int i){
		if(i<=type.num.length){
			if(type==BAMB||type==ROND||type==CARA){
				return new TuileNum(type,i);
			}else if(type==VENT){
				return new TuileVen(type,i);
			}else if(type==DRAG){
				return new TuileDra(type,i);
			}else if(type==SAIS){
				return new TuileSai(type,i);
			}else if(type==FLEU){
				return new TuileFle(type,i);
			}else {
				return null;
			}
		}else {
			throw new IllegalArgumentException("Pas de "+type.getName()+"au dela de "+type.num.length);
		}
	}





	ArrayList<Tuile> tuilesList;

	public static void initialize(ArrayList<Tuile> tuilesList){
		TuileFactory tf=new TuileFactory(); 
		for (TypeTuile type: TypeTuile.values()){
			if(type==SAIS||type==FLEU){
				for(Valuable valeur : type.num ){
					Tuile tuile=tf.createTuile(type,valeur.ordinal());
					tuilesList.add(tuile);
				}
			}else{
				for (int i = 0; i<4; i++){
					for(Valuable valeur : type.num ){
						Tuile tuile=tf.createTuile(type,valeur.ordinal());
						tuilesList.add(tuile);
					}
				}
			}
		}
		for (int i = 0; i<4; i++){
			for(TuileNum.NumTuile n : TuileNum.NumTuile.values() ){
				Tuile tuile0=tf.createTuile(BAMB,n.ordinal());
				tuilesList.add(tuile0);
				Tuile tuile1=tf.createTuile(CARA,n.ordinal());
				tuilesList.add(tuile1);
				Tuile tuile2=tf.createTuile(ROND,n.ordinal());
				tuilesList.add(tuile2);
			}
			for (TuileVen.VenTuile n : TuileVen.VenTuile.values()) {
				Tuile tuile=tf.createTuile(VENT,n.ordinal());
				tuilesList.add(tuile);
			}
			for (TuileDra.DraTuile n : TuileDra.DraTuile.values()) {
				Tuile tuile=tf.createTuile(DRAG,n.ordinal());
				tuilesList.add(tuile);
			}
		}

		Collections.shuffle(tuilesList);
		tf.tuilesList=tuilesList;
	}


	public enum TypeTuile{
		BAMB("bambou",TuileNum.NumTuile.values()),
		ROND("rond",TuileNum.NumTuile.values()),
		CARA("caractère",TuileNum.NumTuile.values()),
		VENT("vent",TuileVen.VenTuile.values()),
		DRAG("dragon",TuileDra.DraTuile.values()),
		SAIS("saison",TuileSai.SaiTuile.values()),
		FLEU("fleur",TuileFle.FleTuile.values());

		String nomType;
		Valuable[] num;

		private TypeTuile(String nomType, Valuable[] num){
			this.nomType=nomType;
			this.num=num;
		}
		public String getName(){
			return nomType;
		}
	}

}
