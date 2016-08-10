package com.squirrel.app;

import java.util.ArrayList;

import com.squirrel.ctrl.Gestionnaire;
import com.squirrel.model.FacadeTuile;
import com.squirrel.model.HandFacade;
import com.squirrel.model.Joueur;
import com.squirrel.model.Mur;
import com.squirrel.model.MurException;
import com.squirrel.model.Tuile;
import com.squirrel.model.TuileFactory.TypeTuile;
import com.squirrel.model.TuileVen.VenTuile;


public class MahjongInitialisation 

{
	public Mur murEst;
	public Mur murOuest;
	public Mur murSud;
	public Mur murNord;
	
	/*public HandFacade mainEst;
	public HandFacade mainOuest;
	public HandFacade mainNord;
	public HandFacade mainSud;*/
	
	Joueur jEst = new Joueur ("Jean-Pierre", VenTuile.EST);
	Joueur jOuest = new Joueur ("Hervé", VenTuile.OUE);
	Joueur jSud = new Joueur ("Denis",VenTuile.SUD);
	Joueur jNord = new Joueur ("Paulette", VenTuile.NOR);
	
	ArrayList<Tuile> listeTuiles;
	public Mur murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile;
	public Mur murSpe;
	
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
		//this.creationDes4Mains();

		//Distribution des tuiles dans les quatre mains selon la distribution du mahjong traditionnel
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, jEst);
			}
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, jSud);
			}
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, jOuest);
			}
			for (int i = 0; i < 4; i++) {
				murPioche = this.uneTuileDansLaMain(murPioche, jNord);
			}
		}
		//Distribution d'une tuiles dans les mains pour terminer la distribution
		murPioche = this.uneTuileDansLaMain(murPioche, jEst);
		murPioche = this.uneTuileDansLaMain(murPioche, jSud);
		murPioche = this.uneTuileDansLaMain(murPioche, jOuest);
		murPioche = this.uneTuileDansLaMain(murPioche, jNord);
		murPioche = this.uneTuileDansLaMain(murPioche, jEst);
		
		//On vérifie chacune des mains pour retirer les fleurs et saisons et on les remplace par des tuiles venant du "mur spécial"
		murSpecial = verifFleursEtSaisons(murSpecial, jEst);
		murSpecial = verifFleursEtSaisons(murSpecial, jSud);
		murSpecial = verifFleursEtSaisons(murSpecial, jOuest);
		murSpecial = verifFleursEtSaisons(murSpecial, jNord);
		
		//La c'est pour que partie sache ou piocher
		murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile=murPioche;
		murSpe=murSpecial;
		
	}

	/**
	 * Méthode permettant de vérifier s'il y a des tuiles fleurs ou des tuiles saisons dans la main, de les retirer s'il y en a, 
	 * de les mettre dans une ArrayList bonus, et de piocher une nouvelle tuile, elle même vérifiée, dans le sens contraire de la
	 * pioche classique
	 * @param murSpecial = mur dans lequel on pioche dans le sens contraire
	 * @param currentHand = main dans laquelle on véfifie
	 * @param currentbonus = liste où l'on place les tuiles retirées de la main
	 * @return
	 */
	Mur verifFleursEtSaisons(Mur murSpecial, Joueur currentJoueur) {
		int i = 0;
		while ( i < currentJoueur.getHand().handSize()) {
			Tuile tuile1=currentJoueur.getHand().get(i);
			if (tuile1.getType().equals(TypeTuile.FLEU) || tuile1.getType().equals(TypeTuile.SAIS)){
				currentJoueur.getBonus().add(tuile1);
				currentJoueur.getHand().remove(tuile1);
				murSpecial = this.uneTuileSpecialeDansLaMain(murSpecial, currentJoueur.getHand());
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
	Mur uneTuileDansLaMain(Mur murPioche, Joueur curentJoueur) {
		try {
			Tuile tuilePiochee = murPioche.piocherTuile();
			int breche1 = murPioche.getBreche();
			curentJoueur.getHand().remplirMain(tuilePiochee);
			if(breche1==34){
				murPioche = murPioche.nextMur(murPioche, tousLesMurs(this));
			}
		} catch (MurException e) {
			e.printStackTrace();
		}
		return murPioche;
	}
	
	/**
	 * Methode permettant de prendre une tuile dans le mur spécial pour la donner à la main choisie 
	 * et de changer le mur spécial si ce dernier est completement vidé de ses tuiles
	 * @param murSpecial
	 * @param mainCourrante
	 * @return
	 */
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
	
	/*private void creationDes4Mains() {
		this.mainEst = new HandFacade();
		this.mainOuest = new HandFacade();
		this.mainSud = new HandFacade();
		this.mainNord = new HandFacade();

	}*/
}
