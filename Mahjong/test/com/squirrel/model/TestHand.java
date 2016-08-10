package com.squirrel.model;



//import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;
import org.junit.Test;


import com.squirrel.model.Hand;

public class TestHand {

	@Test
	public void testTri (){
		System.out.println("==========");
		System.out.println("testTri");
		System.out.println("==========");
		/**
		 *Ce test crée les tuiles du jeu de Mahjong (elles sont mélangées lors de leur création)
		 *On teste la capacité de notre fonction triTuile() à les ranger par famille et par valeur 
		 *Test validé le 04/08/2016 16:30
		 **/
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();

		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour le test.
		List<Tuile> list= hand.tuilesListOfHand;

		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		//on affiche list avant de la trier
		System.out.println(list);


		//On trie notre main  
		hand.triTuiles((List<Tuile>) list);

		//On visualise ce que contient la liste rentrée en paramètre.
		System.out.println(list);
		//On visualise ce que contient liste remplie.
		System.out.println(hand.tuilesListOfHand);
		for (int i=0; i<144;i++){
			System.out.print(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getType().getName());
			System.out.print(" ");
			System.out.println(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getValeur().getName());
		}


	}



	@Test 	
	/** On crée une main dont on vérifie le remplissage, 
	 * on vérifie que la demande d'ajout d'une 15eme tuile renvoie l'exception correspondante
	 * validé le 04/08/2016 17:30
	 */
	public void testRemplissageMain() {
		/*	System.out.println("==========");
		System.out.println("testRemplissageMain");
		System.out.println("==========");
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();
		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour piocher dedans .
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);



		try {

			for(int i=0; i<14 ; i++){
				hand.fillHand(facadeTuile.getITuile(i,list));
			}
			hand.fillHand(facadeTuile.getITuile(14,list));

		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			for (Tuile t : hand.tuilesListOfHand) {
				System.out.print(t.getType().getName() + " ");
				System.out.println(t.getValeur().getName()); 
			}

		}*/

	}
	@Test	
	public void testFindCombinaison() {
		/*System.out.println("==========");
		System.out.println("testFindCombinaison");
		System.out.println("==========");
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();
		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour piocher dedans .
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		try {
			for(int i=0; i<14 ; i++){
				hand.fillHand(facadeTuile.getITuile(i,list));
			}

		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hand.triTuiles(hand.tuilesListOfHand);
		System.out.println("Main triée");
		for (Tuile t : hand.tuilesListOfHand) {
			System.out.print(t.getType().getName() + " ");
			System.out.println(t.getValeur().getName()); 
		}
		System.out.println("on est la");

		/////////////////////////////// Trop d'imbrication -> a revoir un test = une fonction
		List<List<Tuile>> res=new ArrayList<List<Tuile>>();
		List<Tuile> seules=new ArrayList<Tuile>();

		res=hand.findCombinaisons(hand.tuilesListOfHand);

		hand.petiteVerif(res, seules);

		System.out.print(res);


		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}



		for (Tuile t : hand.tuilesListOfHand) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}*/


	}


	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à ranger les groupes dans une list de list et les seules dans une liste
	 *Test validé le 09.08.2016 
	 **/
		public void testTrouveGroupesTuilesIdentiques(){
	//		System.out.println("==========");
	//		System.out.println("testTrouveGroupesTuilesIdentiques");
	//		System.out.println("==========");
	//		Hand hand = new Hand();
	//		ArrayList<Tuile> list = new ArrayList<Tuile>();
	//		//On instancie la facade de Kevin. 
	//		FacadeTuile facadeTuile = new FacadeTuile();
	//		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
	//		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
	//
	//		try {
	//
	//			for(int i=0; i<14 ; i++){
	//				hand.fillHand(facadeTuile.getITuile(i,list));
	//			}
	//
	//		} catch (MainPleineException e) {
	//			
	//			e.printStackTrace();
	//		}
	//		hand.triTuiles(hand.tuilesListOfHand);
	//		System.out.println("Main triée");
	//		System.out.println("----------");
	//		
	//		//on affiche les tuiles triées
	//		for (Tuile t : hand.tuilesListOfHand) {
	//			System.out.print(t.getType().getName() + " ");
	//			System.out.println(t.getValeur().getName()); 
	//		}
	//		
	//		
	//		//On instancie la liste res
	//		List<List<Tuile>> res;
	//		res=new ArrayList<List<Tuile>>();
	//		List<Tuile> seules;
	//		seules=new ArrayList<Tuile>();
	//		List<Tuile> listClone;
	//		listClone=new ArrayList<Tuile>();
	//		listClone.addAll(hand.tuilesListOfHand);
	//		
	//		
	//		System.out.println("Groupes formés");
	//		System.out.println("----------");
	//		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
	//		
	//		//Affichage des listes trouvées (la main, les groupes identifiés et les tuiles seules)
	//		System.out.println("-listClone--------");
	//		System.out.println("----------");
	//		System.out.println(listClone);
	//		
	//		System.out.println("-res--------");
	//		System.out.println("----------");
	//		for (int j=0;j<res.size();j++){
	//			for (Tuile t : res.get(j)) {
	//				System.out.print(t.getType().getName()+" ");
	//				System.out.println(t.getValeur().getName()); 
	//			}
	//		}
	//		
	//		System.out.println("---seules------");
	//		System.out.println("----------");
	//		for (Tuile t : seules) {
	//			System.out.print(t.getType().getName()+" ");
	//			System.out.println(t.getValeur().getName()); 
	//		}
	//		
}


	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à recombiner les tuiles seules pour fabriquer des suites
	 *Test validé le 09.08.2016
	 **/
	public void recombinaisonSeulesSeules(){
//		System.out.println("==========");
//		System.out.println("recombinaisonSeulesSeules");
//		System.out.println("==========");
//		Hand hand = new Hand();
//		ArrayList<Tuile> list = new ArrayList<Tuile>();
//		//On instancie la facade de Kevin. 
//		FacadeTuile facadeTuile = new FacadeTuile();
//		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
//		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
//
//		try {
//
//			for(int i=0; i<14 ; i++){
//				hand.fillHand(facadeTuile.getITuile(i,list));
//			}
//			
//
//		} catch (MainPleineException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		hand.triTuiles(hand.tuilesListOfHand);
//		System.out.println("Main triée");
//		System.out.println("----------");
//		
//		//on affiche les tuiles triées
//		for (Tuile t : hand.tuilesListOfHand) {
//			System.out.print(t.getType().getName() + " ");
//			System.out.println(t.getValeur().getName()); 
//		}
//		
//		
//		//On instancie la liste res
//		List<List<Tuile>> res;
//		res=new ArrayList<List<Tuile>>();
//		List<Tuile> seules;
//		seules=new ArrayList<Tuile>();
//		List<Tuile> listClone;
//		listClone=new ArrayList<Tuile>();
//		listClone.addAll(hand.tuilesListOfHand);
//		
//		
//		System.out.println("Groupes formés");
//		System.out.println("----------");
//		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
//		
//		
//		//Affichage des listes trouvées (avant recombinaison)
//		System.out.println("-listClone--avant recombinaison------");
//		System.out.println("----------");		
//		System.out.println(listClone);
//				
//				System.out.println("-res---avant recombinaison-----");
//				System.out.println("----------");
//				for (int j=0;j<res.size();j++){
//					for (Tuile t : res.get(j)) {
//						System.out.print(t.getType().getName()+" ");
//						System.out.println(t.getValeur().getName()); 
//					}
//				}
//				
//				System.out.println("---seules--avant recombinaison----");
//				System.out.println("----------");
//				for (Tuile t : seules) {
//					System.out.print(t.getType().getName()+" ");
//					System.out.println(t.getValeur().getName()); 
//				}
//		
//		
//		hand.recombinaisonSeulesSeules(res, seules);
//		
//		//Affichage des listes trouvées (après recombinaison)
//		System.out.println("-listClone--après recombinaison------");
//		System.out.println("----------");
//		System.out.println(listClone);
//		
//		System.out.println("-res--après recombinaison------");
//		System.out.println("----------");
//		for (int j=0;j<res.size();j++){
//			for (Tuile t : res.get(j)) {
//				System.out.print(t.getType().getName()+" ");
//				System.out.println(t.getValeur().getName()); 
//			}
//		}
//		
//		System.out.println("---seules--après recombinaison----");
//		System.out.println("----------");
//		for (Tuile t : seules) {
//			System.out.print(t.getType().getName()+" ");
//			System.out.println(t.getValeur().getName()); 
//		}
//		
//		
	}
	
	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à recombiner les tuiles seules  avec une tuile d'un groupe pour fabriquer des suites
	 *Test validé le ??
	 **/
	public void testRecombinaisonSeulesCombisDeux(){
		System.out.println("==========");
		System.out.println("testRecombinaisonSeulesCombisDeux");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);

		try {

			for(int i=0; i<14 ; i++){
				hand.fillHand(facadeTuile.getITuile(i,list));
			}

		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hand.triTuiles(hand.tuilesListOfHand);
		System.out.println("Main triée");
		System.out.println("----------");
		
		//on affiche les tuiles triées
		for (Tuile t : hand.tuilesListOfHand) {
			System.out.print(t.getType().getName() + " ");
			System.out.println(t.getValeur().getName()); 
		}
		
		
		//On instancie la liste res
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);
		
		
		System.out.println("Groupes formés");
		System.out.println("----------");
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
		
		hand.recombinaisonSeulesSeules(res, seules);
		//Affichage des listes trouvées (avec  recombinaison SeulesSeules)
		System.out.println("-listClone--avec  recombinaison SeulesSeules------");
		System.out.println("----------");		
		System.out.println(listClone);
				
				System.out.println("-res---avec  recombinaison SeulesSeules-----");
				System.out.println("----------");
				for (int j=0;j<res.size();j++){
					for (Tuile t : res.get(j)) {
						System.out.print(t.getType().getName()+" ");
						System.out.println(t.getValeur().getName()); 
					}
				}
				
				System.out.println("---seules--avec  recombinaison SeulesSeules----");
				System.out.println("----------");
				for (Tuile t : seules) {
					System.out.print(t.getType().getName()+" ");
					System.out.println(t.getValeur().getName()); 
				}
		
		
		
		
		hand.recombinaisonSeulesCombisDeux( res, seules);
		
		//Affichage des listes trouvées (après recombinaison)
		System.out.println("-listClone--après recombinaison--SeulesCombisDeux----");
		System.out.println("----------");
		System.out.println(listClone);
		
		System.out.println("-res--après recombinaison-SeulesCombisDeux-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}
		
		System.out.println("---seules--après recombinaison-SeulesCombisDeux---");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}
		
		
	}
	
	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à recombiner une tuile seule  avec deux tuile d'un groupe pour fabriquer des suites
	 *Test validé le ??
	 **/
	//TODO
	public void testRecombinaisonSeulesCombis(){
//		System.out.println("==========");
//		System.out.println("testRecombinaisonSeulesCombis");
//		System.out.println("==========");
//		Hand hand = new Hand();
//		ArrayList<Tuile> list = new ArrayList<Tuile>();
//		//On instancie la facade de Kevin. 
//		FacadeTuile facadeTuile = new FacadeTuile();
//		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
//		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
//
//		try {
//
//			for(int i=0; i<14 ; i++){
//				hand.fillHand(facadeTuile.getITuile(i,list));
//			}
//
//		} catch (MainPleineException e) {
//			
//			e.printStackTrace();
//		}
//		hand.triTuiles(hand.tuilesListOfHand);
//		System.out.println("Main triée");
//		System.out.println("----------");
//		
//		//on affiche les tuiles triées
//		for (Tuile t : hand.tuilesListOfHand) {
//			System.out.print(t.getType().getName() + " ");
//			System.out.println(t.getValeur().getName()); 
//		}
//		
//		
//		//On instancie la liste res
//		List<List<Tuile>> res;
//		res=new ArrayList<List<Tuile>>();
//		List<Tuile> seules;
//		seules=new ArrayList<Tuile>();
//		List<Tuile> listClone;
//		listClone=new ArrayList<Tuile>();
//		listClone.addAll(hand.tuilesListOfHand);
//		
//		
//		System.out.println("Groupes formés");
//		System.out.println("----------");
//		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
//		//recombinaisons
//		hand.recombinaisonSeulesSeules(res, seules);
//		hand.recombinaisonSeulesCombisDeux( res, seules);
//		
//		//Affichage des listes trouvées (avec  recombinaison SeulesSeules)
//		System.out.println("-listClone--avec  recombinaison SeulesCombisDeux------");
//		System.out.println("----------");		
//		System.out.println(listClone);
//				
//				System.out.println("-res---avec  recombinaison SeulesCombisDeux-----");
//				System.out.println("----------");
//				for (int j=0;j<res.size();j++){
//					for (Tuile t : res.get(j)) {
//						System.out.print(t.getType().getName()+" ");
//						System.out.println(t.getValeur().getName()); 
//					}
//				}
//				
//				System.out.println("---seules--avec  recombinaison SeulesCombisDeux----");
//				System.out.println("----------");
//				for (Tuile t : seules) {
//					System.out.print(t.getType().getName()+" ");
//					System.out.println(t.getValeur().getName()); 
//				}
//		
//		
//		
//		
//		hand.recombinaisonSeulesCombis( res, seules);
//		
//		//Affichage des listes trouvées (après recombinaison)
//		System.out.println("-listClone--après recombinaison--SeulesCombis----");
//		System.out.println("----------");
//		System.out.println(listClone);
//		
//		System.out.println("-res--après recombinaison-SeulesCombis-----");
//		System.out.println("----------");
//		for (int j=0;j<res.size();j++){
//			for (Tuile t : res.get(j)) {
//				System.out.print(t.getType().getName()+" ");
//				System.out.println(t.getValeur().getName()); 
//			}
//		}
//		
//		System.out.println("---seules--après recombinaison-SeulesCombis---");
//		System.out.println("----------");
//		for (Tuile t : seules) {
//			System.out.print(t.getType().getName()+" ");
//			System.out.println(t.getValeur().getName()); 
//		}
//		
		
	}
	
	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à vérifier qu'aucune tuile n'est seule dans res
	 *Test validé le ??
	 **/
	//TODO
	public void testpetiteVerif(){
		//petiteVerif
	}
	
}


