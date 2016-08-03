package com.squirrel.app;

import com.squirrel.ctrl.Gestionnaire;

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

	}

	

}
