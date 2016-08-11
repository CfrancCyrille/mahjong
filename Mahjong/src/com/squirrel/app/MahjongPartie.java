package com.squirrel.app;

import static com.squirrel.app.MahjongPartie.EtatPartie.JOUEURAPPELLE;
import static com.squirrel.app.MahjongPartie.EtatPartie.TROPTUILE;
import static com.squirrel.app.MahjongPartie.EtatPartie.TUILEATTEND;
import static com.squirrel.app.MahjongPartie.Combinaison.PUNG;
import static com.squirrel.app.MahjongPartie.Combinaison.CHOW;
import static com.squirrel.app.MahjongPartie.Combinaison.KONG;
import static com.squirrel.app.MahjongPartie.Combinaison.MAHJONG;
import java.util.ArrayList;

import com.squirrel.model.HandFacade;
import com.squirrel.model.Joueur;
import com.squirrel.model.Mur;
import com.squirrel.model.Tuile;



public class MahjongPartie implements Runnable {
	private static final int ENTRETOURS = 20;	//définir le nombre passages par sleeptime lors de l'attente d'un appel (exemple sleep 500ms, ENTRETOURS=20 alors on attend 10 secondes :D )

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
		Tuile tuile = null;
		int tuilesRestantes=144;
		int sleepTime_ms=500;
		int nbPassage=0;
		int nbAttente=0;
		
		while(!mahjong && tuilesRestantes>14){


			switch(etat){
			case TROPTUILE:

				if(mahjong){
					break;
				}
				//informe que c'est au joueur courant de selectionner une tuile
				reqSelection();
				tuile=repSelectionTuile;

				jetteTuile(jCourant, tuile);
				etat = TUILEATTEND;

			case TUILEATTEND:
				reqAfficheTuile(tuile);
				nbAttente++;
				
				if(repAppelJoueur.isAppel()){
					etat=JOUEURAPPELLE;

				}else if (nbAttente>ENTRETOURS){
					nbAttente=0;
					mah.uneTuileDansLaMain(murPioche,jSuivant);
					mah.verifFleursEtSaisons(murSpe,jSuivant);
					liste=ordreJoueurs(jSuivant);
					etat=TROPTUILE;
				}




			case JOUEURAPPELLE:
				if(tuile!=null){
					if(isCombinaisonValid(tuile,repAppelJoueur.getHand(),KONG)) {
						repAppelJoueur.getHand().remplirMain(tuile);
						repAppelJoueur.setAppel(false);
						liste=ordreJoueurs(repAppelJoueur);
						etat=TROPTUILE;
					}
					else{
						repAppelJoueur.incremPenalite();
						etat=TUILEATTEND;
					}
				}

			default:
				break;
			}
			nbPassage++;
			try {
				Thread.sleep(sleepTime_ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}





	public void reqAfficheTuile(Tuile tuile) {
		// TODO donne a tous les joueurs la tuile en attente

	}

	volatile Joueur repAppelJoueur;
	volatile Combinaison repAppelCombi;
	public void repAppel(Joueur j, Combinaison combi){
		// TODO Si un des joueurs à appelé une combinaison
		j.setAppel(true);
		if(combi==null){
			combi=CHOW;
		}
		repAppelJoueur=j;
		repAppelCombi=combi;
	}


	volatile Joueur joueurRequete;

	public void reqSelection() {
		// TODO demande au joueur j de selectionner une tuile	
	}

	volatile Tuile repSelectionTuile;
	public void repSelection(Tuile tuile) {
		// TODO attend la reponse de selection du joueur
		repSelectionTuile=tuile;
	}

	// Cette méthode permet de savoir si la combinaison d'un joueur essaie de faire
	private boolean isCombinaisonValid(Tuile tuile, HandFacade hand, Combinaison combi) {
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
	public enum Combinaison{
		CHOW("chow"),PUNG("pung"),KONG("kong"),MAHJONG("mah jong");
		String nomCombi;
		int value;
		private Combinaison(String nomCombi){
			this.nomCombi=nomCombi;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomCombi;
		}

	}
}
