package com.example.binarysearchtree.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TreeRepository {

    private final List<String> trees = new ArrayList<>();

    public void save(String numbers, String treeJson) {
        trees.add("Input: " + numbers + ", Tree: " + treeJson);
    }

    public List<String> findAll() {
        return trees;
    }
}
