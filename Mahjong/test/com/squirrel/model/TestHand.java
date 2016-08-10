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
		 *Ce test cr�e les tuiles du jeu de Mahjong (elles sont m�lang�es lors de leur cr�ation)
		 *On teste la capacit� de notre fonction triTuile() � les ranger par famille et par valeur 
		 *Test valid� le 04/08/2016 16:30
		 **/
		//On instancie un objet hand de type Hand qui contient : hand.tuilesListOfHand = new ArrayList<Tuile>();
		Hand hand = new Hand();

		//On cree une reference "list" a une liste qui prend pour valeur le "new ArrayList<Tuile>" afin de l'utiliser pour le test.
		List<Tuile> list= hand.tuilesListOfHand;

		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		//on affiche list avant de la trier
		System.out.println(list);


		//On trie notre main  
		hand.triTuiles((List<Tuile>) list);

		//On visualise ce que contient la liste rentr�e en param�tre.
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
	/** On cr�e une main dont on v�rifie le remplissage, 
	 * on v�rifie que la demande d'ajout d'une 15eme tuile renvoie l'exception correspondante
	 * valid� le 04/08/2016 17:30
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
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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
		System.out.println("Main tri�e");
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
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � ranger les groupes dans une list de list et les seules dans une liste
	 *Test valid� le 09.08.2016 
	 **/
		public void testTrouveGroupesTuilesIdentiques(){
	//		System.out.println("==========");
	//		System.out.println("testTrouveGroupesTuilesIdentiques");
	//		System.out.println("==========");
	//		Hand hand = new Hand();
	//		ArrayList<Tuile> list = new ArrayList<Tuile>();
	//		//On instancie la facade de Kevin. 
	//		FacadeTuile facadeTuile = new FacadeTuile();
	//		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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
	//		System.out.println("Main tri�e");
	//		System.out.println("----------");
	//		
	//		//on affiche les tuiles tri�es
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
	//		System.out.println("Groupes form�s");
	//		System.out.println("----------");
	//		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
	//		
	//		//Affichage des listes trouv�es (la main, les groupes identifi�s et les tuiles seules)
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
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � recombiner les tuiles seules pour fabriquer des suites
	 *Test valid� le 09.08.2016
	 **/
	public void recombinaisonSeulesSeules(){
//		System.out.println("==========");
//		System.out.println("recombinaisonSeulesSeules");
//		System.out.println("==========");
//		Hand hand = new Hand();
//		ArrayList<Tuile> list = new ArrayList<Tuile>();
//		//On instancie la facade de Kevin. 
//		FacadeTuile facadeTuile = new FacadeTuile();
//		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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
//		System.out.println("Main tri�e");
//		System.out.println("----------");
//		
//		//on affiche les tuiles tri�es
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
//		System.out.println("Groupes form�s");
//		System.out.println("----------");
//		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
//		
//		
//		//Affichage des listes trouv�es (avant recombinaison)
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
//		//Affichage des listes trouv�es (apr�s recombinaison)
//		System.out.println("-listClone--apr�s recombinaison------");
//		System.out.println("----------");
//		System.out.println(listClone);
//		
//		System.out.println("-res--apr�s recombinaison------");
//		System.out.println("----------");
//		for (int j=0;j<res.size();j++){
//			for (Tuile t : res.get(j)) {
//				System.out.print(t.getType().getName()+" ");
//				System.out.println(t.getValeur().getName()); 
//			}
//		}
//		
//		System.out.println("---seules--apr�s recombinaison----");
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
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � recombiner les tuiles seules  avec une tuile d'un groupe pour fabriquer des suites
	 *Test valid� le ??
	 **/
	public void testRecombinaisonSeulesCombisDeux(){
		System.out.println("==========");
		System.out.println("testRecombinaisonSeulesCombisDeux");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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
		System.out.println("Main tri�e");
		System.out.println("----------");
		
		//on affiche les tuiles tri�es
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
		
		
		System.out.println("Groupes form�s");
		System.out.println("----------");
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
		
		hand.recombinaisonSeulesSeules(res, seules);
		//Affichage des listes trouv�es (avec  recombinaison SeulesSeules)
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
		
		//Affichage des listes trouv�es (apr�s recombinaison)
		System.out.println("-listClone--apr�s recombinaison--SeulesCombisDeux----");
		System.out.println("----------");
		System.out.println(listClone);
		
		System.out.println("-res--apr�s recombinaison-SeulesCombisDeux-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}
		
		System.out.println("---seules--apr�s recombinaison-SeulesCombisDeux---");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}
		
		
	}
	
	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � recombiner une tuile seule  avec deux tuile d'un groupe pour fabriquer des suites
	 *Test valid� le ??
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
//		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
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
//		System.out.println("Main tri�e");
//		System.out.println("----------");
//		
//		//on affiche les tuiles tri�es
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
//		System.out.println("Groupes form�s");
//		System.out.println("----------");
//		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
//		//recombinaisons
//		hand.recombinaisonSeulesSeules(res, seules);
//		hand.recombinaisonSeulesCombisDeux( res, seules);
//		
//		//Affichage des listes trouv�es (avec  recombinaison SeulesSeules)
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
//		//Affichage des listes trouv�es (apr�s recombinaison)
//		System.out.println("-listClone--apr�s recombinaison--SeulesCombis----");
//		System.out.println("----------");
//		System.out.println(listClone);
//		
//		System.out.println("-res--apr�s recombinaison-SeulesCombis-----");
//		System.out.println("----------");
//		for (int j=0;j<res.size();j++){
//			for (Tuile t : res.get(j)) {
//				System.out.print(t.getType().getName()+" ");
//				System.out.println(t.getValeur().getName()); 
//			}
//		}
//		
//		System.out.println("---seules--apr�s recombinaison-SeulesCombis---");
//		System.out.println("----------");
//		for (Tuile t : seules) {
//			System.out.print(t.getType().getName()+" ");
//			System.out.println(t.getValeur().getName()); 
//		}
//		
		
	}
	
	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � v�rifier qu'aucune tuile n'est seule dans res
	 *Test valid� le ??
	 **/
	//TODO
	public void testpetiteVerif(){
		//petiteVerif
	}
	
}


