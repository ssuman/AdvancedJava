package com.rit.practice.multithreading;

public class PrintIdInOrder extends Thread {

	String id;
	private static Object obj = new Object();
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
								// System.out.println("hi 1");
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
					} else if (id.equals("b")) {
						if (!print2) {
							try {
								obj.notify();
								// System.out.println("hi 2");
								sleep(1000);
								obj.wait();
							} catch (InterruptedException e) {

							}
						} else {
							System.out.print(id + " ");
							sleep(1000);
							obj.notify();
							print2 = false;
							print3 = true;
							obj.wait();
						}
					} else {
						if (!print3) {
							try {
								obj.notify();
								// System.out.println("hi 3");
								sleep(1000);
								obj.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							System.out.print(id + " ");
							sleep(1000);
							obj.notifyAll();
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

	public static void main(String[] args) {
		PrintIdInOrder thread1 = new PrintIdInOrder("a");
		PrintIdInOrder thread2 = new PrintIdInOrder("b");
		PrintIdInOrder thread3 = new PrintIdInOrder("c");

		thread1.start();
		thread2.start();
		thread3.start();
	}

}
