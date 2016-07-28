/*
 * 	myPalindrome:   Version1:  Uses two stacks to see if a word is a palindrome - i.e. word that is the same when written in reverse order.  
 * 						Does this by pushing all the characters in the word into both stacks, but in opposite orders for each stack.
 * 						Finally compares each character by popping both stacks, comparing characters for equality.
 * 						NOTE: JCF (java built-in) Stack ADT used (not a self implemented version)
 * 
 * 					Version2:  Uses one stack (LIFO) and one queue (FIFO) to see if a word is a palindrome.  Because a stack is a LIFO
 * 						ADT and a queue is a FIFO ADT, comparing for equality the characters POPPED from the stack with those DEQUEUED
 * 						from the queue is straight forward.
 * 						NOTE: JCF (java built-in) LinkedList ADT used to implement the JCF Queue interface (not a self implemented version)
 * 					
 */

package cs105Palindrome;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class myPalindrome {

	public static void main(String[] args) {
		myPalindrome prog = new myPalindrome();	// default constructor called
		
		String word1 = "peter";
		String word2 = "otto";

		System.out.println("Testing for palindromes by only using JCF Stack implementation");
		System.out.println("--------------------------------------------------------------");
		System.out.println(prog.isPalindrome(word1) ? "Yes, '"+word1+"' is a Palindrome" : "No, '"+word1+"' is NOT a Palindrome");
		System.out.println(prog.isPalindrome(word2) ? "Yes, '"+word2+"' is a Palindrome" : "No, '"+word2+"' is NOT a Palindrome");

		System.out.println("\nTesting for palindromes by implementing JCF Queue interface with JCF LinkedList implementation");
		System.out.println("----------------------------------------------------------------------------------------------");		
		System.out.println(prog.isPalindromeV2(word1) ? "Yes, '"+word1+"' is a Palindrome" : "No, '"+word1+"' is NOT a Palindrome");
		System.out.println(prog.isPalindromeV2(word2) ? "Yes, '"+word2+"' is a Palindrome" : "No, '"+word2+"' is NOT a Palindrome");		
	}
	
	/**
	 * Function isPalindromeV2()
	 * @param w		any string to be tested if palindrome
	 * @return		true, if w is a palindrome, false otherwise
	 * 
	 */
	private boolean isPalindromeV2(String w){
		Stack<Character> s1 = new Stack<Character>();
		Queue<Character> q1 = new LinkedList<Character>();	// note: Queue is only a JCF interface; JCF LinkedList implements JCF Queue
		
		// PUSH and ENQUEUE all characters into stack and queue respectively
		for (int i=0; i<w.length(); i++){
			// PUSH char onto stack s1
			s1.push(w.charAt(i));
			// also OFFER to queue q1 (i.e. enqueue, same as push at the e/o queue)
			q1.offer(w.charAt(i));
		}
		
		// compare (for inequality) the characters popped from stack with those dequeued (polled) from the queue		
		while (!s1.isEmpty()){
			if (s1.pop() != q1.poll()){
				return false;	// loop terminates as soon as any inequality found
			}
		}		
		return true;	// only executed if no inequality was found
	}
	
	
	/**
	 * Function isPalindrome()
	 * @param w		any string
	 * @return		true, if w is a palindrome, false otherwise
	 * Conditions:	makes use of helper function getStringReverse(String w);
	 */
	private boolean isPalindrome(String w){
		Stack<Character> s1 = new Stack<Character>();
		Stack<Character> s2 = new Stack<Character>();
		String wReverse = getStringReverse(w);
		
		// push 1 - in order
		for (int i=0; i<w.length(); i++){
			s1.push(w.charAt(i));
		}		
		
		// push 2 - in REVERSE order		
		for (int j=0; j<w.length(); j++){
			s2.push(wReverse.charAt(j));
		}
		
		// pop and compare, return false if false any time, else true
		while (!s1.isEmpty()){
			if (s1.pop()!= s2.pop()){ return false;}
		}
		
		return true; 	// this will execute ONLY if word given is a palindrome
	}
	
	/**
	 * getStringReverse()
	 * @param w		takes a string
	 * @return		returns the same string, but characters in reverse order
	 */
	private String getStringReverse(String w){
		Stack<Character> s1 = new Stack<Character>();
		String output = "";

		// iterate over each character in the word w, pushing to stack s1 
		// (note, cannot iterate with for (char c : w) because w is not an iterable collection (e.g. array, list, etc.)
		for (int i=0; i<w.length(); i++){			
			s1.push(w.charAt(i));
		}

		// Stack is an Iterable collection, hence we can iterate over each character in the collection, 
		// popping and saving into a string
		Iterator<Character> iter = s1.iterator();
		while (iter.hasNext()){
			output += s1.pop();
		}
		
		return output;
	}

}
