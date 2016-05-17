package anthonynsimon.algorithms.lists;

import anthonynsimon.datastructures.util.SinglyNode;
import anthonynsimon.datastructures.Stack;
import anthonynsimon.datastructures.LinkedList;

public class ListPalindromeChecker<E> extends LinkedList<E> {
  
  public boolean isPalindrome() {
    if (this.head == null) {
      return false;
    }
    
    // Using the runner technique to know when we reached the middle of the list
    SinglyNode<E> runner = this.head;
    SinglyNode<E> laggard = this.head;
    
    Stack<E> stack = new Stack<>();

    int numberOfItems = 0;
    while (runner != null) {
      // Advance the laggard pointer every other iteration after the first one.
      // As the runner advances twice as many positions, the laggard will end up
      // one position after the middle when the runner has reached the end of the list
      if (numberOfItems % 2 == 0) {
        // Push the data and move the laggard
        stack.push(laggard.getData());
        laggard = laggard.getNext();
      }
      
      // Always advance the runner and the item count
      runner = runner.getNext();
      numberOfItems++;
    }
    
    // If we have an even number of items, then we pushed the exact middle to the stack.
    // Pop one element so that we only have "mirrored pairs" for the comparison.
    // No need to move the laggard as it is already pointing at the next position.
    if (numberOfItems % 2 == 1) {
      stack.pop();
    }
    
    // Compare the laggard's current position data against what we pushed to the stack.
    // At this point if the list is a palindrome every position should match the stack because
    // it is essentially a reversed second half of the list.
    while (laggard != null) {
      if (laggard.getData() != stack.pop()) {
        return false;
      }
      laggard = laggard.getNext();
    }
    
    return true;
  }
}