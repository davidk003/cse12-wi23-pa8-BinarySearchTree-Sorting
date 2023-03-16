import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
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

    //====================== INSERT TESTS =================================

    @Test
    public void insertIntoEmptyBST()
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



    //======================== EXCEPTION TESTS ============================
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
    public void removeNullKey()
    {
        
    }


}
