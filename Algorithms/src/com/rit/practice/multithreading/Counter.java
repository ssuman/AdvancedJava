package com.rit.practice.multithreading;

public class Counter {

	private static int increment = 0;

	public void add() {
			increment++;
			System.out.println(increment);
	}

	public static int get() {
		synchronized (Counter.class) {
			return increment;
		}
	}
}
