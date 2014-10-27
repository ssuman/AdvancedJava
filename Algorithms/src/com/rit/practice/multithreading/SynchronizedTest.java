package com.rit.practice.multithreading;

public class SynchronizedTest extends Thread {

	private Counter counter;
	//private volatile int arr[] = new ar

	public SynchronizedTest(Counter cnt) {
		this.counter = cnt;
		
	}

	@Override
	public void run() {
		counter.add();
	}

	public static void main(String[] args) throws InterruptedException {
		Counter cnt = new Counter();
		Thread A = new SynchronizedTest(cnt);
		Thread B = new SynchronizedTest(cnt);
		A.start();
		B.start();

		//counter.add();
		//A.join();
		//B.join();

		System.out.println("Hi");
	}
}
