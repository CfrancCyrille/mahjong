package com.squirrel.model;



import static org.junit.Assert.*;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

import com.squirrel.app.MahjongPartie;
import com.squirrel.app.MahjongPartie.Combinaison;
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

			e.printStackTrace();
		}finally {
			for (Tuile t : hand.tuilesListOfHand) {
				System.out.print(t.getType().getName() + " ");
				System.out.println(t.getValeur().getName()); 
			}

		}*/

	}




	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � ranger les groupes dans une list de list et les seules dans une liste
	 *Test valid� le 09.08.2016 
	 **/
	public void testTrouveGroupesTuilesIdentiques(){
		System.out.println("==========");
		System.out.println("testTrouveGroupesTuilesIdentiques");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();

		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);


		remplissageDeMain(hand, list, facadeTuile);

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

		//Affichage des listes trouv�es (la main, les groupes identifi�s et les tuiles seules)
		System.out.println("-listClone--------");
		System.out.println("----------");
		System.out.println(listClone);

		System.out.println("-res--------");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules------");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}

	}


	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � recombiner les tuiles seules pour fabriquer des suites
	 *Test valid� le 09.08.2016
	 **/
	public void recombinaisonSeulesSeules(){
		System.out.println("==========");
		System.out.println("recombinaisonSeulesSeules");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();


		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);


		remplissageDeMain(hand, list, facadeTuile);

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


		//Affichage des listes trouv�es (avant recombinaison)
		System.out.println("-listClone--avant recombinaison------");
		System.out.println("----------");		
		System.out.println(listClone);

		System.out.println("-res---avant recombinaison-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules--avant recombinaison----");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}


		hand.recombinaisonSeulesSeules(res, seules);

		//Affichage des listes trouv�es (apr�s recombinaison)
		System.out.println("-listClone--apr�s recombinaison------");
		System.out.println("----------");
		System.out.println(listClone);

		System.out.println("-res--apr�s recombinaison------");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules--apr�s recombinaison----");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}


	}

	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � recombiner les tuiles seules  avec une tuile d'un groupe pour fabriquer des suites
	 *Test valid� le 10.08.2016
	 **/
	public void testRecombinaisonSeulesCombisDeux(){
		System.out.println("==========");
		System.out.println("testRecombinaisonSeulesCombisDeux");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);


		remplissageDeMain(hand, list, facadeTuile);


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
		//ListClone
		System.out.println("-listClone--apr�s recombinaison--SeulesCombisDeux----");
		System.out.println("----------");
		System.out.println(listClone);

		//res
		System.out.println("-res--apr�s recombinaison-SeulesCombisDeux-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}
		//Seules
		System.out.println("---seules--apr�s recombinaison-SeulesCombisDeux---");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}


	}



	private void remplissageDeMain(Hand hand, ArrayList<Tuile> list, FacadeTuile facadeTuile) {
		try {

			for(int i=0; i<14 ; i++){
				hand.fillHand(facadeTuile.getITuile(i,list));
			}

		} catch (MainPleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private FacadeTuile genererJeuDeTuilesMelangees(ArrayList<Tuile> list) {
		//On instancie la facade de Kevin. 
		FacadeTuile facadeTuile = new FacadeTuile();
		// On applique la m�thode de la facade pour remplir la liste "list" pr�c�demment cr��e
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		return facadeTuile;
	}

	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � recombiner une tuile seule  avec deux tuile d'un groupe pour fabriquer des suites
	 *Test valid� le 10.08.2016 ...
	 **/

	public void testRecombinaisonSeulesCombis(){
		System.out.println("==========");
		System.out.println("testRecombinaisonSeulesCombis");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();



		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);


		remplissageDeMain(hand, list, facadeTuile);




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
		//recombinaisons
		hand.recombinaisonSeulesSeules(res, seules);
		hand.recombinaisonSeulesCombisDeux( res, seules);

		//Affichage des listes trouv�es (avec  recombinaison SeulesSeules)
		System.out.println("-listClone--avec  recombinaison SeulesCombisDeux------");
		System.out.println("----------");		
		System.out.println(listClone);

		System.out.println("-res---avec  recombinaison SeulesCombisDeux-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules--avec  recombinaison SeulesCombisDeux----");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}




		hand.recombinaisonSeulesCombis( res, seules);

		//Affichage des listes trouv�es (apr�s recombinaison)
		System.out.println("-listClone--apr�s recombinaison--SeulesCombis----");
		System.out.println("----------");
		System.out.println(listClone);

		System.out.println("-res--apr�s recombinaison-SeulesCombis-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules--apr�s recombinaison-SeulesCombis---");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}


	}

	@Test
	/**
	 *Ce test cr�e une main factice
	 *On teste la capacit� de notre fonction � v�rifier qu'aucune tuile n'est seule dans res
	 *Test valid� le 10.08.2016
	 **/

	public void testpetiteVerif(){
		System.out.println("==========");
		System.out.println("testpetiteVerif");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();



		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);


		remplissageDeMain(hand, list, facadeTuile);




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
		//recombinaisons
		hand.recombinaisonSeulesSeules(res, seules);
		hand.recombinaisonSeulesCombisDeux( res, seules);
		hand.recombinaisonSeulesCombis( res, seules);

		//Affichage des listes trouv�es (avec  recombinaison SeulesSeules)
		System.out.println("-listClone--avec  recombinaison ------");
		System.out.println("----------");		
		System.out.println(listClone);

		System.out.println("-res---avec  recombinaison -----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules--avec  recombinaison ----");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}

		System.out.println("-taille res--avant petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}

		hand.petiteVerif(res, seules);


		//Affichage des listes trouv�es (apr�s recombinaison)
		System.out.println("-listClone--apr�s petiteVerif----");
		System.out.println("----------");
		System.out.println(listClone);

		System.out.println("-res--apr�s petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}

		System.out.println("---seules--apr�s petiteVerif---");
		System.out.println("----------");
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}

		System.out.println("-taille res--apr�s petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
	}

	@Test
	/**
	 *on teste la capacit� de notre fonction a detecter un MahJong
	 *Test valid� le 10.08.2016
	 **/

	public void testVerifMahjong(){
		System.out.println("testVerifMahjong");
		System.out.println("----------------------");

		//on cr�e un faux res
		List<List<Tuile>> res = new ArrayList<List<Tuile>>();
		FacadeTuile tuileFacade=new FacadeTuile();
		ArrayList<Tuile> tuilesList = new ArrayList<Tuile>();
		ArrayList<Tuile> list = tuileFacade.getTuilesList(tuilesList);
		boolean mahjongPossible=false;
		Hand hand = new Hand();

		Tuile tuile1=tuileFacade.getITuile(1, list);
		List<Tuile> list3 = new ArrayList<Tuile>();
		List<Tuile> list2 = new ArrayList<Tuile>();

		for (int i=0;i<3;i++){
			list3.add(tuile1);
		}
		for (int i=0;i<2;i++){
			list2.add(tuile1);
		}




		for (int i=0;i<4;i++){
			res.add(list3);
		}
		res.add(list2);

		mahjongPossible = hand.verifMahjong(res);
		System.out.println(mahjongPossible);
		String str ="";
		if(mahjongPossible==true){
			str="Bravo";
		}else{ 
			str="Perdu";
		}

		String actual = str;
		String expected = "Bravo";

		assertEquals(expected,actual);
	}







	@Test
	/**
	 *on teste la combinaison de nos fonctions : de triTuiles � petite v�rif -> automatisation
	 *Test valid� le 10.08.2016
	 **/

	public void testFindCombinaisons(){
		System.out.println("==========");
		System.out.println("testpetiteVerif");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();

		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);

		remplissageDeMain(hand, list, facadeTuile);


		List<List<Tuile>> res = hand.findCombinaisons(hand.tuilesListOfHand);


		System.out.println("-res--apr�s traitement-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}
		System.out.println("-taille res--apr�s petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		System.out.println(hand.mahjongPossible);

	}

	@Test
	/**
	 *on teste l'identification de nos combinaisons pr�sentes dans la main (pung, kong, chow) apr�s que les groupes furent form�s par findCombinaisons.
	 *Test valid� le 12.08.2016
	 **/

	public void testIdentificationCombi(){
		System.out.println("==========");
		System.out.println("testIdentificationCombi");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();

		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);

		remplissageDeMain(hand, list, facadeTuile);

		//On trouve les combinaisons (paire, pung, kong, chow) de la main ind�pendamment de leur nom
		List<List<Tuile>> res = hand.findCombinaisons(hand.tuilesListOfHand);

		//On les affiche
		System.out.println("-res--apr�s traitement-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
		}
		System.out.println("-taille res--apr�s petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		System.out.println(hand.mahjongPossible);

		//On cr�e notre hashMap qui a pour cl�s : pung, kong, chow et qui prend en param�tre la liste de nos combinaisons.
		//On applique la fonction identeficationCombi qui va r�partir les combinaisons dans les listes auxquelles elles appartiennent (PUNG, KONG, CHOW)
		HashMap<Combinaison, List<List<Tuile>>> resCombi = hand.identificationCombi(res);

		System.out.println("-resCombi--apr�s findCombinaisons-----");
		System.out.println("----------");
		
		//On affiche les cl�s associ�es aux combinaisons trouv�es grace au getName renvoyant � l'�num�ration de la classe MahjongPartie
		for (Combinaison mapKey : resCombi.keySet()) {

			List<List<Tuile>> currentCombinaisonList = resCombi.get(mapKey);

			System.out.println("------------------");
			System.out.println(mapKey.getName()+" ");
			System.out.println("------------------");

			// On affiche les tuiles correspondantes � la combinaison trouv�e
			int i=1;
			for (List<Tuile> elementCurrentCombi : currentCombinaisonList) {
				
				System.out.println("N�"+i);
				i++;
				for (Tuile tuile : elementCurrentCombi) {
					System.out.print(tuile.getType().getName()+" ");
					System.out.println(tuile.getValeur().getName()+" ");
				}


			}



		}


	}

}



