/* 
 * DoubleIntegral.java 
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
 * @author      Kanth
 * @author      Suman
 */
public class DoubleIntegral {
	
	
	public synchronized float compute(float X,float Y){
		return X*X+Y+Y;
	}
}
