package Phase2;

import java.io.FileWriter;

public class AVLTree<T> {
///////////////////////////////////////////////////////Node
public class AVLNode<T>{
public int key ;
public T data ;	
AVLNode<T> left ;
AVLNode<T> right;
AVLNode<T> parent;
int balance_factor;


public AVLNode(int key , T data) {
this.key = key;
this.data = data;
left = right = parent = null;
this.balance_factor = 0;
}
public T getData() {
return data;
}

public AVLNode<T> getLeft() {
return left;
}

public AVLNode<T> getRight() {
return right;
}

public int getBalence_factor() {
return balance_factor;
}

@Override
public String toString() {
return "AVLNode [key=" + key + ", data=" + data + ", left=" + left + ", right=" + right + "," 
+ ", balance_factor=" + balance_factor + "]";
}
}/////////////////////////////////////////////End Node

private AVLNode<T> root ;
private AVLNode<T> current ;
private int count;

public AVLTree() {
root = current = null;
count=0;
}

public boolean empty() {
return root==null;
}

public boolean full() {
return false;
}

public T retrieve () {
if(current != null)
return current.data;
return null;
}

public int size() { 
return count;
}
	
public AVLNode<T> getRoot(){
return root;
}

//check if the the node exists
public boolean findkey(int tkey) {
	AVLNode<T> p = root , q=root;
	if(empty())
		return false;
	while(p != null) {
		q=p;
		if(p.key == tkey) {
			current = p;
			return true;
		}
		else if (tkey< p.key)
			p=p.left;
		else
			p=p.right;
	}
	current= q;
	return false;
}

//search that returns data for the given key 
private T ExtractKeyData(AVLNode<T> t , int key) {
if(t == null)
return null;
if(t.key == key) {
current =t;
return t.data;
}
if(t.key>key)
return  ExtractKeyData(t.getLeft(), key);
else
return  ExtractKeyData(t.getRight(), key);
}
	
	//--------------- by laila -----------
	public T ExtractKeyData(int key) {
		return ExtractKeyData(root, key);
	}
/////////////////////////////////////////////

private void updateBalanceFactor(AVLNode t) {
	if(t.balance_factor>1 || t.balance_factor<-1){//check if node is bigger than 1 or smaller than -1
		rebalance(t); 
		return;
	}
	//check if ancesters need rebalancing
	if(t.parent!= null){
		if(t == t.parent.left)//update the left is heaviear
			t.parent.balance_factor = t.parent.balance_factor - 1;
		else//right is heavier
			t.parent.balance_factor = t.parent.balance_factor +	 1;
			//check all upper nodes with recursion till we get to a zero balanced node 
		if(t.parent.balance_factor != 0)
			updateBalanceFactor(t.parent);
	}
	
}




//single left rotation
private void leftRotation(AVLNode<T> t){
	AVLNode<T> updatedRoot = t.right;
	t.right =updatedRoot.left;
	if(updatedRoot.left != null)
	updatedRoot.left.parent= t;
	updatedRoot.parent = t.parent;
	if(t.parent == null)
		root = updatedRoot;
	else if (t == t.parent.left)
		t.parent.left = updatedRoot;
	else t.parent.right = updatedRoot;
	updatedRoot.left = t;
	t.parent = updatedRoot;
	
	t.balance_factor = t.balance_factor -1 - Math.max(0, updatedRoot.balance_factor );
	updatedRoot.balance_factor = updatedRoot.balance_factor -1 + Math.min(0, t.balance_factor );
	
}
///////////////////////////////////////////////

//single right rotation
private void rightRotation(AVLNode<T> t){
	AVLNode<T> updatedRoot = t.left;
	t.left =updatedRoot.right;
	if(updatedRoot.right != null)
	updatedRoot.right.parent= t;
	updatedRoot.parent = t.parent;
	if(t.parent == null)
		root = updatedRoot;
	else if (t == t.parent.right)
		t.parent.right = updatedRoot;
	else t.parent.left = updatedRoot;
	updatedRoot.right = t;
	t.parent = updatedRoot;
	
	t.balance_factor = t.balance_factor +1 - Math.min(0, updatedRoot.balance_factor );
	updatedRoot.balance_factor = updatedRoot.balance_factor +1 + Math.max(0, t.balance_factor );
	
}///////////////////////////////////////////////

//checks the balance of the tree and if it needs it will rebalance
private void rebalance(AVLNode<T> t){
	
	if(t.balance_factor >1) {
	if(t.right.balance_factor >=0) {//for Single rotation
		 leftRotation(t);
	}
	else {//for double rotation
		rightRotation(t.right);
		 leftRotation(t);
	}
}
	
	if(t.balance_factor < -1) {
		if(t.left.balance_factor <= 0) {//Single rotation
			 rightRotation(t);
		}
		else {//double rotation
		    leftRotation(t.left);
			 rightRotation(t);
		}
	}
}

public boolean insert(int k , T val) {
	AVLNode node = new AVLNode(k , val);
	AVLNode p = null, q= root;
	
	if(findkey(k)) {//check if we already have the key duplicates are forbidden
		return false;
	}	
	if(empty()) {
		root = current = node;
	}
	else {
		while(q != null) {//find nodes place and parent
			p = q;
			if(node.key < q.key)
				q = q.left;
			else 
				q= q.right;
		}
		
		node.parent = p;//nodes parent
		
		if(p.key > node.key)//link the parent with the child
			p.left = node;
		if(p.key < node.key)
			p.right = node;
	}//end else
	count++;
	updateBalanceFactor(node);//fix balance
	return true;
}

public boolean removeKey(int key) {//three cases for removing a key
	AVLNode p = null, q= root;
	
	while(q != null) {
		if(key == q.key)
			break;
		p = q;
		if(key < q.key)
			q = q.left;
		else 
			q= q.right;
}
	if(q == null)
		return false;
	//*******************************************
	//Case 1 where node has two child
	if(q.left != null && q.right != null) {
		AVLNode<T> bm = q;
		AVLNode<T> min =q.right ;
		while(min.left !=null){//find the minumum key of right subtree and its parent
			bm = min;
			min=min.left;
		}
		//copy min data into q
		q.key = min.key;
		q.data = min.data;
		
		//now delete the min node
		//At most the min node can have a right child
		AVLNode child = min.right;
		
		//if min was child of q
		if(bm == q)
			bm.right = child;
		else {
			if(bm.left == min)
				bm.left = child;
			else
				bm.right = child;
		}
		//update the childs parent
		if(child!= null)
			child.parent = bm;
		count --;
		updateBalanceFactor(bm);//check the balance 
		return true;
	}
	//*******************************************
	//Case 2 where node has no children
	if(q.left == null && q.right ==null) {
		if(p==null)
			root = null;

		else if(p.left == q)
		p.left = null;
	else
		p.right =null;
		count --;
		updateBalanceFactor(p);
	return true;
	}
	
	//*******************************************
	//Case 3 where node has one child
	AVLNode child = (q.left != null ? q.left : q.right);
	
		if(p == null) {
			root = child;
			child.parent =null;
		}
		
		else {
		if(p.left == q)	{
		p.left = child;
		}
		else {
			p.right = child;
		} 
		child.parent = p;
		}
		count--;
		updateBalanceFactor(p);
		return true;
	
}	
	
//***************Return List Of All Nodes In AVLTree*****************
public LinkedList<T> inOrderTrverse(){
	LinkedList<T> l = new LinkedList<T>();
	recInOrderTrverse(root , l);
	return l;
}

private void recInOrderTrverse(AVLNode<T> t , LinkedList<T> l) {
	//tree is empty
	if(t==null)
		return;
	recInOrderTrverse(t.left , l);
	l.add(t.data);
	recInOrderTrverse(t.right , l);
}





public interface Visitor<E> {
    void visit(int key, E data);
}

private void inOrder(AVLNode<T> node, Visitor<T> v) {
    if (node == null) return;
    inOrder(node.left, v);
    v.visit(node.key, node.data);
    inOrder(node.right, v);
}

public void inOrder(Visitor<T> v) {
    inOrder(root, v);
}











}//end whole class


















































