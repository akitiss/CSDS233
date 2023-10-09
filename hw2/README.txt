###Part A
files: LLString.java and StringNode.java
I have both classes StringNode and LLString for the doubly linked list to function 

###Part B
files: BinarySearchTree.java and Node.java 
I added class Node.java for the BinarySearchTree to reference

I made all the functions in BinarySearchTree non-static since I thought it would be cleaner via that way compared to putting in the input which tree it was. 

I created the function inOrder and the helper method inOrderString since in-order traversal returns the bst in chronological order.
inOrderString returns the bst as a sorted string, but inOrder converts this value into an array
I also created another helper method findNode to find the node given the key. 
I used all these functions in my kthBiggest function to first sort the bst, then find the key, then find the node associated with the key.

I also have an extra helper function numChecker that will just check if the input is a number 