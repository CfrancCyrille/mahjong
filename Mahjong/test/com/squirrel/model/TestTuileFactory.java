package com.squirrel.model;

import static com.squirrel.model.TuileDra.DraTuile.BLA;
import static com.squirrel.model.TuileDra.DraTuile.ROU;
import static com.squirrel.model.TuileDra.DraTuile.VER;
import static com.squirrel.model.TuileFactory.TypeTuile.BAMB;
import static com.squirrel.model.TuileFactory.TypeTuile.CARA;
import static com.squirrel.model.TuileFactory.TypeTuile.DRAG;
import static com.squirrel.model.TuileFactory.TypeTuile.FLEU;
import static com.squirrel.model.TuileFactory.TypeTuile.ROND;
import static com.squirrel.model.TuileFactory.TypeTuile.SAIS;
import static com.squirrel.model.TuileFactory.TypeTuile.VENT;
import static com.squirrel.model.TuileFle.FleTuile.BAM;
import static com.squirrel.model.TuileFle.FleTuile.CHR;
import static com.squirrel.model.TuileFle.FleTuile.ORC;
import static com.squirrel.model.TuileFle.FleTuile.PRU;
import static com.squirrel.model.TuileNum.NumTuile.CIN;
import static com.squirrel.model.TuileNum.NumTuile.DEU;
import static com.squirrel.model.TuileNum.NumTuile.HUI;
import static com.squirrel.model.TuileNum.NumTuile.NEU;
import static com.squirrel.model.TuileNum.NumTuile.QUA;
import static com.squirrel.model.TuileNum.NumTuile.SEP;
import static com.squirrel.model.TuileNum.NumTuile.SIX;
import static com.squirrel.model.TuileNum.NumTuile.TRO;
import static com.squirrel.model.TuileNum.NumTuile.UN;
import static com.squirrel.model.TuileSai.SaiTuile.AUT;
import static com.squirrel.model.TuileSai.SaiTuile.ETE;
import static com.squirrel.model.TuileSai.SaiTuile.HIV;
import static com.squirrel.model.TuileSai.SaiTuile.PRI;
import static com.squirrel.model.TuileVen.VenTuile.EST;
import static com.squirrel.model.TuileVen.VenTuile.NOR;
import static com.squirrel.model.TuileVen.VenTuile.OUE;
import static com.squirrel.model.TuileVen.VenTuile.SUD;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.squirrel.model.TuileFactory.TypeTuile;


public class TestTuileFactory {

	@Test
	public void testInitializeType() {
		TuileFactory tf=new TuileFactory();
		ArrayList<Tuile> tuileList=new ArrayList<Tuile>();
		tuileList=tf.initialize(tuileList);
		assertEquals(144,tuileList.size());

		int bamb=0;
		int rond=0;
		int cara=0;
		int drag=0;
		int vent=0;
		int sais=0;
		int fleu=0;

		for (Tuile i : tuileList) {
			if (i.getType()==BAMB){
				bamb++;
			}else if(i.getType()==ROND){
				rond++;
			}else if(i.getType()==CARA){
				cara++;
			}else if(i.getType()==VENT){
				vent++;
			}else if(i.getType()==DRAG){
				drag++;
			}else if(i.getType()==SAIS){
				sais++;
			}else if(i.getType()==FLEU){
				fleu++;
			}
			
		}
		assertEquals(36,bamb);
		assertEquals(36,rond);
		assertEquals(36,cara);
		assertEquals(16,vent);
		assertEquals(12,drag);
		assertEquals(4,sais);
		assertEquals(4,fleu);
	}
	@Test
	public void testInitializeNum() {
		TuileFactory tf=new TuileFactory();
		ArrayList<Tuile> tuileList=new ArrayList<Tuile>();
		tuileList=tf.initialize(tuileList);

		int un=0;
		int deux=0;
		int trois=0;
		int quatre=0;
		int cinq=0;
		int six=0;
		int sept=0;
		int huit=0;
		int neuf=0;
		int res=12;
		
		for (Tuile i : tuileList) {
			if(i.getValeur()==UN){
				un++;
			}else if(i.getValeur()==DEU){
				deux++;
			}else if(i.getValeur()==TRO){
				trois++;
			}else if(i.getValeur()==QUA){
				quatre++;
			}else if(i.getValeur()==CIN){
				cinq++;
			}else if(i.getValeur()==SIX){
				six++;
			}else if(i.getValeur()==SEP){
				sept++;
			}else if(i.getValeur()==HUI){
				huit++;
			}else if(i.getValeur()==NEU){
				neuf++;
			}
		}
		assertEquals(res,un);
		assertEquals(res,deux);
		assertEquals(res,trois);
		assertEquals(res,quatre);
		assertEquals(res,cinq);
		assertEquals(res,six);
		assertEquals(res,sept);
		assertEquals(res,huit);
		assertEquals(res,neuf);
	}
	@Test
	public void testInitializeVenDra() {
		TuileFactory tf=new TuileFactory();
		ArrayList<Tuile> tuileList=new ArrayList<Tuile>();
		tuileList=tf.initialize(tuileList);

		int est=0;
		int nord=0;
		int sud=0;
		int ouest=0;
		int blanc=0;
		int vert=0;
		int rouge=0;
		int res=4;
		
		for (Tuile i : tuileList) {
			if(i.getValeur()==EST){
				est++;
			}else if(i.getValeur()==NOR){
				nord++;
			}else if(i.getValeur()==SUD){
				sud++;
			}else if(i.getValeur()==OUE){
				ouest++;
			}else if(i.getValeur()==BLA){
				blanc++;
			}else if(i.getValeur()==ROU){
				rouge++;
			}else if(i.getValeur()==VER){
				vert++;
			}
		}
		assertEquals(res,est);
		assertEquals(res,nord);
		assertEquals(res,sud);
		assertEquals(res,ouest);
		assertEquals(res,blanc);
		assertEquals(res,rouge);
		assertEquals(res,vert);
	}
	@Test
	public void testInitializeSaiFle() {
		TuileFactory tf=new TuileFactory();
		ArrayList<Tuile> tuileList=new ArrayList<Tuile>();
		tuileList=tf.initialize(tuileList);

		int ete=0;
		int hiver=0;
		int automne=0;
		int printemps=0;
		int prunier=0;
		int chrysantheme=0;
		int bambou=0;
		int orchidee=0;
		int res=1;
		
		for (Tuile i : tuileList) {
			if(i.getValeur()==ETE){
				ete++;
			}else if(i.getValeur()==HIV){
				hiver++;
			}else if(i.getValeur()==AUT){
				automne++;
			}else if(i.getValeur()==PRI){
				printemps++;
			}else if(i.getValeur()==PRU){
				prunier++;
			}else if(i.getValeur()==CHR){
				chrysantheme++;
			}else if(i.getValeur()==BAM){
				bambou++;
			}else if(i.getValeur()==ORC){
				orchidee++;
			}
		}
		assertEquals(res,ete);
		assertEquals(res,hiver);
		assertEquals(res,automne);
		assertEquals(res,printemps);
		assertEquals(res,prunier);
		assertEquals(res,chrysantheme);
		assertEquals(res,bambou);
		assertEquals(res,orchidee);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testCreateTuileValeur(){
		TuileFactory tf=new TuileFactory();
		for (TypeTuile t : TypeTuile.values()) {
			Tuile tuile0=tf.createTuile(t, 0);
			Tuile tuile1=tf.createTuile(t, 1);
			Tuile tuile2=tf.createTuile(t, 2);
			Tuile tuile3=tf.createTuile(t, 3);
			Tuile tuile4=tf.createTuile(t, 4);
			Tuile tuile5=tf.createTuile(t, 5);
			Tuile tuile6=tf.createTuile(t, 6);
			Tuile tuile7=tf.createTuile(t, 7);
			Tuile tuile8=tf.createTuile(t, 8);
			Tuile tuile9=tf.createTuile(t, 9);
			assertEquals(0,tuile0.getValeur().ordinal());
			assertEquals(1,tuile1.getValeur().ordinal());
			assertEquals(2,tuile2.getValeur().ordinal());
			assertEquals(3,tuile3.getValeur().ordinal());
			assertEquals(4,tuile4.getValeur().ordinal());
			assertEquals(5,tuile5.getValeur().ordinal());
			assertEquals(6,tuile6.getValeur().ordinal());
			assertEquals(7,tuile7.getValeur().ordinal());
			assertEquals(8,tuile8.getValeur().ordinal());
			assertEquals(9,tuile9.getValeur().ordinal());
		}
		
	}
	
	@Test
	public void testCreateTuileType(){
		TuileFactory tf=new TuileFactory();
		for (TypeTuile t : TypeTuile.values()) {
			Tuile tuile0=tf.createTuile(t, 0);
			assertEquals(t,tuile0.getType());
		}
	}
	//IllegalArgumentException
}
