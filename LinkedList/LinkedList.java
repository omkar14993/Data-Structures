import org.graalvm.compiler.graph.Node;

import java.time.temporal.Temporal;
import java.util.*;

public class LinkedList {
    Node head;
    Node tail;

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            this.data = d;
            this.left = null;
            this.right = null;
        }
    }

    /** Inserts a node after the given node in the list */
    public void insertAfter(Node prev_node, int data){
        Node new_node = new Node(data);
        if (prev_node == null)
            throw new IllegalArgumentException(prev_node + "Previous Node cannot be null");
        
        Node temp = prev_node.right;
        new_node.right = temp.right;
        new_node.left = prev_node;
        prev_node.right = new_node;
        temp.left = new_node;
    }

    /* Inserts a node at the end of the list */
    public void insert(int data) {
        Node new_node = new Node(data);
        if(head == null){
            head = new_node;
            return;
        }
        
        Node last = head;
        
        while(last.right != null) {
            last = last.right;
        }
        last.right = new_node;
        new_node.left = last;
        
    }

    /* Print all values in the list */
    public void printList() {
        Node node = head;
        while(node != null) {
            System.out.println(node.data);
            node = node.right;
        }
    }

    public void deleteNode(Node n) {
        Node temp = head;
        if(head == null)
            return;
        System.out.println(temp);
        System.out.println(head);


        while(temp.data != n.data) {
            temp = temp.right;
        }

        temp.left.right = temp.right;
        temp.right.left = temp.left;
    }

    public static void main(String[] args) {
        System.out.println("This is a Doubly LinkedList");
        LinkedList doublyList = new LinkedList();
        doublyList.insert(22);
        doublyList.insert(33);
        doublyList.insert(93);
        // doublyList.printList();
        Node nodeToDelete = new Node(33);
        doublyList.deleteNode(nodeToDelete);
        doublyList.printList();
    }

}