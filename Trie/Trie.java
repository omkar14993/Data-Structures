import java.util.HashMap;
import java.util.Map;

public class Trie {

    private class TrieNode {
        Map<Character,TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
           children = new HashMap<>();
           endOfWord = false; 
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** inserting a word into Trie */
    /** Iterate through each character string in the word and place each char in a Map referencing the child node */
    public void insert(String word) {
        TrieNode current = root;
        for(int i=0; i < word.length(); i++) {
            Character s = word.charAt(i);
            TrieNode node = current.children.get(s);
            if(node == null) {
                node = new TrieNode();
                current.children.put(s, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for(int i=0; i < word.length(); i++) {
            Character s = word.charAt(i);
            TrieNode node = current.children.get(s);
            if(node == null) {
                return false;
            }
            current = node;
        }
        if(current.endOfWord == true)
            return true;
        else
            return false;
    }

    public void delete(String word) {
        // TODO
    }

    public static void main(String[] args) {
        System.out.println("Hello Worlld");
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("bcefg");
        trie.insert("bcd");
        trie.insert("xyz");
        System.out.println(trie.search("abc")); // true
        System.out.println(trie.search("bcd")); // true
        System.out.println(trie.search("bcefg")); // true
        System.out.println(trie.search("abcd")); // true
        System.out.println(trie.search("xxx")); // false
    }
}