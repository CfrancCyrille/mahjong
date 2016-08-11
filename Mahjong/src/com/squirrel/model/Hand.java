package com.squirrel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class Hand implements Serializable {

	public List<Tuile> tuilesListOfHand;
	final int TAILLE_MAIN=14; // constante taille de la main (maximum)
	public boolean mahjongPossible = false;

	//constructeur
	public Hand(){
		this.tuilesListOfHand = new ArrayList<Tuile>();
	}


	Collection<Tuile> fillHand(Tuile t) throws MainPleineException{
		//fillHand() permet d'ajouter une tuile t � la liste tuilesListOfHand 
		// Dans le jeu la tuile vient du Mur
		if(tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{
			throw new MainPleineException();
		}
		return this.tuilesListOfHand;



	}


	@Override
	public String toString() {
		//Affichage
		return   tuilesListOfHand.toString() ;
	}


	Collection<Tuile> pickDefausse(Tuile t, boolean isCombi){
		// pickDefausse() permet d'ajouter une tuile t � la liste tuilesListOfHand depuis la d�fausse 
		// Seulement si la tuile de la d�fausse forme une combinaison avec celles dans tuilesListOfHand (isCombi)
		if (isCombi == true && tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{ System.out.println("Vous ne pouvez pas prendre cette tuile");
		//TODO Ajout une p�nalit� au score du joueur
		}
		return this.tuilesListOfHand;
	}
	// TODO : test :  on peut ajouter des tuiles / on ne peut pas ajouter une 15eme tuile  / si pas de combi, on ne peut pas la prendre


	public Collection<Tuile> triTuiles(List<Tuile> hand){
		//triTuiles(hand) trie les tuiles selon leur type puis leur valeur
		//Range les tuiles par orde alphabetique selon le type
		//Dans une famille, range par valeur
		Collections.sort(hand, new TuileComparatorVal());
		return hand;

	}




	public List<List<Tuile>> findCombinaisons(List<Tuile>tuilesListOfHand){
		//on cr�e une liste de liste pour contenir les combinaisons trouv�es
		List<List<Tuile>> res=new ArrayList<List<Tuile>>();
		//liste des tuiles seules
		List<Tuile> seules=new ArrayList<Tuile>();
		//on "clone" la liste des tuiles de notre main, afin de ne pas modifier la composition de la main
		List<Tuile> listClone = new ArrayList<Tuile>();
		listClone.addAll(tuilesListOfHand);


		triTuiles(listClone);

		//on cherche des groupes dans listclone
		//on met les groupes trouv�s dans res
		//on met les tuiles seules dans seules
		trouveGroupesTuilesIdentiques(res, seules, listClone);

		if (seules.size()!=0){
			//chercher une recombinaison entre seules
			recombinaisonSeulesSeules(res, seules);
		}
		if (seules.size()!=0){
			//si ca ne suffit pas : chercher a associer deux tuiles seules � une tuile de res
			recombinaisonSeulesCombisDeux(res, seules);
			//si ca ne suffit pas : chercher a associer une tuile seule � deux tuiles de res
			recombinaisonSeulesCombis(res, seules);
			//on v�rifie qu'on a pas laiss� de tuile seule dans les combinaisons apr�s recherche
			petiteVerif(res, seules);
		}

		mahjongPossible  = verifMahjong(res);

		return res;

	}

	void trouveGroupesTuilesIdentiques(List<List<Tuile>> res, List<Tuile> seules,
			List<Tuile> listClone) {
		//Le but est de transf�rer les tuiles vers une combi dans res ou "seules", donc tant que listclone n'est pas vide, c'est qu'on ne les a pas toutes compar�es
		while(!listClone.isEmpty()){

			//listCompteur est une liste interm�diaire qui r�cup�re les groupes quand on en trouve
			List<Tuile> listCompteur = new ArrayList<Tuile>();

			//Parcours du tableau, on compare par rapport � la premi�re tuile et si elle a des jumelles, on les groupe dans listCompteur
			for (int j = 1; j< listClone.size(); j++) {
				if((listClone.get(0).getValeur().equals(listClone.get(j).getValeur())) && ((listClone.get(0).getType().equals(listClone.get(j).getType())))){

					listCompteur.add(listClone.get(j));

				}
			}

			//lorsqu'on a fini un groupe:
			//si listCompteur est vide c'est que la tuile est seule, on la met dans "seules"
			//sinon on remplit res, notre liste de combinaisons
			//une fois transf�r�e on efface la tuile de listClone, on peut reprendre la comparaison par rapport � la premei�re tuile
			if(!listCompteur.isEmpty()){
				listCompteur.add(listClone.get(0));	

				switch(listCompteur.size()){
				case 4 : 
					res.add(listCompteur);
					listClone.removeAll(listCompteur);
					break;
				case 3 : res.add(listCompteur);
				listClone.removeAll(listCompteur);
				break;
				case 2 : res.add(listCompteur);
				listClone.removeAll(listCompteur);
				break;

				}
			}
			else{
				seules.add(listClone.get(0));
				listClone.remove(listClone.get(0));
			}


		}
	}

	void recombinaisonSeulesCombis(List<List<Tuile>> res, List<Tuile> seules) {
		//On cherche � recombiner les tuiles seules avec les groupes pr�c�demment form�s en formant des suites
		//Ici on a une tuile seule qui cherche deux tuiles dans res
		//TODO!! pour une suite, les types possibles sont limit�s � ronds, caract�res et bambous
		// i parcourt "seules", j parcours "res"





		for (int i = seules.size()-1; i>-1 ;i--) {

			for (int j=0; j<res.size()-1; j++){
				//si les types et les valeurs sont �gales:

				if(		
						//si la tuile seule est la plus grande de la suite
						(seules.get(i).getType().getName().equals("caract�re")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
						&&(seules.get(i).getType().equals(res.get(j).get(0).getType())) 
						&& (seules.get(i).getType().equals(res.get(j+1).get(0).getType()))
						&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()+2))
						&& (seules.get(i).getValeur().getValue()==(res.get(j+1).get(0).getValeur().getValue()+1)))
					//Remplie une liste interm�diare, la rajouter � res, puis effacer les tuiles qui la composent de "res" et "seules"
				{List<Tuile> listIntermediaire = new ArrayList<Tuile>();		
				listIntermediaire.add(seules.get(i));
				listIntermediaire.add(res.get(j).get(0));
				listIntermediaire.add(res.get(j+1).get(0));

				res.add(listIntermediaire);

				seules.remove(seules.get(i));
				res.get(j).remove(res.get(j).get(0));
				res.get(j+1).remove(res.get(j+1).get(0));


				} else if(		
						//si la tuile seule est la plus petite de la suite 
						(seules.get(i).getType().getName().equals("caract�re")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
						&&(seules.get(i).getType().equals(res.get(j).get(0).getType())) 
						&& (seules.get(i).getType().equals(res.get(j+1).get(0).getType()))
						&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()-1))
						&& (seules.get(i).getValeur().getValue()==(res.get(j+1).get(0).getValeur().getValue()-2)))
					//Remplie une liste interm�diare, la rajouter � res, puis effacer les tuiles qui la composent de "res" et "seules"
				{List<Tuile> listIntermediaire = new ArrayList<Tuile>();		
				listIntermediaire.add(seules.get(i));
				listIntermediaire.add(res.get(j).get(0));
				listIntermediaire.add(res.get(j+1).get(0));

				res.add(listIntermediaire);

				seules.remove(seules.get(i));
				res.get(j).remove(res.get(j).get(0));
				res.get(j+1).remove(res.get(j+1).get(0));


				} else if(		
						//si la tuile seule est le milieu de la suite
						(seules.get(i).getType().getName().equals("caract�re")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
						&&(seules.get(i).getType().equals(res.get(j).get(0).getType())) 
						&& (seules.get(i).getType().equals(res.get(j+1).get(0).getType()))
						&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()-1))
						&& (seules.get(i).getValeur().getValue()==(res.get(j+1).get(0).getValeur().getValue()+1)))
					//Remplie une liste interm�diare, la rajouter � res, puis effacer les tuiles qui la composent de "res" et "seules"
				{List<Tuile> listIntermediaire = new ArrayList<Tuile>();		
				listIntermediaire.add(seules.get(i));
				listIntermediaire.add(res.get(j).get(0));
				listIntermediaire.add(res.get(j+1).get(0));

				res.add(listIntermediaire);

				seules.remove(seules.get(i));
				res.get(j).remove(res.get(j).get(0));
				res.get(j+1).remove(res.get(j+1).get(0));
				}
			}
		} 
	}

	void recombinaisonSeulesCombisDeux(List<List<Tuile>> res, List<Tuile> seules) {
		//On cherche � recombiner deux tuiles seules avec une tuile  de res
		//TODO!! pour une suite, les types possibles sont limit�s � ronds, caract�res et bambous
		// i parcourt "seules", j parcours "res"
		int i=0;
		while (i<seules.size()-1){


			//Lorsqu'on forme une suite ici, passe � true
			boolean isNewSuite=false;

			//on cherche une tuile sup�rieure en valeur a i de 1 ou 2


			// Premi�rement : si seules contient deux tuiles qui se suivent
			if(		(seules.get(i).getType().getName().equals("caract�re")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
					&&(seules.get(i).getType().equals(seules.get(i+1).getType())) 
					&& (seules.get(i).getValeur().getValue()==(seules.get(i+1).getValeur().getValue()-1))){


				for (int j=0; j<res.size(); j++){
					if (
							//Les deux tuiles seules se suivent, on cherche une tuile avant i ou apres i+1
							(seules.get(i).getType().equals(res.get(j).get(0).getType()))
							&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()-2)
							|| (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()+1)))){

						List<Tuile> listIntermediaire = new ArrayList<Tuile>();		

						listIntermediaire.add(seules.get(i));
						listIntermediaire.add(seules.get(i+1));
						listIntermediaire.add(res.get(j).get(0));

						res.add(listIntermediaire);

						seules.remove(seules.get(i+1));
						seules.remove(seules.get(i));
						res.get(j).remove(res.get(j).get(0));
						isNewSuite=true;
						break;
					}
				}

				if(isNewSuite){
					i=i;
				}
				else{
					i++;
				}					
			} 
			// Deuxi�mement : si seules contient deux tuiles avec un d'�cart (ex: 1 et 3)

			else if(		(seules.get(i).getType().getName().equals("caract�re")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
					&&(seules.get(i).getType().equals(seules.get(i+1).getType())) 
					&& (seules.get(i).getValeur().getValue()==(seules.get(i+1).getValeur().getValue()-2))){
				// on cherche dans res, une tuile pour completer
				for (int j=0; j<res.size(); j++){
					if ((seules.get(i).getType().equals(res.get(j).get(0).getType())
							&& seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()-1))){

						List<Tuile> listIntermediaire = new ArrayList<Tuile>();		
						listIntermediaire.add(seules.get(i));
						listIntermediaire.add(seules.get(i+1));
						listIntermediaire.add(res.get(j).get(0));

						res.add(listIntermediaire);

						seules.remove(seules.get(i+1));
						seules.remove(seules.get(i));
						res.get(j).remove(res.get(j).get(0));

						isNewSuite=true;
						break;
					}
				}

				if(isNewSuite){
					i=i;
				}
				else{
					i++;
				}
			}
			// seules ne contient pas deux tuiles pouvant former une suite � cet index
			else{
				// On passe a la paire de tuiles seules suivante
				i++;
			}

			//				if(isNewSuite==false){
			//					i++;
			//				}
		}
	}

	void recombinaisonSeulesSeules(List<List<Tuile>> res, List<Tuile> seules) {
		//TODO!! pour une suite, les types possibles sont limit�s � ronds, caract�res et bambous
		// On cherche � fabriquer une suite
		int i = 0;
		while ( i < seules.size()-2) {

			//Si on trouve des tuiles �gales en type avec des valeurs qui se succ�dent -> suite
			if (              (seules.get(i).getType().getName().equals("caract�re")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
					&& (seules.get(i+1).getType().equals(seules.get(i).getType()))
					&&(seules.get(i+2).getType().equals(seules.get(i).getType()))
					&&(seules.get(i+1).getValeur().getValue()==(seules.get(i).getValeur().getValue()+1))
					&&(seules.get(i+2).getValeur().getValue()==(seules.get(i).getValeur().getValue()+2))){

				List<Tuile>listIntermediaire = new ArrayList<Tuile>();

				//Alors on les places dans une liste "interm�diaire" qui est un chow
				listIntermediaire.add(seules.get(i));
				listIntermediaire.add(seules.get(i+1));
				listIntermediaire.add(seules.get(i+2));

				//notre liste de liste de tuiles vient accueillir cette nouvelle liste de chow
				res.add(listIntermediaire);

				//Puis on efface de la liste seules les tuiles trait�es comme chow, et on recommence avec le reste des tuiles de la liste "seules".

				seules.removeAll(listIntermediaire);
			}
			else{
				i++;
			}
		}
	}

	List<Tuile> petiteVerif(List<List<Tuile>> res, List<Tuile> seules) {
		//Petite v�rification: si il y a des tuiles seules apr�s recombinaison, on les met dans "seules"     
		for (int j=0; j<res.size();j++){
			if(res.get(j).size()==1){
				seules.add(res.get(j).get(0));
				res.remove(res.get(j));
			} else if (res.get(j).size()==2 && (res.get(j).get(0).getValeur().getValue()!=res.get(j).get(1).getValeur().getValue())){
				seules.add(res.get(j).get(0));     
				seules.add(res.get(j).get(1));         
				res.remove(res.get(j));    
			}       
		}       
		return seules;  
	}


	boolean verifMahjong(List<List<Tuile>> res) {

		int nb;

		ArrayList<Integer> tableauTaille = new ArrayList<Integer>();
		List<Integer> mahjong = Arrays.asList(3,3,3,3,2);
		for(int j=0; j<res.size();j++){
			nb = res.get(j).size();
			tableauTaille.add(nb);
		}
		if (tableauTaille.containsAll(mahjong)){
			this.mahjongPossible = true;
		}


		return mahjongPossible;
	}

}




