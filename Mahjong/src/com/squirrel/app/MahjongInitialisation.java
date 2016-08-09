package com.squirrel.app;

import java.util.ArrayList;
import java.util.List;

import com.squirrel.ctrl.Gestionnaire;
import com.squirrel.model.FacadeTuile;
import com.squirrel.model.HandFacade;
import com.squirrel.model.Mur;
import com.squirrel.model.MurException;
import com.squirrel.model.Tuile;


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
	
	ArrayList<Tuile> listeTuiles;
	
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
		Mur murSpecial=null; //"Post it" pour designer le mur dans lequel on récupère une tuile en échange d'une fleur...

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
			murSpecial.setBrecheSpeciale(breche);
		} catch (MurException e1) {
			e1.printStackTrace();
		}

		//Création des 4 mains
		this.creationDes4Mains();

		//Distribution des tuiles dans les quatre mains selon la distribution du mahjong traditionnel
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				this.uneTuileDansLaMain(murPioche, murSpecial, mainEst);
			}
			for (int i = 0; i < 4; i++) {
				this.uneTuileDansLaMain(murPioche, murSpecial, mainSud);
			}
			for (int i = 0; i < 4; i++) {
				this.uneTuileDansLaMain(murPioche, murSpecial, mainOuest);
			}
			for (int i = 0; i < 4; i++) {
				this.uneTuileDansLaMain(murPioche, murSpecial, mainNord);
			}
		}
		//Distribution d'une tuiles dans les mains pour terminer la distribution
		this.uneTuileDansLaMain(murPioche, murSpecial, mainEst);
		this.uneTuileDansLaMain(murPioche, murSpecial, mainSud);
		this.uneTuileDansLaMain(murPioche, murSpecial, mainOuest);
		this.uneTuileDansLaMain(murPioche, murSpecial, mainNord);
		this.uneTuileDansLaMain(murPioche, murSpecial, mainEst);
	}
	
	/**
	 * Methode permettant de prendre une tuile dans le mur de pioche pour la donner à la main choisie 
	 * et de changer le mur de pioche si ce dernier est completement vidé de ses tuiles
	 * @param murPioche et murSpecial vont automatiquement être mis à jour
	 * @param 
	 * @return
	 */
	private void uneTuileDansLaMain(Mur murPioche, Mur murSpecial, HandFacade mainCourrante) {
		try {
			Tuile tuilePiochee = murPioche.piocherTuile();
			//Vérif tuile piochée n'est ni saison ni fleur et la met de coté tant que la tuile piochée est de ce type
			while ((tuilePiochee.getType().getName()).equals("FLEU") || (tuilePiochee.getType().getName()).equals("SAIS")){				
				//On place la fleur ou la saison dans une liste appelée bonus
				List<Tuile> bonus = new ArrayList<Tuile>();
				bonus.add(tuilePiochee);
				//Tirage d'une nouvelle tuile dans le sens contraire à la pioche "classique"
				tuilePiochee = murSpecial.retirerTuile();
				int brecheSpe = murSpecial.getBrecheSpeciale();
				//Si on arrive au bout du mur, on change de mur
				if (brecheSpe==-2){
					murSpecial = murSpecial.prevMur(murSpecial, tousLesMurs(this));
				}		
			}
			//On arrive ici si la tuile piochée n'est ni une fleure ni une saison
			int breche1 = murPioche.getBreche();
			mainCourrante.remplirMain(tuilePiochee);
			//Si on arrive au bout du mur, on change de mur
			if(breche1==34){
				murPioche = murPioche.nextMur(murPioche, tousLesMurs(this));
			}
		} catch (MurException e) {
			e.printStackTrace();
		}
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
