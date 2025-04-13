package com.example.binarysearchtree.service;

import com.example.binarysearchtree.model.TreeNode;
import com.example.binarysearchtree.repository.TreeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    /**
     * Creates a balanced binary search tree from a comma-separated string of
     * numbers,
     * serializes it to JSON, and saves it to the repository.
     *
     * @param numbers Comma-separated string of numbers.
     * @return Pretty-printed JSON representation of the binary search tree.
     */
    public String createTree(String numbers) {
        // Parse and sort the input numbers
        String[] numArray = numbers.split(",");
        int[] sortedNumbers = Arrays.stream(numArray)
                .mapToInt(num -> Integer.parseInt(num.trim()))
                .sorted()
                .toArray();

        // Build a balanced binary search tree
        TreeNode root = buildBalancedTree(sortedNumbers, 0, sortedNumbers.length - 1);

        // Serialize the tree to JSON
        String treeJson = serializeTreeWithRoot(root);

        // Pretty-print the JSON before saving it
        String prettyTreeJson = prettyPrintJson(treeJson);
        treeRepository.save(numbers, prettyTreeJson);

        // Return the pretty-printed JSON
        return prettyTreeJson;
    }

    /**
     * Retrieves all previously processed binary search trees from the repository.
     *
     * @return List of JSON representations of binary search trees.
     */
    public List<String> getPreviousTrees() {
        return treeRepository.findAll();
    }

    /**
     * Builds a balanced binary search tree from a sorted array.
     *
     * @param sortedNumbers The sorted array of numbers.
     * @param start         The start index of the array.
     * @param end           The end index of the array.
     * @return The root of the balanced binary search tree.
     */
    private TreeNode buildBalancedTree(int[] sortedNumbers, int start, int end) {
        if (start > end) {
            return null;
        }

        // Find the middle element and make it the root
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(sortedNumbers[mid]);

        // Recursively build the left and right subtrees
        node.setLeft(buildBalancedTree(sortedNumbers, start, mid - 1));
        node.setRight(buildBalancedTree(sortedNumbers, mid + 1, end));

        return node;
    }

    /**
     * Serializes a binary search tree to a JSON string with a root object.
     *
     * @param root The root of the tree.
     * @return JSON representation of the tree with a root object.
     */
    private String serializeTreeWithRoot(TreeNode root) {
        if (root == null) {
            return "{ \"root\": null }";
        }
        return "{ \"root\": " + serializeTree(root) + " }";
    }

    /**
     * Serializes a binary search tree to a JSON string.
     *
     * @param root The root of the tree.
     * @return JSON representation of the tree.
     */
    private String serializeTree(TreeNode root) {
        if (root == null) {
            return "null";
        }
        return "{" +
                "\"value\": " + root.getValue() + "," +
                "\"left\": " + serializeTree(root.getLeft()) + "," +
                "\"right\": " + serializeTree(root.getRight()) +
                "}";
    }

    /**
     * Pretty-prints a JSON string.
     *
     * @param json The JSON string to format.
     * @return Pretty-printed JSON string.
     */
    private String prettyPrintJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = mapper.readValue(json, Object.class);
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            return writer.writeValueAsString(jsonObject);
        } catch (Exception e) {
            // Return the original JSON if pretty-printing fails
            return json;
        }
    }
}
