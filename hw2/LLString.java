//Design an algorithm to find the node X and swap it with its successor node
//1. Give code.

public class LLString{
    private StringNode head;
    private StringNode tail;
    private int theSize;

    //constructors
    public LLString(){
        lst_head = null;
        lst_tail = null;
        theSize = 0;
    }

    //adds node at the end of the LLString
    void addStringNode(StringNode node){
        if (this.head==null){
            this.head = new StringNode(""); //-------- cuz isnt the head only supposed to refrence stuff, not actually contains stuff?????
            this.tail = node;   
            head.next = tail; //set tail and head pointing to each other 
            tail.prev = head;
        }
        //moves node to the end of the list by being placed after the tail
        node.prev = this.tail; //set node next and prev 
        node.next = null;
        tail.next = node; //make tail point to node 
        tail = node; //new tail 
    }

    //prints out the linked list from head to tail 
    void printLLString(StringNode node){ 
        if (node != null){
            System.out.print(node.str + " ");
            printLLString(node.next);
        }
    }

    //swap node with its sucessor 
    void swapNodeSucessor(StringNode x){
        StringNode trav = this.head; //start at first node 
        while (trav != null) { //move down until reach the end 
            if (trav == x) {  
                StringNode temp = x.next;
                x.next = x; 
                x.next.prev = temp;
                return; 
            }
            trav = trav.next;
        }
    }  

    public static void main(String[] args) {
        //set up the linked list to test on 
        LLString doublyLL = new LLString();
        StringNode sn1 = new StringNode("a");
        StringNode sn2 = new StringNode("b");
        StringNode sn3 = new StringNode("c");
        StringNode sn4 = new StringNode("d");
        StringNode sn5 = new StringNode("e");
        
        doublyLL.addStringNode(sn1);
        doublyLL.addStringNode(sn2);
        doublyLL.addStringNode(sn3);
        doublyLL.addStringNode(sn4);
        doublyLL.addStringNode(sn5);

        //check if the refrences are right 
        System.out.print('\n');
        System.out.print(head.next.str);
        System.out.print(sn1.next.str);
        System.out.print(sn2.next.str);
        System.out.print(sn3.next.str);
        System.out.print(sn4.next.str);
        System.out.print('\n');
        System.out.print(sn2.prev.str);
        System.out.print(sn3.prev.str);
        System.out.print(sn4.prev.str);
        System.out.print(sn5.prev.str);
        System.out.print(tail.prev.str);


        //test swap function 
        doublyLL.swapNodeSucessor(sn3);
        // doublyLL.printLLString(sn1);

    }
}
//2. Explain the major concept of your algorithm. 
    //it loops through the list, and when it reaches x, it sets x as its own sucessor and replaces itself with its old sucessor kept in temp   
//3. Give an example of running your algorithm.
    //linked-list(addresses not written): [head, "wow", "bow", "hi", "yay", "okay"]
    //input: partA("hi"); 
    //result: returns "hi"; linked list: [head, "wow", "bow", "yay", "hi", "okay"]
    //will switch the "hi" and "yay"
//4. Give and justify its runtime.
    //runtime O(n). it'll take O(n) time to find the node X. and it'll take O(1) time to swap node x and its sucessor node
