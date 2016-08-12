package com.squirrel.model;

public class Mur {
	
	private Tuile [] tuilesDuMur;
	
	Tuile[] getTuilesDuMur() {
		return tuilesDuMur;
	}

	public final int NB_TUILE = 36;//Constante correspondant au nb de tuiles dans un mur
	private int premiereCaseVide =0;//Variable permettant de remplir le mur sans oublier de case
	int breche =-3;//Variable permettant de savoir o� "casser" le mur, doit �tre modifi�e par le gestionnaire
					//Comprise entre -2 et 33
	int brecheSpeciale =-3;//Variable permettant de savoir o� "casser" le mur pour les tuiles fleurs et saison,
							//doit �tre modifi�e par le gestionnaire
							//doit �tre comprise entre -1 et 34
	
	//R�cup�re la br�che sp�ciale du gestionnaire et cr�e une exception si la br�che n'est pas dans le mur
	public void setBrecheSpeciale(int brecheSpeciale) throws MurException {
		if(brecheSpeciale<NB_TUILE-1){
			this.brecheSpeciale = brecheSpeciale;
		}
		else{
			throw new MurException("Attention, la br�che doit passer dans le mur suivant");
		}
	}

	//R�cup�re la br�che du gestionnaire et cr�e une exception si la br�che n'est pas dans le mur
	public void setBreche(int breche) throws MurException {
		if(breche<NB_TUILE-2){
			this.breche = breche;
		}
		else{
			throw new MurException("Attention, la br�che doit passer dans le mur suivant");
		}
	}

	/**
	 * Constructeur permettant de g�n�rer un mur qui est un tableau vide de 36 cases
	 */
	public Mur(){
		tuilesDuMur = new Tuile [NB_TUILE];
	}
	
	/**
	 * M�thode permettant d'ajouter le param�tre "tuile" dans la premi�re case vide de "leMur"
	 * @return false = il y a 37 (ou +) tuiles dans le mur
	 * @param tuile
	 * @param tuilesDuMur
	 */
	public boolean ajouterTuile (Tuile tuile){
		if(premiereCaseVide>=NB_TUILE){
			return false;
		}
		tuilesDuMur[premiereCaseVide]=tuile;
		premiereCaseVide=premiereCaseVide+1;
		return true;
	}
	
	/**
	 * Methode permettant de retourner le param�tre "tuile" connaissant la position de "breche" gr�ce
	 * au setter
	 * Renvoie une excpetion si la br�che n'a pas �t� d�finie
	 * Renvoie une exception si le mur n'a plus de tuiles � piocher
	 * @return
	 * @throws MurException 
	 */
	public Tuile piocherTuile () throws MurException{
		if(breche<-2){
			throw new MurException("Attention, la br�che n'est pas d�finie");
		}
		else{
			if(piocheVide()){
				throw new MurException("Attention, le mur n'a plus de tuiles � piocher");
			}
			else{
				Tuile res;
				res = tuilesDuMur[breche+2];
				tuilesDuMur[breche+2] = null;
				breche++;
				return res;
			}
		}
	}
	
	//M�thode utilis�e dans la m�thode "piocherTuile" permettant de savoir si on pioche
	//dans le mur ou dans le vide
	private boolean piocheVide(){
		boolean res;
		if(breche==34){
			res = true;
		}
		else{
			res = false;
		}
		return res;
	}
	
	/**
	 * Methode permettant de retourner le param�tre "tuile" connaissant la position de la 
	 * "brecheSpeciale" gr�ce au setter, pour les tuiles saison et fleurs
	 * Lors du premier appel, "brecheSpeciale" = "breche"
	 * Renvoie une exception si brecheSpeciale n'est pas d�finie
	 * Renvoie une exception si on pioche dans le vide
	 * @return
	 * @throws MurException 
	 */
	public Tuile retirerTuile () throws MurException{
		if(brecheSpeciale<-1){
			throw new MurException("Attention, la br�che n'est pas d�finie");
		}
		else{
			if(piocheSpecialeVide()){
				throw new MurException("Attention, le mur n'a plus de tuiles sp�ciales � piocher");
			}
			else{
				Tuile res;
				res = tuilesDuMur[brecheSpeciale + 1];
				tuilesDuMur[brecheSpeciale + 1] = null;
				brecheSpeciale = brecheSpeciale - 1;
				return res;
			}
		}	
	}
	
	//M�thode utilis�e dans la m�thode "retirerTuile" permettant de savoir si on pioche
	//dans le mur ou dans le vide
	private boolean piocheSpecialeVide(){
		boolean res;
		if(brecheSpeciale==-2){
			res = true;
		}
		else{
			res = false;
		}
		return res;
	}
	
	/**
	 * Methode permettant de passer au mur suivant si on a finit de piocher dans le mur initial 
	 * et de mettre � jour la position de la breche
	 * @param murPioche
	 * @param tousLesMursDujeu
	 * @return
	 */
	public Mur nextMur(Mur murPioche, Mur[] tousLesMursDujeu){

		if (murPioche.equals(tousLesMursDujeu[0])) {
			murPioche = tousLesMursDujeu[1];
			murPioche.breche = -2;
			return murPioche;
		}
		else if (murPioche.equals(tousLesMursDujeu[1])) {
			murPioche = tousLesMursDujeu[2];
			murPioche.breche = -2;
			return murPioche;
		}
		else if (murPioche.equals(tousLesMursDujeu[2])) {
			murPioche = tousLesMursDujeu[3];
			murPioche.breche = -2;
			return murPioche;
		}
		else  {
			murPioche = tousLesMursDujeu[0];
			murPioche.breche = -2;
			return murPioche;
		}
		
	}
	
	public Mur prevMur(Mur murSpecial, Mur[] tousLesMursDujeu){

		if (murSpecial.equals(tousLesMursDujeu[1])) {
			murSpecial = tousLesMursDujeu[0];
			murSpecial.brecheSpeciale = 34;
			return murSpecial;
		}
		else if (murSpecial.equals(tousLesMursDujeu[2])) {
			murSpecial = tousLesMursDujeu[1];
			murSpecial.brecheSpeciale = 34;
			return murSpecial;
		}
		else if (murSpecial.equals(tousLesMursDujeu[3])) {
			murSpecial = tousLesMursDujeu[2];
			murSpecial.brecheSpeciale = 34;
			return murSpecial;
		}
		else  {
			murSpecial = tousLesMursDujeu[3];
			murSpecial.brecheSpeciale = 34;
			return murSpecial;
		}
		
	}

	public int getBreche() {
		// TODO Auto-generated method stub
		return this.breche;
	}
	
	public int getBrecheSpeciale() {
		// TODO Auto-generated method stub
		return this.brecheSpeciale;
	}
}