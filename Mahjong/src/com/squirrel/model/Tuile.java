package com.squirrel.model;
import com.squirrel.model.TuileFactory.TypeTuile;

/**Cette Interface est m�re des classes TuileNum, TuileFle, TuileSai, TuileVen et TuileDra.
 * 
 * @author Kevin
 *
 */
public interface Tuile {
	public TypeTuile getType();
	public Valuable getValeur();
	
}