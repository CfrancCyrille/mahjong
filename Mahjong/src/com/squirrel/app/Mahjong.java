package com.squirrel.app;

import java.util.ArrayList;

import com.squirrel.ctrl.Gestionnaire;
import com.squirrel.model.FacadeTuile;
import com.squirrel.model.Mur;
import com.squirrel.model.MurException;
import com.squirrel.model.Tuile;


public class Mahjong 

{

	public static void main(String[] args) 

	{

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
		Mur murEst = new Mur();
		Mur murOuest = new Mur();
		Mur murSud = new Mur();
		Mur murNord = new Mur();

		Mur murPioche=null;//"Post it" pour designer le mur dans lequel on pioche

		//Génération de la liste de toutes les tuiles
		ArrayList<Tuile> listeTuiles = new 	ArrayList<Tuile>();
		FacadeTuile objFacade = new FacadeTuile();
		listeTuiles=objFacade.getTuilesList(listeTuiles);

		//Remplissage des 4 murs avec les 144 tuiles
		for(int j =0;j<144;j=j+4){
			murEst.ajouterTuile(listeTuiles.get(j));
			murOuest.ajouterTuile(listeTuiles.get(j+1));
			murSud.ajouterTuile(listeTuiles.get(j+2));
			murNord.ajouterTuile(listeTuiles.get(j+3));
		}

		//Determination du mur brechable en fonction du calcul du gestionnaire
		if (murBrechable.equals("Nord")) {
			murPioche = murNord;
		}
		else if (murBrechable.equals("Sud")) {
			murPioche = murSud;
		}
		else if (murBrechable.equals("Ouest")) {
			murPioche = murOuest;
		}
		else if (murBrechable.equals("Est")) {
			murPioche = murEst;
		}

		//Placement de la breche sur le mur à piocher
		try {
			murPioche.setBreche(breche);
		} catch (MurException e) {
			e.printStackTrace();
		}


		//Affichage des infos du debut de jeu
		System.out.println("Joueur commencant : "+joueurCommencant);
		System.out.println("Le mur à detruire en premier est : "+murBrechable);
		System.out.println("Breche : "+breche);


		//Affichage de la premiere tuile piochee
		try {
			System.out.println(murPioche.piocherTuile());
		} catch (MurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
