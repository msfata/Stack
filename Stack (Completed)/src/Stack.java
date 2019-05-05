import java.util.LinkedList;

import com.google.java.contract.ContractImport;
import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

// A last-in-first-out data structure implemented by a singly-linked list.
@ContractImport({"java.util.LinkedList"})
@Invariant("list!=null")
public class Stack {
	// The list field holds a linked list representing the stack.
	// The last element of the list is the top of the stack.
	private LinkedList<Integer> list;
	
	// Convenience method for comparison of two integer lists, after removing
	// the last element of the first (left-hand-side) list.
	// A copy of it is used to avoid modifying the argument.
	@SuppressWarnings("unused")
	private static boolean 
		listEqualsSkippingOne(LinkedList<Integer> a, LinkedList<Integer> b) {
			LinkedList<Integer> askip = new LinkedList<Integer>(a);
			askip.removeLast();
			return askip.equals(b);
	}
	
	// The equals method decides if [this] object is equal (by value) to 
	// the object [obj] provided as argument. 
	public boolean equals(Object obj) {
		if(! (obj instanceof Stack)) return false;
		Stack o = (Stack) obj;
		return list.equals(o.list);
	}

	// Default constructor, initialises an empty stack.
	@Requires("true")
	@Ensures("list.isEmpty()")
	public Stack() {
		list = new LinkedList<Integer>();
	}
	
	// Copy constructor. 
	@Requires("true")
	@Ensures("equals(s)")
	public Stack(Stack s) {
		list = new LinkedList<Integer>(s.list);
	}
	
	// Pure method that indicates if the stack is empty.
	@Requires("true")
	@Ensures("equals(old(new Stack(this)))")
	public boolean isEmpty() {
		return list.isEmpty();
	}
	

	// Method returns the integer stored in the top-most item in the stack.
	// Cannot be used with empty stacks.
	@Requires("!list.isEmpty()")
	@Ensures("result==list.peekLast() && equals(old(new Stack(this)))")
	public int peek() {
		return list.peekLast();
	}

	// Pure method that returns the number of items in the stack
	@Requires("true")
	@Ensures("result==list.size() && equals(old(new Stack(this)))")
	public int length() {
		return list.size();
	}
	
	// Method removes and returns the top-most item.
	@Requires("!list.isEmpty()")
	@Ensures({
		"result==old(list.peekLast())",
		"listEqualsSkippingOne(old(new LinkedList<Integer>(list)), list)"
		})
	public int pop() {
		return list.removeLast();
	}
	
	// Method introduces a new top-most item storing the integer argument in it,
	// and pushes down the stack by one position.
	@Requires("true")
	@Ensures({
		"i==list.peekLast()",
		"listEqualsSkippingOne(list, old(new LinkedList<Integer>(list)))"
		})
	public void push(int i) {
		list.addLast(i);
	}
	
	
}
