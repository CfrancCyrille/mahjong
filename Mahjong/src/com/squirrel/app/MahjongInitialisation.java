package com.squirrel.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.squirrel.app.MahjongPartie.Combinaison;
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

	//Cr�ation des 4 joueurs
	Joueur jEst = new Joueur (VenTuile.EST);
	Joueur jOuest = new Joueur (VenTuile.OUE);
	Joueur jSud = new Joueur (VenTuile.SUD);
	Joueur jNord = new Joueur (VenTuile.NOR);

	ArrayList<Tuile> listeTuiles;

	//Pour que la classe MahjongPartie puisse r�cup�rer les valeurs des murs o� piocher apr�s l'initialisation
	public Mur murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile;
	public Mur murSpe;

	//Cr�ation d'une table de hashage pour associer le pseudo d'un joueur � un ordre de jeu
	Hashtable<String, Integer> player = new Hashtable<String, Integer>();

	public void initialiserUnePartie() {

		String murBrechable;
		int breche;
		int score1=Gestionnaire.lancerDes();
		int score2=Gestionnaire.lancerDes();
		int somme;

		somme=score1+score2+Gestionnaire.lancerDes()+Gestionnaire.lancerDes();
		murBrechable=Gestionnaire.murBrechable(somme, Gestionnaire.muraDetruire(score1, score2));
		breche=Gestionnaire.breche(somme);		

		//Creation des 4 murs
		this.creationDes4Murs();

		Mur murPioche=null;//"Post it" pour designer le mur dans lequel on pioche
		Mur murSpecial=null;

		//G�n�ration de la liste de toutes les tuiles
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
		//Cas particulier si la somme des 4 d�s est �gale � 18 : les deux br�ches ne sont pas dans le m�me mur !
		if (somme == 18){
			if (murBrechable.equals("Nord")) {
				murPioche = this.murNord;
				murSpecial = this.murEst;
			}
			else if (murBrechable.equals("Sud")) {
				murPioche = this.murSud;
				murSpecial = this.murOuest;
			}
			else if (murBrechable.equals("Ouest")) {
				murPioche = this.murOuest;
				murSpecial = this.murNord;
			}
			else if (murBrechable.equals("Est")) {
				murPioche = this.murEst;
				murSpecial = this.murSud;
			}
		}
		else {
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
		}


		//Placement de la breche sur le mur � piocher
		try {
			murPioche.setBreche(breche);
		} catch (MurException e) {
			e.printStackTrace();
		}

		//Placement de la br�che sp�ciale
		try {
			if (somme == 18){
				murSpecial.setBrecheSpeciale(34);
			}
			else {
				murSpecial.setBrecheSpeciale(breche);
			}
		} catch (MurException e1) {
			e1.printStackTrace();
		}

		//Cr�ation des 4 mains et 4 listes de bonus (fleurs et saisons)
		this.creationDes4Mains();
		this.creationDes4ListesBonus();

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

		//On v�rifie chacune des mains pour retirer les fleurs et saisons et on les remplace par des tuiles venant du "mur sp�cial"
		murSpecial = verifFleursEtSaisons(murSpecial, jEst);
		murSpecial = verifFleursEtSaisons(murSpecial, jSud);
		murSpecial = verifFleursEtSaisons(murSpecial, jOuest);
		murSpecial = verifFleursEtSaisons(murSpecial, jNord);
		
		//On v�rifie pour chaque joueur qu'il n'a pas des kong � retirer da sa main
		murSpecial = traitementKong(murSpecial, jEst);
		murSpecial = traitementKong(murSpecial, jSud);
		murSpecial = traitementKong(murSpecial, jOuest);
		murSpecial = traitementKong(murSpecial, jNord);

		//La c'est pour que partie sache ou piocher
		murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile=murPioche;
		murSpe=murSpecial;

	}

	/**
	 * M�thode permettant de v�rifier s'il y a des tuiles fleurs ou des tuiles saisons dans la main, de les retirer s'il y en a, 
	 * de les mettre dans une ArrayList bonus, et de piocher une nouvelle tuile, elle m�me v�rifi�e, dans le sens contraire de la
	 * pioche classique
	 * @param murSpecial = mur dans lequel on pioche dans le sens contraire
	 * @param currentHand = main dans laquelle on v�fifie
	 * @param currentbonus = liste o� l'on place les tuiles retir�es de la main
	 * @return
	 */
	Mur verifFleursEtSaisons(Mur murSpecial, Joueur currentJoueur) {
		int i = 0;
		while ( i < currentJoueur.getHand().handSize()) {
			Tuile tuile1=currentJoueur.getHand().get(i);
			if (tuile1.getType().equals(TypeTuile.FLEU) || tuile1.getType().equals(TypeTuile.SAIS)){
				currentJoueur.getBonus().add(tuile1);
				currentJoueur.getHand().remove(tuile1);
				murSpecial = this.uneTuileSpecialeDansLaMain(murSpecial, currentJoueur);
			}
			else {
				i++;
			}
		}
		return murSpecial;
	}

	/**
	 * Methode permettant de prendre une tuile dans le mur de pioche pour la donner � la main choisie 
	 * et de changer le mur de pioche si ce dernier est completement vid� de ses tuiles
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
	 * Methode permettant de prendre une tuile dans le mur sp�cial pour la donner � la main choisie 
	 * et de changer le mur sp�cial si ce dernier est completement vid� de ses tuiles
	 * @param murSpecial
	 * @param mainCourrante
	 * @return
	 */
	Mur uneTuileSpecialeDansLaMain(Mur murSpecial, Joueur curentJoueur) {
		try {
			Tuile tuilePiochee = murSpecial.retirerTuile();
			int breche1 = murSpecial.getBrecheSpeciale();
			curentJoueur.getHand().remplirMain(tuilePiochee);
			if(breche1==-1){
				murSpecial = murSpecial.prevMur(murSpecial, tousLesMurs(this));
			}
		} catch (MurException e) {
			e.printStackTrace();
		}
		return murSpecial;
	}

	//Pour passer d'un mur � un autre dans le sens du d�roulement du jeu
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
		this.jEst.setHand (new HandFacade());
		this.jOuest.setHand (new HandFacade());
		this.jSud.setHand (new HandFacade());
		this.jNord.setHand (new HandFacade());

	}

	private void creationDes4ListesBonus() {
		this.jEst.setBonus (new ArrayList<Tuile>());
		this.jOuest.setBonus (new ArrayList<Tuile>());
		this.jSud.setBonus (new ArrayList<Tuile>());
		this.jNord.setBonus (new ArrayList<Tuile>());

	}

	//On rentre le pseudo du joueur dans la hashtable et on l'associe � un num�ro allant de 1 � 4
	int nbPlayer = 0;
	public void addJoueur (String pseudo) throws MahjongInitialisationException{
		nbPlayer ++;
		if (nbPlayer < 5){
			player.put(pseudo, nbPlayer);
		}
		else {
			throw new MahjongInitialisationException("Attention, il n'est pas possible de jouer avec plus de 4 joueurs");
		}
	}

	//A partir du pseudo du joueur, on renvoit le vent qui lui correspond
	public String quelEstMonVent (String pseudo){
		//On choisit al�atoirement un nb entre 1 et 4 qui correspond au joueur qui va commencer
		int joueurCommencant;
		joueurCommencant=Gestionnaire.premierTirage();
		//A partir du pseudo, on r�cup�re le num�ro attribu� au joueur avec la m�thode addJoueur
		int numPseudo = player.get(pseudo);
		//On cherche le vent qui lui corespond et on le renvoit
		if (joueurCommencant == numPseudo){
			return "Est";
		} else if (numPseudo == joueurCommencant +1 || (joueurCommencant==4 && numPseudo==1)){
			return "Sud";
		} else if (numPseudo == joueurCommencant +2 || (joueurCommencant==3 && numPseudo==1)|| (joueurCommencant==4 && numPseudo==2)){
			return "Ouest";
		}
		return "Nord";

	}


	public Mur traitementKong (Mur murSpecial, Joueur jCourant){

		//On r�cup�re dans une hashmap res les combinaisons du joueur courant tri�es par type
		jCourant.getHand().res=jCourant.getHand().getCombinaison();
		HashMap<Combinaison, List<List<Tuile>>> combinaisons = jCourant.getHand().getIdentificationCombi();

		//ajouter les listes pr�sentes dans la HashMap avec mot cl� kong dans la liste bonus du joueur
		//sortir ces tuiles de la hashmap (remove)
		//ajouter une tuile dans la main du joueur avec la m�thode retirer tuile
		int nbKong=combinaisons.get(Combinaison.KONG).size();
		for(int k=0; k<nbKong ; k++){
			jCourant.getBonus().addAll(combinaisons.get(Combinaison.KONG).get(0));
			jCourant.getHand().hand.tuilesListOfHand.removeAll(combinaisons.get(Combinaison.KONG).get(k));
			if(murSpecial != null){
				murSpecial = this.uneTuileSpecialeDansLaMain(murSpecial, jCourant);
				
			}
		}
		return murSpecial;
	}
}
