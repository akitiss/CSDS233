###Part A
files: LLString.java and StringNode.java
I have both classes StringNode and LLString for the doubly linked list to function 
I have the extra functions addStringNode and printLLString to actually test if my swap function can work 
Questions for part A are answered on the file LLString.java

Logic and thought process for the code is drawn out in the written component providing details of the multiple cases. 

###Part B
files: BinarySearchTree.java and Node.java 
I added class Node.java for the BinarySearchTree so Nodes could exist

I made all the functions in BinarySearchTree non-static since the bst wasn't a parameter

I created the function inOrder and the helper method inOrderString since in-order traversal returns the bst in chronological order.
inOrderString returns the bst as a sorted string, but inOrder converts this value into an array for easy access
I also created another helper method findNode to find the node given the key. 
I used all these functions in my kthBiggest function to first sort the bst, then find the key, then find the node associated with the key.

I also have an extra helper function numChecker that will just check if the input is a number 