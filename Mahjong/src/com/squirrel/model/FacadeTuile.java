package com.squirrel.model;
import java.util.ArrayList;
import com.squirrel.model.TuileFactory.TypeTuile;

/**
 * 
 * @author Kevin
 *
 */
public class FacadeTuile {
	
	int i=0;
	// méthode permettant de recevoir la prochaine tuile de la liste
	public Tuile getNextTuile(ArrayList<Tuile> tuilesList){
		Tuile tuile=null;
		if(tuilesList.isEmpty()==true){
			TuileFactory.initialize(tuilesList);
		}
		if(i>tuilesList.size()-1){
			//TODO Gerer l'exception "plus de tuile"
			Tuile fausseTuile=new Tuile(TypeTuile.DRAG,Valuable.SaiTuile.ETE);
			return fausseTuile;
		}else{
			tuile=tuilesList.get(i);
		}
		
		i++;
		return tuile;
	}
	
	// méthode permettant de recevoir la liste des tuiles
	public ArrayList<Tuile> getTuilesList(ArrayList<Tuile> tuilesList){
		if(tuilesList.isEmpty()==true){
			TuileFactory.initialize(tuilesList);
		}
		return tuilesList;
	}
	
	// méthode permettant d'obtenir la ieme tuile
	public Tuile getITuile(int i,ArrayList<Tuile> tuilesList){
		if(tuilesList.isEmpty()==true){
			TuileFactory.initialize(tuilesList);
		}
		Tuile tuile=null;
		tuile= tuilesList.get(i);
		//TODO Gerer l'exception out of boundary sur le get(i)
		return tuile;
	}
}
