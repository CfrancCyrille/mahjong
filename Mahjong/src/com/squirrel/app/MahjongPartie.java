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
			reqSelection(jCourant);
			tuile=repSelection();
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
				if(isCombinaisonValid(tuile,liste.get(i).getHand(),KONG)) {
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
		

	}


	private Tuile repSelection() {
		// TODO Auto-generated method stub
		return null;
	}


	private void reqSelection(Joueur j) {
		// TODO Obtenir la tuile selectionnee par le joueur

		
		
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
