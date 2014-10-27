package com.rit.practice.multithreading;

public class DoubleIntegralThread extends Thread {

	private final float X_MAX_SIZE = 1;
	private final float Y_MAX_SIZE = 2;
	private final float X_MIN_SIZE = -1;
	private final float Y_MIN_SIZE = -2;

	private static float area = 0.0f;

	private static float oldArea = 0.0f;

	private DoubleIntegral dbi;

	public float getArea() {
		return area;
	}

	public DoubleIntegralThread() {

	}

	public DoubleIntegralThread(DoubleIntegral dbi) {
		this.dbi = dbi;
	}

	public void loopThrough() {
		float delta =0.090f;

		while (Math.abs(oldArea - area) < 0.01) {
			for (float i = X_MIN_SIZE; i < X_MAX_SIZE; i = i + delta) {
				for (float j = Y_MIN_SIZE; j < Y_MAX_SIZE; j = j + delta) {
					// synchronized (dbi) {
					oldArea = area;
					area += dbi.compute(i, j) * delta * delta;
					// }
				}
			}
		}
	}

	@Override
	public void run() {
		loopThrough();
	}

	public static void main(String[] args) throws InterruptedException {
		DoubleIntegral dbi = new DoubleIntegral();
		DoubleIntegralThread dbir = new DoubleIntegralThread(dbi);
		dbir.loopThrough();
		/*
		 * DoubleIntegralThread dbi_array[] = new DoubleIntegralThread[200] ;
		 * for (int i = 0; i < 200; i++) { dbi_array[i]=new
		 * DoubleIntegralThread(dbi); }
		 * 
		 * for(int i=0; i<200;i++){ dbi_array[i].start(); }
		 * 
		 * for(int i=0;i<200;i++){ dbi_array[i].join(); }
		 */

		System.out.println(DoubleIntegralThread.area);

	}
}
