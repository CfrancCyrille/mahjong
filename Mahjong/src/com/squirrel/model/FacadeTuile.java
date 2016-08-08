package com.squirrel.model;
import java.util.ArrayList;


/**Cette classe essaie de respecter le design pattern de facade.
 * Elle a �t� cr��e dans le but de faciliter les op�ration sur les Tuiles relatives � la classe TuileFactory.
 * 
 * @author Kevin
 *
 */
public class FacadeTuile {
	TuileFactory tf=new TuileFactory();
	int i=0;
	// m�thode permettant de recevoir la prochaine tuile de la liste
	public Tuile getNextTuile(ArrayList<Tuile> tuilesList){
		Tuile tuile=null;
		if(tuilesList.isEmpty()==true){
			tf.initialize(tuilesList);
		}
		if(i<tuilesList.size()){
			tuile=tuilesList.get(i);
		}else{
			tuile=null;
		}
		
		i++;
		return tuile;
	}
	
	// m�thode permettant de recevoir la liste des tuiles
	public  ArrayList<Tuile> getTuilesList(ArrayList<Tuile> tuilesList){
		if(tuilesList.isEmpty()==true){
			tf.initialize(tuilesList);
		}
		return tuilesList;
	}
	
	// m�thode permettant d'obtenir la ieme tuile
	public Tuile getITuile(int i,ArrayList<Tuile> tuilesList){
		if(tuilesList.isEmpty()==true){
			tf.initialize(tuilesList);
		}
		Tuile tuile=null;
		tuile= tuilesList.get(i);
		//TODO Gerer l'exception out of boundary sur le get(i)
		return tuile;
	}
}
