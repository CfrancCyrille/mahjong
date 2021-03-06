package com.squirrel.model;
import com.squirrel.model.TuileFactory.TypeTuile;
/**Classe des tuiles de type Fleur.
 * 
 * @author Kevin
 *
 */
public class TuileFle implements Tuile {
	TuileFactory.TypeTuile type;
	Valuable valeur;

	
	 TuileFle(TypeTuile type, int i){
		super();
		this.type = type;
		FleTuile[] tabNumTuile=FleTuile.values();
		this.valeur=tabNumTuile[i];
			
	}
	
	public TuileFactory.TypeTuile getType() {
		return type;
	}
	public Valuable getValeur() {
		return valeur;
	}

	
	
	public enum FleTuile implements Valuable{
		PRU("prunier"),ORC("orchidée"),CHR("chrysanthème"),BAM("bambou");
		String nomNum;
		int value;
		private FleTuile(String nomNum){
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
