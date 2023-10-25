/*******************************************************
 * Assignemnt 3
 * Name: Li April
 * UID: axl1039
 ********************************************************/

public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> mRoot; // root node
    // nodes of AVL Tree(internal)

    class AVLTreeNode<T extends Comparable<T>> {
        T key; // key
        int height; // height
        AVLTreeNode<T> left; // left children
        AVLTreeNode<T> right; // right children

        /**
         * Node class for AVL Tree
         */
        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    // AVLTree Constructor
    public AVLTree() {
        mRoot = null;
    }

    /*
     * Gets the height of the tree
     */
    private int height(AVLTreeNode<T> tree) {
        if (tree != null)
            return tree.height;

        return 0;
    }

    public int height() {
        return height(mRoot);
    }

    /**
     * Find the max value among the given numbers.
     * 
     * @param a First number
     * @param b Second number
     * @return Maximum value
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /*
     * Question: a-1
     * Preorder traversal "AVL tree", print the result
     */
    private void preOrder(AVLTreeNode<T> tree) {
        //node-left-right
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * Question: a-2
     * In-order traversal "AVL tree", print the result
     */
    private void inOrder(AVLTreeNode<T> tree) {
        //left-node-right
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * Question: a-3
     * Post-order traversal "AVL tree", print the result
     */
    private void postOrder(AVLTreeNode<T> tree) {
        if (tree != null) {
            //left-right-node
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * (Recursion) Search the node whose key-value is key in "AVL tree x"
     */
    private AVLTreeNode<T> search(AVLTreeNode<T> x, T key) {
        if (x == null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    public AVLTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
     * (Non-Recursion) Search the node whose key-value is key in "AVL tree x"
     */
    private AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x;
        }

        return x;
    }

    public AVLTreeNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /*
     * Question: a-4
     * Find min node：return the smallest node of the AVL tree when "tree" as the
     * root
     */
    private AVLTreeNode<T> minimum(AVLTreeNode<T> tree) {
        while (tree.left != null){
            tree = tree.left; //go as left as you can to find min number 
        }
        return tree;
    }

    public T minimum() {
        AVLTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * Question: a-5
     * Finds max node: return the largest node of the AVL tree with "tree" as the
     * root
     */
    private AVLTreeNode<T> maximum(AVLTreeNode<T> tree) {
        while (tree.right != null){
            tree = tree.right; //go as right as you can to find max number
        }
        return tree;
    }

    public T maximum() {
        AVLTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * RR(a right rotation)。
     *
     * return: the root node after rotated
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;

        return k1;
    }

    /*
     * LL: (left roration)。
     *
     * return: the root node after rotated
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;

        return k2;
    }

    /*
     * LR: (left double roration)。
     *
     * return: the root node after rotated
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3) {
        k3.left = leftLeftRotation(k3.left);

        return rightRightRotation(k3);
    }

    /*
     * RL: (right double roration)。
     *
     * return: the root node after rotated
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1) {
        k1.right = rightRightRotation(k1.right);

        return leftLeftRotation(k1);
    }

    /*
     * Question a-6
     * insert an element into the tree, return the root node
     *
     * @param tree: the root node of AVL tree
     * 
     * @param key: the insert key-value
     * 
     * @return tree: root node
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) {
        if (tree == null) {
            tree = new AVLTreeNode<T>(key, null, null);
            if (tree == null) {
                System.out.println("ERROR: create avltree node failed!");
                return null;
            }
        } else {
            int cmp = key.compareTo(tree.key);

            if (cmp < 0) { // Case: The key should be inserted into the "left subtree of the tree"
                tree.left = insert(tree.left, key);
            } else if (cmp > 0) { // Case: The key should be inserted into the "right subtree of the tree"
                tree.right = insert(tree.right, key);
            } else { // cmp==0
                System.out.println("Insert Fail: Cannot insert the same element!");
            }
        }
        
        //since this is recursive it will balance the nodes from the bottom then to the top 
        tree.height = max(height(tree.left), height(tree.right)) + 1;
        int balance = height(tree.right) - height(tree.left);
        // print();
        
        // System.out.println("\nTHIS IS BALANCE: " + balance); 
        // preOrder(tree);
        // System.out.println("========= CURRENT KEY: " + tree.key);

        if (balance >= 2){
            if (key.compareTo(tree.right.key) < 0){
                //case 3d: add left subtree to y's right child
                tree = rightLeftRotation(tree); 
            } else {
                //case 3b: add right subtree to y's right child 
                // System.out.println("\nTHE KEY : " + tree.key);
                // System.out.println("TREE LEFT HEIGHT: " + height(tree.left));
                // System.out.println("TREE RIGHT HEIGHT: " + height(tree.right));
                tree = leftLeftRotation(tree);
                // System.out.println("TREE LEFT HEIGHT: " + height(tree.left));
                // System.out.println("TREE RIGHT HEIGHT: " + height(tree.right));

            }
        } else if (balance <= -2){
            if (key.compareTo(tree.left.key) < 0){
                //case 3a: add left subtree to y's left child 
                tree = rightRightRotation(tree);
            } else {
                //case 3d: add left subtree to y's right child 
                tree = leftRightRotation(tree);
            }
        }   

        return tree;
    }

    public void insert(T key) {
        mRoot = insert(mRoot, key);
    }

    /*
     * Question: a-7
     * Delete the node (z), then return the root node
     *
     * @param tree: the root node of AVL tree
     * 
     * @param z: the node to be deleted
     * 
     * @return tree: root node
     */
    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
        // if the root is empty or there are no nodes to delete, return "null"
        if (tree == null || z == null)
            return null;

        int cmp = z.key.compareTo(tree.key);
        if (cmp < 0) { // The node to be deleted is in the "left subtree of tree"
            tree.left = remove(tree.left, z);

        } else if (cmp > 0) { // The node to be deleted is in the "right subtree of tree"
            tree.right = remove(tree.right, z); 

        } else {
            // If both the left and right children of "tree" are not empty
            if ((tree.left != null) && (tree.right != null)) {
                //replace with inorder sucessor 
                AVLTreeNode<T> min = minimum(tree.right);
                tree.key = min.key;
                tree.right = remove(tree.right, min);

            } else {
                AVLTreeNode<T> tmp = tree;
                tree = (tree.left != null) ? tree.left : tree.right;
                tmp = null;
            }
        }

        if (tree == null){
            return tree;
        }
        
        //rotate if not in balance 
        tree.height = max(height(tree.left), height(tree.right)) + 1;
        int balance = height(tree.right) - height(tree.left);
        
        int leftBalance = 0;
        int rightBalance = 0;
        if (tree.left != null){
            leftBalance = height(tree.left.right) - height(tree.left.left);
        } 
        if (tree.right != null){
            rightBalance = height(tree.right.right) - height(tree.right.left);
        }

        if (balance <= 2){
            if (leftBalance > 0) {
                tree = leftRightRotation(tree); 
            } else {
                tree = leftLeftRotation(tree);
            }
        } else if (balance <= -2){
            if (rightBalance < 0){
                tree = rightLeftRotation(tree);
            } else {
                tree = rightRightRotation(tree);
            }
        }

        return tree;
    }

    public void remove(T key) {
        AVLTreeNode<T> z;

        if ((z = search(mRoot, key)) != null)
            
             remove(mRoot, z);
    }

    /*
     * Destory Tree
     */
    private void destroy(AVLTreeNode<T> tree) {
        if (tree == null)
            return;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree = null;
    }

    public void destroy() {
        destroy(mRoot);
    }

    /*
     * Print Tree
     *
     * @param key: key-value
     * 
     * @param direction:0 : means the node this the root node.
     * 
     * @param direction:-1 : -1，means the node is the left child of its parent;
     * 
     * @param direction:1 : 1，means the node is the right child of its parent;
     */
    private void print(AVLTreeNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)
                System.out.printf("%2d is root\n", tree.key, key);
            else
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
