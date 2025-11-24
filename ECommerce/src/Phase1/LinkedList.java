package Phase1;


public class LinkedList<T>  {

	    class Node<T> {//Start Node Class
		public T data;
		public Node<T> next;

		public Node() {
			data = null;
			next = null;
		}
		public Node(T val) {
			data = val;
			next = null;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node<T> getNext() {
			return next;
		}
		public void setNext(Node<T> next) {
			this.next = next;
		}
		}//End of node class
	    
	
//linked list
private Node<T> head;
private Node<T> current;
int size = 0;

public LinkedList () {
	head=current=null;
	size = 0 ;
}

public boolean empty() {
	return head==null;
}

public boolean last() {
	return current.next ==null;
}
public boolean full() {
	return false;
}
public void findfirst() {
	 current= head;
}
public void findnext() {
	 current= current.next;
}
public T retrieve() {
	return current.data;
}
public void update(T val) {
	 current.data = val;
}

public void add (T val) {
	Node<T> tmp;
	if(empty()) {
		current= head =new Node<T> (val);
	}
	else {
		tmp = current.next;
		current.next = new Node<T>(val);
		current = current.next;
		current.next = tmp;
	}
	size++;
}
public void remove() {
	if(current == head) {
		head = head.next;
	}
	else {
		Node<T> tmp = head;
		while(tmp != current)
			tmp = tmp.next;
		
		tmp.next = current.next;
	}
	if(current.next == null)
		current = head;
	else
		current = current.next;
	size--;
}
public int size() {
	return size;
}

public void display() {
	if(head == null)
		System.out.println("There is no data");
	else {
		Node<T> tmp = head;
		while( tmp!= null) {
			System.out.println(tmp.data +" ");
			tmp=tmp.next;
		}
	}
	System.out.println(" ");
}

public Node<T> getHead() {
	return head;
}



}