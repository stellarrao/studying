package javaexercise.interview.Algorithm;

class Node {
    //数据域
    private int data;
    
    //指针域
    private Node next;
    
    public Node(int data) {
        super();
        this.data = data;
    }
    
    public int getData() {
        return data;
    }
    
    public void setData(int data) {
        this.data = data;
    }
    
    public Node getNext() {
        return next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
    
    public static Node RecursionReverse(Node head) {
        
        if(head == null || head.getNext() == null) {
            return head;
        }
        
        Node reversedHead = RecursionReverse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reversedHead;
    }
    
    public static Node TraversalReverse(Node head) {
        
        if(head == null) {
                return head;
        }
        
        Node pre = head;
        Node cur = head.getNext();
        Node tem;
        
        while(cur != null) {
            tem = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tem;
        }
        head.setNext(null);
        return pre;
    }
    
    public static void print(Node head) {
        if(head == null)
        {
            System.out.println("end");
            return;
        }
        else
        {
            System.out.println(head.data);
            print(head.getNext());
        }
    }
}


public class LinkedNodeReverse
{
    public static void main(String [] args) {
        
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        
        node3.setNext(null);
        node2.setNext(node3);
        node1.setNext(node2);

        Node.print(node1);
        
//        Node head = Node.RecursionReverse(node1);
        Node head = Node.TraversalReverse(node1);
        
        Node.print(head);
    }

}
