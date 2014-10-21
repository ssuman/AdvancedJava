/* 
 * HpTreeSet.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package com.rit.algos;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * This program Implements a red black tree
 * 
 * 
 * @author      Suman
 * @author      Kanth
 */
public class RedBlackTree extends TreeSet {

	
	private static boolean RED = true;
	private static boolean BLACK = false;

	private static TreeNode root;
	

	RedBlackTree() {
		root = null;
	}

	/**
	 * Function will return a boolean value
	 * 
	 * @return root		if root is null return true else false
	 */
	public boolean isEmpty() {
		return (root == null) ? true : false;
	}
	
	/**
	 * clear will make root null and root count will become 0.
	 */
	public void clear(){
		if(root ==null)
			return;
		root.setCount(0);
		root=null;
		
		
	}

	/***
	 * add function will return a boolean variable if inserted properly. 
	 * Else false;
	 * 
	 * @param 		e		Element e which we want to insert
	 */
	public boolean add(Object e) {
		try{
		root = add(root, e);
		return true;
		}finally{
			
		}
	}
	
	
	/**
	 * returns the size of the tree. In other words returns number of elements.
	 * 
	 * @return    Number of elements
	 */
	public int size(){
		return size(root);
		
		
	}
	
	/**
	 * Place holder function
	 */
	public boolean remove(Object e){
		return true;
	}

	/***
	 * Compute the number of nodes
	 * @param root	root of the tree
	 * @return		the size of the tree
	 */
	private int size(TreeNode root) {
		if(root==null)
			return 0;
		return root.getCount();
		
	}
	
	/***
	 * @return	returns an Iterator object
	 */
	public Iterator<Object> iterator() {
		
		if(root==null)
			return new TreeIterator(root,0);
		
		return new TreeIterator(root,root.getCount());
	}

	/***
	 * checks where the object is present
	 * @param		o	Element which we want to search.
	 */
	public boolean contains(Object o) {
		return contains(root, o);
	}

	/***
	 * checks for the element within the tree
	 * @param 		node		root node	
	 * @param 		o			element of the tree
	 * @return		true if the element is present else false
	 */
	private boolean contains(TreeNode node, Object o) {
		if (node == null)
			return false;
		int cmp = o.toString().compareTo(node.getInfo().toString());
		if (cmp < 0)
			return contains(node.getLeft(), o);

		else if (cmp > 0)
			return contains(node.getRight(), o);

		else if (cmp == 0)
			return true;

		return false;

	}
	
	
	/***
	 * Id node is red or black
	 * @param 	node	current node
	 * @return	true of RED else false;
	 */
	private boolean isColorRed(TreeNode node) {
		if (node == null)
			return false;
		return node.isColor();
	}

	/***
	 * Adding the element into the tree
	 * @param node	current node, starts with root.
	 * @param e		object e which we want to add
	 * @return		node is returned
	 */
	private TreeNode add(TreeNode node, Object e) {
		if (node == null)
			return new TreeNode(e, RED);
		int cmp = e.toString().compareTo(node.getInfo().toString());
		if (cmp < 0)
			node.setLeft(add(node.getLeft(), e));
		else if (cmp > 0)
			node.setRight(add(node.getRight(), e));
		else
			node.setInfo(e);

		// Left rotate if node's right is RED and left is BLACK
		if (isColorRed(node.getRight()) && !isColorRed(node.getLeft()))
			node = rotateLeft(node);
		// Right rotate -  if node's left is RED and node's left's left is RED
		if (isColorRed(node.getLeft()) && isColorRed(node.getLeft().getLeft()))
			node = rotateRight(node);
		//	flip color if left and right are both RED.
		if (isColorRed(node.getLeft()) && isColorRed(node.getRight()))
			node = changeColor(node);

		return node;
	}

	/***
	 * Flip color 
	 * @param node		current node
	 * @return	will return the node
	 */
	private TreeNode changeColor(TreeNode node) {
		node.setColor(RED);
		node.getLeft().setColor(BLACK);
		node.getRight().setColor(BLACK);
		return node;
	}

	/***
	 * Rotate right -if left  is RED and left's left is RED
	 * @param node		current node
	 * @return		node
	 */
	private TreeNode rotateRight(TreeNode node) {
		TreeNode temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(node);
		temp.setColor(node.isColor());
		node.setColor(RED);

		return temp;
	}

	/***
	 * Rotate left -if right is RED
	 * @param 		node		current node
	 * @return		node
	 */
	private TreeNode rotateLeft(TreeNode node) {

		TreeNode temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);
		temp.setColor(node.isColor());
		node.setColor(RED);
		return temp;
	}

	public static void main(String[] args) {
		RedBlackTree bst = new RedBlackTree();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 30; i++) {
			bst.add(new String("" + i));

		}
		Iterator it = bst.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
		
		for (int i = 0; i < 30; i++) {
			bst.contains(new String(""+i));

		}
		long end = System.currentTimeMillis();

		//System.out.println(end - start);
	}

}
