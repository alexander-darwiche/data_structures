package applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// our - classes
import datastructures.Node;

// reads input from a csv file
public class CCC_Driver {
    public int[] readFromCSV(String filePath) {
        List<Integer> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    int value = Integer.parseInt(parts[1].trim());
                    values.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {

        CCC_BinaryTree tree = new CCC_BinaryTree();

        Node root;

        int[] values = {50, 30, 20, 40, 70, 60, 80};
        System.out.println("\nCreating Binary Search Tree using a Stack (inorder):");
        boolean doInorder = true;
        root = tree.createBSTFromRandomOrderStack(values, doInorder);
        tree.prettyPrint(root);
        System.out.println("\nCreating Binary Search Tree using a Stack (single while loop):");
        root = tree.createBSTFromRandomOrderStack(values, false);
        tree.prettyPrint(root);
        //          root = tree.createBSTFromRandomOrderRecursive(values);
        //          root = tree.createBSTFromRandomOrderIteratively(values); // not stack

        System.out.print("\nIn-order traversal: ");
        int[] inOrder_new_value = tree.inOrderTraversal(root);
        System.out.println("\nIn-order traversal (stack): " + Arrays.toString(inOrder_new_value));

        System.out.println("\nBegin Quiz:");
        // int[] postOrder = tree.postOrderTraversalSingleStack(root);
        System.out.println("\n___");

        // System.out.println("\nPost-order traversal: " + Arrays.toString(postOrder));

        // Creating a number array from a csv input file
        String filePath = "input_BST_v0.csv";
        //format rows: <rank>, <integer>
        CCC_Driver driver = new CCC_Driver(); // <rank> is NOT used.
        int[] new_values = driver.readFromCSV(filePath);
        // System.out.println("\nInteger array generated of CSV file: " + Arrays.toString(values));
    }
}
