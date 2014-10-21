/* 
 * TreeIterator.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package com.rit.algos;

import java.util.Iterator;

/**
 * This program Implements an Iterator 
 * 
 * @author      Suman
 * @author      Kanth
 */
public class TreeIterator implements Iterator<Object>{
	
	Object myArray [];

	int cursor =0;
	private int SIZE;
	private TreeNode node;
	private static int index =0;
	
	public TreeIterator(TreeNode node,int size){
		myArray = new Object[size];
		this.node =node;
		this.SIZE=size;
		index=0;
		inOrder(node);
	}
	
	/***
	 * In order traversal will be performed
	 * 
	 * @param node
	 */
	private void inOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft());
		myArray[index++]=node.getInfo();
		inOrder(node.getRight());
	}
	
	/***
	 * has next if cursor is equal to size will return false
	 */
	@Override
	public boolean hasNext() {
		return cursor != SIZE;
	}
	
	
	@Override
	public Object next() {
		return myArray[cursor++]; 
	}
	@Override
	public void remove() {
	}

	/**
	 * @return the node
	 */
	public TreeNode getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(TreeNode node) {
		this.node = node;
	}
	
	
	
}
