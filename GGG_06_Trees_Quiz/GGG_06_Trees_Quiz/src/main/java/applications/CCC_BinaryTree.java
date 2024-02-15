package applications;

import datastructures.Node;
import graphics.TreePrinter;

import java.util.Arrays;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CCC_BinaryTree {
    private boolean verbose = false;
    private final VerbosePrinter printer;

    // Constructor
    public CCC_BinaryTree() {
        printer = new VerbosePrinter();
    }


    // Utility Routines ---
    public class VerbosePrinter {
        // Inner class for verbose printing
        public void beVerbose(String format, Object... args) {
            if (verbose) {
                System.out.printf(format, args);
            }
        }
    }
    // Method to set verbosity
    public void setVerbose(boolean value) {
        verbose = value;
    }


    public void beVerbose(String format, Object... args) {
        if (verbose) {
            System.out.printf(format, args);
        }
    }

    // print an ASCII graphic version of the tree
    public void prettyPrint(Node root) {
        TreePrinter.printNode(root);
    }


    // *** --------------------------------------------------------------------
    // Create: A Binary Search Tree (BST) using a Stack
    //
    // This method uses a nested while loop structure.
    //  * The inner loop is used to navigate down the tree to find the correct insertion point.
    //  * The outer loop ensures that the process continues until the new node is inserted.
    //
    // Method relies on the fact that when the inner loop exits,
    //  current will be at a position where the new node should be inserted.
    //
    // A nice readable approach that maybe intuitive for those who are comfortable with
    // nested loops and the logic of navigating a BST.
    //
    //  Time Complexity: O(h), where h is the height of the BST.
    //      The height of the BST determines the maximum number of nodes
    //      that need to be traversed.
    // Space Complexity: O(h), due to the use of a stack to simulate the recursive traversal.
    //      In the worst case, the stack may contain up to h nodes,
    //      where h is the height of the BST.
    private void addNodeToStackBST_Inorder(Node root, int value) {
        Node node = new Node(value);
        Node current = root;

        Stack<Node> stack = new Stack<>();

        while (current != null || !stack.isEmpty()) {
            // find location in tree
            while (current != null) {
                stack.push(current);
                if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            current = stack.pop();

            // Inserted node into tree
            if (value < current.value) {
                // we got to here by discovering left is null
                if (current.left == null) {
                    current.left = node;
                    break;
                } else { System.out.println("Error: should not get here... "); }
            } else {
                // we got to here by discovering right is null
                if (current.right == null) {
                    current.right = node;
                    break;
                } else { System.out.println("Error: should not get here... "); }
            }
        }
    }



    // *** --------------------------------------------------------------------
    // Create: A Binary Search Tree (BST) using a Stack
    //
    // Another stack method that a SINGLE while loop with a boolean flag isFound to
    //  indicate when the correct insertion point has been found.
    // It separates the concerns of navigating down the tree (using stack.pop())
    //  & checking for the insertion point into different parts of the loop.
    //
    // This approach has a LINEAR flow in logic, as it
    //  avoids nested loops and uses a flag to clearly indicate when the insertion is complete.
    // different way of using a stack but still creates the 'same' BST tree
    //
    //  Time Complexity: O(h), where h is the height of the BST.
    //      The height of the BST determines the maximum number of nodes
    //      that need to be traversed.
    // Space Complexity: O(h), due to the use of a stack to simulate the recursive traversal.
    //      In the worst case, the stack may contain up to h nodes,
    //      where h is the height of the BST.
    private Node addNodeToStackBST(Node root, int value) {
        Node node = new Node(value);
        Node popped_node = null;
        Node parent = null;
        boolean isFound = false;
        Stack<Node> stack = new Stack<>();

        // root determines the direction in
        //      the tree where to insert our node
        stack.push(root); // seed the stack.
        while( !isFound && !stack.isEmpty() ) {
            popped_node = stack.pop();

            if( node.value < popped_node.value ) {
                //  IF leftmost then this is the place
                if (popped_node.left == null) {
                    // found place
                    popped_node.left = node;
                    isFound = true;       // or -- break;
                } else { // if not then continue looking
                    stack.push(popped_node.left);
                }
            }
            else {
                    //  this node needs to be placed right
                    if( popped_node.right == null ) {
                        popped_node.right = node;
                        isFound = true; // or -- break;
                    }
                    else {
                        stack.push(popped_node.right);
                    }
                }
            }
        return root;
    }

    // *** --------------------------------------------------------------------
    // Builds a BST using a stack using either of the approaches above:
    //  nested or
    //  non nested while loop
    public Node createBSTFromRandomOrderStack(int[] values, boolean do_inorder ) {
        if (values == null || values.length == 0) {
            return null;
        }

        // create a BST By adding  nodes iteratively from a list of values
        Node root = new Node(values[0]);
        for (int i = 1; i < values.length; i++) {
            if( do_inorder ) { addNodeToStackBST_Inorder(root, values[i]);  }
            else              { addNodeToStackBST(root, values[i]); }
        }
        return root;
    }

    // *** --------------------------------------------------------------------
    // Skip a Stack! Builds a BST tree recursively -- this is more standard
    private Node insertIntoBST( Node root, int value ) {
        if (root == null) {
            return new Node( value );
        }

        if (value < root.value) {
            // insert smaller node on the left
            root.left = insertIntoBST(root.left, value);
        } else {
            // insert a larger node on the right
            root.right = insertIntoBST(root.right, value);
        }
        return root;
    }

    public Node createBSTFromRandomOrderRecursive(int[] values) {
        Node root = null;
        for (int value : values) {
            root = insertIntoBST(root, value);
        }
        return root;
    }

    // *** --------------------------------------------------------------------
    // Builds the BST iteratively using a simple while loop (for the insert)
    //  avoid the recursion - and is pretty and clean.
    public Node createBSTFromRandomOrderIteratively(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        Node root = new Node(values[0]);
        for (int i = 1; i < values.length; i++) {
            addNodeToBST(root, values[i]);
        }
        return root;
    }

    private void addNodeToBST(Node root, int value) {
        Node newNode = new Node(value);
        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (value < current.value) {
                // go left while we can
                current = current.left;
            } else {
                // go right if we must
                current = current.right;
            }
        }

        assert parent != null; // Error Condition here.
        if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // *** --------------------------------------------------------------------
    // In-order TRAVERSAL of a tree using a STACK.
    //
    // Traversal order: left subtree, visit the node, right subtree.
    // The process involves:
    //      1. Traversing to the leftmost node,
    //      2. Visiting the node,
    //      3. Traversing the right subtree.
    //
    // Note: In an in-order traversal of a binary search tree (BST),
    //       the nodes are visited in ascending order.

    public int[] inOrderTraversal(Node root) {
        // left
        // visit
        // right

        List<Integer> return_array = new ArrayList<>();

        if (root == null)  {
            // return;
            return return_array.stream().mapToInt(i -> i).toArray();
        }

        // --- start at the node 'root'
        Stack<Node> stack = new Stack<>();
        Node current = root;

        //  Goal: repeatedly find the smallest node in the tree
        //      push nodes onto stack until we get to the 'left most node'
        while (current != null || !stack.isEmpty()) {
            // **** LEFT
            while (current != null) {
                // left, left, left
                printer.beVerbose( "\n\tPushing: current.value: " + current.value );
                stack.push(current);
                current = current.left;
            }

            // Leftmost is the 'smallest' if a 'BST' Tree
            // visit it.
            if(current == null) {
                // current.left of what was put on stack 'last'
                printer.beVerbose( "\n\tCheck: current: "  + null );
            }

            // **** VISIT
            current = stack.pop(); // The stack non-empty current is not null
            printer.beVerbose( "\n\tPopping: popped.value: "  + current.value );
            // 'process' we could just print it out. as a processing statement
            return_array.add(current.value);

            // **** RIGHT
            if(current.right == null) {
                printer.beVerbose( "\n\tCheck: current.right: "  + null );
            }
            else {
                printer.beVerbose( "\n\tCheck: current.right: "  + current.value );
            }

            current = current.right;

            // Notice the OR in the while loop
        }
        return  return_array.stream().mapToInt(i -> i).toArray();
    }

    // Recursive in-order traversal
    public void inOrderTraversalRecursive(Node root) {
        if (root == null) return;
        inOrderTraversalRecursive(root.left);
        System.out.print(root.value + " ");
        inOrderTraversalRecursive(root.right);
    }


    // *** --------------------------------------------------------------------
    // Traverses a tree (pre-order): Using a Stack
    //
    // Traversal order: visit the node, left subtree, right subtree.
    //      visit the node,
    //      then traverse the left subtree,
    //      then the right subtree.
    //
    //     The right child is pushed onto the stack first
    //      so that the left child is processed first.

    public void preOrderTraversal(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop(); // visit
            System.out.print(current.value + " ");

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }


    // Recursive pre-order traversal
    public void preOrderTraversalRecursive(Node root) {
        if (root == null) return;

        System.out.print(root.value + " ");
        preOrderTraversalRecursive(root.left);
        preOrderTraversalRecursive(root.right);
    }


    // *** --------------------------------------------------------------------
    // Traverses a tree (POST-order): Using a Stack
    //
    // Traversal order: visit the node, left subtree, right subtree.
    //      then traverse the left subtree,
    //      then the right subtree.
    //      visit the node,

    // *** --------------------------------------------------------------------
// Traverses a tree (POST-order): Using a Stack
    public int[] postOrderTraversalSingleStack(Node root) {
        if (root == null) {
            // Task 1: Handle the case where the tree is empty
            return new int[0];
        }

        Stack<Node> stack = new Stack<>();
        List<Integer> resultList = new ArrayList<>();
        Node current = root;
        Node lastVisited = null;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                // Task 2: Push the current node onto the stack and advance to its left child
                break; // remove this break
            }

            // Task 3: Check if the stack is not empty before proceeding
            if (!stack.isEmpty()) {
                Node node = stack.peek(); // Look at the top node without popping it


                    // Task 4: Decide whether to move to the right child or to process the current node
                // if (node.right != null && node.right != lastVisited) {
                if (false) { // Placeholder condition, students need to replace 'false' with the correct condition

                    // Task 5: Move to the right child if condition is met
                    current = node.right;
                } else {
                    // Task 6: Pop the node from the stack and add its value to resultList
                    stack.pop(); // This line should always be present to avoid compilation error
                    resultList.add(node.value); // Example placeholder, adjust as needed for the quiz
                    lastVisited = node; // Mark this node as last visited
                    // Note: No need for an explicit 'current = null;' here as it's implied in the loop's logic
                }

            }
        }

        // Task 7: Convert the resultList to an array and return it
        return resultList.stream().mapToInt(i -> i).toArray();
    }











    public static void main(String[] args) {
        CCC_BinaryTree tree = new CCC_BinaryTree();
        tree.setVerbose(true);

        // Build a tree without concern of ordering children
        //  is not a binary search tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Tree Pretty Print:");
        tree.prettyPrint(root);

        // see Driver for more methods in action ---
    }
}
