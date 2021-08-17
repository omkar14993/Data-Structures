import java.util.*;


public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;

    private Node root = null;

    private class Node {
        Node left;
        Node right;
        T data;
        public Node(Node left, Node right, T value) {
            this.left = left;
            this.right = right;
            this.data = value;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int nodesCount() {
        return this.nodeCount;
    }

    public boolean addNode(T element) {
        // check if the value exists in the binary tree and return false.
        if(containsNode(element)) {
            return false;
        }
        else {
            root = add(element, root);
            nodeCount++;
            return true;
        }
    }

    private Node add(T element, Node currentNode) {
        // base case, if node is null, create a new node
        if(currentNode == null)
            currentNode =  new Node(null, null, element);
        else {
            if(element.compareTo(currentNode.data) < 0) {
                currentNode.left = add(element, currentNode.left);
            } else {
                currentNode.right = add(element, currentNode.right);
            }
        }

        return currentNode;

    }


    public boolean containsNode(T element) {        
        return contains(element, root);
    }

    private boolean contains(T elem, Node currentNode) {
        // Base case if node == null, i.e. the element does not exist in tree;
        if(currentNode == null)
            return false;
        
        int diff = elem.compareTo(currentNode.data);

        // if diff < 0, the value of `elem` is less than currentNode's value, therefore traverse left sub-tree
        // if diff > 0, the value of `elem` is greater than currentNode's value, therefore traverse right sub-tree
        if(diff < 0)
            return contains(elem, currentNode.left);
        else if(diff > 0)
            return contains(elem, currentNode.right);
        else return true;
    }

    public Node findNode(T element, Node currentNode) {

        int diff = element.compareTo(currentNode.data);

        if(diff < 0)
            return findNode(element, currentNode.left);
        else if(diff > 0)
            return findNode(element, currentNode.right);
        else return currentNode;
    }



    public boolean deleteNode(T element) {
        if(!containsNode(element))
            return false;
        else {
            root = delete(element, root);
            nodeCount--;
            return true;
        }
    }

    // 3 Cases to consider when deleting a node

    // 1st Case :- If the node to be deleted is a leaf node, i.e. no children
    // we can safely delete this node.
    
    // 2nd Case :- If the node to be deleted contains either a Left OR a right child,
    // we can replace the entire Left/Right subtree of the node to be deleted with it's parent.
    
    // 3rd Case :- If the node to be deleted contains Left AND Right sub-tree
    // we can either get and replace the node to be deleted's value with largest value from left sub-tree or smallest value from right sub-tree.
    //  i :- Largest value from left sub-tree is the rightmost node of left sub-tree
    // ii :- Smallest value from right sub-tree is the leftmost node of right sub-tree.
    
    private Node delete(T element, Node node) {

        if(node == null)
            return null;
        
        int diff = element.compareTo(node.data);

        // traverse into left sub-tree to find the node to be deleted.
        if(diff < 0)
            node.left = delete(element, node.left);
        
        //traverse into right sub-tree to find the node to be deleted.
        else if(diff > 0)
            node.right = delete(element, node.right);
        
        // found the node to be deleted
        else {

            // Cases 1 & 2 for deleting the node are handled here.

            // If right sub-tree or no sub-tree exists
            if(node.left == null)
                return node.right;
            
            // If left sub-tree or no sub-tree exists
            else if(node.right == null)
                return node.left;
            
            // Case 3 
            // for this implementation we are finding smallest Value from right-subtree by traversing far left as possible on right-subtree. 
            else {
                Node tmp = findMin(node.right);

                node.data = tmp.data;

                node.right = delete(tmp.data, node.right);
            }

        }
        return node;
    }

    private Node findMin(Node node) {
        while(node.left != null)
            node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while(node.right != null)
            node = node.right;
        return node;
    }

}
