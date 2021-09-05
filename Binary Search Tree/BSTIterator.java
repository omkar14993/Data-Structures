/**
 * Definition for a binary tree node.
*/ 

class BSTIterator {
    
    private TreeNode currentNode;
    private java.util.Stack<TreeNode> nodeStack = new java.util.Stack<>();
    
    private java.util.Stack<TreeNode> stack1 = new java.util.Stack<>();
    private java.util.Stack<TreeNode> stack2 = new java.util.Stack<>();
    
    private java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public BSTIterator(TreeNode root) {
        // for INORDER and PRE-ORDER traversal
        nodeStack.push(root);
        currentNode = root;
    
        // for POST-ORDER traversal
        // The nodes are already processed in this constructor for POST-ORDER traversal
        stack1.push(root);
        while(!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if(node.left != null) stack1.push(node.left);
            if(node.right != null) stack1.push(node.right);
        }
        
        // for BFS - LEVEL-ORDER traversal
        queue.offer(root);
    }
    

    //IN-ORDER
    @Override
    public int next() {
        while(currentNode != null && currentNode.left != null) {
            nodeStack.push(currentNode.left);
            currentNode = currentNode.left;
        }
        
        TreeNode nodeValue = nodeStack.pop();
        
        if(nodeValue.right != null) {
            nodeStack.push(nodeValue.right);
            currentNode = nodeValue.right;
        }
        
        return nodeValue.val;
    }
    
    @Override
    public boolean hasNext() {
        return currentNode != null && !nodeStack.isEmpty();
    }

    
    
    // PRE-ORDER
    @Override
    public int next() {
        TreeNode node = new TreeNode();
        if(!nodeStack.isEmpty()) {
            node = nodeStack.pop();
            if(node.right != null)
                nodeStack.push(node.right);
            if(node.left != null)
                nodeStack.push(node.left);
        }
        return node.val;
    }
    
    @Override
    public boolean hasNext() {
        return !nodeStack.isEmpty();
    }
   

    
    //POST-ORDER
    @Override
    public int next() {
        return stack2.pop().val;
    }
    
    @Override
    public boolean hasNext() {
        return !stack2.isEmpty();            
    }
    
    
    //BFS- LEVEL ORDER TRAVERSAL
    @Override
    public int next() {
        TreeNode node = new TreeNode();
        if(!queue.isEmpty()) {
            node = queue.poll();
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return node.val;
    }
    
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

 }
