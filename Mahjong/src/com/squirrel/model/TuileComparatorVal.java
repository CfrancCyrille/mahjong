package com.squirrel.model;

import java.util.Comparator;

public class TuileComparatorVal implements Comparator<Tuile>{
	
	private TuileComparatorType tuileComparatorType=new TuileComparatorType();
	
	// compare les valeurs des tuiles dans une meme famille
	public int compare(Tuile t, Tuile t1){
		int compareType=tuileComparatorType.compare(t, t1);
		if(compareType==0){
			int val= (t.getValeur().getValue());
			int val1= (t1.getValeur().getValue());
		    return (val - val1);
		}

		return compareType;
	}


}
