package com.squirrel.model;

public class TempoThread extends Thread{
	public boolean terminated=false;

	public void run(){
		super.run();
		terminated=false;
		int sleepTime_ms=20000;
		try {
			Thread.sleep(sleepTime_ms);
			terminated=true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
