/* 
 * DoubleIntegralThread.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package com.rit.practice.multithreading;

/**
 * This program computes the volume - double integral given a function f(X,y)
 * 
 * @author Kanth
 * @author Suman
 */
public class DoubleIntegralThread extends Thread {

	private float X;
	private float Y;

	public synchronized float getAxis() {
		return X;
	}

	public synchronized void setAxis(float axis) {
		this.X = axis;
	}

	/**
	 * @return the y
	 */
	public synchronized float getY() {
		return Y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public synchronized void setY(float y) {
		Y = y;
	}

	private float area = 0.0f;
	private float delta = 0.1f;

	private DoubleIntegral dbi;

	/***
	 * 
	 * @return area returns area of thread
	 */
	public float getArea() {
		return area;
	}

	/***
	 * 
	 * @param delta
	 *            represents width and height of the delta
	 */
	public synchronized void setDelta(float delta) {
		this.delta = delta;
	}

	public DoubleIntegralThread() {

	}

	public DoubleIntegralThread(DoubleIntegral dbi) {
		this.dbi = dbi;
	}

	/***
	 * 
	 * @return area returns area of individual thread
	 */
	public synchronized float loopThrough() {
		area = 0.0f;
		area = dbi.compute(X, Y) * delta * delta;
		return area;

	}

	/***
	 * Thread execution starts here.
	 */
	@Override
	public void run() {
		synchronized (dbi) {
			this.area = loopThrough();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		final float X_MAX_SIZE = 1;
		final float Y_MAX_SIZE = 2;
		final float X_MIN_SIZE = -1;
		final float Y_MIN_SIZE = -2;
		int SIZE = 800;
		float delta = 0.1f;
		float area = 0.0f;
		float oldArea = 0.0f;

		do {
			try {
				DoubleIntegralThread dbi_array[] = new DoubleIntegralThread[SIZE];
				DoubleIntegral dbi = new DoubleIntegral();
				area = 0.0f;
				oldArea = 0.0f;

				for (int i = 0; i < SIZE; i++) {
					dbi_array[i] = new DoubleIntegralThread(dbi);
				}

				// Set delta for the thread
				for (int i = 0; i < SIZE; i++) {
					dbi_array[i].setDelta(delta);
				}

				// Set X and Y axis for the thread
				int k = 0;
				for (float i = X_MIN_SIZE; i < X_MAX_SIZE; i = i + delta) {
					for (float j = Y_MIN_SIZE; j < Y_MAX_SIZE; j = j + delta) {

						dbi_array[k].setAxis(i);
						dbi_array[k].setY(j);
						k++;
					}
				}

				// Starting the threads. Thread execution starts at run.
				k = 0;
				for (float i = X_MIN_SIZE; i < X_MAX_SIZE; i = i + delta) {
					for (float j = Y_MIN_SIZE; j < Y_MAX_SIZE; j = j + delta) {

						dbi_array[k++].start();

					}
				}

				// Main thread will will wait this all thread execution are
				// complete
				for (int i = 0; i < SIZE; i++) {
					dbi_array[i].join();
				}

				// Compute the sum of area
				for (int i = 0; i < SIZE; i++) {
					area += dbi_array[i].getArea();
				}

				System.out.println(area);
				delta = delta - 0.01f;

				// Compute the size for the next loop
				k = 0;
				for (float i = X_MIN_SIZE; i < X_MAX_SIZE; i = i + delta) {
					for (float j = Y_MIN_SIZE; j < Y_MAX_SIZE; j = j + delta) {
						k++;
					}
				}

				SIZE = k;
			} catch (OutOfMemoryError oee) {
				System.gc();
			}
		} while (Math.abs(oldArea - area) > 0.01f);
		System.out.println(area);

	}
}
