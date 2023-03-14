import org.junit.Assert.*;

import static org.junit.Assert.assertThrows;

import org.junit.Test;


public class CustomTester {
    
    @Test
    public void insertNullKey()
    {
        MyBST<Integer, Integer> testBST = new MyBST<>();
        assertThrows(NullPointerException.class, ()->{testBST.insert(null, new Integer(5));});
    }
}
