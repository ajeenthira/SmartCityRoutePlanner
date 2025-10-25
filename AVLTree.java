package smartcity;

// AVLTree.java
// AVL Tree by [Your Name]
// Graph implementation by [kokilaraj]
public class AVLTree {
    // Inner Node class
    private class Node {
        Location data;
        Node left, right;
        int height;
        
        Node(Location data) {
            this.data = data;
            this.height = 1;
        }
    }
    
    private Node root;
    
    // Get height of node
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
    
    // Get balance factor
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    
    // Right rotate
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    
    // Left rotate
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        return y;
    }
    
    // Insert location into AVL tree
    public void insert(Location data) {
        root = insert(root, data);
    }
    
    private Node insert(Node node, Location data) {
        if (node == null) {
            return new Node(data);
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node; // Duplicate locations not allowed
        }
        
        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        int balance = getBalance(node);
        
        // Left Left Case
        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    // Delete location from AVL tree
    public void delete(Location data) {
        root = delete(root, data);
    }
    
    private Node delete(Node root, Location data) {
        if (root == null) return root;
        
        if (data.compareTo(root.data) < 0) {
            root.left = delete(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = delete(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null || root.right == null) {
                Node temp = (root.left == null) ? root.right : root.left;
                
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                // Node with two children
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }
        
        if (root == null) return root;
        
        // Update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        
        int balance = getBalance(root);
        
        // Balancing cases
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    // Display all locations in sorted order
    public void inorder() {
        System.out.println("Locations in sorted order:");
        inorder(root);
        System.out.println();
    }
    
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println("  - " + node.data);
            inorder(node.right);
        }
    }

}

