package com.squirrel.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class Hand {

	public List<Tuile> tuilesListOfHand;
	final int TAILLE_MAIN=14; // constante taille de la main (maximum)

	//constructeur
	public Hand(){

		this.tuilesListOfHand = new ArrayList<Tuile>();
	}

	//fillHand() permet d'ajouter une tuile t à la liste tuilesListOfHand 
	// Dans le jeu la tuile vient du Mur
	Collection<Tuile> fillHand(Tuile t) throws MainPleineException{
		if(tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{
			throw new MainPleineException();
		}
		return this.tuilesListOfHand;



	}

	//Affichage
	@Override
	public String toString() {
		return   tuilesListOfHand.toString() ;
	}

	// pickDefausse() permet d'ajouter une tuile t à la liste tuilesListOfHand depuis la défausse 
	// Seulement si la tuile de la défausse forme une combinaison avec celles dans tuilesListOfHand (isCombi)
	Collection<Tuile> pickDefausse(Tuile t, boolean isCombi){
		if (isCombi == true && tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{ System.out.println("Vous ne pouvez pas prendre cette tuile");
		//TODO Ajout une pénalité au score du joueur
		}
		return this.tuilesListOfHand;
	}
	// TODO : test :  on peut ajouter des tuiles / on ne peut pas ajouter une 15eme tuile  / si pas de combi, on ne peut pas la prendre

	//triTuiles(hand) tri les tuiles selon leur type puis leurs valeurs
	public Collection<Tuile> triTuiles(List<Tuile> hand){
		//Range les tuiles par orde alphabetique selon le type
		//Dans une famille, range par valeur
		Collections.sort(hand, new TuileComparatorVal());
		return hand;

	}


	// TODO : parcourt de la liste si besoin à définir
	/*Iterator itr = al.iterator();
    while(itr.hasNext()) {
       Object element = itr.next();
       System.out.print(element + " ");
    }*/





	// TODO identification des groupes de tuiles (2,3,4) et suites
	// TODO : test
	//	  HashMap<StringBuffer, Object> idGroupe(List<Tuile>tuilesListOfHand){
	//		int c = 1; 
	//		HashMap<StringBuffer, Object> groupesTuiles = new HashMap<StringBuffer, Object>();
	//		for (int i = 0; i <tuilesListOfHand.size() ; i++) {
	//			while((tuilesListOfHand.get(i).getValeur().equals(tuilesListOfHand.get(i+1).getValeur()))&& ((tuilesListOfHand.get(i).getType().equals(tuilesListOfHand.get(i+1).getType()))));
	//			c++;
	//			String type =tuilesListOfHand.get(i).getType().getName();
	//			String valeur=tuilesListOfHand.get(i).getValeur().getName();
	//			StringBuffer sb = new StringBuffer();
	//			sb.append(type).append(",").append(valeur);
	//			groupesTuiles.put(sb , c);
	//			groupesTuiles.put(sb , tuilesListOfHand.get(i));
	//		}
	//		return  groupesTuiles;
	//	}
	//	


	// TODO identification des combinaisons parmis les groupes
	// TODO : test
	public List<List<Tuile>> findCombinaisons(List<Tuile>tuilesListOfHand){
		List<List<Tuile>> res=new ArrayList<List<Tuile>>();
		List<Tuile> seules=new ArrayList<Tuile>();
		List<Tuile> listclone = new ArrayList<Tuile>();
		listclone.addAll(tuilesListOfHand);

		List<Tuile> listCompteur = new ArrayList<Tuile>();

		for (int j = 1; j< listclone.size(); j++) {
			if((listclone.get(0).getValeur().equals(listclone.get(j).getValeur())) && ((listclone.get(0).getType().equals(listclone.get(j).getType())))){

				listCompteur.add(listclone.get(j));

			}
		}

		if(!listCompteur.isEmpty()){
			listCompteur.add(listclone.get(0));	
			
			switch(listCompteur.size()){
			case 4 : 
				res.add(listCompteur);
				listclone.removeAll(listCompteur);
				break;
			case 3 : res.add(listCompteur);
			listclone.removeAll(listCompteur);
			break;
			case 2 : res.add(listCompteur);
			listclone.removeAll(listCompteur);
			break;

			}
		}
		else{
			seules.add(listclone.get(0));
			listclone.remove(listclone.get(0));
		}








		// Faut qu'on sache combien y en a de chaque
		{
			// Compter combien y a de chaque : idGroupe
			// Identifier les combinaisons formées par les groupes
			// Rechercher les suites
		}
		// Faut qu'on sache combien y a de suites
		return res;
	}

}




