package com.rit.algos;



import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;

public class RedBlackTree extends TreeSet {

	private static final long serialVersionUID = 1L;
	private static boolean RED = true;
	private static boolean BLACK = false;

	private static TreeNode root;
	private static Queue<Object> qu = new LinkedList<>();

	RedBlackTree() {
		root = null;
	}

	public boolean isEmpty() {
		return (root == null) ? true : false;
	}
	
	public void clear(){
		if(root ==null)
			return;
		root.setCount(0);
		root=null;
		qu.clear();
		
	}

	public boolean add(Object e) {
		try{
		root = add(root, e);
		return true;
		}finally{
			
		}
	}
	
	
	public int size(){
		return size(root);
		
		
	}

	private int size(TreeNode root) {
		if(root==null)
			return 0;
		return root.getCount();
		
	}
	public Iterator<Object> iterator() {
		inOrder(root);
		return qu.iterator();
	}

	private void inOrder(TreeNode node) {
		if (node == null) {
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

	private boolean isColorRed(TreeNode node) {
		if (node == null)
			return false;
		return node.isColor();
	}

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

		if (isColorRed(node.getRight()) && !isColorRed(node.getLeft()))
			node = rotateLeft(node);
		if (isColorRed(node.getLeft()) && isColorRed(node.getLeft().getLeft()))
			node = rotateRight(node);
		if (isColorRed(node.getLeft()) && isColorRed(node.getRight()))
			node = changeColor(node);

		return node;
	}

	private TreeNode changeColor(TreeNode node) {
		node.setColor(RED);
		node.getLeft().setColor(BLACK);
		node.getRight().setColor(BLACK);
		return node;
	}

	private TreeNode rotateRight(TreeNode node) {
		TreeNode temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(node);
		temp.setColor(node.isColor());
		node.setColor(RED);

		return temp;
	}

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
		for (int i = 0; i < 3000000; i++) {
			Random rn = new Random();
			int random = rn.nextInt(50000000);
			bst.add(new String("" + random));

		}
		
		for (int i = 0; i < 30; i++) {
			Random rn = new Random();
			int random = rn.nextInt(50000000);
			bst.contains(new String(""+random));

		}
		long end = System.currentTimeMillis();

		System.out.println(end - start);
	}

}
