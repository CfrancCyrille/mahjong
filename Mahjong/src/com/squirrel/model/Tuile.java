package com.squirrel.model;
import com.squirrel.model.TuileFactory.TypeTuile;
/**Cette classe est un cr�ateur de tuiles.
 * @author Kevin
 */
public class Tuile {
	/**Une tuile est d�finie par son type et sa valeur.
	 * Ces deux variables sont �tendues au package pour �tre appel�es depuis les classes de gestion de combinaisons
	 */
	TuileFactory.TypeTuile type;
	Valuable valeur;

	/**Cronstructeur de tuiles pour cr�er les objets tuiles avec un type et une valeur
	 * @param type
	 * @param valeur
	 */
	public Tuile(TypeTuile type, Valuable valeur) {
		super();
		this.type = type;
		this.valeur = valeur;
	}
	
	/**Getters sur les arguments des tuiles pour obtenir facilement des informations depuis une autre partie du programme
	 * @return type, valeur
	 */
	public TuileFactory.TypeTuile getType() {
		return type;
	}
	public Valuable getValeur() {
		return valeur;
	}

}
