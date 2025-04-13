package com.example.binarysearchtree.service;

import com.example.binarysearchtree.model.TreeNode;
import com.example.binarysearchtree.repository.TreeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    /**
     * Creates a binary search tree from a comma-separated string of numbers,
     * serializes it to JSON, and saves it to the repository.
     *
     * @param numbers Comma-separated string of numbers.
     * @return Pretty-printed JSON representation of the binary search tree.
     */
    public String createTree(String numbers) {
        String[] numArray = numbers.split(",");
        TreeNode root = null;

        // Insert each number into the binary search tree
        for (String num : numArray) {
            root = insert(root, Integer.parseInt(num.trim()));
        }

        // Serialize the tree to JSON
        String treeJson = serializeTreeWithRoot(root);

        // Save the original input and serialized tree to the repository
        treeRepository.save(numbers, treeJson);

        // Pretty-print the JSON
        return prettyPrintJson(treeJson);
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
     * Inserts a value into the binary search tree.
     *
     * @param root  The root of the tree.
     * @param value The value to insert.
     * @return The updated root of the tree.
     */
    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.getValue()) {
            root.setLeft(insert(root.getLeft(), value));
        } else {
            root.setRight(insert(root.getRight(), value));
        }
        return root;
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
