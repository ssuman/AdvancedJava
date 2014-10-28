package com.rit.practice.multithreading;

public class DoubleIntegralThread extends Thread {

	private final float X_MAX_SIZE = 1;
	private final float Y_MAX_SIZE = 2;
	private final float X_MIN_SIZE = -1;
	private final float Y_MIN_SIZE = -2;

	private float area = 0.0f;

	private float oldArea = 0.0f;

	private DoubleIntegral dbi;

	public float getArea() {
		return area;
	}

	public DoubleIntegralThread() {

	}

	public DoubleIntegralThread(DoubleIntegral dbi) {
		this.dbi = dbi;
	}

	public float loopThrough(float delta) {
		area=0.0f;
		for (float i = X_MIN_SIZE; i < X_MAX_SIZE; i = i + delta) {
			for (float j = Y_MIN_SIZE; j < Y_MAX_SIZE; j = j + delta) {
				area+= dbi.compute(i, j) * delta * delta;
			}
		}
		
		return area;

	}

	@Override
	public void run() {
		synchronized (dbi) {
			//loopThrough();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DoubleIntegral dbi = new DoubleIntegral();
		DoubleIntegralThread dbir = new DoubleIntegralThread(dbi);
		float oldArea = 0.0f;
		float area = 0.0f;
		float delta = 0.1f;
		do {
			oldArea = area;
			area = dbir.loopThrough(delta);
			delta = delta - 0.01f;
		} while (Math.abs(oldArea - area) > 0.001);
		// Thread.currentThread().join();
		/*
		 * DoubleIntegralThread dbi_array[] = new DoubleIntegralThread[200] ;
		 * for (int i = 0; i < 200; i++) { dbi_array[i]=new
		 * DoubleIntegralThread(dbi); }
		 * 
		 * for(int i=0; i<200;i++){ dbi_array[i].start(); }
		 * 
		 * for(int i=0;i<200;i++){ dbi_array[i].join(); }
		 */

		System.out.println(dbir.getArea());

	}
}
