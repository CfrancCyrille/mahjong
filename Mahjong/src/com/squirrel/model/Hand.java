package com.squirrel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.squirrel.app.MahjongInitialisation;
import com.squirrel.app.MahjongPartie.Combinaison;



public class Hand implements Serializable {

	public List<Tuile> tuilesListOfHand;
	final int TAILLE_MAIN=14; // constante taille de la main (maximum)
	public boolean mahjongPossible = false;
	

	//constructeur
	public Hand(){
		this.tuilesListOfHand = new ArrayList<Tuile>();
	}


	Collection<Tuile> fillHand(Tuile t) throws MainPleineException{
		//fillHand() permet d'ajouter une tuile t à la liste tuilesListOfHand 
		// Dans le jeu la tuile vient du Mur
		if(tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{
			throw new MainPleineException();
		}
		return this.tuilesListOfHand;
	}


	
	public void toStringList(List<Tuile> listAffichable) {
		//Affichage
		
		
			for (Tuile t : listAffichable) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 

			}	
		
		
	}
	
	public void toStringListList(List<List<Tuile>> listListAffichable) {
		//Affichage
		
		for (int j=0;j<listListAffichable.size();j++){
			for (Tuile t : listListAffichable.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
			System.out.println(" ");
		}
		
		
		
	}
	
	
	public void toStringHashMap(HashMap<Combinaison, List<List<Tuile>>> hashMapAffichable){
		//Affichage
		for (Combinaison mapKey : hashMapAffichable.keySet()) {

			List<List<Tuile>> currentCombinaisonList = hashMapAffichable.get(mapKey);

			System.out.println("------------------");
			System.out.println(mapKey.getName()+" ");
			System.out.println("------------------");

			int i=1;
			for (List<Tuile> elementCurrentCombi : currentCombinaisonList) {
				
				System.out.println("N°"+i);
				i++;
				for (Tuile tuile : elementCurrentCombi) {
					System.out.print(tuile.getType().getName()+" ");
					System.out.println(tuile.getValeur().getName()+" ");
				}


			}

		}
		
		
	}
	
	boolean isCombi(Tuile t){
		//isCombi vérifie si la tuile défaussée demandée forme une combinaison avec la main
		//pour cela on utilise findCombinaisons et on regarde si la tuile défaussée est dans res (une combianson est forcément supérieure à deux tuiles)
		//si la tuile défaussée a une jumelle dans la main et qu'une seule se retrouve dans res on considère que c'est celle défaussée et récupéerée
		boolean isCombi = false;
		
		List<Tuile> listTestee = this.tuilesListOfHand;	
		listTestee.add(t);
		List<List<Tuile>> res = findCombinaisons(listTestee);
				for(int j=0; j<res.size();j++){
					if (res.get(j).size()>2 && res.get(j).contains(t)){
						isCombi=true;
					}
				}
		
		return isCombi;
		
	}

	Collection<Tuile> pickDefausse(Tuile t, boolean isCombi){
		// pickDefausse() permet d'ajouter une tuile t à la liste tuilesListOfHand depuis la défausse 
		// Seulement si la tuile de la défausse forme une combinaison avec celles dans tuilesListOfHand (isCombi)
		if (isCombi == true && tuilesListOfHand.size()<TAILLE_MAIN){
			this.tuilesListOfHand.add(t);
		}else{ System.out.println("Vous ne pouvez pas prendre cette tuile");
		//TODO Ajout une pénalité au score du joueur
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




	public List<List<Tuile>> findCombinaisons(List<Tuile> tuilesListOfHand){
		//on crée une liste de liste pour contenir les combinaisons trouvées
		List<List<Tuile>> res=new ArrayList<List<Tuile>>();
		//liste des tuiles seules
		List<Tuile> seules=new ArrayList<Tuile>();
		//on "clone" la liste des tuiles de notre main, afin de ne pas modifier la composition de la main
		List<Tuile> listClone = new ArrayList<Tuile>();
		listClone.addAll(tuilesListOfHand);


		triTuiles(listClone);

		//on cherche des groupes dans listclone
		//on met les groupes trouvés dans res
		//on met les tuiles seules dans seules
		trouveGroupesTuilesIdentiques(res, seules, listClone);

		if (seules.size()!=0){
			//chercher une recombinaison entre seules
			recombinaisonSeulesSeules(res, seules);
		}
		if (seules.size()!=0){
			//si ca ne suffit pas : chercher a associer deux tuiles seules à une tuile de res
			recombinaisonSeulesCombisDeux(res, seules);
			//si ca ne suffit pas : chercher a associer une tuile seule à deux tuiles de res
			recombinaisonSeulesCombis(res, seules);
			//on vérifie qu'on a pas laissé de tuile seule dans les combinaisons après recherche
			petiteVerif(res, seules);
		}

		mahjongPossible  = verifMahjong(res);

		return res;

	}

	void trouveGroupesTuilesIdentiques(List<List<Tuile>> res, List<Tuile> seules,
			List<Tuile> listClone) {
		//Le but est de transférer les tuiles vers une combi dans res ou "seules", donc tant que listclone n'est pas vide, c'est qu'on ne les a pas toutes comparées
		while(!listClone.isEmpty()){

			//listCompteur est une liste intermédiaire qui récupère les groupes quand on en trouve
			List<Tuile> listCompteur = new ArrayList<Tuile>();

			//Parcours du tableau, on compare par rapport à la première tuile et si elle a des jumelles, on les groupe dans listCompteur
			for (int j = 1; j< listClone.size(); j++) {
				if((listClone.get(0).getValeur().equals(listClone.get(j).getValeur())) && ((listClone.get(0).getType().equals(listClone.get(j).getType())))){

					listCompteur.add(listClone.get(j));

				}
			}

			//lorsqu'on a fini un groupe:
			//si listCompteur est vide c'est que la tuile est seule, on la met dans "seules"
			//sinon on remplit res, notre liste de combinaisons
			//une fois transférée on efface la tuile de listClone, on peut reprendre la comparaison par rapport à la premeière tuile
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
		//On cherche à recombiner les tuiles seules avec les groupes précédemment formés en formant des suites
		//Ici on a une tuile seule qui cherche deux tuiles dans res
		//TODO!! pour une suite, les types possibles sont limités à ronds, caractères et bambous
		// i parcourt "seules", j parcours "res"





		for (int i = seules.size()-1; i>-1 ;i--) {

			for (int j=0; j<res.size()-1; j++){
				//si les types et les valeurs sont égales:

				if(		
						//si la tuile seule est la plus grande de la suite
						(seules.get(i).getType().getName().equals("caractère")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
						&&(seules.get(i).getType().equals(res.get(j).get(0).getType())) 
						&& (seules.get(i).getType().equals(res.get(j+1).get(0).getType()))
						&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()+2))
						&& (seules.get(i).getValeur().getValue()==(res.get(j+1).get(0).getValeur().getValue()+1)))
					//Remplie une liste intermédiare, la rajouter à res, puis effacer les tuiles qui la composent de "res" et "seules"
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
						(seules.get(i).getType().getName().equals("caractère")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
						&&(seules.get(i).getType().equals(res.get(j).get(0).getType())) 
						&& (seules.get(i).getType().equals(res.get(j+1).get(0).getType()))
						&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()-1))
						&& (seules.get(i).getValeur().getValue()==(res.get(j+1).get(0).getValeur().getValue()-2)))
					//Remplie une liste intermédiare, la rajouter à res, puis effacer les tuiles qui la composent de "res" et "seules"
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
						(seules.get(i).getType().getName().equals("caractère")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
						&&(seules.get(i).getType().equals(res.get(j).get(0).getType())) 
						&& (seules.get(i).getType().equals(res.get(j+1).get(0).getType()))
						&& (seules.get(i).getValeur().getValue()==(res.get(j).get(0).getValeur().getValue()-1))
						&& (seules.get(i).getValeur().getValue()==(res.get(j+1).get(0).getValeur().getValue()+1)))
					//Remplie une liste intermédiare, la rajouter à res, puis effacer les tuiles qui la composent de "res" et "seules"
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
		//On cherche à recombiner deux tuiles seules avec une tuile  de res
		//TODO!! pour une suite, les types possibles sont limités à ronds, caractères et bambous
		// i parcourt "seules", j parcours "res"
		int i=0;
		while (i<seules.size()-1){


			//Lorsqu'on forme une suite ici, passe à true
			boolean isNewSuite=false;

			//on cherche une tuile supérieure en valeur a i de 1 ou 2


			// Premièrement : si seules contient deux tuiles qui se suivent
			if(		(seules.get(i).getType().getName().equals("caractère")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
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
			// Deuxièmement : si seules contient deux tuiles avec un d'écart (ex: 1 et 3)

			else if(		(seules.get(i).getType().getName().equals("caractère")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
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
			// seules ne contient pas deux tuiles pouvant former une suite à cet index
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
		//TODO!! pour une suite, les types possibles sont limités à ronds, caractères et bambous
		// On cherche à fabriquer une suite
		int i = 0;
		while ( i < seules.size()-2) {

			//Si on trouve des tuiles égales en type avec des valeurs qui se succèdent -> suite
			if (              (seules.get(i).getType().getName().equals("caractère")||seules.get(i).getType().getName().equals("bambou")||seules.get(i).getType().getName().equals("rond"))
					&& (seules.get(i+1).getType().equals(seules.get(i).getType()))
					&&(seules.get(i+2).getType().equals(seules.get(i).getType()))
					&&(seules.get(i+1).getValeur().getValue()==(seules.get(i).getValeur().getValue()+1))
					&&(seules.get(i+2).getValeur().getValue()==(seules.get(i).getValeur().getValue()+2))){

				List<Tuile>listIntermediaire = new ArrayList<Tuile>();

				//Alors on les places dans une liste "intermédiaire" qui est un chow
				listIntermediaire.add(seules.get(i));
				listIntermediaire.add(seules.get(i+1));
				listIntermediaire.add(seules.get(i+2));

				//notre liste de liste de tuiles vient accueillir cette nouvelle liste de chow
				res.add(listIntermediaire);

				//Puis on efface de la liste seules les tuiles traitées comme chow, et on recommence avec le reste des tuiles de la liste "seules".

				seules.removeAll(listIntermediaire);
			}
			else{
				i++;
			}
		}
	}

	List<Tuile> petiteVerif(List<List<Tuile>> res, List<Tuile> seules) {
		//Petite vérification: si il y a des tuiles seules après recombinaison, on les met dans "seules"     
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


	HashMap<Combinaison, List<List<Tuile>>> identificationCombi(List<List<Tuile>>resCombi){
		//Création d'une liste qui contiendra les 3 précédentes. (chow, pung, kong)
		HashMap<Combinaison, List<List<Tuile>>> combinaisons = new HashMap<Combinaison, List<List<Tuile>>>();
		
		//Fonction qui retourne la liste des chows, pungs, kungs trouvés dans res après findCombinaisons
		
		//Création d'une liste de chows
		List<List<Tuile>> chow =new ArrayList <List<Tuile>>();
		
		//Création d'une liste de pungs
		List<List<Tuile>> pung = new ArrayList <List<Tuile>>();
		
		//Création d'une liste de kong
		List<List<Tuile>> kong = new ArrayList <List<Tuile>>();

		
		//On parcourt le res déterminé au préalable par la fonction findCombinaisons
		for (int j=0;j<resCombi.size();j++){
			int taille = resCombi.get(j).size();
			
			//et on associe une variable "combinaison" qui prend pour valeur le nombre de tuiles par groupe de res
			int combinaison = taille;

			
			switch (combinaison){
			//si groupe de 3 trouvé :
			case 3 :
				if(resCombi.get(j).get(0).getValeur().getValue()==resCombi.get(j).get(1).getValeur().getValue()){
					//on le met dans la liste pung si tuiles identiques
					pung.add(resCombi.get(j));

			
				}else
					//sinon c'est un chow alors on le place dans la liste chow
				{
					chow.add(resCombi.get(j));

				}
				break;
				
				//si groupe de 4 trouvé
			case 4 :
				//c'est forcément un kong, donc on le met dans la liste kong
				if(resCombi.get(j).get(0).getValeur().getValue()==resCombi.get(j).get(1).getValeur().getValue()){
					kong.add(resCombi.get(j));

					//TODO : traitement du kong
					
					break;

				}

			}
		}
		//on remplit notre liste contenant les listes de combinaisons trouvées et on retourne le résultat à la fonction
		combinaisons.put(Combinaison.PUNG, pung);
		combinaisons.put(Combinaison.KONG, kong);
		combinaisons.put(Combinaison.CHOW, chow);
		return combinaisons;
		

	}
	
	
	
}




