/**
 * This implementation of UnionFind only deals with integers using arrays, i.e. we match the array's index with it's value to verify the root node.
 * if array's index == array's value, it is the root node.
 * Initially each node points to itself, i.e. index == value
 */
import java.util.*;
public class UnionFind {
    int size;
    int[] sz;
    int[] id;
    int numComponents;

    public UnionFind(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Should be greater than 0");
        
        this.size = this.numComponents = size;
        id = new int[size];
        sz = new int[size];
        for(int i = 0; i < size; i++) {
            id[i] = i; //each node/element points to inself initially.
            sz[i] = 1;
        }
    }

    public void displayElements() {
        for(int i = 0; i < id.length; i++) {
            System.out.print("element->" + i);
            System.out.println("root->" + id[i]);
        }
    }

    // Find root of the given element
    public int findRoot(int element) {
        int root = element;
        while(root != id[root]) {
            root = id[root];
        }

        // now that we have found the root of element, perform path compression
        // i.e. link all the elements from element --> root, directly to root.
        // for example, element = 5 and path to root is 5 --> 2 --> 6 --> 8 which takes 3 iterations.
        // perform path compression, i.e. 5 --> 8, 2 --> 8, 6 --> 8 which would then require only 1 iteration to get to the root value.

        while (element != root) {
            int next = id[element];
            id[element] = root;
            element = next;
        }

        return root;
    }

    // find if two elements have the same root,
    public boolean isConnected(int element1, int element2) {
        return findRoot(element1) == findRoot(element2);
    }

    // returns size of the root of `element`
    public int size(int element) {
        return sz[findRoot(element)];
    }

    // return total number of components/sets
    public int components() {
        return numComponents;
    }

    public void unify(int element1, int element2) {
        int root1 = findRoot(element1);
        int root2 = findRoot(element2);
        // if both the elements have same root, they are already unified
        if(root1 == root2)
            return;
        
        // Merge smaller component into larger one
        if(sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        numComponents--;
    }

    public static void main(String args[]) {
        UnionFind set = new UnionFind(6);
        //set.displayElements();
        set.unify(4,5);
        set.unify(1,2);
        set.unify(2,3);
        set.unify(2,4);
        set.displayElements();
        System.out.println(set.components());
        System.out.println(set.size(4));
        set.displayElements();
        
    }

}