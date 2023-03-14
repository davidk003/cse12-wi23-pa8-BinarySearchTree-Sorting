/*
 * Name: David Kim
 * Email: dck003@ucsd.edu
 * PID: A17394361
 * Sources used: Zybook instructions for percolation method
 * 
 * This file contains PA8's MyBST class.
 */
import java.util.ArrayList;

//QUESTIONS: so search(null) should always return null?
//WHEN INSERT() replaces a node, should it make a new Node object or can I
//replace only the value?
public class MyBST<K extends Comparable<K>, V>
{
    MyBSTNode<K, V> root = null;
    int size = 0;

    public int size()
    {
        return size;
    }

    /**
     * Insert a new node containing the arguments key and value into the
     * binary search tree according to the binary search tree properties.
     * Update the tree size accordingly. If a node with key already exists in
     * the tree, replace the value in the existing node with value.
     * Note: We won't change the key of the pre-existing node.
     * If a node with key already exists in the tree, return the original value
     * replaced by value. Otherwise, if the insertion did not lead to a
     * replacement (i.e., created a new node as a leaf, note that if the tree
     * only has a root then the root itself is a leaf), return null.
     * 
     * @param key value by which to order a node or replace a node in MyBST
     * @param value value of the node.
     * @return old value that was replaced by new value, if new node was
     * created and not replaced, return null.
     * @throws NullPointerException if key is null
     */
    public V insert(K key, V value)
    {
        if(key == null){throw new NullPointerException();}
        //Situation where BST is empty: root is null, or size is 0. 
        //Ex: root might not be null MAYBE if its been initialized before???
        if(root == null || size == 0)//Possibly remove size==0 later
        {
            root = new MyBSTNode<K,V>(key, value, null);
            return null;
        }

        //Can assume root is not null/BST contains a root
        MyBSTNode<K,V> currNode = root;
        while(currNode.getKey().compareTo(key)!=0)
        {
            //if current node's key is less than the key, traverse left
            if(currNode.getKey().compareTo(key)<0)
            {
                //currNode's key is less, try to traverse less but is null,
                //the node can be inserted into that place.
                if(currNode.getLeft()==null)
                {
                    currNode.setLeft(new MyBSTNode<K,V>(key, value, currNode));
                    return null;
                }
                currNode = currNode.getLeft();
            }
            else//currNode.getKey().compareTo(key)>0)cant be less than or equal
            {
                if(currNode.getRight()==null)
                {
                    currNode.setRight(new MyBSTNode<K,V>(key, value, currNode));
                    return null;
                }
                currNode = currNode.getRight();
            }
        }

        V returnVal = currNode.getValue();
        currNode.setValue(value);
        return returnVal;
        //When the while loop exits, keys must be equal, therefore replace curr
        // MyBSTNode<K,V> replacementNode =
        //     new MyBSTNode<>(key, value, currNode.getParent());

    }

    /**
     * Search for a node with key equal to key and return the value
     * associated with that node. The tree structure should not be affected by
     * this. If no such node exists, return null instead. Again, note that
     * we've overloaded the meaning of returning null.
     * Note: key might be null.
     * We'll assume that no other key is equal to null
     * (even though the compareTo() of type K might define this differently).
     * This means that search(null) should always return null.
     * @param key to search MyBST for.
     * @return value of the matching key. If no match is found, return null.
     */
    public V search(K key)
    {
        if(key==null||root==null){return null;}
        MyBSTNode<K,V> currNode = root;
        while(currNode.getKey().compareTo(key)!=0)
        {
            //if current node's key is less than the key, traverse left
            if(currNode.getKey().compareTo(key)<0)
            {
                //We know the key is less than currNode's key, and if no
                //further traversal left is possible (aka null left node),
                //there can be no equal key. Return null.
                if(currNode.getLeft()==null){return null;}
                currNode = currNode.getLeft();
            }
            else//currNode.getKey().compareTo(key)>0)cant be less than or equal
            {
                if(currNode.getRight()==null){return null;}
                currNode = currNode.getRight();
            }
        }
        //If/when the while loop exits, currNode's key must be equal to arg key
        return currNode.getValue();
    }

    /**
     * Search for a node with key equal to key and return the value
     * associated with that node. The node should be removed (disconnected)
     * from the tree and all references should be fixed, if needed. Update
     * the tree size accordingly. When removing the node, if the node is not
     * a leaf node, then we may have to make some changes to the tree to
     * maintain its integrity. Make sure to fix all relevant references to
     * parents/children. For leaf nodes, no replacement is needed. If the
     * node has a single child, move that child up to take its place.
     * (Example) If the node has two children, then we will just overwrite
     * the data at the node we plan to remove with the data from the
     * successor, saving us the need to reconnect all the nodes if we had
     * completely removed the node from the tree. Then remove the successor
     * from the tree
     * (can be done iteratively or recursively).
     * (Example) If no such node exists, return null instead.
     * Again, note that we've overloaded the meaning of returning null.
     * Note: key might be null. We'll assume that no other key is equal to null
     * (even though the compareTo()of type K might define this differently).
     * This means that remove(null)should always return null and not
     * remove any nodes.
     * @param key to search MyBST for and remove node with key.
     * @return
     */
    public V remove(K key)
    {
        //QUESTION: "If the node has a single child, move that child up to take its place."
        // TODO
        return null;
    }

    /**
     * Do an in-order traversal of the tree, adding each node to the end of
     * an ArrayList, which will be returned. After the entire traversal, this
     * ArrayList should contain all nodes in the tree. These nodes will be
     * sorted by the key order (with the smallest key at index 0) due to the
     * order of the traversal. If the tree is empty, an empty (not null)
     * ArrayList should be returned. Hint: it might be helpful to think about
     * what the successor method does and how it relates to an in-order
     * traversal. Using sorting algorithms is not allowed for this method.
     * @return
     */
    public ArrayList<MyBSTNode<K, V>> inorder()
    {
        ArrayList<MyBSTNode<K, V>> inorderList = new ArrayList<>();
        recursiveBSTInorderList(root, inorderList);
        return null;
    }

    private void recursiveBSTInorderList(MyBSTNode<K,V> node,
        ArrayList<MyBSTNode<K, V>> inorderList)
    {
        if(node == null){return;}

        recursiveBSTInorderList(node.getLeft(), inorderList);
        inorderList.add(node);
        recursiveBSTInorderList(node.getRight(), inorderList);

    }

    static class MyBSTNode<K, V>
    {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue()
        {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue)
        {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent()
        {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent)
        {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft()
        {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft)
        {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight()
        {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight)
        {
            this.right = newRight;
        }

        public MyBSTNode<K, V> successor()
        {
            // TODO
            return null;
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj)
        {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString()
        {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
