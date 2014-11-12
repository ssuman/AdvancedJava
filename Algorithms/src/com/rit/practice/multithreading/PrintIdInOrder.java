package com.rit.practice.multithreading;

/* 
 * PrintIdInOrder.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
/**
 * This program prints in order a, b and c
 * 
 * @author Kanth
 * @author Suman
 */
public class PrintIdInOrder extends Thread {

	String id;
	// Lock on this obect
	private static Object obj = new Object();
	
	// These boolean variables will be used to print in order
	private static boolean print1 = true;
	private static boolean print2 = false;
	private static boolean print3 = false;

	public PrintIdInOrder(String id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (true) {

			try {
				synchronized (obj) {

					if (id.equals("a")) {

						// If print1 is false
						// make it wait. Before that notify other threads
						if (!print1) {
							try {					
								obj.notify();
								sleep(1000);
								obj.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							System.out.print(id + " ");
							sleep(1000);
							obj.notify();
							print1 = false;
							print2 = true;
							obj.wait();
						}
						
					} 
					// If the id is equal to "b".
					else if (id.equals("b")) {
						
						// If print is false
						// make it wait. Before that notify other threads.
						if (!print2) {
							try {
								obj.notify();
								sleep(1000);
								obj.wait();
							} catch (InterruptedException e) {

							}
						} 
						// else print id
						else {
							System.out.print(id + " ");
							sleep(1000);
							obj.notify();
							print2 = false;
							print3 = true;
							obj.wait();
						}
					} 
					// If the id is equal to "c"
					else {
						// If print3 is false.
						// notify and wait.
						if (!print3) {
							try {
								obj.notify();
								sleep(1000);
								obj.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} 
						// else print Id and print1 true.
						else {
							System.out.print(id + " ");
							sleep(1000);
							obj.notify();
							print3 = false;
							print1 = true;
							obj.wait();
						}
					}
				}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/***
	 * This is where execution begins.
	 * 
	 * @param 		args		
	 */
	public static void main(String[] args) {
		PrintIdInOrder thread1 = new PrintIdInOrder("a");
		PrintIdInOrder thread2 = new PrintIdInOrder("b");
		PrintIdInOrder thread3 = new PrintIdInOrder("c");

		thread1.start();
		thread2.start();
		thread3.start();
	}

}
