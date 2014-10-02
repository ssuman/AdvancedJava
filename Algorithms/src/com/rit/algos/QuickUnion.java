package com.rit.algos;

public class QuickUnion {
	
	private int [] arr;
	
	QuickUnion(int N){
		arr = new int[N];
		for(int i=0;i<N;i++)
			arr[i]=i;
	}
	
	public int root(int p){
		while(arr[p]!=p)
			p=arr[p];
		return p;
	}
	
	public boolean connected(int p,int q){
		return root(p)==root(q);
	}
	
	public void union(int p, int q){
		int id1 = root(p);
		int id2 = root(q);
		arr[id1]=id2;
	}

	public static void main(String[] args) {
		QuickUnion qu = new QuickUnion(10);
		
		qu.union(1, 3);
		qu.union(4, 5);
		qu.union(3, 5);
		
		System.out.println(qu.connected(1, 5));

	}

}
