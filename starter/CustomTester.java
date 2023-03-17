/*
 * Name: David Kim
 * Email: dck003@ucsd.edu
 * PID: A17394361
 * Sources used: N/A
 * 
 * This file contains a custom tester class for PA8's MyBST.
 */
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class CustomTester
{
    //=================== SETUP ========================================
    
    /**
     * Loads a BST by BST rules without using insert.
     * @param keys array of keys to load nodes
     * @param values array of values to load nodes
     * @return BST loaded with keys and values given
     */
    public static MyBST<Integer, String>
        loadBST(Integer[] keys, String[] values)
    {
        MyBST<Integer, String> testBST = new MyBST<>();
        testBST.root = new MyBST.MyBSTNode<>(keys[0], values[0], null);
        testBST.size = 1;
    
        for (int i = 1; i < keys.length; i++)
        {
            MyBST.MyBSTNode<Integer, String> currNode = testBST.root;
            MyBST.MyBSTNode<Integer, String> parentNode = null;
            Integer key = keys[i];
            String value = values[i];
    
            while (currNode != null)
            {
                int comparisonVal = key.compareTo(currNode.getKey());
                if (comparisonVal == 0)
                {
                    currNode.setValue(value);
                    break;
                }
                else if (comparisonVal < 0)
                {
                    parentNode = currNode;
                    currNode = currNode.getLeft();
                }
                else
                {
                    parentNode = currNode;
                    currNode = currNode.getRight();
                }
            }
    
            if (currNode == null)
            {
                MyBST.MyBSTNode<Integer, String> newNode =
                    new MyBST.MyBSTNode<>(key, value,null);
                newNode.setParent(parentNode);
    
                if (key.compareTo(parentNode.getKey()) < 0)
                {
                    parentNode.setLeft(newNode);
                }
                else
                {
                    parentNode.setRight(newNode);
                }
    
                testBST.size++;
            }
        }
        return testBST;
    }
    
    /**
     * Testing the loadBST() method
     */
    @Test
    public void testInsertionLoadBST()
    {
        Integer[] keys = {5, 3, 7, 2, 4, 6, 8};
        String[] values = 
            {"five", "three", "seven", "two", "four", "six", "eight"};
        
        MyBST<Integer, String> loadedBST = loadBST(keys, values);
        MyBST<Integer, String> realtestBST = new MyBST<>();
        for (int i = 0; i < keys.length; i++)
        {
            assertNull(realtestBST.insert(keys[i], values[i]));
        }
        assertEquals(realtestBST.root, loadedBST.root);
        assertEquals(realtestBST.size(), loadedBST.size());

        compareBST(loadedBST, realtestBST);
    }

    /**
     * Compares 2 MyBSTs with assertions
     * @param expectedBST the expected "correct" result
     * @param actualBST your result
     */
    public void compareBST(MyBST<Integer, String> expectedBST, 
    MyBST<Integer, String> actualBST)
    {
        MyBST.MyBSTNode<Integer, String> expectedNode = expectedBST.root;
        MyBST.MyBSTNode<Integer, String> actualNode = actualBST.root;
        while (expectedNode != null && actualNode != null)
        {
            // assertEquals(expectedNode.getKey(), actualNode.getKey());
            // assertEquals(expectedNode.getValue(), actualNode.getValue());
            assertTrue(null, expectedNode.equals(actualNode));
            if (expectedNode.getLeft() == null)
            {
                assertNull(actualNode.getLeft());
            }
            else
            {
                assertNotNull(actualNode.getLeft());
            }
            if (expectedNode.getRight() == null)
            {
                assertNull(actualNode.getRight());
            }
            else
            {
                assertNotNull(actualNode.getRight());
            }
            expectedNode = expectedNode.getLeft();
            actualNode = actualNode.getLeft();
        }
    }

    // public void compareBST(MyBST<Integer, String> expectedBST, 
    //     MyBST<Integer, String> actualBST)
    // {
    //     MyBST.MyBSTNode<Integer, String> expectedNode = expectedBST.root;
    //     MyBST.MyBSTNode<Integer, String> actualNode = actualBST.root;
    //     while (expectedNode != null && actualNode != null)
    //     {
    //         assertEquals(expectedNode.getKey(), actualNode.getKey());
    //         assertEquals(expectedNode.getValue(), actualNode.getValue());
    //         assertEquals(expectedNode.getLeft() != null,
    //             actualNode.getLeft() != null);
    //         assertEquals(expectedNode.getRight() != null,
    //             actualNode.getRight() != null);
    //         expectedNode = expectedNode.getLeft();
    //         actualNode = actualNode.getLeft();
    //     }
    // }
    //better way found



    //==================== Initialize BST test ============================

    /**
     * Tests to make sure initial conditions of a BST are correct
     */
    @Test
    public void initialBST()
    {
        MyBST<Integer, String> testBST = new MyBST<>();
        assertEquals(0, testBST.size);
        assertNull(testBST.root);

    }

    
    //====================== INSERT TESTS =============================

    /** Test for inserting a null key, should throw NullPointerException */
    @Test
    public void insertNullKey()
    {
        MyBST<Integer, String> testBST = new MyBST<>();


        assertThrows(NullPointerException.class,
            ()->{testBST.insert(null, "Five");});
        assertNull(testBST.root);
        assertEquals(0, testBST.size);

        //Check null insert after BST has a node
        testBST.root=new MyBST.MyBSTNode<Integer,String>(5, "Five", null);
        testBST.size++;

        assertThrows(NullPointerException.class,
            ()->{testBST.insert(null, "Six");});
        assertEquals(new MyBST.MyBSTNode<Integer,String>(5, "Five", null),
            testBST.root);
        assertEquals(1, testBST.size);
    }

    @Test
    public void insertOneNodeIntoEmptyBST()
    {
        MyBST<Integer, String> intStringBST = new MyBST<>();
        assertEquals(0, intStringBST.size());
        assertNull(intStringBST.root);

        assertNull(intStringBST.insert(5, "Five"));

        assertEquals(1, intStringBST.size());
        assertEquals(Integer.valueOf(5), intStringBST.root.getKey());
        assertEquals("Five", intStringBST.root.getValue());
        assertNull(intStringBST.root.getLeft());
        assertNull(intStringBST.root.getRight());
    
    }

    @Test
    public void insertMultipleNodesIntoEmptyBST()
    {
        MyBST<Integer, String> intStringBST = new MyBST<>();
        assertEquals(0, intStringBST.size());
        assertNull(intStringBST.root);

        assertNull(intStringBST.insert(5, "Five"));

        assertEquals(1, intStringBST.size());
        assertEquals(Integer.valueOf(5), intStringBST.root.getKey());
        assertEquals("Five", intStringBST.root.getValue());
        assertNull(intStringBST.root.getLeft());
        assertNull(intStringBST.root.getRight());
    
    }

    @Test
    public void testInsertMultipleNodes() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(5, "five");
        bst.insert(3, "three");
        bst.insert(7, "seven");
        bst.insert(1, "one");
        bst.insert(4, "four");
        bst.insert(6, "six");
        bst.insert(8, "eight");
        assertEquals("five", bst.root.getValue());
        assertEquals("three", bst.root.getLeft().getValue());
        assertEquals("seven", bst.root.getRight().getValue());
        assertEquals("one", bst.root.getLeft().getLeft().getValue());
        assertEquals("four", bst.root.getLeft().getRight().getValue());
        assertEquals("six", bst.root.getRight().getLeft().getValue());
        assertEquals("eight",bst.root.getRight().getRight().getValue());
    }

    @Test
    public void testInsert() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(50, "A");
        bst.insert(30, "B");
        bst.insert(20, "C");
        bst.insert(40, "D");
        bst.insert(70, "E");
        bst.insert(60, "F");
        bst.insert(80, "G");
        assertEquals(bst.root.getKey(), Integer.valueOf(50));
        assertEquals(bst.root.getValue(), "A");
        assertEquals(bst.root.getLeft().getKey(), Integer.valueOf(30));
        assertEquals(bst.root.getLeft().getValue(), "B");
        assertEquals(bst.root.getLeft().getLeft().getKey(),
            Integer.valueOf(20));
        assertEquals(bst.root.getLeft().getLeft().getValue(), "C");
        assertEquals(bst.root.getLeft().getRight().getKey(),
            Integer.valueOf(40));
        assertEquals(bst.root.getLeft().getRight().getValue(), "D");
        assertEquals(bst.root.getRight().getKey(), Integer.valueOf(70));
        assertEquals(bst.root.getRight().getValue(), "E");
        assertEquals(bst.root.getRight().getLeft().getKey(),
            Integer.valueOf(60));
        assertEquals(bst.root.getRight().getLeft().getValue(), "F");
        assertEquals(bst.root.getRight().getRight().getKey(),
            Integer.valueOf(80));
        assertEquals(bst.root.getRight().getRight().getValue(), "G");
    }

    /**
     * Tests if BST can handle duplicate key insertion (40 inserted twice)
     */
    @Test
    public void testInsertDuplicateKey()
    {
        MyBST<Integer, String> bst = new MyBST<>();
        assertEquals(bst.size(), 0);
        assertNull(bst.root);

        bst.insert(50, "A");
        assertEquals(bst.size(), 1);
        assertEquals(bst.root.getKey(), Integer.valueOf(50));
        assertEquals(bst.root.getValue(), "A");

        bst.insert(30, "B");
        assertEquals(bst.size(), 2);
        assertEquals(bst.root.getLeft().getKey(), Integer.valueOf(30));
        assertEquals(bst.root.getLeft().getValue(), "B");

        bst.insert(20, "C");
        assertEquals(bst.size(), 3);
        assertEquals(bst.root.getLeft().getLeft().getKey(),
        Integer.valueOf(20));
        assertEquals(bst.root.getLeft().getLeft().getValue(), "C");

        bst.insert(40, "D");
        assertEquals(bst.size(), 4);
        assertEquals(bst.root.getLeft().getRight().getKey(),
        Integer.valueOf(40));
        assertEquals(bst.root.getLeft().getRight().getValue(), "D");

        bst.insert(70, "E");
        assertEquals(bst.size(), 5);
        assertEquals(bst.root.getRight().getKey(), Integer.valueOf(70));
        assertEquals(bst.root.getRight().getValue(), "E");

        bst.insert(60, "F");
        assertEquals(bst.size(), 6);
        assertEquals(bst.root.getRight().getLeft().getKey(),
        Integer.valueOf(60));
        assertEquals(bst.root.getRight().getLeft().getValue(), "F");

        bst.insert(80, "G");
        assertEquals(bst.size(), 7);
        assertEquals(bst.root.getRight().getRight().getKey(),
        Integer.valueOf(80));
        assertEquals(bst.root.getRight().getRight().getValue(), "G");

        assertEquals("D", bst.insert(40, "H"));
        assertEquals(bst.size(), 7);
        assertEquals(bst.root.getLeft().getRight().getKey(),
        Integer.valueOf(40));
        assertEquals(bst.root.getLeft().getRight().getValue(), "H");


    }



    //======================== SEARCH TESTS ===============================
    
    /**
     * Testing to search MyBST for a null key. From the readme:
     * "This means that search(null) should always return null."
     */

    @Test
    public void searchNullKey()
    {
        MyBST<Integer, String> testBST = new MyBST<>();

        assertEquals(0, testBST.size);
        assertNull(testBST.search(null));
        assertEquals(testBST.root, null);
        

        testBST.root = new MyBST.MyBSTNode<>(5, "Five", null);
        testBST.size++;

        assertEquals(1, testBST.size);
        assertNull(testBST.search(null));
        assertEquals(new MyBST.MyBSTNode<>(5, "Five", null), testBST.root);
    }

    @Test
    public void searchKeySingleNode()
    {
        Integer[] keys = {6};
        String[] values = {"Six"};
        MyBST<Integer, String> loadedBST = loadBST(keys, values);
        MyBST<Integer, String> testBST = new MyBST<>();
        testBST.root = new MyBST.MyBSTNode<Integer,String>(6, "Six", null);
        testBST.size++;

        assertEquals(values[0], testBST.search(6));
        assertEquals(1, testBST.size);//Size unchanged
        //Root should be unchanged
        assertEquals(new MyBST.MyBSTNode<>(6, "Six", null), testBST.root);
        
        assertEquals(values[0], loadedBST.search(6));
        assertEquals(1, loadedBST.size);//Size unchanged
        //Root should be unchanged
        assertEquals(new MyBST.MyBSTNode<>(6, "Six", null), loadedBST.root);
    }

    



    //======================== REMOVE TESTS ===============================

    //REMOVE() NEEDS THE MOST TESTING !!!!!!!!!!!!!!!!!

    /**
     * Attempts to remove a null key from the BST. By the readme, "remove(null)
     * should return null and !!!!!SHOULD NOT NOT REMOVE ANY NODES!!!!;
     */

    @Test
    public void removeNullKeyEmptyBST()
    {
        MyBST<Integer, String> testBST = new MyBST<>();

        assertNull(testBST.remove(null));
        assertEquals(0, testBST.size);
        assertNull(testBST.root);
        //Check that all removal doesnt change the size or root.

        //Check remove(null) after BST has a node
        testBST.root=new MyBST.MyBSTNode<Integer,String>(5, "Five", null);
        testBST.size++;

        assertNull(testBST.remove(null));
        assertEquals(1, testBST.size);
        assertEquals(new MyBST.MyBSTNode<Integer,String>(5, "Five", null),
            testBST.root);

    }

    /**
     * Remove a null key from an BST with one node
     */
    @Test
    public void removeNullKeyNonEmptyBST()
    {
        MyBST<Integer, String> testBST = new MyBST<>();

        testBST.root = new MyBST.MyBSTNode<Integer,String>(5, "five", null);
        testBST.size++;

        assertNull(testBST.remove(null));

        assertEquals(1, testBST.size);
        assertEquals(new MyBST.MyBSTNode<Integer,String>(5, "five", null),
            testBST.root);
        
    }

    //============= TEST SUCCESSOR() ====================================//
    
    /**
     * Successor() on a node without a right subtree
     */
    @Test
    public void testSuccessorWithNoRightSubtree()
    {
        MyBST.MyBSTNode<Integer, String> root=
            new MyBST.MyBSTNode<>(5, "A", null);
        MyBST.MyBSTNode<Integer, String> node1=
            new MyBST.MyBSTNode<>(2, "B",root);
        MyBST.MyBSTNode<Integer, String> node2=
            new MyBST.MyBSTNode<>(8, "C",root);
        MyBST.MyBSTNode<Integer, String> node3=
            new MyBST.MyBSTNode<>(1, "D",node1);
        MyBST.MyBSTNode<Integer, String> node4=
            new MyBST.MyBSTNode<>(3, "E",node1);
        MyBST.MyBSTNode<Integer, String> node5=
            new MyBST.MyBSTNode<>(6, "F",node2);
        MyBST.MyBSTNode<Integer, String> node6=
            new MyBST.MyBSTNode<>(9, "G",node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        assertEquals(node1.successor(), node4);
    }
    
    /**
     * Successor() on a node with right subtree, traverses left after.
     */
    @Test
    public void testSuccessorWithRightSubtree()
    {
        MyBST.MyBSTNode<Integer, String> root =
            new MyBST.MyBSTNode<>(5, "Five", null);
        MyBST.MyBSTNode<Integer, String> node1 =
            new MyBST.MyBSTNode<>(2, "Two", root);
        MyBST.MyBSTNode<Integer, String> node2 =
            new MyBST.MyBSTNode<>(8, "Eight", root);
        MyBST.MyBSTNode<Integer, String> node3 =
            new MyBST.MyBSTNode<>(1, "One", node1);
        MyBST.MyBSTNode<Integer, String> node4 =
            new MyBST.MyBSTNode<>(3, "Three", node1);
        MyBST.MyBSTNode<Integer, String> node5 =
            new MyBST.MyBSTNode<>(6, "Six", node2);
        MyBST.MyBSTNode<Integer, String> node6 =
            new MyBST.MyBSTNode<>(9, "Nine", node2);
        MyBST.MyBSTNode<Integer, String> node7 =
            new MyBST.MyBSTNode<>(7, "Seven", node5);
        root.setRight(node2);
        root.setLeft(node1);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node5.setRight(node7);
        assertEquals(node2.successor(), node6);
        assertEquals(node1.successor(), node4);
        assertEquals(node5.successor(), node7);
        assertEquals(root.successor(), node5);
    }
    
    @Test
    public void testSuccessorWithNoSuccessor()
    {
        MyBST.MyBSTNode<Integer, String> root =
            new MyBST.MyBSTNode<>(5, "A", null);
        MyBST.MyBSTNode<Integer, String> node1 =
            new MyBST.MyBSTNode<>(2, "B", root);
        MyBST.MyBSTNode<Integer, String> node2 =
            new MyBST.MyBSTNode<>(8, "C", root);
        MyBST.MyBSTNode<Integer, String> node3 =
            new MyBST.MyBSTNode<>(1, "D", node1);
        MyBST.MyBSTNode<Integer, String> node4 =
            new MyBST.MyBSTNode<>(3, "E", node1);
        MyBST.MyBSTNode<Integer, String> node5 =
            new MyBST.MyBSTNode<>(6, "F", node2);
        MyBST.MyBSTNode<Integer, String> node6 =
            new MyBST.MyBSTNode<>(9, "G", node2);
        MyBST.MyBSTNode<Integer, String> node7 =
            new MyBST.MyBSTNode<>(7, "H", node5);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node5.setRight(node7);
        assertNull(node6.successor());
    }

    @Test
    public void testSuccessorWithRightSubtreeAndLeftmostNodeWithNoLeftChild()
    {
        MyBST.MyBSTNode<Integer, String> root =
            new MyBST.MyBSTNode<>(5, "Five", null);
        MyBST.MyBSTNode<Integer, String> node2 =
            new MyBST.MyBSTNode<>(2, "Two", root);
        MyBST.MyBSTNode<Integer, String> node4 =
            new MyBST.MyBSTNode<>(4, "Four", node2);
        MyBST.MyBSTNode<Integer, String> node8 =
            new MyBST.MyBSTNode<>(8, "Eight", root);

        root.setLeft(node2);
        root.setRight(node8);
        node2.setRight(node4);

        // Check that the successor of node2 is node4
        assertEquals(node4, node2.successor());

        // Set node4's right child to null to make it the leftmost node in the right subtree with no left child
        node4.setRight(null);

        // Check that the successor of node2 is node8
        assertEquals(node8, node2.successor());
    }

    @Test
    public void testSuccessorLargeTree()
    {
        Integer[] keys = {5, 3, 7, 2, 4, 6, 8};
        String[] values = 
            {"five", "three", "seven", "two", "four", "six", "eight"};
        MyBST<Integer, String> loadedBST = loadBST(keys, values);
        //Get minimum
        MyBST.MyBSTNode<Integer,String> minNode =
            loadedBST.root.getLeft().getLeft();
        assertEquals(minNode,
            new MyBST.MyBSTNode<>(2, "two", loadedBST.root.getLeft()));

        for(int i = 2; i < 9; i++)
        {
            assertEquals(Integer.valueOf(i), minNode.getKey());
            minNode = minNode.successor();
        }
        assertNull(minNode);
    }

    /**
     * Test successor() on a right linear tree
     */
    @Test
    public void testSuccessorLinearTree()
    {
        Integer[] keys = {1, 2, 3, 4, 5, 6, 7};
        String[] values = 
            {"one", "two", "three", "four", "five", "six", "seven"};
        MyBST<Integer, String> loadedBST = loadBST(keys, values);

        MyBST.MyBSTNode<Integer,String> currNode = loadedBST.root;
        
        for(int i = 1; i < 8; i++)
        {
            assertEquals(Integer.valueOf(i), currNode.getKey());
            assertEquals(values[i-1], currNode.getValue());
            assertEquals(currNode.getRight(), currNode.successor());
            currNode = currNode.successor();
        }
    }

    /**
     * Test successor where there is no larger node/root.
     */
    @Test
    public void testSuccessorNoLargerNode()
    {
        MyBST<Integer, String> testBST = new MyBST<>();
        testBST.root = new MyBST.MyBSTNode<Integer,String>(5, "Five", null);
        testBST.size++;

        assertNull(testBST.root.successor());
        assertEquals(1, testBST.size);

        testBST.root.setRight(
            new MyBST.MyBSTNode<Integer,String>(6, "Six", testBST.root));
        testBST.size++;

        assertNull(testBST.root.getRight().successor());
        assertEquals(2, testBST.size);
    }
}
