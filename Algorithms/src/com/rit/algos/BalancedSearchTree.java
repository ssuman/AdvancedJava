package com.rit.algos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class BalancedSearchTree {

	private static TreeNode root;
	private static Queue<Object> qu = new LinkedList<>();
	BalancedSearchTree() {
		root = null;
	}

	public boolean isEmpty() {
		return (root == null) ? true : false;
	}

	public void add(Object e) {
		root = add(root, e);

	}
	
	public Iterator<Object> iterator(){
		inOrder(root);
		return qu.iterator();
	}

	

	private void inOrder(TreeNode node) {
		if(node==null){
			return;
		}
		inOrder(node.getLeft());
		qu.add(node.getInfo());
		inOrder(node.getRight());
	}

	public boolean contains(Object o) {
		return contains(root, o);
	}

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

	private TreeNode add(TreeNode node, Object e) {
		if(node == null)
			return new TreeNode(e);
		int cmp = e.toString().compareTo(node.getInfo().toString());
		if (cmp < 0)
			node.setLeft(add(node.getLeft(), e));
		else if (cmp > 0)
			node.setRight(add(node.getRight(), e));
		else
			node.setInfo(e);
		return node;
	}

	public static void main(String[] args) {
		BalancedSearchTree bst = new BalancedSearchTree();
		
		for(int i=0;i<100;i++){
			Random rn = new Random();
			int random = rn.nextInt(1000);
			bst.add(new String(""+random));
			
		}
		
		Iterator<Object> it = bst.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
