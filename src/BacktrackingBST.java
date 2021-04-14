
import org.w3c.dom.Node;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.NoSuchElementException;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }

    public Node getRoot() {
        if (root == null) {
            throw new NoSuchElementException("empty tree has no root");
        }
        return root;
    }

    public Node search(int k) { //gets the node with the specified key
        if (root == null)
            throw new RuntimeException("Tree is Empty");
        Node tmp = root;
        while (tmp.left != null | tmp.right != null) { //going down the tree
            if (tmp.left != null & k < tmp.key)
                tmp = tmp.left;
            else if (tmp.right != null & k > tmp.key)
                tmp = tmp.right;
            else if (tmp.left == null & k < tmp.key | tmp.right == null & k > tmp.key) //cases where we can't go further, the key isn't in the tree
                return null;
            else
                return tmp;
        }
        if (tmp.key == k) //tmp is a leaf
            return tmp;
        return null;
    }

    public void insert(Node node) { //inserts node according to BST logic
        reTrackInsert(node);
        emptyRedoStack();
    }

    public void reTrackInsert(Node node){
        if (node == null)
            throw new IllegalArgumentException("Node is null");
        if (root == null) { //tree's empty, insert as root
            root = node;
            root.setParent(null);
        } else {
            Node tmp = root;
            if (tmp.left != null | tmp.right != null) { //If tmp isn't a leaf
                while (tmp.left != null | tmp.right != null) { //going down the tree
                    if (tmp.left != null & node.getKey() < tmp.key) //going left
                        tmp = tmp.left;
                    else if (tmp.right != null & node.getKey() > tmp.key) //going right
                        tmp = tmp.right;
                    else if (tmp.left == null & node.getKey() < tmp.key) { //if we can't go further, inserts
                        tmp.left = node;
                        node.setParent(tmp);
                        tmp = node;
                    } else { //if we can't go further, inserts
                        tmp.right = node;
                        node.setParent(tmp);
                        tmp = node;
                    }
                }
            }
            if (tmp.getKey() < node.getKey()) { //if tmp is a leaf, deciding which side to add to
                tmp.right = node;
                node.setParent(tmp);
            } else if (tmp.getKey() > node.getKey()) {
                tmp.left = node;
                node.setParent(tmp);
            }
        }
        Object[] array = new Object[2]; //for backtracking
        array[0] = true;
        array[1] = copyNode(node);
        stack.push(array);
    }

    public void delete(Node node) { //deletes the specified node from the tree
        reTrackDelete(node);
        emptyRedoStack();
    }

    public Node copyNode(Node node){
        Node copy=new Node(node.getKey(),node.value);
        copy.setParent(node.getParent());
        copy.left=node.left;
        copy.right=node.right;
        return copy;
    }

    private void reTrackDelete(Node node){
        if (node == null)
            throw new IllegalArgumentException("Node is null");
        if (search(node.getKey()) == null)
            throw new IllegalArgumentException("Node doesn't exist in the tree");
        Object[]arr=new Object[3];
        arr[0]=false;
        arr[1]=copyNode(node);
        if (node.left == null & node.right == null) { //if the node is a leaf
            if (node.getParent() == null) //if node is root
                root = null;
            else {
                if (node.getParent().left!=null && node.getParent().left.getKey() == node.getKey())
                    node.getParent().left = null;
                else
                    node.getParent().right = null;
            }
        } else if (node.left != null & node.right == null) { //if has only left son
            if (node.getParent() == null) { //if node is root
                root = node.left;
                node.left.setParent(null);
            } else {
                if (node.getParent().left!=null && node.getParent().left.getKey() == node.getKey()) //deciding if node is a right/left son
                    node.getParent().left = node.left;
                else
                    node.getParent().right = node.left;
                node.left.setParent(node.getParent());
            }
        } else if (node.left == null & node.right != null) { //if has only right son
            if (node.getParent() == null) { //if node is root
                root = node.right;
                node.right.setParent(null);
            } else { //deciding if node is a right/left son
                if (node.getParent().right!=null && node.getParent().right.getKey() == node.getKey())
                    node.getParent().right = node.right;
                else
                    node.getParent().left = node.right;
                node.right.setParent(node.getParent());
            }
        } else { //if has 2 sons
            Node succ = successor(node);
            arr[2]=copyNode(succ);
            reTrackDelete(succ);//recursion is at most 2 times, doesn't depend on the input's size
            succ.left = node.left;
            succ.right = node.right;
            succ.setParent(node.getParent());
            if (node.getParent() == null) //if node is root
                root = succ;
            else {
                if (node.getParent().right!=null && node.getParent().right.getKey() == node.getKey()) //deciding if node is a right/left son
                    node.getParent().right = succ;
                else
                    node.getParent().left = succ;
            }
            node.left.setParent(succ);
            if(node.right!=null)
                node.right.setParent(succ);
        }
        stack.push(arr);
    }

    public Node minimum() { //gets the minimum node in the tree
        if (root == null)
            throw new RuntimeException("Root is null");
        Node tmp = root;
        while (tmp.left != null) //going down left, in BST, minimum will be the leftmost son
            tmp = tmp.left;
        return tmp;
    }

    public Node maximum() { //gets the minimum node in the tree
        if (root == null)
            throw new RuntimeException("Root is null");
        Node tmp = root;
        while (tmp.right != null) //going down right, in BST, minimum will be the rightmost son
            tmp = tmp.right;
        return tmp;
    }

    public Node successor(Node node) { //finds the successor of the specified node
        if (root == null)
            throw new RuntimeException("Tree is empty");
        if (search(node.getKey()) == null)
            throw new IllegalArgumentException("Node doesn't exist in the tree");
        //We want to find the leftmost son of the node's right son
        if (node.right != null) { //if node has a right son
            Node tmp = node.right;
            while (tmp.left != null)
                tmp = tmp.left;
            return tmp;
        } else { //if doesn't have a right son, returns the first parent which has one of the node's ancestor as a left son
            if (node.getParent() == null)
                throw new RuntimeException("Node doesn't have a successor");
            else {
                if (node.getParent().left == node)
                    return node.getParent();
                else {
                    Node tmp = node;
                    while (tmp.getParent() != null && tmp.getParent().right == tmp)
                        tmp = tmp.getParent();
                    if (tmp.getParent() == null)
                        throw new RuntimeException("Node doesn't have a successor");
                    return tmp.getParent();
                }
            }
        }
    }

    public Node predecessor(Node node) { //finds the predecessor of the specified node
        if (root == null)
            throw new RuntimeException("Tree is empty");
        if (search(node.getKey()) == null)
            throw new IllegalArgumentException("Node doesn't exist in the tree");
        //We want to find the rightmost son of the node's left son
        if (node.left != null) { //if node has a left son
            Node tmp = node.left;
            while (tmp.right != null)
                tmp = tmp.right;
            return tmp;
        } else { //if doesn't have a left son, returns the first parent which has one of the node's ancestor as a right son
            if (node.getParent() == null)
                throw new RuntimeException("Node doesn't have a successor");
            else {
                if (node.getParent().right == node)
                    return node.getParent();
                else {
                    Node tmp = node;
                    while (tmp.getParent() != null && tmp.getParent().left == tmp)
                        tmp = tmp.getParent();
                    if (tmp.getParent() == null)
                        throw new RuntimeException("Node doesn't have a successor");
                    return tmp.getParent();
                }
            }
        }
    }

    @Override
    public void backtrack() {
        if (!stack.isEmpty()) {
            Object[] arr = (Object[]) stack.pop();
            Node node = (Node) arr[1];
            if ((boolean) arr[0]) {// if last action was insert
                if (node.getParent().left == node)
                    node.getParent().left = null;
                else
                    node.getParent().right = null;
                node.setParent(null);
                redoStack.push(arr);
            } else {//last action was delete
                if(node.right==null & node.left==null)//node is a leaf
                    reTrackInsert(node);
                else if(node.right==null & node.left!=null){//node has a left son
                    if(node.parent.right==node.left)
                        node.parent.right=node;
                    else
                        node.parent.left=node;
                    node.left.setParent(node);
                }
                else if(node.right!=null & node.left==null){// node has a right son
                    if(node.parent.right==node.right)
                        node.parent.right=node;
                    else
                        node.parent.left=node;
                    node.right.setParent(node);
                }
                else{//node has two sons
                    Node succ=(Node)arr[2];
                    node.left.setParent(node);
                    if(node.getParent()!= null && node.getParent().left.getKey()==succ.getKey())
                        node.getParent().left=node;
                    else if(node.getParent()!= null)
                        node.getParent().right=node;
                    else
                        root=node;
                    if(node.right.getKey()!=succ.getKey())
                        succ.getParent().left=succ;
                    else
                        node.right=succ;
                }
            }
        }
    }

    @Override
    public void retrack() {
//        if (!redoStack.isEmpty()) {
//            Object[] arr = (Object[]) redoStack.pop();
//            if((boolean)arr[0])
//                reTrackInsert((Node)arr[1]);
//            else{
//
//            }
//        }
    }

    private void emptyRedoStack(){
        while(!redoStack.isEmpty())
            redoStack.pop();
    }


    public void printPreOrder() { //prints in a "preOrder" order
        if (root == null) //tree's empty
            System.out.println("");
        else {
            String ans = "";
            ans = root.print(ans);
            System.out.println(ans.substring(0, ans.length() - 1)); //cutting last " "
        }
    }

    @Override
    public void print() { //uses printPreOrder to print
        printPreOrder();
    }

    public static class Node {
        // These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;

        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public String print(String acc) { //function to help main print function
            acc = this.key + " ";
            if (left != null)
                acc = acc + left.print(acc);
            if (right != null)
                acc = acc + right.print(acc);
            return acc;
        }
    }
}