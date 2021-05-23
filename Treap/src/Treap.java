/*
CS570 HW5---Treap
@ author: Zimu Jiao
 */

import java.util.Random;
import java.util.Stack;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Treap<E extends Comparable>  {
    // Inner Class Node<E>
    private static class Node<E extends Comparable> {
        // Data field:
        public E data;
        public int priority;
        public Node<E> left = null;
        public Node<E> right = null;

        // Constructor:
        public Node() {
            this.data = null;
            this.priority = 0;
        }

        public Node(E data){
            this.data=data;
            this.priority=0;
        }

        public Node(E data, int priority) throws Exception {
            if (data == null) {
                throw new Exception();
            }
            this.data = data;
            this.priority = priority;
        }

        // Methods:
        public Node<E> rotateRight() {
            Node<E> NNode = this.left;
            this.left=NNode.right;
            NNode.right=this;
            return NNode;
        }

        public Node<E> rotateLeft(){
            Node<E> NNode= this.right;
            this.right=NNode.left;
            NNode.left=this;
            return NNode;
        }

        public String toString(){
            if(this.data!=null) {
                return "(Key=" + this.data.toString() + ", Priority=" + this.priority + ")";
            }else{
                return null;
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------
    // Class Treap's Data fields:
    private Random priorityGennerator;
    private Node<E> root;

    // Constructor:
    public Treap(){
        this.priorityGennerator = new Random();
        this.root=null;
    }
    public Treap(long seed){
        this.priorityGennerator= new Random(seed);
        this.root=null;
    }

    // Methods:
    boolean add(E key, int priority) throws Exception {
        if(this.root==null) {
            this.root=new Node<>(key,priority);
            return true;
        }
        Node current=this.root;
        Stack<Node<E>> ancestor=new Stack<Node<E>>();
        Node<E> added = new Node(key,priority);
        while(current!= null){
            ancestor.push(current);
            if(key.compareTo(current.data)>0){
                current=current.right;
            } else{
                current=current.left;
            }
        }
        if(key.compareTo(ancestor.peek().data)>0) {
            ancestor.peek().right = added;
        }else{
            ancestor.peek().left=added;
        }

        while(added.priority> ancestor.peek().priority){
            Node<E> parent=ancestor.pop();
            if(!ancestor.isEmpty()){
                Node<E> grandpa= ancestor.peek();
                if(parent.right!=null && parent.right.equals(added)){
                    if(parent.equals(grandpa.right)) {
                        grandpa.right = parent.rotateLeft();
                    }else {
                        grandpa.left = parent.rotateLeft();
                    }
                }else{
                    if(parent.equals(grandpa.right)) {
                        grandpa.right = parent.rotateRight();
                    }else {
                        grandpa.left = parent.rotateRight();
                    }
                }
            }else{
                if(added.equals(parent.right)){
                    this.root=parent.rotateLeft();
                    return true;
                }else{
                    this.root=parent.rotateRight();
                    return true;
                }
            }
        }
        return true;
    }

    boolean add(E key){
        Node current= new Node(key);
        current.left=null;
        current.right=null;
        return true;
    }

    boolean delete(E key) {
        delete(root,key);
        return true;
    }
    private Node<E> delete(Node<E> node,E data){
        if(node==null){
            return null;
        }
        if(data.compareTo(node.data)<0){
            node.left=delete(node.left,data);
        }else if(data.compareTo(node.data)>0){
            node.right=delete(node.right,data);
        }else{
            if(node.left==null && node.right==null) {
                return null;
            }else if(node.left==null ||(node.right!=null && node.left.priority>node.right.priority)){
                node=node.rotateLeft();
                node.left=delete(node.left,data);
            }else{
                node=node.rotateRight();
                node.right=delete(node.right,data);
            }
        }
        return node;
    }

    private boolean find(Node<E> root, E key){
        if(root==null){
            return false;
        }
        Node<E> current = root;
        if(current.data==key){
            return true;
        }else{
            return find(current.left,key) || find(current.right,key);
        }
    }

    public String toString(){
        StringBuilder r=toString(root,1);
        return r.toString();
    }

    private StringBuilder toString(Node<E> current,int dp){
        //Travel and record all nodes in r.
        StringBuilder r=new StringBuilder();
        for(int i=0;i<dp;i++){
            r.append("\t");
        }
        if (current==null){
            return r.append("null\n");
        }else {
            r.append(current.toString() + "\n");
            r.append(toString(current.left, dp + 1));
            r.append(toString(current.right, dp + 1));
            return r;
        }
    }
    //------------------------------------------------------------------
    public static void main(String[] args){
        Treap<Integer> testTree = new Treap();
        try{
            testTree.add(4,19);
            testTree.add(2,31);
            testTree.add(6,70);
            testTree.add(1,84);
            testTree.add(3,12);
            testTree.add(5,83);
            testTree.add(7,26);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(testTree.toString());
        testTree.delete(6);
        System.out.println(testTree.toString());



    }

}
