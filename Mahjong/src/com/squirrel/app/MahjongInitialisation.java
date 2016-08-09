package com.squirrel.app;

import java.util.ArrayList;
import java.util.List;

import com.squirrel.ctrl.Gestionnaire;
import com.squirrel.model.FacadeTuile;
import com.squirrel.model.HandFacade;
import com.squirrel.model.Mur;
import com.squirrel.model.MurException;
import com.squirrel.model.Tuile;
import com.squirrel.model.TuileFactory.TypeTuile;


public class MahjongInitialisation 

{
	public Mur murEst;
	public Mur murOuest;
	public Mur murSud;
	public Mur murNord;
	
	public HandFacade mainEst;
	public HandFacade mainOuest;
	public HandFacade mainNord;
	public HandFacade mainSud;
	
	List bonusEst = new ArrayList();
	List bonusOuest = new ArrayList();
	List bonusSud = new ArrayList();
	List bonusNord = new ArrayList();
	
	ArrayList<Tuile> listeTuiles;
	public Mur murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile;
	
	public void initialiserUnePartie() {
		@SuppressWarnings("unused")
		int joueurCommencant;
		String murBrechable;
		int breche;
		int score1=Gestionnaire.lancerDes();
		int score2=Gestionnaire.lancerDes();
		int somme;
		joueurCommencant=Gestionnaire.premierTirage();
		somme=score1+score2+Gestionnaire.lancerDes()+Gestionnaire.lancerDes();
		murBrechable=Gestionnaire.murBrechable(somme, Gestionnaire.muraDetruire(score1, score2));
		breche=Gestionnaire.breche(somme);

		//Creation des 4 murs
		this.creationDes4Murs();

		Mur murPioche=null;//"Post it" pour designer le mur dans lequel on pioche
		Mur murSpecial=null;

		//Génération de la liste de toutes les tuiles
		listeTuiles = new 	ArrayList<Tuile>();
		FacadeTuile objFacade = new FacadeTuile();
		listeTuiles=objFacade.getTuilesList(listeTuiles);

		//Remplissage des 4 murs avec les 144 tuiles
		for(int j =0;j<144;j=j+4){
			this.murEst.ajouterTuile(listeTuiles.get(j));
			this.murOuest.ajouterTuile(listeTuiles.get(j+1));
			this.murSud.ajouterTuile(listeTuiles.get(j+2));
			this.murNord.ajouterTuile(listeTuiles.get(j+3));
		}

		//Determination du mur brechable en fonction du calcul du gestionnaire
		if (murBrechable.equals("Nord")) {
			murPioche = this.murNord;
			murSpecial = this.murNord;
		}
		else if (murBrechable.equals("Sud")) {
			murPioche = this.murSud;
			murSpecial = this.murSud;
		}
		else if (murBrechable.equals("Ouest")) {
			murPioche = this.murOuest;
			murSpecial = this.murOuest;
		}
		else if (murBrechable.equals("Est")) {
			murPioche = this.murEst;
			murSpecial = this.murEst;
		}

		//Placement de la breche sur le mur à piocher
		try {
			murPioche.setBreche(breche);
		} catch (MurException e) {
			e.printStackTrace();
		}

		//Placement de la brèche spéciale
		try {
			murPioche.setBrecheSpeciale(breche);
		} catch (MurException e1) {
			e1.printStackTrace();
		}

		//Création des 4 mains
		this.creationDes4Mains();

		//Distribution des tuiles dans les quatre mains selon la distribution du mahjong traditionnel
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, mainEst);
			}
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, mainSud);
			}
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, mainOuest);
			}
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, mainNord);
			}
		}
		//Distribution d'une tuiles dans les mains pour terminer la distribution
		murPioche = this.uneTuileDansLaMain(murPioche, mainEst);
		murPioche = this.uneTuileDansLaMain(murPioche, mainSud);
		murPioche = this.uneTuileDansLaMain(murPioche, mainOuest);
		murPioche = this.uneTuileDansLaMain(murPioche, mainNord);
		murPioche = this.uneTuileDansLaMain(murPioche, mainEst);
		
		murSpecial = verifFleursEtSaisons(murSpecial, mainEst, bonusEst);
		murSpecial = verifFleursEtSaisons(murSpecial, mainSud, bonusSud);
		murSpecial = verifFleursEtSaisons(murSpecial, mainOuest, bonusOuest);
		murSpecial = verifFleursEtSaisons(murSpecial, mainNord, bonusNord);
		
		//La c'est pour que partie sache ou piocher
		murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile=murPioche;
	}

	private Mur verifFleursEtSaisons(Mur murSpecial, HandFacade currentHand, List currentbonus) {
		int i = 0;
		while ( i < currentHand.handSize()) {
			Tuile tuile1=currentHand.get(i);
			if (tuile1.getType().equals(TypeTuile.FLEU) || tuile1.getType().equals(TypeTuile.SAIS)){
				currentbonus.add(tuile1);
				currentHand.remove(tuile1);
				murSpecial = this.uneTuileSpecialeDansLaMain(murSpecial, currentHand);
			}
			else {
				i++;
			}
		}
		return murSpecial;
	}
	
	/**
	 * Methode permettant de prendre une tuile dans le mur de pioche pour la donner à la main choisie 
	 * et de changer le mur de pioche si ce dernier est completement vidé de ses tuiles
	 * @param murPioche
	 * @param mainEst
	 * @return
	 */
	private Mur uneTuileDansLaMain(Mur murPioche, HandFacade mainEst) {
		try {
			Tuile tuilePiochee = murPioche.piocherTuile();
			int breche1 = murPioche.getBreche();
			mainEst.remplirMain(tuilePiochee);
			if(breche1==34){
				murPioche = murPioche.nextMur(murPioche, tousLesMurs(this));
			}
		} catch (MurException e) {
			e.printStackTrace();
		}
		return murPioche;
	}
	
	private Mur uneTuileSpecialeDansLaMain(Mur murSpecial, HandFacade mainCourrante) {
		try {
			Tuile tuilePiochee = murSpecial.retirerTuile();
			int breche1 = murSpecial.getBrecheSpeciale();
			mainCourrante.remplirMain(tuilePiochee);
			if(breche1==-1){
				murSpecial = murSpecial.prevMur(murSpecial, tousLesMurs(this));
			}
		} catch (MurException e) {
			e.printStackTrace();
		}
		return murSpecial;
	}

	private static Mur[] tousLesMurs(MahjongInitialisation mahjong) {
		return new Mur[]{mahjong.murNord, mahjong.murOuest, mahjong.murSud, mahjong.murEst};
	}
	

	private void creationDes4Murs() {
		this.murEst = new Mur();
		this.murOuest = new Mur();
		this.murSud = new Mur();
		this.murNord = new Mur();

	}
	
	private void creationDes4Mains() {
		this.mainEst = new HandFacade();
		this.mainOuest = new HandFacade();
		this.mainSud = new HandFacade();
		this.mainNord = new HandFacade();

	}
}
