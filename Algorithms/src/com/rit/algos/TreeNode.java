package com.rit.algos;



public class TreeNode {
	private Object info;
	private TreeNode left;
	private TreeNode right;
	private static int count=0;
	private boolean color;

	TreeNode() {
		
		left = null;
		right = null;
		//count = 0;
	}

	public TreeNode(Object e,boolean color) {
		info = e;
		left = null;
		right = null;
		count = count+1;
		this.color = color;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public TreeNode getLeft() {
		if(left ==null)
			return null;
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		if(right==null)
			return null;
		
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public boolean isColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}

}

