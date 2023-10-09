//Design an algorithm to find the node X and swap it with its successor node
//1. Give code.

public class LLString{
    private StringNode lst_head;
    private StringNode lst_tail;
    private int theSize;

    //constructors
    public LLString(){
        lst_head = null;
        lst_tail = null;
        theSize = 0;
    }

    //adds node at the end of the LLString
    void addStringNode(StringNode node){
        if (this.lst_head==null){
            this.lst_head = new StringNode(""); //-------- cuz isnt the head only supposed to refrence stuff, not actually contains stuff?????
            this.lst_tail = node;   
            this.lst_head.next = this.lst_tail; //set tail and head pointing to each other 
            this.lst_tail.prev = this.lst_head;
        }
        //moves node to the end of the list by being placed after the tail
        node.prev = this.lst_tail; //set node next and prev 
        node.next = null;
        this.lst_tail.next = node; //make tail point to node 
        this.lst_tail = node; //new tail 
    }

    //prints out the linked list from head to tail 
    void printLLString(StringNode node){ 
        if (node != null){
            System.out.print(node.str + " ");
            printLLString(node.next);
        }
    }

    // swap node with its successor 
    void swapNodeSucessor(StringNode x){
        if (x == null || x.next == null){ //if at the end of the llstring, don't swap 
            return;
        }

        //create variables to easily reference the nodes that need references changed 
        StringNode sucessor = x.next;
        StringNode predecessor = x.prev;
        StringNode postSucessor = x.next.next;

        if (this.lst_head.next == x){ //if x is the first term on the linked list
            this.lst_head.next = sucessor;
            sucessor.prev = this.lst_head;
        } else { //if x is not the first term 
            sucessor.prev = predecessor;
            predecessor.next = sucessor;
        }

        x.prev = sucessor; //point x and sucessor to each other  
        sucessor.next = x; 

        if (sucessor == this.lst_tail){ //if sucessor is the tail, make x the new tail 
            x.next = null;
            this.lst_tail = x;
        } else if (postSucessor == this.lst_tail){ //if postSucessor is the tail
            this.lst_tail.prev = x;
            x.next = this.lst_tail;
        } else { //if sucessor is not the tail 
            x.next = postSucessor;
            postSucessor.prev = x;
        }
    }  

    public static void main(String[] args) {
        //set up the linked list to test on 
        StringNode sn11 = new StringNode("a");
        StringNode sn12 = new StringNode("b");
        StringNode sn13 = new StringNode("c");
        StringNode sn14 = new StringNode("d");
        StringNode sn21 = new StringNode("a");
        StringNode sn22 = new StringNode("b");
        StringNode sn23 = new StringNode("c");
        StringNode sn24 = new StringNode("d");
        StringNode sn31 = new StringNode("a");
        StringNode sn32 = new StringNode("b");
        StringNode sn33 = new StringNode("c");
        StringNode sn34 = new StringNode("d");
        StringNode sn41 = new StringNode("a");
        StringNode sn42 = new StringNode("b");
        StringNode sn43 = new StringNode("c");
        StringNode sn44 = new StringNode("d");

        LLString l1 = new LLString();
        LLstring l2 = new LLString();
        LLString l3 = new LLString();
        LLString l4 = new LLString();
        
        l1.addStringNode(sn1);
        l1.addStringNode(sn2);
        l1.addStringNode(sn3);
        l1.addStringNode(sn4);
        l2.addStringNode(sn1);
        l2.addStringNode(sn2);
        l2.addStringNode(sn3);
        l2.addStringNode(sn4);
        l3.addStringNode(sn1);
        l3.addStringNode(sn2);
        l3.addStringNode(sn3);
        l3.addStringNode(sn4);
        l4.addStringNode(sn1);
        l4.addStringNode(sn2);
        l4.addStringNode(sn3);
        l4.addStringNode(sn4);

        //test swap function 
        LLString l1 = doublyLL;
        LLString l2 = doublyLL;
        LLString l3 = doublyLL;
        LLString l4 = doublyLL;
        System.out.print("original:");
        doublyLL.printLLString(doublyLL.lst_head);
        l1.swapNodeSucessor(sn1);
        System.out.print("\nswap : " + sn1.str + " ->");
        l1.printLLString(l1.lst_head);
        l2.swapNodeSucessor(sn2);
        System.out.print("\nswap : " + sn2.str + " ->");
        l2.printLLString(l2.lst_head);
        l3.swapNodeSucessor(sn3);
        System.out.print("\nswap : " + sn3.str + " ->");
        l3.printLLString(l3.lst_head);
        l4.swapNodeSucessor(sn4);
        System.out.print("\nswap : " + sn4.str + " ->");
        l4.printLLString(l4.lst_head);
    }
}
//2. Explain the major concept of your algorithm. 
    //it switches the next and prev references of the 4 nodes affected by the swap: predecessor, x, sucessor, and postSucessor. and, if any of these values are the head or tail, there are different operations run    
//3. Give an example of running your algorithm.
    //examples are given in the main 
//4. Give and justify its runtime.
    //runtime O(1). all the components of the function take no longer than O(1) time. I am just swapping the next and prev values.
