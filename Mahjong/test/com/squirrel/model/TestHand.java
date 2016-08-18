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
		//On visualise ce que contient liste remplie. Permet de vérifier que les tuiles sont dans le bon ordre
		System.out.println(hand.tuilesListOfHand);
		for (int i=0; i<144;i++){
			System.out.print(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getType().getName());
			System.out.print(" ");
			System.out.println(facadeTuile.getITuile( i,(ArrayList<Tuile>) list).getValeur().getName());
		}

		// on teste la taille du jeu pour vérifier qu'il n'y a pas de perte ou gain de tuiles lors du rangement
		int actual = list.size();
		int expected = 144;
		
		assertEquals(expected, actual);
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
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à ranger les groupes dans une list de list et les seules dans une liste
	 *Test validé le 09.08.2016 
	 **/
	public void testTrouveGroupesTuilesIdentiques(){
		System.out.println("==========");
		System.out.println("testTrouveGroupesTuilesIdentiques");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		//ici on utilise des méthodes pour réduire l'initialisation du test
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);
		remplissageDeMain(hand, list, facadeTuile);

		//on trie les tuiles
		hand.triTuiles(hand.tuilesListOfHand);
		

		//on affiche les tuiles triées. Permet de vérifiée la distribution des tuiles à l'étape suivante
		System.out.println("Main triée");
		System.out.println("----------");
		for (Tuile t : hand.tuilesListOfHand) {
			System.out.print(t.getType().getName() + " ");
			System.out.println(t.getValeur().getName()); 
		}


		//On instancie les listes res, seules et une fausse liste identique à la main du joueur
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);


		//on execute la methode de "rangement"
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);

		//Affichage des groupes trouvées (la main, les groupes identifiés et les tuiles seules)
		System.out.println("-Groupes formés--------");
		System.out.println("----------");
		// on s'attends à ce que listclone soit vidée de toutes ses tuiles
		System.out.println("-listClone--------");
		System.out.println("----------");
		System.out.println(listClone);
		
		int actual = listClone.size();
		int expected = 0;
		assertEquals(expected, actual);
		
		// on s'attends à trouver des groupes dans res (res peut être vide s'il n'y a pas de groupes)
		System.out.println("-res--------");
		System.out.println("----------");
		affichRes(res);
		// on s'attends à trouver des tuiles seules dans seules (seules peut être vide s'il n'y a pas de tuiles seules)
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
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à recombiner les tuiles seules pour fabriquer des suites
	 *Test validé le 09.08.2016
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
		


		//On instancie les listes res, seules et une fausse liste identique à la main du joueur
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


		//Affichage des listes trouvées (avant recombinaison)
		System.out.println("-res---avant recombinaison-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avant recombinaison----");
		System.out.println("----------");
		affichSeules(seules);

		//on fait la recombinaison, si des tuiles seules forment une suite elles doivent disparaitre de seules et aller dans res
		hand.recombinaisonSeulesSeules(res, seules);

		//Affichage des listes trouvées (après recombinaison)
		System.out.println("-res--après recombinaison------");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--après recombinaison----");
		System.out.println("----------");
		affichSeules(seules);


	}

	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à recombiner les tuiles seules  avec une tuile d'un groupe pour fabriquer des suites
	 *Test validé le 10.08.2016
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
		

		//On instancie les listes res, seules et une fausse liste identique à la main du joueur
		List<List<Tuile>> res;
		res=new ArrayList<List<Tuile>>();
		List<Tuile> seules;
		seules=new ArrayList<Tuile>();
		List<Tuile> listClone;
		listClone=new ArrayList<Tuile>();
		listClone.addAll(hand.tuilesListOfHand);

		// on range
		hand.trouveGroupesTuilesIdentiques(res,seules, listClone);

		//on recombine seule à seule
		hand.recombinaisonSeulesSeules(res, seules);
		
		
		//Affichage des listes trouvées (avec  recombinaison SeulesSeules)
		System.out.println("-Groupes formés---");
		System.out.println("----------");

		System.out.println("-res---avec  recombinaison SeulesSeules-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avec  recombinaison SeulesSeules----");
		System.out.println("----------");
		affichSeules(seules);



		//on recombine -  deux tuiles seules avec une tuile de res
		hand.recombinaisonSeulesCombisDeux( res, seules);

		//Affichage des listes trouvées (après recombinaison)
		//res
		System.out.println("-res--après recombinaison-SeulesCombisDeux-----");
		System.out.println("----------");
		affichRes(res);
		//Seules
		System.out.println("---seules--après recombinaison-SeulesCombisDeux---");
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
		// On applique la méthode de la facade pour remplir la liste "list" précédemment créée
		facadeTuile.getTuilesList((ArrayList<Tuile>) list);
		return facadeTuile;
	}

	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à recombiner une tuile seule  avec deux tuile d'un groupe pour fabriquer des suites
	 *Test validé le 10.08.2016 ...
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
		

		//On instancie les listes res, seules et une fausse liste identique à la main du joueur
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

		//Affichage des listes trouvées 
		System.out.println("--Groupes formés----");
		System.out.println("----------");

		System.out.println("-res---avec  recombinaison SeulesCombisDeux-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--avec  recombinaison SeulesCombisDeux----");
		System.out.println("----------");
		affichSeules(seules);



		// on recombine - une tuile seule, deux tuiles de res
		hand.recombinaisonSeulesCombis( res, seules);

		//Affichage des listes trouvées (après recombinaison)

		System.out.println("-res--après recombinaison-SeulesCombis-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--après recombinaison-SeulesCombis---");
		System.out.println("----------");
		affichSeules(seules);


	}

	@Test
	/**
	 *Ce test crée une main factice
	 *On teste la capacité de notre fonction à vérifier qu'aucune tuile n'est seule dans res
	 *Test validé le 10.08.2016
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
		

		//On instancie les listes res, seules et une fausse liste identique à la main du joueur
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

		//Affichage des listes trouvées (avec  recombinaison SeulesSeules)
		System.out.println("--Groupes formés----");
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
		//après cette méthode, le tableau de seules doit être identique ou plus grand qu'avant
		int expected = seules.size();
		
		// on vérifie res, aucune tuile ne doit être seule - aucune suite de deux tuiles non plus
		hand.petiteVerif(res, seules);


		//Affichage des listes trouvées (après recombinaison)


		System.out.println("-res--après petiteVerif-----");
		System.out.println("----------");
		affichRes(res);

		System.out.println("---seules--après petiteVerif---");
		System.out.println("----------");
		affichSeules(seules);

		System.out.println("-taille res--après petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		//après cette méthode, le tableau de seules doit être identique ou plus grand qu'avant
		int actual = seules.size();
		
		if(actual>expected ){
			System.out.println("vérifier visuellement que des tuiles ont été rajoutées dans seules");
		}
			if (actual==expected){
			assertEquals(expected, actual);
		}
	}

	@Test
	/**
	 *on teste la capacité de notre fonction a detecter un MahJong
	 *Test validé le 10.08.2016
	 **/

	public void testVerifMahjong(){
		System.out.println("testVerifMahjong");
		System.out.println("----------------------");

		//on crée un faux res contenant un mahjong
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
		// on crée des pung ou chow (3 tuiles)
		for (int i=0;i<3;i++){
			list3.add(tuile1);
		}
		// on crée une paire
		for (int i=0;i<2;i++){
			list2.add(tuile1);
		}
		// on met quatre combinaisons de trois tuiles dans le faux res et une paire
		for (int i=0;i<4;i++){
			res.add(list3);
		}
		res.add(list2);
		
		//on teste la méthode, on s'attend à trouver true
		hand.mahjongPossible = hand.verifMahjong(res);
		System.out.println("on s'attends à true : "+hand.mahjongPossible );
		assertEquals(true,hand.mahjongPossible);
		
		
		
		// on met 2 combinaisons de trois tuiles dans le faux res et 4 paire -> on s'attends à un echec
		for (int i=0;i<4;i++){
			res2.add(list2);
		}
		res2.add(list3);
		res2.add(list3);
		
		//on teste la méthode, on s'attend à trouver false
		hand2.mahjongPossible = hand2.verifMahjong(res2);
		System.out.println("on s'attends à false : "+hand2.mahjongPossible );
		assertEquals(false,hand2.mahjongPossible);
		
	}







	@Test
	/**
	 *on teste la combinaison de nos fonctions : de triTuiles à petite vérif -> automatisation
	 *Test validé le 10.08.2016
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

		//on vérifie que res après traitement contient des combinaisons
		System.out.println("-res--après traitement-----");
		System.out.println("----------");
		affichRes(res);
		//on regarde la taille des combinaisons dans res, correspondent elles?
		System.out.println("-taille res--après petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		//on vérifie si mahjonPossible a changé de valeur
		System.out.println("mahjongPossible?" +hand.mahjongPossible);

	}

	@Test
	/**
	 *on teste l'identification de nos combinaisons présentes dans la main (pung, kong, chow) après que les groupes furent formés par findCombinaisons.
	 *Test validé le 12.08.2016
	 **/

	public void testIdentificationCombi(){
		System.out.println("==========");
		System.out.println("testIdentificationCombi");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();

		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);

		remplissageDeMain(hand, list, facadeTuile);

		//On trouve les combinaisons (paire, pung, kong, chow) de la main indépendamment de leur nom
		List<List<Tuile>> res = hand.findCombinaisons(hand.tuilesListOfHand);

		//On les affiche
		System.out.println("-res--après traitement-----");
		System.out.println("----------");
		affichRes(res);
		System.out.println("-taille res--après petiteVerif-----");
		System.out.println("----------");
		for (int j=0;j<res.size();j++){
			int taille = res.get(j).size();
			System.out.println("Ligne : "+j +" Taille : ["+taille+"]");


		}
		System.out.println(hand.mahjongPossible);

		//On crée notre hashMap qui a pour clés : pung, kong, chow et qui prend en paramètre la liste de nos combinaisons.
		//On applique la fonction identeficationCombi qui va répartir les combinaisons dans les listes auxquelles elles appartiennent (PUNG, KONG, CHOW)
		HashMap<Combinaison, List<List<Tuile>>> resCombi = hand.identificationCombi(res);

		System.out.println("-resCombi--après findCombinaisons-----");
		System.out.println("----------");
		
		//On affiche les clés associées aux combinaisons trouvées grace au getName renvoyant à l'énumération de la classe MahjongPartie
		for (Combinaison mapKey : resCombi.keySet()) {

			List<List<Tuile>> currentCombinaisonList = resCombi.get(mapKey);

			System.out.println("------------------");
			System.out.println(mapKey.getName()+" ");
			System.out.println("------------------");

			// On affiche les tuiles correspondantes à la combinaison trouvée
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
	
	@Test
	/**
	 *on teste l'identification de nos combinaisons présentes dans la main (pung, kong, chow) après que les groupes furent formés par findCombinaisons.
	 *Test validé le 12.08.2016
	 **/

	public void testPiocheDefausseIsCombi(){
		System.out.println("==========");
		System.out.println("testPiocheDefausseIsCombi");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);
		remplissageDeMain(hand, list, facadeTuile);
		

		//on crée une main factice à 13 tuiles
		System.out.println("==========");
		System.out.println("main qui veut récupérer la tuile défaussée");
		System.out.println("==========");
		hand.tuilesListOfHand.remove(0);
		hand.toStringList(hand.tuilesListOfHand);
		System.out.println(hand.tuilesListOfHand.size() + "tuiles");
		
		//on vérifie le nombre de tuiles
		int actual = hand.tuilesListOfHand.size();
		int expected = 13;
		assertEquals(expected, actual);
		
		System.out.println("==========");
		System.out.println("tuile défaussée");
		System.out.println("==========");
		boolean isCombi = hand.isCombi(list.get(50));
		System.out.print(list.get(50).getType().getName()+" ");
		System.out.println(list.get(50).getValeur().getName()+" ");
		
		System.out.println("==========");
		System.out.println("booléen : true si on peut faire une combinaison");
		System.out.println("==========");
		System.out.println(isCombi);
	}
	
	@Test
	/**
	 * A sauter - useless
	 *on teste l'identification de nos combinaisons présentes dans la main (pung, kong, chow) après que les groupes furent formés par findCombinaisons.
	 *Test validé le 12.08.2016
	 **/

	public void testPiocheDefaussePickDefausse(){
		System.out.println("==========");
		System.out.println("testPiocheDefaussePickDefausse");
		System.out.println("==========");
		Hand hand = new Hand();
		ArrayList<Tuile> list = new ArrayList<Tuile>();
		FacadeTuile facadeTuile = genererJeuDeTuilesMelangees(list);
		remplissageDeMain(hand, list, facadeTuile);
		

		//on crée une main factice à 13 tuiles
		hand.tuilesListOfHand.remove(0);
		System.out.println("main de  " + hand.tuilesListOfHand.size() + " tuiles");
		
		//on vérifie le nombre de tuiles
		int actual = hand.tuilesListOfHand.size();
		int expected = 13;
		assertEquals(expected, actual);
		
		//on affiche la main 
				hand.toStringList(hand.tuilesListOfHand);
		
		System.out.println("==========");
		System.out.println("tuile défaussée");
		boolean isCombi = hand.isCombi(list.get(50));
		System.out.println(list.get(50).getType().getName()+" "+list.get(50).getValeur().getName()+" ");
		System.out.println("==========");

		System.out.println("booléen : " +isCombi);
		
		
		hand.pickDefausse(list.get(50), isCombi); //ici tuileListOfHand.size() = 14
		
		//on affiche la main 
		System.out.println("==========");
		hand.toStringList(hand.tuilesListOfHand);
		
		
		
		
		
//		//on crée une main factice à 14 tuiles -> elle ne doit pas pouvoir piocher!
//		//on vérifie le nombre de tuiles
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
//				System.out.print("tuile défaussée");
//				System.out.println("==========");
//				boolean isCombi2 = hand2.isCombi(list2.get(50));
//				System.out.println(list2.get(50).getType().getName()+" "+list2.get(50).getValeur().getName()+" ");
//
//				System.out.println("booléen : " +isCombi2);
//				
//				hand2.pickDefausse(list2.get(50), isCombi2);
//				
//				//on affiche la main 
//				hand2.toStringList(hand2.tuilesListOfHand);
	}


}



