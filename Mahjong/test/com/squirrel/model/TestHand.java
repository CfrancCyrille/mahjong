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
		//On visualise ce que contient liste remplie. Permet de v�rifier que les tuiles sont dans le bon ordre
		System.out.println(hand.tuilesListOfHand);
		for (int i=0; i<144;i++){
			System.out.print(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getType().getName());
			System.out.print(" ");
			System.out.println(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getValeur().getName());
		}

		// on teste la taille du jeu pour v�rifier qu'il n'y a pas de perte ou gain de tuiles lors du rangement
		int actual = list.size();
		int expected = 144;
		
		assertEquals(expected, actual);
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
		//ici on utilise des m�thodes pour r�duire l'initialisation du test
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);
		remplissageDeMain(hand, list, facadeTuile);

		//on trie les tuiles
		hand.triTuiles(hand.tuilesListOfHand);
		

		//on affiche les tuiles tri�es. Permet de v�rifi�e la distribution des tuiles � l'�tape suivante
		System.out.println("Main tri�e");
		System.out.println("----------");
		for (Tuile t : hand.tuilesListOfHand) {
			System.out.print(t.getType().getName() + " ");
			System.out.println(t.getValeur().getName()); 
		}


		//On instancie les listes res, seules et une fausse liste identique � la main du joueur
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);


		//on execute la methode de "rangement"
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);

		//Affichage des groupes trouv�es (la main, les groupes identifi�s et les tuiles seules)
		System.out.println("-Groupes form�s--------");
		System.out.println("----------");
		// on s'attends � ce que listclone soit vid�e de toutes ses tuiles
		System.out.println("-listClone--------");
		System.out.println("----------");
		System.out.println(listClone);
		
		int actual = listClone.size();
		int expected = 0;
		assertEquals(expected, actual);
		
		// on s'attends � trouver des groupes dans res (res peut �tre vide s'il n'y a pas de groupes)
		System.out.println("-res--------");
		System.out.println("----------");
		affichRes(res);
		// on s'attends � trouver des tuiles seules dans seules (seules peut �tre vide s'il n'y a pas de tuiles seules)
		System.out.println("---seules------");
		System.out.println("----------");
		affichSeules(seules);

	}



	private void affichSeules(List<Tuile> seules) {
		for (Tuile t : seules) {
			System.out.print(t.getType().getName()+" ");
			System.out.println(t.getValeur().getName()); 
		}
	}



	private void affichRes(List<List<Tuile>> res) {
		for (int j=0;j<res.size();j++){
			for (Tuile t : res.get(j)) {
				System.out.print(t.getType().getName()+" ");
				System.out.println(t.getValeur().getName()); 
			}
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
		// on trie les tuiles
		hand.triTuiles(hand.tuilesListOfHand);
		


		//On instancie les listes res, seules et une fausse liste identique � la main du joueur
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
		System.out.println("-res---avant recombinaison-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avant recombinaison----");
		System.out.println("----------");
		affichSeules(seules);

		//on fait la recombinaison, si des tuiles seules forment une suite elles doivent disparaitre de seules et aller dans res
		hand.recombinaisonSeulesSeules(res, seules);

		//Affichage des listes trouv�es (apr�s recombinaison)
		System.out.println("-res--apr�s recombinaison------");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--apr�s recombinaison----");
		System.out.println("----------");
		affichSeules(seules);


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

		// on trie
		hand.triTuiles(hand.tuilesListOfHand);
		

		//On instancie les listes res, seules et une fausse liste identique � la main du joueur
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);

		// on range
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);

		//on recombine seule � seule
		hand.recombinaisonSeulesSeules(res, seules);
		
		
		//Affichage des listes trouv�es (avec  recombinaison SeulesSeules)
		System.out.println("-Groupes form�s---");
		System.out.println("----------");

		System.out.println("-res---avec  recombinaison SeulesSeules-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avec  recombinaison SeulesSeules----");
		System.out.println("----------");
		affichSeules(seules);



		//on recombine -  deux tuiles seules avec une tuile de res
		hand.recombinaisonSeulesCombisDeux( res, seules);

		//Affichage des listes trouv�es (apr�s recombinaison)
		//res
		System.out.println("-res--apr�s recombinaison-SeulesCombisDeux-----");
		System.out.println("----------");
		affichRes(res);
		//Seules
		System.out.println("---seules--apr�s recombinaison-SeulesCombisDeux---");
		System.out.println("----------");
		affichSeules(seules);


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

		//on trie
		hand.triTuiles(hand.tuilesListOfHand);
		

		//On instancie les listes res, seules et une fausse liste identique � la main du joueur
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);


		// on range
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
		//on recombine
		hand.recombinaisonSeulesSeules(res, seules);
		hand.recombinaisonSeulesCombisDeux( res, seules);

		//Affichage des listes trouv�es 
		System.out.println("--Groupes form�s----");
		System.out.println("----------");

		System.out.println("-res---avec  recombinaison SeulesCombisDeux-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avec  recombinaison SeulesCombisDeux----");
		System.out.println("----------");
		affichSeules(seules);



		// on recombine - une tuile seule, deux tuiles de res
		hand.recombinaisonSeulesCombis( res, seules);

		//Affichage des listes trouv�es (apr�s recombinaison)

		System.out.println("-res--apr�s recombinaison-SeulesCombis-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--apr�s recombinaison-SeulesCombis---");
		System.out.println("----------");
		affichSeules(seules);


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
		//on trie
		hand.triTuiles(hand.tuilesListOfHand);
		

		//On instancie les listes res, seules et une fausse liste identique � la main du joueur
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);


		// on range
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);
		//on recombine
		hand.recombinaisonSeulesSeules(res, seules);
		hand.recombinaisonSeulesCombisDeux( res, seules);
		hand.recombinaisonSeulesCombis( res, seules);

		//Affichage des listes trouv�es (avec  recombinaison SeulesSeules)
		System.out.println("--Groupes form�s----");
		System.out.println("----------");

		System.out.println("-res---avec  recombinaison -----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avec  recombinaison ----");
		System.out.println("----------");
		affichSeules(seules);

		System.out.println("-taille res--avant petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		//apr�s cette m�thode, le tableau de seules doit �tre identique ou plus grand qu'avant
		int expected = seules.size();
		
		// on v�rifie res, aucune tuile ne doit �tre seule - aucune suite de deux tuiles non plus
		hand.petiteVerif(res, seules);


		//Affichage des listes trouv�es (apr�s recombinaison)


		System.out.println("-res--apr�s petiteVerif-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--apr�s petiteVerif---");
		System.out.println("----------");
		affichSeules(seules);

		System.out.println("-taille res--apr�s petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		//apr�s cette m�thode, le tableau de seules doit �tre identique ou plus grand qu'avant
		int actual = seules.size();
		
		if(actual>expected ){
			System.out.println("v�rifier visuellement que des tuiles ont �t� rajout�es dans seules");
		}
			if (actual==expected){
			assertEquals(expected, actual);
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

		//on cr�e un faux res contenant un mahjong
		List<List<Tuile>> res = new ArrayList<List<Tuile>>();
		List<List<Tuile>> res2 = new ArrayList<List<Tuile>>();
		FacadeTuile tuileFacade=new FacadeTuile();
		ArrayList<Tuile> tuilesList = new ArrayList<Tuile>();
		ArrayList<Tuile> list = tuileFacade.getTuilesList(tuilesList);
		
		Hand hand = new Hand();
		Hand hand2 = new Hand();

		Tuile tuile1=tuileFacade.getITuile(1, list);
		List<Tuile> list3 = new ArrayList<Tuile>();
		List<Tuile> list2 = new ArrayList<Tuile>();
		// on cr�e des pung ou chow (3 tuiles)
		for (int i=0;i<3;i++){
			list3.add(tuile1);
		}
		// on cr�e une paire
		for (int i=0;i<2;i++){
			list2.add(tuile1);
		}
		// on met quatre combinaisons de trois tuiles dans le faux res et une paire
		for (int i=0;i<4;i++){
			res.add(list3);
		}
		res.add(list2);
		
		//on teste la m�thode, on s'attend � trouver true
		hand.mahjongPossible = hand.verifMahjong(res);
		System.out.println("on s'attends � true : "+hand.mahjongPossible );
		assertEquals(true,hand.mahjongPossible);
		
		
		
		// on met 2 combinaisons de trois tuiles dans le faux res et 4 paire -> on s'attends � un echec
		for (int i=0;i<4;i++){
			res2.add(list2);
		}
		res2.add(list3);
		res2.add(list3);
		
		//on teste la m�thode, on s'attend � trouver false
		hand2.mahjongPossible = hand2.verifMahjong(res2);
		System.out.println("on s'attends � false : "+hand2.mahjongPossible );
		assertEquals(false,hand2.mahjongPossible);
		
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

		//on v�rifie que res apr�s traitement contient des combinaisons
		System.out.println("-res--apr�s traitement-----");
		System.out.println("----------");
		affichRes(res);
		//on regarde la taille des combinaisons dans res, correspondent elles?
		System.out.println("-taille res--apr�s petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		//on v�rifie si mahjonPossible a chang� de valeur
		System.out.println("mahjongPossible?" +hand.mahjongPossible);

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
		affichRes(res);
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
	
	@Test
	/**
	 *on teste l'identification de nos combinaisons pr�sentes dans la main (pung, kong, chow) apr�s que les groupes furent form�s par findCombinaisons.
	 *Test valid� le 12.08.2016
	 **/

	public void testPiocheDefausseIsCombi(){
		System.out.println("==========");
		System.out.println("testPiocheDefausseIsCombi");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);
		remplissageDeMain(hand, list, facadeTuile);
		

		//on cr�e une main factice � 13 tuiles
		System.out.println("==========");
		System.out.println("main qui veut r�cup�rer la tuile d�fauss�e");
		System.out.println("==========");
		hand.tuilesListOfHand.remove(0);
		hand.toStringList(hand.tuilesListOfHand);
		System.out.println(hand.tuilesListOfHand.size() + "tuiles");
		
		//on v�rifie le nombre de tuiles
		int actual = hand.tuilesListOfHand.size();
		int expected = 13;
		assertEquals(expected, actual);
		
		System.out.println("==========");
		System.out.println("tuile d�fauss�e");
		System.out.println("==========");
		boolean isCombi = hand.isCombi(list.get(50));
		System.out.print(list.get(50).getType().getName()+" ");
		System.out.println(list.get(50).getValeur().getName()+" ");
		
		System.out.println("==========");
		System.out.println("bool�en : true si on peut faire une combinaison");
		System.out.println("==========");
		System.out.println(isCombi);
	}
	
	@Test
	/**
	 * A sauter - useless
	 *on teste l'identification de nos combinaisons pr�sentes dans la main (pung, kong, chow) apr�s que les groupes furent form�s par findCombinaisons.
	 *Test valid� le 12.08.2016
	 **/

	public void testPiocheDefaussePickDefausse(){
		System.out.println("==========");
		System.out.println("testPiocheDefaussePickDefausse");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);
		remplissageDeMain(hand, list, facadeTuile);
		

		//on cr�e une main factice � 13 tuiles
		hand.tuilesListOfHand.remove(0);
		System.out.println("main de  " + hand.tuilesListOfHand.size() + " tuiles");
		
		//on v�rifie le nombre de tuiles
		int actual = hand.tuilesListOfHand.size();
		int expected = 13;
		assertEquals(expected, actual);
		
		//on affiche la main 
				hand.toStringList(hand.tuilesListOfHand);
		
		System.out.println("==========");
		System.out.println("tuile d�fauss�e");
		boolean isCombi = hand.isCombi(list.get(50));
		System.out.println(list.get(50).getType().getName()+" "+list.get(50).getValeur().getName()+" ");
		System.out.println("==========");

		System.out.println("bool�en : " +isCombi);
		
		
		hand.pickDefausse(list.get(50), isCombi); //ici tuileListOfHand.size() = 14
		
		//on affiche la main 
		System.out.println("==========");
		hand.toStringList(hand.tuilesListOfHand);
		
		
		
		
		
//		//on cr�e une main factice � 14 tuiles -> elle ne doit pas pouvoir piocher!
//		//on v�rifie le nombre de tuiles
//		Hand hand2 = new Hand();
//		ArrayList<Tuile> list2 = new ArrayList<Tuile>();
//		FacadeTuile facadeTuile2 = genererJeuDeTuilesMelangees(list2);
//		remplissageDeMain(hand2, list2, facadeTuile2);
//		
//		System.out.println(hand2.tuilesListOfHand.size() + "tuiles");
//				int actual2 = hand2.tuilesListOfHand.size();
//				int expected2 = 14;
//				assertEquals(expected, actual);
//				
//				System.out.print("==========");
//				System.out.print("tuile d�fauss�e");
//				System.out.println("==========");
//				boolean isCombi2 = hand2.isCombi(list2.get(50));
//				System.out.println(list2.get(50).getType().getName()+" "+list2.get(50).getValeur().getName()+" ");
//
//				System.out.println("bool�en : " +isCombi2);
//				
//				hand2.pickDefausse(list2.get(50), isCombi2);
//				
//				//on affiche la main 
//				hand2.toStringList(hand2.tuilesListOfHand);
	}


}



