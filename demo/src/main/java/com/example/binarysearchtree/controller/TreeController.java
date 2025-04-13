package com.example.binarysearchtree.controller;

import com.example.binarysearchtree.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "index";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, Model model) {
        String treeJson = treeService.createTree(numbers);
        model.addAttribute("tree", treeJson);
        return "result";
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        model.addAttribute("trees", treeService.getPreviousTrees());
        return "previous";
    }
}
