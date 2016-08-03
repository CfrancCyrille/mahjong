package com.squirrel.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gestionnaire 
{
	//constructeur
	public Gestionnaire()
	{
		super();
	}
	//methode permettant de simuler un lancer de des
	public static int lancerDes()
	{
		Random desUn = new Random();
		return 1+desUn.nextInt(6);
	}
	//methode determinant le mur qu'il faudra detruire apres le premier jet de des
	public static String muraDetruire(int scoredes1, int scoredes2)
	{
		int somme=scoredes1+scoredes2;
		String mur="";
		//le jeu etant compose de 4 murs, cette condition de redemarrer un tour apres avoir compter 4
		if(somme > 4 && somme < 9)
		{
			somme=somme-4;
		}
		else if(somme > 8)
		{
			somme=somme-8;
		}
		//transforme la valeur du mur en direction (vent)
		switch (somme)
		{
			case 1:
				mur="Est";
				break;
			case 2:
				mur="Sud";
				break;
			case 3:
				mur="Ouest";
				break;
			case 4:
				mur="Nord";
				break;
		}
		return mur;
	}
	//cette methode permet le tirage au sort de facon aleatoire du joueur qui commencera le jeu (donc de l'est)
	public static int premierTirage()
	{
		int scoreDes[]={0,0,0,0}; //tableau recueuillant les différents scores de dés des joueurs
		List<Integer> relance = new ArrayList<Integer>(); //liste conservant les numeros des joueurs ayant fait le plus gros nombre
		int scoreMaximum=0;
		//on remplit le tableau de dés avec des valeurs aléatoires
		for (int i = 0; i < 4; i++) 
		{
			scoreDes[i]=lancerDes();
		}
		//on les affiche
		for (int i = 0; i < 4; i++) 
		{
			System.out.println("score du joueur : " + i + " : " + scoreDes[i]);
		}
		System.out.println("-----------------------------");
		//on garde le score maximum des lancers de dés
		for (int i = 0; i < 4; i++) 
		{
			if(scoreDes[i]>scoreMaximum)
			{
				scoreMaximum=scoreDes[i];
			}
		}
		//on garde les numéros des joueurs ayant fait le plus gros score
		for (int i = 0; i < 4; i++) 
		{
			if(scoreDes[i]==scoreMaximum)
			{
				relance.add(new Integer(i));
			}
		}
		//boucle dans le cas où plusieurs joueurs ont fait le score max
		while(relance.size() != 1 )
		{
			scoreMaximum=0;
			//Ces joueurs relancent les dés
			for (int i = 0; i < relance.size(); i++) 
			{
				scoreDes[relance.get(i)]=lancerDes();
			}
			//On affiche les nouveaux scores de ces lancers
			for (int i = 0; i < relance.size(); i++) 
			{
				System.out.println("score du joueur : " + relance.get(i) + " : " + scoreDes[relance.get(i)]);
			}
			System.out.println("-----------------------------");
			//On remplace la nouvelle valeur max des nouveaux lancers
			for (int i = 0; i < relance.size(); i++) 
			{
				if(scoreDes[relance.get(i)]>scoreMaximum)
				{
					scoreMaximum=scoreDes[relance.get(i)];
				}
			}
			//on garde le numero des joueurs ayant fait le plus grand nombre
			for (int i = relance.size()-1; i >=0; i--) 
			{
				if(scoreDes[relance.get(i)]==scoreMaximum)
				{
					//On ne fait rien
				}
				else
				{
					//sinon on le supprime de la liste
					relance.remove(i);
				}
			}
		}
		//on le joueur commencant la partie (Est)
		return (relance.get(0)+1);
	}
	//methode permettant de determiner les debordement sur un autre mur lors de la breche
	public static String murBrechable(int sommeDe, String nomMur)
	{
		//condition si on ne depasse pas la capacite d'un mur
		if(sommeDe<19)
		{
		}
		else
		{
			//sinon on change le mur qui sera breche
            switch (nomMur)
    		{
    			case "Est":
    				return "Nord";
    			case "Sud":
    				return "Est";
    			case "Ouest":
    				return "Sud";
    			case "Nord":
    				return "Ouest";
    		}
        }
		return nomMur;
	}	
	//methode determinant la position exacte de la colonne brechee
	public static int breche(int sommeDe)
	{
		//condition si on ne depasse pas la capacite d'un mur, la position sera la tuile du dessus de la colonne
		if(sommeDe<19)
		{
			return sommeDe = sommeDe*2-2;
		}
		//sinon la tuile sera la tuile du dessus de la colonne mais sur un autre mur
		else
		{
			return sommeDe = ((sommeDe-18)*2)-2;
		}
	}
}

