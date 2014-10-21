package com.rit.algos;

import java.util.Iterator;

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
	
	private void inOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft());
		myArray[index++]=node.getInfo();
		inOrder(node.getRight());
	}
	
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
