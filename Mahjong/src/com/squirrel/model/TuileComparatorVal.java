package com.squirrel.model;

import java.util.Comparator;

public class TuileComparatorVal implements Comparator<Tuile>{
	
	
	// compare les valeurs des tuiles dans une meme famille
	public int compare(Tuile t, Tuile t1){
		String type=  t.getType().getName();
		String type1=  t1.getType().getName();
		int val=  Integer.parseInt(t.getValeur().getName());
		int val1=  Integer.parseInt(t1.getValeur().getName());
		if(type==type1){					// ou equals??
			return (val - val1);
		}
		return 0;
	}


}
