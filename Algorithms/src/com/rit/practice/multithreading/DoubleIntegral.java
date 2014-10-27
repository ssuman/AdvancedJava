package com.rit.practice.multithreading;

public class DoubleIntegral {
	
	public synchronized float compute(float X,float Y){
		return X*X+Y+Y;
	}
}
