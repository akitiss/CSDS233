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

    //swap node with its successor 
    // void swapNodeSucessor(StringNode x){
    //     if (x == null || x.next == null){ //if at the end of the llstring, don't swap 
    //         return;
    //     }

    //     //create variables to easily reference the nodes that need references changed 
    //     StringNode sucessor = x.next;
    //     StringNode predecessor = x.prev;
    //     StringNode postSucessor = x.next.next;

    //     if (this.lst_head.next == x){ //if x is the first term on the linked list
    //         this.lst_head.next = sucessor;
    //         sucessor.prev = this.lst_head;
    //     } else { //if x is not the first term 
    //         sucessor.prev = predecessor;
    //         predecessor.next = sucessor;
    //     }

    //     x.prev = sucessor; //point x and sucessor to each other  
    //     sucessor.next = x; 

    //     if (sucessor == this.lst_tail){ //if sucessor is the tail, make x the new tail 
    //         x.next = null;
    //         this.lst_tail = x;
    //     } else if (postSucessor == this.lst_tail){ //if postSucessor is the tail
    //         this.lst_tail.prev = x;
    //         x.next = this.lst_tail;
    //     } else { //if sucessor is not the tail 
    //         x.next = postSucessor;
    //         postSucessor.prev = x;
    //     }
    // }  

        void swapNodeSucessor(StringNode x){
            //create variables to easily reference the nodes that need references changed 
            StringNode sucessor = x.next;
            StringNode predecessor = x.prev;
            StringNode postSucessor = x.next.next;

            //case 0: x is tail or is null
            if (x == null || x.next == null){
                return;
            } //case 1: head and tail not predecessor, x, sucessor, or postSucessor   
            else if (predecessor != this.lst_head && x != this.lst_tail && sucessor != this.lst_tail && postSucessor != this.lst_tail){
                predecessor.next = sucessor;                
                x.next = postSucessor;
                x.prev = sucessor;
                sucessor.next = x;
                sucessor.prev = predecessor;
                postSucessor.prev = x;
            } else if (predecessor == this.lst_head){ //case 2: head is the predecessor
                this.lst_head.next = sucessor;
                x.next = postSucessor;
                x.prev = sucessor;
                sucessor.next = x;
                sucessor.prev = this.lst_head;
                postSucessor.prev = x;
            } else if (sucessor == this.lst_tail){ //case 3: sucessor is the tail 
                predecessor.next = sucessor;
                x.next = null;
                x.prev = sucessor;
                this.lst_tail = x;
                sucessor.prev = predecessor;
                sucessor.next = x;
            } else if (postSucessor == this.lst_tail){  //case 4: postSucessor is the tail
                predecessor.next = sucessor;
                x.next = postSucessor;
                x.prev = this.lst_tail;
                sucessor.next = x;
                sucessor.prev = predecessor;
                this.lst_tail.prev = x;
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

        //test swap function 
        LLString l1 = doublyLL;
        LLString l2 = doublyLL;
        LLString l3 = doublyLL;
        LLString l4 = doublyLL;
        LLString l5 = doublyLL;
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
        l5.swapNodeSucessor(sn5);
        System.out.print("\nswap : " + sn5.str + " ->");
        l5.printLLString(l5.lst_head);

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
