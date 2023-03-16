/*
 * Name: David Kim
 * Email: dck003@ucsd.edu
 * PID: A17394361
 * Sources used: Zybook instructions for percolation method
 * 
 * This file contains a custom tester class for PA8's MyBST.
 */
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class CustomTester {
    
    @Before
    public void generateRandomBST()
    {

    }

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
    
            while (currNode != null) {
                int comparisonVal = key.compareTo(currNode.getKey());
                if (comparisonVal == 0) {
                    currNode.setValue(value);
                    break;
                } else if (comparisonVal < 0) {
                    parentNode = currNode;
                    currNode = currNode.getLeft();
                } else {
                    parentNode = currNode;
                    currNode = currNode.getRight();
                }
            }
    
            if (currNode == null) {
                MyBST.MyBSTNode<Integer, String> newNode =
                    new MyBST.MyBSTNode<>(key, value,null);
                newNode.setParent(parentNode);
    
                if (key.compareTo(parentNode.getKey()) < 0) {
                    parentNode.setLeft(newNode);
                } else {
                    parentNode.setRight(newNode);
                }
    
                testBST.size++;
            }
        }
        return testBST;
    }
    
    @Test
    public void testInsertionLoadBST() {
        Integer[] keys = {5, 3, 7, 2, 4, 6, 8};
        String[] values = 
            {"five", "three", "seven", "two", "four", "six", "eight"};
        
        MyBST<Integer, String> loadedBST = loadBST(keys, values);
        MyBST<Integer, String> realtestBST = new MyBST<>();
        for (int i = 0; i < keys.length; i++) {
            assertNull(realtestBST.insert(keys[i], values[i]));
        }
        assertEquals(realtestBST.root, loadedBST.root);
        assertEquals(realtestBST.size(), loadedBST.size());

        compareBST(loadedBST, realtestBST);
    }


    
    public void compareBST(MyBST<Integer, String> expectedBST, 
    MyBST<Integer, String> actualBST)
    {
        MyBST.MyBSTNode<Integer, String> expectedNode = expectedBST.root;
        MyBST.MyBSTNode<Integer, String> actualNode = actualBST.root;
        while (expectedNode != null && actualNode != null)
        {
            assertEquals(expectedNode.getKey(), actualNode.getKey());
            assertEquals(expectedNode.getValue(), actualNode.getValue());
            if (expectedNode.getLeft() == null) {
                assertNull(actualNode.getLeft());
            } else {
                assertNotNull(actualNode.getLeft());
            }
            if (expectedNode.getRight() == null) {
                assertNull(actualNode.getRight());
            } else {
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



    //====================== INSERT TESTS =============================

    /** Test for inserting a null key, should throw NullPointerException */
    @Test
    public void insertNullKey()
    {
        MyBST<Integer, Integer> testBST = new MyBST<>();
        Integer testInteger = 5;
        assertThrows(NullPointerException.class,
            ()->{testBST.insert(null, testInteger);});
        //Check null insert after BST is loaded.
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

    @Test
    public void testInsertDuplicateKey() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(50, "A");
        bst.insert(30, "B");
        bst.insert(20, "C");
        bst.insert(40, "D");
        bst.insert(70, "E");
        bst.insert(60, "F");
        bst.insert(80, "G");
        bst.insert(40, "H");
        assertEquals(bst.root.getKey(), Integer.valueOf(50));
        assertEquals(bst.root.getValue(), "A");
        assertEquals(bst.root.getLeft().getKey(), Integer.valueOf(30));
        assertEquals(bst.root.getLeft().getValue(), "B");
        assertEquals(bst.root.getLeft().getLeft().getKey(),
            Integer.valueOf(20));
        assertEquals(bst.root.getLeft().getLeft().getValue(), "C");
        assertEquals(bst.root.getLeft().getRight().getKey(),
            Integer.valueOf(40));
        assertEquals(bst.root.getLeft().getRight().getValue(), "H");
        assertEquals(bst.root.getRight().getKey(), Integer.valueOf(70));
        assertEquals(bst.root.getRight().getValue(), "E");
        assertEquals(bst.root.getRight().getLeft().getKey(),
            Integer.valueOf(60));
        assertEquals(bst.root.getRight().getLeft().getValue(), "F");
        assertEquals(bst.root.getRight().getRight().getKey(),
            Integer.valueOf(80));
        assertEquals(bst.root.getRight().getRight().getValue(), "G");
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
        assertNull(testBST.search(null));

        testBST.insert(5, "Five");
        assertNull(testBST.search(null));

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
    }

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


}
