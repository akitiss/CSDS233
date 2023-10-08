import java.util.Scanner;

public class BinarySearchTree{
    private Node root; 

    //constructor
    public BinarySearchTree(){
        root = null; 
    }

    //all not static since none of the methods take in a binary tree as a parameter

    //inserts a node in the BST
    void insert(Node root, int key){
        Node parent = null; //parent of node 
  
        while (root != null){ //find where the new node belongs 
            parent = root;
            if (key < root.key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
 
        if (key < parent.key) { //insert the  new node 
            parent.left = new Node(key);
        } else {
            parent.right = new Node(key);
        }
     }

    
    // preorder traversal of BST
    // node-left-right
    void preorderRec(Node root){
        if (root != null) { //if node isn't null print it out 
            System.out.print(root.key + " "); //check node
            
            preorderRec(root.left); //then check left then right
            preorderRec(root.right);
        }
        
    }
    
    //find sum of all the keys of a BST
    int sum(Node root){
        if (root == null){ //base case 
            return 0;
        }
        return root.key + sum(root.left) + sum(root.right);
    } 
    
    //find the k’th biggest element in BST
    Node kthBiggest(Node root, int k){
        int count = 0; 
        Node biggest = null;

        while (root != null){
            if (root.right == null){
                count += 1; //means it is at the biggest non-checked element so add count 
                if (count == k){ 
                    biggest = root;
                }
                root = root.left; //means biggest element isn't on the right node to switch to left
            } else {
                //find sucessor (most left node on the subtree)
                Node current = root.right;
                while (current.left != null && current.left != root){
                    current = current.left; 
                }

                if (current.left == null){//---------------------i dont understand ----------
                    current.left = root; 
                    root = root.right; //move to the right 
                } else { //restoring tree
                    current.left = null; 
                    count += 1;
                    if (count == k){
                        biggest = root;
                    }
                    root = root.left; //check left child 
                }
            }
        }
        return biggest;

    }

    //method to check if input is a num from hw1
    //return true if input is not a number  
    static boolean numChecker(String input){
        if (input==""){ //if empty string, not a number 
            return true;
        }
        for (int i=0; i<input.length();i++){
            Character letter = input.charAt(i);
            if (!(letter >= '0' && letter <= '9')){ //if the char isn't a number return true 
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        boolean run = true;
        Scanner in = new Scanner(System.in); //scanner to read in user input 

        BinarySearchTree tree = new BinarySearchTree();

        while (tree.root==null) {
            System.out.print("Enter a root node for the BST: ");
            String input = in.nextLine();
            if (!numChecker(input)){ //checking to make sure input is valid number 
                tree.root = new Node(Integer.parseInt(input));
            } else {
                System.out.println("Invalid input");
            }
        }         

        while (run){ //while the user hasn't quit, keep running 
            System.out.print("\nBinary Search Tree Operations \n 1. insert \n 2. preorder traversal \n 3. sum of all keys \n 4. return kth biggest term \n 6. quit \nChoose an option: ");
            String option = in.nextLine();
            
            if (option.equals("1")){ //runs through each function depending on what is entered
                System.out.print("\nEnter key to be inserted: ");
                String input = in.nextLine();
                if (!numChecker(input)){ //check if input is integer
                    tree.insert(tree.root, Integer.parseInt(input));
                } else {
                    System.out.println("Inavlid input");
                }
            } else if (option.equals("2")){
                tree.preorderRec(tree.root);
                System.out.println("");
            } else if (option.equals("3")){
                System.out.println("Sum: " + tree.sum(tree.root));
            } else if (option.equals("4")){
                System.out.print("\nEnter value for k: ");
                String input = in.nextLine();
                if (!numChecker(input)){ //check if input is integer
                    Node k = tree.kthBiggest(tree.root, Integer.parseInt(input));
                    System.out.println("" + k.key + " is the " + input + " biggest term in the bst");
                } else {
                    System.out.println("Inavlid input");
                }
            } else if (option.equals("5")){
                run = false; 
            }
        }
        
    }


}

