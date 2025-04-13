package com.example;

import com.example.binarysearchtree.controller.TreeController;
import com.example.binarysearchtree.model.BinarySearchTree;
import com.example.binarysearchtree.model.TreeNode;
import com.example.binarysearchtree.repository.TreeRepository;
import com.example.binarysearchtree.service.TreeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BinarySearchTreeApplicationTests {

    // Mock dependencies
    @Mock
    private Model model;

    @Mock
    private TreeRepository treeRepository;

    @Mock
    private TreeService treeService;

    // Inject mocks into the controller
    @InjectMocks
    private TreeController treeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Tests for TreeController
    @Test
    void testEnterNumbers() {
        String viewName = treeController.enterNumbers();
        assertEquals("index", viewName);
    }

    @Test
    void testProcessNumbers() {
        String numbers = "5,3,8";
        String treeJson = "{ \"root\": { \"value\": 5 } }";

        when(treeService.createTree(numbers)).thenReturn(treeJson);

        String viewName = treeController.processNumbers(numbers, model);

        assertEquals("result", viewName);
        verify(model, times(1)).addAttribute("tree", treeJson);
    }

    @Test
    void testPreviousTrees() {
        when(treeService.getPreviousTrees()).thenReturn(List.of("Tree1", "Tree2"));

        String viewName = treeController.previousTrees(model);

        assertEquals("previous", viewName);
        verify(model, times(1)).addAttribute("trees", List.of("Tree1", "Tree2"));
    }

    // Tests for TreeRepository
    @Test
    void testSaveAndFindAll() {
        TreeRepository treeRepository = new TreeRepository();

        String input1 = "5,3,8";
        String treeJson1 = "{ \"root\": { \"value\": 5 } }";
        String input2 = "10,6,15";
        String treeJson2 = "{ \"root\": { \"value\": 10 } }";

        treeRepository.save(input1, treeJson1);
        treeRepository.save(input2, treeJson2);
        List<String> result = treeRepository.findAll();

        assertEquals(2, result.size());
        assertEquals("Input: 5,3,8, Tree: { \"root\": { \"value\": 5 } }", result.get(0));
        assertEquals("Input: 10,6,15, Tree: { \"root\": { \"value\": 10 } }", result.get(1));
    }

    // Tests for BinarySearchTree
    @Test
    void testBinarySearchTreeInsert() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        bst.insert(1);
        bst.insert(4);

        TreeNode root = bst.getRoot();
        assertEquals(5, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(8, root.getRight().getValue());
        assertEquals(1, root.getLeft().getLeft().getValue());
        assertEquals(4, root.getLeft().getRight().getValue());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
    }

    // Test for TreeService: Verify Balanced Tree Creation
    @Test
    void testCreateBalancedTree() {
        // Arrange
        String numbers = "5,3,8,1,4,7,10";
        String expectedJson = "{ " +
                "\"root\": { " +
                "\"value\": 5, " +
                "\"left\": { " +
                "\"value\": 3, " +
                "\"left\": { " +
                "\"value\": 1, " +
                "\"left\": null, " +
                "\"right\": null " +
                "}, " +
                "\"right\": { " +
                "\"value\": 4, " +
                "\"left\": null, " +
                "\"right\": null " +
                "} " +
                "}, " +
                "\"right\": { " +
                "\"value\": 8, " +
                "\"left\": { " +
                "\"value\": 7, " +
                "\"left\": null, " +
                "\"right\": null " +
                "}, " +
                "\"right\": { " +
                "\"value\": 10, " +
                "\"left\": null, " +
                "\"right\": null " +
                "} " +
                "} " +
                "} " +
                "}";

        when(treeService.createTree(numbers)).thenReturn(expectedJson);

        // Act
        String resultJson = treeService.createTree(numbers);

        // Assert
        assertNotNull(resultJson);
        assertEquals(expectedJson.trim(), resultJson.trim());
    }

    @Test
    void contextLoads() {
        // Default Spring Boot test to ensure the application context loads successfully
    }
}
