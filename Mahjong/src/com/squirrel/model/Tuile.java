package com.squirrel.model;
import com.squirrel.model.TuileFactory.TypeTuile;

/**Cette Interface est mère des classes TuileNum, TuileFle, TuileSai, TuileVen et TuileDra.
 * 
 * @author active_admin
 *
 */
public interface Tuile {
	public TypeTuile getType();
	public Valuable getValeur();
	
}