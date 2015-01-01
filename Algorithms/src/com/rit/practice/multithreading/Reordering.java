package com.rit.practice.multithreading;

public class Reordering implements Runnable {

	private int x=0;
	private boolean ready = false;
	
	public void writer(){
		ready=true;
		x=6;
	}
	
	public void reader(){
		if(ready)
			System.out.println(x);
		
		else
			System.out.println(x);	
	}
	
	public static void main(String args[]){
		Reordering reorder = new Reordering();
		new Thread(reorder).start();
		new Thread(reorder).start();
	}

	@Override
	public void run() {
		writer();
		reader();
		
	}
}
