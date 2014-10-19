package com.rit.algos;

public class TreeNode {
	private Object info;
	private TreeNode left;
	private TreeNode right;
	private int count;

	TreeNode() {
		
		left = null;
		right = null;
		count = 0;
	}

	public TreeNode(Object e) {
		info = e;
		left = null;
		right = null;
		count = 0;
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
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

}

