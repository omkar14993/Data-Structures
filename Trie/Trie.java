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
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if currrent.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        //if true is returned then delete the mapping of character and trienode reference from map.
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        }
        return false;
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
        trie.delete("abc");
        System.out.println(trie.search("abc")); // false
        System.out.println(trie.search("abcd")); // true
    }
}