package com.squirrel.app;

import static com.squirrel.app.MahjongPartie.EtatPartie.JOUEURAPPELLE;
import static com.squirrel.app.MahjongPartie.EtatPartie.TROPTUILE;
import static com.squirrel.app.MahjongPartie.EtatPartie.TUILEATTEND;

import java.util.ArrayList;

import com.squirrel.model.HandFacade;
import com.squirrel.model.Joueur;
import com.squirrel.model.Mur;
import com.squirrel.model.Tuile;
import com.squirrel.model.TempoThread;


public class MahjongPartie implements Runnable {
	MahjongInitialisation mah=new MahjongInitialisation();

	/*Joueur jEst=new Joueur("jean-pierre", mah.mainEst,EST);
	Joueur jNord=new Joueur("hervé",mah.mainNord,NOR);
	Joueur jOuest=new Joueur("denis",mah.mainOuest,OUE);
	Joueur jSud=new Joueur("paulette",mah.mainSud,SUD);
	 */
	ArrayList<Tuile> defausse=new ArrayList<Tuile>();


	public void lancerPartie(){
		// Creation d'une partie
		mah.initialiserUnePartie();


		ArrayList<Joueur> liste=new ArrayList<Joueur>();
		Joueur jCourant;
		Joueur jSuivant;
		jCourant=mah.jEst;
		liste = ordreJoueurs(jCourant);
		jSuivant=liste.get(1);
		Mur murPioche=mah.murPiochePostInitialisationCatalystiquementDerisoireEtCompletementInutile;
		Mur murSpe=mah.murSpe;


		EtatPartie etat=TROPTUILE;
		boolean mahjong=false;
		TempoThread tempo = new TempoThread();
		Tuile tuile = null;
		int joueur=0;
		
		switch(etat){
		case TROPTUILE:

			if(mahjong){
				break;
			}
			//informe que c'est au joueur courant de selectionner une tuile
			tuile=selectionne();
			jetteTuile(jCourant, tuile);
			etat = TUILEATTEND;

		case TUILEATTEND:
			tempo.start();
			for(int i=1;i<4;i++){
				if(liste.get(i).isAppel()){
					joueur=i;
					etat=JOUEURAPPELLE;
				}else if (tempo.terminated){
					mah.uneTuileDansLaMain(murPioche,jSuivant);
					mah.verifFleursEtSaisons(murSpe,jSuivant);
					liste=ordreJoueurs(jSuivant);
					etat=TROPTUILE;
				}

			}


		case JOUEURAPPELLE:
			int i =joueur;
			if(tuile!=null){
				if(isCombinaisonValid(tuile,liste.get(i).getHand())){
					liste.get(i).getHand().remplirMain(tuile);
					liste.get(i).setAppel(false);
					liste=ordreJoueurs(liste.get(i));
					etat=TROPTUILE;
				}
				else{
					liste.get(i).incremPenalite();
					etat=TUILEATTEND;
				}
			}

		default:
			break;
		}
		/*boolean jPioche = true;
		//ici debut de while arret sur mhjong ou plus de tuiles dans mur
		Tuile tuile = null; 	//=selec ion ne(jCourant);
		jetteTuile(jCourant, tuile);

		boolean tuilePiochee=false;

		// Tant que le joueur suivant ne pioche pas et qu'il y a une tuile dans le tas qui n'est pas appelee
		while(!jPioche || !tuilePiochee){
			for(int i=1;i<4;i++){
				if(liste.get(i).isAppel()){
					// Changer la condition du prochain if sur savoir si la combinaison est valable
					if(isCombinaisonValid(tuilePiochee,liste.get(i).getHand())){
						liste.get(i).getHand().remplirMain(tuile);
						liste.get(i).setAppel(false);
						liste=ordreJoueurs(liste.get(i));
						tuilePiochee=true;
					}
				    else{
				    	liste.get(i).incremPenalite();
				    }
				}
			}
		}
		if(!tuilePiochee){
			mah.uneTuileDansLaMain(murPioche,jSuivant.getHand());
			liste=ordreJoueurs(jSuivant);
		}

		//ici fin de while global*/

	}


	private Tuile selectionne() {
		// TODO Obtenir la tuile selectionnee par le joueur

		//TODO ligne suivante à modifier pour donner la bonne tuile
		return mah.jEst.getHand().get(0);
	}


	private boolean isCombinaisonValid(Tuile tuile, HandFacade hand) {
		// TODO savoir si la combinaison est valable a coder

		return false;
	}


	// Méthode de défausse d'une tuile. On rempli la liste de défausse associée a la partie.
	public void jetteTuile(Joueur joueur, Tuile tuile){
		defausse.add(tuile);
		joueur.getHand().remove(tuile);
	}




	//Méthode renvoyant une liste des joueurs dans l'ordre suivant: courant suivant appellant appelant.
	public ArrayList<Joueur> ordreJoueurs(Joueur j){
		ArrayList<Joueur> liste=new ArrayList<Joueur>();
		switch (j.getVent()) {
		case EST:
			liste.add(j);
			liste.add(mah.jSud);
			liste.add(mah.jOuest);
			liste.add(mah.jNord);
			break;
		case SUD:
			liste.add(j);
			liste.add(mah.jOuest);
			liste.add(mah.jNord);
			liste.add(mah.jEst);
			break;
		case OUE:
			liste.add(j);
			liste.add(mah.jNord);
			liste.add(mah.jEst);
			liste.add(mah.jSud);
			break;
		case NOR:
			liste.add(j);
			liste.add(mah.jEst);
			liste.add(mah.jSud);
			liste.add(mah.jOuest);
			break;
		default:
			return null;
		}
		return liste;
	}


	@Override
	public void run() {
		lancerPartie();

	}

	public enum EtatPartie{
		TUILEATTEND("tuile en attente"),JOUEURAPPELLE("un joueur appelle"),TROPTUILE("un des joueurs à une tuile en trop");
		String nomEtat;
		int value;
		private EtatPartie(String nomEtat){
			this.nomEtat=nomEtat;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomEtat;
		}
	}
}
