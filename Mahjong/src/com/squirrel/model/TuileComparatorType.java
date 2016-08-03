package com.squirrel.model;

import java.util.Comparator;

public class TuileComparatorType implements Comparator<Tuile> {

	private String type;
	private String valeur; 
	
	

// compare les familles et leur attribue un ordre alphabétique
	public int compare(Tuile t, Tuile t1){
		String type=  t.getType().getName();
		String type1=  t1.getType().getName();
		return (int)(type.charAt(0) - type1.charAt(0));
	}
	
	

	
}
